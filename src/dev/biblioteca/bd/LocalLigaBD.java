package dev.biblioteca.bd;

import dev.biblioteca.Leitor;
import dev.biblioteca.Livro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class LocalLigaBD extends LigaBD {
    private static final Path LEITORES_PATH = Path.of("leitores.txt");
    private static final Path LIVROS_PATH = Path.of("livros.txt");

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
    public void excluirLeitor(String nleitor) {
        List<Leitor> list = this.obterLeitors();
        Iterator<Leitor> iterator = list.iterator();
        while (iterator.hasNext()) {
            Leitor leitor = iterator.next();
            if (leitor.getNleitor().equals(nleitor)) {
                iterator.remove();
                break;
            }
        }

        this.saveLeitores(list);
    }

    
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
            if (lv.getTitulo().equals(livro.getTitulo())) {
                lv.setAutor(livro.getAutor());
                lv.setEditora(livro.getEditora());
                lv.setAnolancamento(livro.getAnolancamento());
                lv.setDisponivel(livro.isDisponivel());
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
                writer.write(livro.getAnolancamento() + ";");
                writer.write(livro.isDisponivel() ? "true" : "false");

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
                writer.write(leitor.getNleitor() + ';');
                writer.write(leitor.getEmail() + ';');
                writer.write(leitor.getTelefone() + ';');
                writer.write(leitor.getLogin() + ';');
                writer.write(leitor.getPass() + ";");

                List<String> livros = leitor.getLivroRequesitados();

                for (String livro : livros) {
                    writer.write(livro + "$");
                }

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
                list.add(new Livro(data[0], data[1], data[2], data[3], Boolean.valueOf(data[4])));
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
                Leitor leitor = new Leitor(data[0], data[1], data[2], data[3], data[4], data[5]);
                list.add(leitor);

                if (data.length > 6) {
                    String[] livros = data[6].split("\\$");
                    for (String livro : livros) {
                        leitor.addLivro(livro);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void atualizarEstadoLivro(String titulo, LivroEstado estado) {
        Livro livro = buscarLivroPorTitulo(titulo).get();
        livro.setDisponivel(estado == LivroEstado.DISPONIVEL);
        this.atualizarLeitor(LOGGED_LEITOR);
        this.atualizarLivro(livro);
    }
}
