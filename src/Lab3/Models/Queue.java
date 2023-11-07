package Lab3.Models;

public interface Queue {
    void enqueue(int value);
    int dequeue();
    int peek();
    boolean isEmpty();
    boolean isFull();
}
