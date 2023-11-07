package Lab3.Models;

public class LinkedListQueue implements Queue {
    private Node head;
    private int maxSize;
    private int size;

    public LinkedListQueue(int size) {
        this.maxSize = size;
        this.head = null;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public boolean isFull() {
        return this.size == this.maxSize;
    }

    @Override
    public void enqueue(int value) {
        if (this.isFull()) {
            System.out.println("Queue is full.");
            return;
        }
        Node newNode = new Node();
        newNode.variable(value);
        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size += 1;
    }

    @Override
    public int dequeue() {
        this.size -= 1;
        if (this.isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            int value = this.head.value;
            this.head = this.head.next;
            return value;
        }
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            return this.head.value;
        }
    }

    @Override
    public void print() {
        Node current = this.head;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("]\n");
    }
}
