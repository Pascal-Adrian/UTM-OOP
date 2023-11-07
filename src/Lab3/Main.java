package Lab3;

import Lab3.Behaviour.StacksTest;
import Lab3.Models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (!message.equals("q")) {
            System.out.println("1. ArrayUpStack\n2. ArrayDownStack\n3. LinkedListStack\n");
            System.out.print("> ");
            message = scanner.nextLine();
            switch (message) {
                case "1", "2", "3" -> {
                    StacksTest stacksTest = new StacksTest(Integer.parseInt(message));
                    stacksTest.run();
                }
                case "q" -> System.out.println("Shutting down...");
                default -> System.out.println("Invalid command.");
            }
        }
        scanner.close();
    }
}
