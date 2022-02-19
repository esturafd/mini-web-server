package me.esturafd.webserver;

import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import me.esturafd.webserver.actions.ConsumerAction;
import me.esturafd.webserver.actions.ProducerAction;

public class RequestStorage {
    
    private final int capacity;
    private final Queue<Socket> socketQueue;
    private final ReentrantLock locker = new ReentrantLock(true);
    private final Condition queueIsEmpty = locker.newCondition();

    public RequestStorage(int capacity) {
        this.capacity = capacity;
        this.socketQueue = new LinkedBlockingQueue<>(capacity);
    }

    public void consume(ConsumerAction<Socket> action) {
        System.out.println("1.consumo de storage: " + socketQueue.size());
        try {
            locker.lock();
            System.out.println("is empty: " + socketQueue.isEmpty());
            if (socketQueue.isEmpty()) {
                System.out.println("antes del await");
                queueIsEmpty.await();
                System.out.println("despues del await");
            }
            Socket socket = socketQueue.poll();
            if (socket != null) action.consume(socket);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void produce(ProducerAction<Socket> action) {
        try {
            locker.lock();
            if (socketQueue.size() == capacity) return;
            socketQueue.offer(action.produce());
            System.out.println("termina produce");
            queueIsEmpty.signal();
        } finally {
            locker.unlock();
        }
    }
}
