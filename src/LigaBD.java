import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public abstract class LigaBD {
    public static LigaBD bd;
    
    public static LigaBD getBD() {
        if (bd == null) {
            bd = Constants.USE_SQL_DATABASE ? new SqlLigaBD() : new LocalLigaBD();
        }
        return bd;
    }
    
    public abstract void inserirLivro(Livro livro);
    public abstract Optional<Livro> buscarLivroPorTitulo(String titulo);
    public abstract List<Livro> obterLivros();
    public abstract void atualizarLivro(Livro livro);
    public abstract void excluirLivro(String titulo);
    public abstract void inserirLeitor(Leitor leitor);
    public abstract List<Leitor> obterLeitors();
    
    public static class LocalLigaBD extends LigaBD {
        private static final Path LEITORES_PATH = Path.of("leitores.txt");
        private static final Path LIVROS_PATH = Path.of("livros.txt");
        
        @Override
        public void inserirLivro(Livro livro) {
            List<Livro> list = this.obterLivros();
            list.add(livro);
            this.saveLivros(list);
        }

        @Override
        public Optional<Livro> buscarLivroPorTitulo(String titulo) {
            List<Livro> list = this.obterLivros();
            Iterator<Livro> iterator = list.iterator();
            while (iterator.hasNext()) {
                Livro livro = iterator.next();
                if (livro.getTitulo().equals(titulo)) {
                    return Optional.of(livro);
                }
            }
            
            return Optional.empty();
        }

        @Override
        public List<Livro> obterLivros() {
            return this.loadLivros();
        }

        @Override
        public void atualizarLivro(Livro livro) {
            List<Livro> list = this.obterLivros();
            Iterator<Livro> iterator = list.iterator();
        
            while (iterator.hasNext()) {
                Livro lv = iterator.next();
                if (livro.getTitulo().equals(livro.getTitulo())) {
                    lv.setAutor(livro.getAutor());
                    lv.setEditora(livro.getEditora());
                    lv.setAnolancamento(livro.getAnolancamento());
                    break;
                }
            }
            
            this.saveLivros(list);
        }

        @Override
        public void excluirLivro(String titulo) {
            List<Livro> list = this.obterLivros();
            Iterator<Livro> iterator = list.iterator();
            while (iterator.hasNext()) {
                Livro livro = iterator.next();
                if (livro.getTitulo().equals(titulo)) {
                    iterator.remove();
                    break;
                }
            }
            
            this.saveLivros(list);
        }

        @Override
        public void inserirLeitor(Leitor leitor) {
            List<Leitor> list = this.obterLeitors();
            list.add(leitor);
            this.saveLeitores(list);
        }

        @Override
        public List<Leitor> obterLeitors() {
            return this.loadLeitores();
        }
        
        private void saveLivros(List<Livro> list) {
            try (BufferedWriter writer = Files.newBufferedWriter(LIVROS_PATH)) {
                for (int i = 0; i < list.size(); ++i) {
                    Livro livro = list.get(i);
                    writer.write(livro.getTitulo() + ';');
                    writer.write(livro.getAutor() + ';');
                    writer.write(livro.getEditora() + ';');
                    writer.write(livro.getAnolancamento());
                    
                    if (i + 1 < list.size()) {
                        writer.write('\n');
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private void saveLeitores(List<Leitor> list) {
            try (BufferedWriter writer = Files.newBufferedWriter(LEITORES_PATH)) {
                for (int i = 0; i < list.size(); ++i) {
                    Leitor leitor = list.get(i);
                    writer.write(leitor.getNome() + ';');
                    writer.write(leitor.getDatanas() + ';');
                    writer.write(leitor.getNleitor() + ';');
                    writer.write(leitor.getEmail() + ';');
                    writer.write(leitor.getTelefone() + ';');
                    writer.write(leitor.getLogin() + ';');
                    writer.write(leitor.getPass());
                    
                    if (i + 1 < list.size()) {
                        writer.write('\n');
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private List<Livro> loadLivros() {
            if (!Files.exists(LIVROS_PATH)) {
                return Collections.emptyList();
            }
            
            List<Livro> list = new ArrayList<>();
            
            try (BufferedReader reader = Files.newBufferedReader(LIVROS_PATH)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    list.add(new Livro(data[0], data[1], data[2], data[3]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return list;
        }
        
        private List<Leitor> loadLeitores() {
            if (!Files.exists(LEITORES_PATH)) {
                return Collections.emptyList();
            }
            
            List<Leitor> list = new ArrayList<>();
            
            try (BufferedReader reader = Files.newBufferedReader(LEITORES_PATH)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    list.add(new Leitor(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return list;
        }
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
            try {
                Connection connection = conectar();
                String sql = "INSERT INTO leitores (nome, datanas, nleitor, email, telefone, login, pass) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, leitor.getNome());
                    preparedStatement.setString(2, leitor.getDatanas());
                    preparedStatement.setString(3, leitor.getNleitor());
                    preparedStatement.setString(4, leitor.getEmail());
                    preparedStatement.setString(5, leitor.getTelefone());
                    preparedStatement.setString(6, leitor.getLogin());
                    preparedStatement.setString(7, leitor.getPass());

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
        public List<Leitor> obterLeitors() {
            List<Leitor> leitores = new ArrayList<>();
            try {
                Connection connection = conectar();
                String sql = "SELECT * FROM leitores";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Leitor leitor = new Leitor(resultSet.getString("nome"),
                                resultSet.getString("datanas"),
                                resultSet.getString("nleitor"),
                                resultSet.getString("email"),
                                resultSet.getString("telefone"),
                                resultSet.getString("login"),
                                resultSet.getString("pass"));
                        leitores.add(leitor);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return leitores;
        }

      @Override
        public List<Livro> obterLivros() {
            List<Livro> livros = new ArrayList<>();
            try {
                Connection connection = conectar();
                String sql = "SELECT * FROM livros";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Livro livro = new Livro(resultSet.getString("titulo"),
                                resultSet.getString("autor"),
                                resultSet.getString("editora"),
                                resultSet.getString("anolancamento"));
                        livros.add(livro);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return livros;
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
        public Optional<Livro> buscarLivroPorTitulo(String titulo) {
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
                        return Optional.of(livro);
                    } else {
                        return Optional.empty();
                    }
                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
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