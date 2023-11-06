package Lab3.Models;

public class ArrayDownStack implements Stack {
    int[] stack;
    int free;
    int size;

    public ArrayDownStack(int size) {
        this.stack = new int[size];
        this.free = size - 1;
        this.size = size;
    }

    @Override
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("Stack is full.");
        } else {
            this.stack[this.free] = value;
            this.free -= 1;
        }
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            int value = this.stack[this.free + 1];
            this.free += 1;
            return value;
        }
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            return this.stack[this.free + 1];
        }
    }

    @Override
    public boolean isEmpty() {
        return this.free == this.size - 1;
    }

    public boolean isFull() {
        return this.free == -1;
    }

    public void print() {
        System.out.print("[ ");
        for (int i = this.free + 1; i < this.size; i++) {
            System.out.print(this.stack[i] + " ");
        }
        System.out.println("]\n");
    }
}

