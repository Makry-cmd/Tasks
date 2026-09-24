package Task4;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер очереди должен быть больше нуля!");
        }
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T delete = queue.poll();
        notifyAll();
        return delete;
    }

    public synchronized int size() {
        return queue.size();
    }
}
