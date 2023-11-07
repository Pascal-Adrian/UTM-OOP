package Lab3.Models;

public class LinkedListQueue implements Queue {
    private Node head;

    public LinkedListQueue() {
        this.head = null;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void enqueue(int value) {
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
    }

    @Override
    public int dequeue() {
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
