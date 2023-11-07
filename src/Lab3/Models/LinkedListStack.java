package Lab3.Models;

public class LinkedListStack implements Stack {

    private Node last;
    private int maxSize;
    private int size;


    public LinkedListStack(int size) {
        this.maxSize = size;
        this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return this.last == null;
    }

    @Override
    public boolean isFull() {
        return this.size == this.maxSize;
    }

    @Override
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("Stack is full.");
            return;
        }
        Node newNode = new Node();
        newNode.variable(value);
        newNode.next(this.last);
        this.last = newNode;
        size += 1;
    }

    @Override
    public int pop() {
        this.size -= 1;
        if (this.isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            int value = this.last.value;
            this.last = this.last.next;
            return value;
        }
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            return this.last.value;
        }
    }

    @Override
    public void print() {
        Node current = this.last;
        System.out.print("[ ");
        String values = "";
        while (current != null) {
            values = current.value + " " + values;
            current = current.next;
        }
        System.out.println(values + "]\n");
    }
}
