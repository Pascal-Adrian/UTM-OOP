package Lab3.Behaviour;

import Lab3.Models.ArrayDownQueue;
import Lab3.Models.ArrayUpQueue;
import Lab3.Models.LinkedListQueue;
import Lab3.Models.Queue;

import java.util.Scanner;

public class QueuesTest {
    Queue queue;
    Scanner scanner;
    int type;

    public QueuesTest(int type) {
        this.type = type;
        this.setQueue(type);
        scanner = new Scanner(System.in);
    }

    private void setQueue(int type) {
        switch (type) {
            case 1 -> this.queue = new ArrayUpQueue(5);
            case 2 -> this.queue = new ArrayDownQueue(5);
            case 3 -> this.queue = new LinkedListQueue(5);
        }
    }

    public void run() {
        String message = "";
        System.out.println("\n" + this.queueType() + " test. Enter 'h' for help.");
        System.out.println("===========================================");
        while (!message.equals("q")) {
            System.out.print("> ");
            String[] input = scanner.nextLine().split(" ");
            message = input[0];
            switch (message) {
                case "h" -> System.out.println("""
                        enqueue <value> - enqueue value to queue
                        dequeue         - dequeue value from queue
                        peek            - peek value from queue
                        q               - quit
                        """);
                case "enqueue" -> {
                    if (input.length < 2) {
                        System.out.println("No value provided.");
                        break;
                    }
                    int value = Integer.parseInt(input[1]);
                    this.queue.enqueue(value);
                    this.queue.print();
                }
                case "dequeue" -> {
                    int value = this.queue.dequeue();
                    System.out.println(value);
                    this.queue.print();
                }
                case "peek" -> {
                    int value = this.queue.peek();
                    System.out.println(value);
                    this.queue.print();
                }
                case "q" -> System.out.println("Shutting down...");
                default -> System.out.println("Invalid command.");
            }
        }
        System.out.println("===========================================");
    }

    private String queueType() {
        if (this.type == 1) {
            return "ArrayUpQueue";
        } else if (this.type == 2) {
            return "ArrayDownQueue";
        } else {
            return "LinkedListQueue";
        }
    }
}
