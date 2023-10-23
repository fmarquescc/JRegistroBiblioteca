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
                    list.add(new Leitor(data[0], data[1], data[2], data[3], data[4], data[5]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return list;
        }
    }
