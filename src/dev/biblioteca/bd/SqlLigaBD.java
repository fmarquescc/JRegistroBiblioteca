package dev.biblioteca.bd;

import dev.biblioteca.Leitor;
import dev.biblioteca.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlLigaBD extends LigaBD {
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


/*
-- Criar a base de dados caso ela não exista
CREATE DATABASE IF NOT EXISTS biblioteca;


--  tabela 'livros'
CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    editora VARCHAR(255),
    anolancamento VARCHAR(4)
);


-- tabela 'leitores'
CREATE TABLE leitores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    datanas DATE,
    nleitor VARCHAR(10) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(20),
    login VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL
);

ALTER TABLE leitores
ADD livros_registrados TEXT;

\*
