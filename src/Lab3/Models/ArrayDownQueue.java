package Lab3.Models;

public class ArrayDownQueue implements Queue {
    int[] queue;
    int head;
    int size;

    public ArrayDownQueue(int size) {
        this.queue = new int[size];
        this.head = size - 1;
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return this.head == size - 1;
    }

    @Override
    public boolean isFull() {
        return this.head == - 1;
    }

    @Override
    public void enqueue(int value) {
        if (this.isFull()) {
            System.out.println("Queue is full.");
        } else {
            this.queue[this.head] = value;
            this.head -= 1;
        }
    }

    @Override
    public int dequeue() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            int value = this.queue[this.size - 1];
            for (int i = this.size - 1; i > this.head + 1; i--) {
                this.queue[i] = this.queue[i - 1];
            }
            this.head += 1;
            return value;
        }
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            return this.queue[this.size - 1];
        }
    }

    @Override
    public void print() {
        System.out.print("[ ");
        for (int i = this.size - 1; i > this.head; i--) {
            System.out.print(this.queue[i] + " ");
        }
        System.out.println("]\n");
    }

}
