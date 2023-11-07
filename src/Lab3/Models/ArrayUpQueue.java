package Lab3.Models;

public class ArrayUpQueue implements Queue {
    int[] queue;
    int tail;
    int size;

    public ArrayUpQueue(int size) {
        this.queue = new int[size];
        this.tail = -1;
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return this.tail == -1;
    }

    public boolean isFull() {
        return this.tail == this.size - 1;
    }

    @Override
    public void enqueue(int value) {
        if (this.isFull()) {
            System.out.println("Queue is full.");
        } else {
            this.tail += 1;
            this.queue[this.tail] = value;
        }
    }

    @Override
    public int dequeue() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            int value = this.queue[0];
            for (int i = 0; i < this.tail; i++) {
                this.queue[i] = this.queue[i + 1];
            }
            this.tail -= 1;
            return value;
        }
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            return this.queue[0];
        }
    }

    public void print() {
        System.out.print("[ ");
        for (int i = 0; i <= this.tail; i++) {
            System.out.print(this.queue[i] + " ");
        }
        System.out.println("]\n");
    }
}
