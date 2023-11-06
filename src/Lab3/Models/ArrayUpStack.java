package Lab3.Models;

public class ArrayUpStack implements Stack {
    private int[] stack;
    private int top;
    private int size;

    public ArrayUpStack(int size) {
        this.stack = new int[size];
        this.top = -1;
        this.size = size;
    }

    @Override
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("Stack is full.");
        } else {
            this.top += 1;
            this.stack[this.top] = value;
        }
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            int value = this.stack[this.top];
            this.top -= 1;
            return value;
        }
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            return this.stack[this.top];
        }
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public boolean isFull() {
        return this.top == this.size - 1;
    }
}
