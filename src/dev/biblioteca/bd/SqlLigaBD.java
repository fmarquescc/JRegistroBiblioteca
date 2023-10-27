package dev.biblioteca.bd;

import dev.biblioteca.Leitor;
import dev.biblioteca.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SqlLigaBD extends LigaBD {
    static String url = "jdbc:mysql://localhost:3306/biblioteca?useTimezone=true&serverTimezone=UTC";
    static String user = "root";
    static String pass = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(SqlLigaBD.url, SqlLigaBD.user, SqlLigaBD.pass);
    }

    
    
    @Override
    public void atualizarLeitor(Leitor leitor) {
        List<Leitor> list = this.obterLeitors();
        Iterator<Leitor> iterator = list.iterator();

        while (iterator.hasNext()) {
            Leitor lv = iterator.next();
            if (lv.getNleitor().equals(leitor.getNleitor())) {
                lv.setEmail(leitor.getEmail());
                lv.setTelefone(leitor.getTelefone());
                lv.setLogin(leitor.getLogin());
                lv.setPass(leitor.getPass());
                lv.setNome(leitor.getNome());
                lv.getLivroRequesitados().clear();
                for (String titulo : leitor.getLivroRequesitados()) {
                    lv.addLivro(titulo);
                }
                break;
            }
        }

        this.saveLeitores(list);
    }

  @Override
public void inserirLeitor(Leitor leitor) {
    try {
        Connection connection = conectar();
        String sql = "INSERT INTO leitores (nome, nleitor, email, telefone, login, pass) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, leitor.getNome());
            preparedStatement.setString(2, leitor.getNleitor());
            preparedStatement.setString(3, leitor.getEmail());
            preparedStatement.setString(4, leitor.getTelefone());
            preparedStatement.setString(5, leitor.getLogin());
            preparedStatement.setString(6, leitor.getPass());

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
        
        try {
            Connection connection = conectar();
            String sql = "SELECT leitores.nome,leitores.email,leitores.nleitor,leitores.telefone,leitores.login,leitores.pass, livros.titulo FROM emprestimos INNER JOIN leitores ON emprestimos.idleitor = leitores.id INNER JOIN livros ON emprestimos.idlivro = livros.id;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Leitor leitor = null;
                    for (Leitor l : leitores) {
                        if (l.getNome().equals(resultSet.getString("nome"))) {
                            leitor = l;
                            break;
                        }
                    }
                    if (leitor != null) {
                        leitor.addLivro(resultSet.getString("titulo"));
                    }
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
                    livro.setDisponivel(LivroEstado.getById(resultSet.getInt("estado_id_estado")) == LivroEstado.DISPONIVEL);
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
    
    private void saberEstadoLivro(Livro livro) {
       String sql = "SELECT titulo FROM livro WHERE ";
       try {
        Connection connection = conectar();
     
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet res = preparedStatement.executeQuery();
            livro.setDisponivel(LivroEstado.getById(res.getInt(1)) == LivroEstado.DISPONIVEL);
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
    public void inserirLivro(Livro livro) {
        try {
            Connection connection = conectar();
            String sql = "INSERT INTO livros (titulo, autor, editora, anolancamento, estado_id_estado) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, livro.getTitulo());
                preparedStatement.setString(2, livro.getAutor());
                preparedStatement.setString(3, livro.getEditora());
                preparedStatement.setString(4, livro.getAnolancamento());
                preparedStatement.setInt(5, LivroEstado.DISPONIVEL.ordinal() + 1);

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
                    livro.setDisponivel(LivroEstado.getById(resultSet.getInt("estado_id_estado")) == LivroEstado.DISPONIVEL);
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

    private void saveLeitores(List<Leitor> list) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluirLeitor(String nleitor) {
        try {
            Connection connection = conectar();
            String sql = "DELETE FROM leitores WHERE nleitor = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nleitor);
                preparedStatement.executeUpdate();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public void atualizarEstadoLivro(String titulo, LivroEstado estado) {
        Connection connection = null;
        try {
           connection = conectar();
           
            String query= "UPDATE livros SET estado_id_estado="+(estado.ordinal()+1) + " WHERE titulo=?";    
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, titulo);
                preparedStatement.executeUpdate();
            }
            
            if (estado == LivroEstado.DISPONIVEL) {
                String sql = "DELETE FROM emprestimos WHERE idleitor IN (SELECT id FROM leitores WHERE nleitor = \"" + LOGGED_LEITOR.getNleitor() + "\") AND idlivro IN (SELECT id FROM livros WHERE titulo = \"" + titulo + "\")";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.executeUpdate();
                }
            } else {
                String sql = "INSERT INTO emprestimos (idestado,idlivro,idleitor) VALUES (2, (SELECT id FROM livros WHERE titulo = \"" + titulo + "\"), (SELECT id FROM leitores WHERE nleitor = \"" + LOGGED_LEITOR.getNleitor() +"\"))";
            
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1){}
        }
    }
}



/*
-- Criar a base de dados caso ela não exista
CREATE DATABASE IF NOT EXISTS biblioteca;


--  tabela 'livros'
CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255)NOT NULL,
    editora VARCHAR(255) NOT NULL,
    anolancamento VARCHAR(4) NOT NULL,
    disponibilidade INT NOT NULL,
);


-- tabela 'leitores'
CREATE TABLE leitores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nleitor VARCHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    login VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    livros_requesitados INT ,
);


<<<<<<< HEAD
ALTER TABLE leitores
ADD livros_registrados TEXT;

*/

