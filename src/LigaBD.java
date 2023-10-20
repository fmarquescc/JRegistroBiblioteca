import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class LigaBD {
    public static LigaBD bd;
    
    public static LigaBD getBD() {
        if (bd == null) {
            bd = new SqlLigaBD();
        }
        return bd;
    }
    
    public abstract void inserirLivro(Livro livro);
    public abstract Livro buscarLivroPorTitulo(String titulo);
    public abstract List<Livro> obterLivros();
    public abstract void atualizarLivro(Livro livro);
    public abstract void excluirLivro(String titulo);
    public abstract void inserirLeitor(Leitor leitor);
    public abstract List<Leitor> obterLeitors();
    
    public static class LocalLigaBD {
    }

    public static class SqlLigaBD extends LigaBD {
        static String url = "jdbc:mysql://localhost:3306/utilizador?useTimezone=true&serverTimezone=UTC";
        static String user = "root";
        static String pass = "";
        
        public Connection conectar() throws SQLException {
            return DriverManager.getConnection(SqlLigaBD.url, SqlLigaBD.user, SqlLigaBD.pass);
        }

        @Override
        public void inserirLeitor(Leitor leitor) {
            // TODO: Implementar Ricardo
        }

        @Override
        public List<Leitor> obterLeitors() {
            // TODO: Implementar Ricardo
            return null;
        }

        @Override
        public List<Livro> obterLivros() {
            // TODO: Implementar Ricardo
            return null;
        }

        @Override
        public void inserirLivro(Livro livro) {
            try {
                Connection connection = conectar();
                String sql = "INSERT INTO livros (titulo, autor, editora, anolancamento) VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, livro.getTitulo());
                    preparedStatement.setString(2, livro.getAutor());
                    preparedStatement.setString(3, livro.getEditora());
                    preparedStatement.setString(4, livro.getAnolancamento());

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Livro buscarLivroPorTitulo(String titulo) {
            try {
                Connection connection = conectar();
                String sql = "SELECT * FROM livros WHERE titulo = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, titulo);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        Livro livro = new Livro(resultSet.getString("titulo"),
                                resultSet.getString("autor"),
                                resultSet.getString("editora"),
                                resultSet.getString("anolancamento"));
                        return livro;
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void atualizarLivro(Livro livro) {
            try {
                Connection connection = conectar();
                String sql = "UPDATE livros SET autor = ?, editora = ?, anolancamento = ? WHERE titulo = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, livro.getAutor());
                    preparedStatement.setString(2, livro.getEditora());
                    preparedStatement.setString(3, livro.getAnolancamento());
                    preparedStatement.setString(4, livro.getTitulo());

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void excluirLivro(String titulo) {
            try {
                Connection connection = conectar();
                String sql = "DELETE FROM livros WHERE titulo = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, titulo);
                    preparedStatement.executeUpdate();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}