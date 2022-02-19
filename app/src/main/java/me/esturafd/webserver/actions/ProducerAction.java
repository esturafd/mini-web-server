package me.esturafd.webserver.actions;

@FunctionalInterface
public interface ProducerAction<T> {
    T produce();
}
