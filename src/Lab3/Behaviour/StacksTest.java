package Lab3.Behaviour;

import Lab3.Models.ArrayDownStack;
import Lab3.Models.ArrayUpStack;
import Lab3.Models.LinkedListStack;
import Lab3.Models.Stack;

import java.util.Scanner;

public class StacksTest {
    Stack stack;
    Scanner scanner;
    int type;

    public StacksTest(int type) {
        this.type = type;
        this.scanner = new Scanner(System.in);
        this.setStack(type);
    }

    private void setStack(int type) {
        switch (type) {
            case 1 -> this.stack = new ArrayUpStack(5);
            case 2 -> this.stack = new ArrayDownStack(5);
            case 3 -> this.stack = new LinkedListStack(5);
        }
    }

    public void run() {
        String message = "";
        System.out.println("\n" + this.stackType() + " test. Enter 'h' for help.");
        System.out.println("===========================================");
        while (!message.equals("q")) {
            System.out.print("> ");
            String[] input = scanner.nextLine().split(" ");
            message = input[0];
            switch (message) {
                case "h" -> System.out.println("""
                        push <value> - push value to stack
                        pop          - pop value from stack
                        peek         - peek value from stack
                        q            - quit
                        """);
                case "push" -> {
                    if (input.length < 2) {
                        System.out.println("No value provided.");
                        break;
                    }
                    int value = Integer.parseInt(input[1]);
                    this.stack.push(value);
                    this.stack.print();
                }
                case "pop" -> {
                    int value = this.stack.pop();
                    System.out.println(value);
                    this.stack.print();
                }
                case "peek" -> {
                    int value = this.stack.peek();
                    System.out.println(value);
                    this.stack.print();
                }
                case "q" -> System.out.println("Exiting...");
                default -> System.out.println("Invalid command.");
            }
        }
        System.out.println("===========================================\n");
    }

    private String stackType() {
        if (this.type == 1) {
            return "ArrayUpStack";
        } else if (this.type == 2) {
            return "ArrayDownStack";
        } else {
            return "LinkedListStack";
        }
    }
}
