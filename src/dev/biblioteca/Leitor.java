package dev.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Leitor {
    private String nome;
    private String nleitor;
    private String email;
    private String telefone;
    private String login;
    private String pass;
    private List<String> livroRequesitados = new ArrayList<>();
    public Leitor(String nome, String nleitor, String email, String telefone, String login, String pass) {
        this.nome = nome;
        this.nleitor = nleitor;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.pass = pass;
    }

    public void addLivro(Livro livro) {
        this.livroRequesitados.add(livro.getTitulo());
    }

    public void removeLivro(Livro livro) {
        this.removeLivro(livro.getTitulo());
    }
    
    public void removeLivro(String titulo) {
        Iterator<String> iter = this.livroRequesitados.iterator();
        while (iter.hasNext()) {
            String tl = iter.next();
            if (titulo.equals(tl)) {
                iter.remove();
                break;
            }
        }
    }
    
    public void addLivro(String titulo) {
        this.livroRequesitados.add(titulo);
    }
    
    public boolean hasLivro(Livro livro) {
        return this.hasLivro(livro.getTitulo());
    }
    
    public boolean hasLivro(String titulo) {
        for (String livro : this.livroRequesitados) {
            if (livro.equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getLivroRequesitados() {
        return livroRequesitados;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNleitor() {
        return nleitor;
    }

    public void setNleitor(String nleitor) {
        this.nleitor = nleitor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
