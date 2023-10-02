package Lab1.Behavior;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class User {
    private final Scanner scanner;
    public User() {
        scanner = new Scanner(System.in);
    }

    public String readInput(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }

    public LocalDate readDate(String string) {
        while (true) {
            String dateString = readInput(string);
            try {
                return LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Enter new value: ");
            }
        }
    }
}
