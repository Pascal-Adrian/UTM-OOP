package Lab3;

import Lab3.Models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedListQueue stack = new LinkedListQueue();
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (!message.equals("q")) {
            System.out.print("> ");
            String[] input = scanner.nextLine().split(" ");
            message = input[0];
            switch (message) {
                case "enqueue" -> {
                    if (input.length < 2) {
                        System.out.println("No value provided.");
                        break;
                    }
                    int value = Integer.parseInt(input[1]);
                    stack.enqueue(value);
                    stack.print();
                }
                case "dequeue" -> {
                    int value = stack.dequeue();
                    System.out.println(value);
                    stack.print();
                }
                case "peek" -> {
                    int value = stack.peek();
                    System.out.println(value);
                    stack.print();
                }
                case "q" -> System.out.println("Exiting...");
                default -> System.out.println("Invalid command.");
            }
        }
    }
}
