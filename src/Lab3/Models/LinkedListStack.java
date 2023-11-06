package Lab3.Models;

public class LinkedListStack implements Stack {
    private class node {
        int value;
        node next;

        public void variable(int value) {
            this.value = value;
        }

        public void next(node next) {
            this.next = next;
        }
    }

    private node last;

    public LinkedListStack() {
        this.last = null;
    }

    @Override
    public void push(int value) {
        node newNode = new node();
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

    @Override
    public boolean isEmpty() {
        return this.last == null;
    }

    public void print() {
        node current = this.last;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("]\n");
    }
}
