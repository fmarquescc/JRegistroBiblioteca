import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class LigaBD {
    public static class LocalLigaBD {
        static String url = "jdbc:mysql://localhost:3306/utilizador?useTimezone=true&serverTimezone=UTC";
        static String user = "root";
        static String pass = "";
    }

    public static class SqlLigaBD {
        public static Connection conectar() throws SQLException {
            return DriverManager.getConnection(LocalLigaBD.url, LocalLigaBD.user, LocalLigaBD.pass);
        }

        public static void inserirLivro(Livro livro) throws SQLException {
            Connection connection = conectar();
            String sql = "INSERT INTO livros (titulo, autor, editora, anolancamento) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, livro.getTitulo());
                preparedStatement.setString(2, livro.getAutor());
                preparedStatement.setString(3, livro.getEditora());
                preparedStatement.setString(4, livro.getAnolancamento());
                
                preparedStatement.executeUpdate();
            } finally {
                connection.close();
            }
        }

        public static Livro buscarLivroPorTitulo(String titulo) throws SQLException {
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
            } finally {
                connection.close();
            }
        }

        public static void atualizarLivro(Livro livro) throws SQLException {
            Connection connection = conectar();
            String sql = "UPDATE livros SET autor = ?, editora = ?, anolancamento = ? WHERE titulo = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, livro.getAutor());
                preparedStatement.setString(2, livro.getEditora());
                preparedStatement.setString(3, livro.getAnolancamento());
                preparedStatement.setString(4, livro.getTitulo());
                
                preparedStatement.executeUpdate();
            } finally {
                connection.close();
            }
        }

        public static void excluirLivro(String titulo) throws SQLException {
            Connection connection = conectar();
            String sql = "DELETE FROM livros WHERE titulo = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, titulo);
                preparedStatement.executeUpdate();
            } finally {
                connection.close();
            }
        }
    }
}