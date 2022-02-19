package me.esturafd.webserver.actions;

@FunctionalInterface
public interface ConsumerAction<T> {
    void consume(T obj);
}
