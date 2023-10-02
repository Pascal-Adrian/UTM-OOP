package Lab1.Behavior;

import Lab1.Classes.Faculty;
import Lab1.Classes.Student;
import Lab1.Classes.StudyField;
import Lab1.Classes.University;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UniversityLoop {
    private Scanner scanner;
    private String command;
    private University university;

    public UniversityLoop() {
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
                case "3":
                    displayFacultiesByStudyFied();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        scanner.close();
    }


    public String getUserInput(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }

    private StudyField selectStudyField(String string) {
        StudyField[] temp = StudyField.values();
        String studyFields = "(|";
        for (int i = 0; i < temp.length; i++) {
            studyFields += (i+1) + ". " + temp[i].toString() + "|";
        }
        studyFields += ")";
        System.out.println(string);
        System.out.print(studyFields + ": ");
        return temp[Integer.parseInt(scanner.nextLine())];
    }

    private void createFaculty() {
        StudyField[] studyField = StudyField.values();
        String name = getUserInput("Enter name: ");
        String abbreviation = getUserInput("Enter abbreviation: ");
        Faculty faculty = new Faculty(name, abbreviation, selectStudyField("Enter the order number for the field of study"));
        university.addFaculty(faculty);
    }

    private void displayFaculties() {
        List<Faculty> faculties = university.getFaculties();
        for (int i = 0; i < faculties.size(); i++) {
            String[] strings = faculties.get(i).getFacultyDetails();
            displayFaculty(strings, i);
        }
    }

    private void displayFacultiesByStudyFied() {
        List<Faculty> faculties = university.getFaculties();
        StudyField studyField = selectStudyField("Enter the order number for the field of study");
        int index = 0;
        for (int i = 0; i < faculties.size(); i++) {
            String[] facultyDetails = faculties.get(i).getFacultyDetails();
            if (facultyDetails[2].equals(studyField.toString())) {
                index++;
                displayFaculty(facultyDetails, index);
            }
        }
    }

    private void displayFaculty(String[] strings, int index) {
        System.out.print((index+1) + ".  ");
        System.out.println("Name: " + strings[0]);
        System.out.println("\tAbbreviation: " + strings[1]);
        System.out.println("\tStudy field: " + strings[2]);
        System.out.println("\tNumber of students: " + strings[3] + "\n");
    }


    private Student getStudent() {
        String firstName = getUserInput("First name: ");
        String lastName = getUserInput("Last name: ");
        String email = getUserInput("Email: ");
        Date enrollmentDate = getDate("Enrollment date: ");
        Date dateOfBirth = getDate("Date of birth: ");
        Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
        return student;
    }

    public Date getDate(String string) {
        while (true) {
            String dateString =getUserInput(string);
            try {
                Date enrollmentDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                return enrollmentDate;
            } catch (ParseException e) {
                System.out.println("Invalid time format. Enter new value: ");
            }
        }
    }
}
