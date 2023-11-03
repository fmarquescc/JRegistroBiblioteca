package dev.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Event<T> {
    private T invoker;
    private final Function<List<T>, T> invokerFactory;
    private final List<T> handlers = new ArrayList<>();
    
    public Event(Function<List<T>, T> invokerFactory) {
        this.invokerFactory = invokerFactory;
        this.rebuild();
    }
    
    public static <T> Event<T> create(Function<List<T>, T> invokerFactory) {
        return new Event<>(invokerFactory);
    }
    
    public static Event<Runnable> create() {
        return new Event<>(callbacks -> () -> {
            for (Runnable callback : callbacks) {
                callback.run();
            }
        });
    }
    
    public void register(T listener) {
        this.handlers.add(listener);
        this.rebuild();
    }
    
    public void unregister(T listener) {
        this.handlers.remove(listener);
        this.rebuild();
    }
    
    public void unregisterAll() {
        this.handlers.clear();
        this.rebuild();
    }
    
    private void rebuild() {
        this.invoker = this.invokerFactory.apply(this.handlers);
    }
    
    public T invoker() {
        return this.invoker;
    }
}