package dev.biblioteca.bd;

import dev.biblioteca.Constants;
import dev.biblioteca.Event;
import dev.biblioteca.Leitor;
import dev.biblioteca.Livro;
import java.util.List;
import java.util.Optional;

public abstract class LigaBD {
    public static LigaBD bd;
    
    public static boolean FUNCIONARIO_LOGGED = false;
    public static Leitor LOGGED_LEITOR = null;
    
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
    
    public static final Event<Runnable> LIVROS_UPDATE_EVENT = Event.create();
    public static final Event<ActionMessage> ACTION_MESSAGE_EVENT = Event.create(callbacks -> message -> {
        for (ActionMessage callback : callbacks) {
            callback.run(message);
        }
    });
    
    public interface ActionMessage {
        void run(String message);
    }
}