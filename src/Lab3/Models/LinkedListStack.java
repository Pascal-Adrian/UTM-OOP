package Lab3.Models;

public class LinkedListStack implements Stack {

    private Node last;

    public LinkedListStack() {
        this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return this.last == null;
    }

    @Override
    public void push(int value) {
        Node newNode = new Node();
        newNode.variable(value);
        newNode.next(this.last);
        this.last = newNode;
    }

    @Override
    public int pop() {
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

    public void print() {
        Node current = this.last;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("]\n");
    }
}
