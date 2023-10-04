package Lab1.Behavior;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class User {
    private Scanner scanner;
    public User() {
        scanner = new Scanner(System.in);
    }

    public String readInput(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}
