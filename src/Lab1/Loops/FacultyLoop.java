package Lab1.Loops;

import Lab1.Classes.Faculty;
import Lab1.Classes.StudyField;
import Lab1.Classes.University;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FacultyLoop {
    private Scanner scanner;
    private String command;
    private University university;

    public FacultyLoop() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.command = "";
    }

    public void run() {
        while (!this.command.equals("0")) {
            this.command = getUserInput("Enter command -> ");

            switch (command) {
                case "0":
                    break;
                case "1":
                    createFaculty();
                    break;
                case "2":
                    displayFaculties();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        scanner.close();
    }

    private String getUserInput(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }

    private String getStudyFieldsOptions() {
        StudyField[] temp = StudyField.values();
        String studyFields = "(|";
        for (int i = 0; i < temp.length; i++) {
            studyFields += (i+1) + ". " + temp[i].toString() + "|";
        }
        studyFields += ")";
        return studyFields;
    }

    private Date getDateInput(String string) {
        while (true) {
            String intermediateString = scanner.nextLine();
            try {
                Date enrollmentDate = new SimpleDateFormat("dd/MM/yyyy").parse(intermediateString);
                return enrollmentDate;
            } catch (ParseException e) {
                System.out.println("Invalid time format. Enter new value: ");
            }
        }
    }

    private void createFaculty() {
        StudyField[] studyField = StudyField.values();
        String name = getUserInput("Enter name: ");
        String abbreviation = getUserInput("Enter abbreviation: ");
        int fieldIndex = Integer.parseInt(getUserInput("Enter the order number for the field of study\n"
                + getStudyFieldsOptions() + ": "));
        Faculty faculty = new Faculty(name, abbreviation, studyField[fieldIndex]);
        university.addFaculty(faculty);
    }

    private void displayFaculties() {
        List<Faculty> faculties = university.getFaculties();
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i+1) + ". ");
            String[] facultyDetails = faculties.get(i).getFacultyDetails();
            System.out.println("Name: " + facultyDetails[0]);
            System.out.println("Abbreviation: " + facultyDetails[1]);
            System.out.println();
        }
    }
}
