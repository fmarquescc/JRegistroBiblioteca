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

        public static void inserirLivro(Livros livro) throws SQLException {
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

        public static Livros buscarLivroPorTitulo(String titulo) throws SQLException {
            Connection connection = conectar();
            String sql = "SELECT * FROM livros WHERE titulo = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, titulo);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    Livros livro = new Livros(resultSet.getString("titulo"),
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

        public static void atualizarLivro(Livros livro) throws SQLException {
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

public class Livros {
    private String titulo;    
    private String autor;
    private String editora;
    private String anolancamento;

    public Livros(String titulo, String autor, String editora, String anolancamento) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anolancamento = anolancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public String getAnolancamento() {
        return anolancamento;
    }
}

public class Leitor {
    private String nome;
    private String datanas;
    private String nleitor;
    private String email;
    private String telefone;
    private String login;
    private String pass;

    public Leitor(String nome, String datanas, String nleitor, String email, String telefone, String login, String pass) {
        this.nome = nome;
        this.datanas = datanas;
        this.nleitor = nleitor;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public String getDatanas() {
        return datanas;
    }

    public String getNleitor() {
        return nleitor;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
