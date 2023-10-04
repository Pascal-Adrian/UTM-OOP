package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.Student;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class FacultyLoop {
    private final User user;
    private final Faculty faculty;
    private String command;
    private String loopIdentifier;
    private LogManager logManager;
    private String[] logData;

    public FacultyLoop(Faculty faculty) {
        this.faculty = faculty;
        this.user = new User();
        this.loopIdentifier = "[" + faculty.getAbbreviation() + "]";
        this.logManager = new LogManager();
        this.logData = new String[3]; Arrays.fill(this.logData, "");
        this.command = "";
    }

    public void run() {
        this.logData[0] = loopIdentifier;
        this.logData[1] = "of";
        this.logData[2] = "Entered " + faculty.getAbbreviation() + " faculty";
        logManager.addLog(logData);
        System.out.println(loopIdentifier + "\n\n(Enter fh for help)");
        while (!this.command.equals("qf")) {
            String input = user.readInput(loopIdentifier + " (Enter command) -> ");
            String[] commands = input.split("/");
            this.command = commands[0];
            this.logData[1] = input;
            switch (this.command) {
                case "fh" -> {System.out.println("""
                        
                        es   -  add student to faculty (data can be introduced through "/" after the command)
                        cse  -  check a student's presence in this faculty by its email
                        gs   -  graduate a student from this faculty
                        ds   -  display current students
                        dgs  -  display graduated students
                        qf    -  quit
                        
                        fh   -  help
                        
                        """);
                this.logData[2] = "Display help string";
                }
                case "qf" -> this.logData[2] = "Quit faculty loop";
                case "es" -> handleStudentEnroll(commands);
                case "cse" -> checkStudentByEmail();
                case "gs" -> graduateStudent();
                case "ds" -> displayStudents();
                case "dgs"-> displayGraduatedStudents();
                default -> {
                    System.out.println("\n" + loopIdentifier +
                            " (\"" + this.command + "\" is not a valid command. Enter fh for help)\n");
                    logData[2] = "Incorrect command";
                }
            }
            logManager.addLog(this.logData);
        }
    }

    private void enrollStudent() {
        String firstName = user.readInput("\nFirst name: ");
        String lastName = user.readInput("Last name: ");
        String email = user.readInput("Email: ");
        LocalDate enrollmentDate;
        LocalDate dateOfBirth;
        try {
            enrollmentDate = LocalDate.parse(user.readInput("Enrollment date (yyyy-mm-dd): "));
            dateOfBirth = LocalDate.parse(user.readInput("Date of birth (yyyy-mm-dd): "));
        } catch (Exception e) {
            logData[2] = "Tried and could not create student";
            System.out.println("\n" + loopIdentifier + " (Could not create student)\n");
            return;
        }
        faculty.addStudent(new Student(firstName, lastName, email, enrollmentDate, dateOfBirth));
        System.out.println("\n" + loopIdentifier + " (Student created and enrolled successfully.)\n");
        logData[2] = "Created and enrolled student manually: " + firstName + " " + lastName;
    }

    public void handleStudentEnroll(String[] data) {
        if (data.length == 6) {
            try {
                faculty.addStudent(new Student(data[1], data[2], data[3],
                        LocalDate.parse(data[4]), LocalDate.parse(data[5])));
                logData[2] = "Created and enrolled student through quick method: " + data[1] + " " + data[2];
                System.out.println("\n" + loopIdentifier + " (Student created and enrolled successfully.)\n");
            } catch (Exception e) {
                logData[2] = "Tried and could not create student";
                System.out.println("\n" + loopIdentifier + " (Could not create student)\n");
            }
        } else {
            enrollStudent();
        }
    }

    private void displayStudent(Student student, int index) {
        System.out.print("\n" + index + ".  ");
        System.out.println("First name: " + student.getFirstName());
        System.out.println("\tLast name: " + student.getLastName());
        System.out.println("\tEmail: " + student.getEmail());
        System.out.println("\tEnrollment date: " + student.getEnrollmentDate().toString());
        System.out.println("\tDate of birth: " + student.getDateOfBirth().toString());
    }

    private void displayGraduatedStudents() {
        List<Student> graduatedStudents = this.faculty.getGraduatedStudents();
        if (!graduatedStudents.isEmpty()) {
            for (int i = 0; i < graduatedStudents.size(); i++) {
                displayStudent(graduatedStudents.get(i), i + 1);
            }
            System.out.println("\n");
            logData[2] = "Displayed graduated students.";
        } else {
            System.out.println("\n" + loopIdentifier + " (No students yet.)\n");
            logData[2] = "Tried to display graduated students, but found none.";
        }
    }

    private void displayStudents() {
        List<Student> students = this.faculty.getStudents();
        if (!students.isEmpty()) {
            for (int i = 0; i < students.size(); i++) {
                displayStudent(students.get(i), i + 1);
            }
            System.out.println("\n");
            logData[2] = "Displayed currently enrolled students.";
        } else {
            System.out.println("\n" + loopIdentifier + " (No students yet.)\n");
            logData[2] = "Tried to display currently enrolled students, but found none.";
        }
    }

    private void checkStudentByEmail() {
        String email = user.readInput("Enter email: ");
        if (this.faculty.checkStudentByEmail(email)) {
            System.out.println("\n" + loopIdentifier + " (Student found.)\n");
            logData[2] = "Searched for a student by email and found it";
        } else {
            System.out.println("\n" + loopIdentifier + " (No such student with this email.)\n");
            logData[2] = "Searched for a student by email and did not found any students with this email " + email;
        }
    }

    private void graduateStudent() {
        String email = user.readInput("Enter students email: ");
        if (this.faculty.checkStudentByEmail(email)){
            this.faculty.graduateStudent(email);
            System.out.println("\n" + loopIdentifier + " (Student graduated successfully.)\n");
            logData[2] = "Graduated student by email (" + email + ")";
        } else {
            System.out.println("\n" + loopIdentifier + " (No enrolled student with this email.)");
            logData[2] = "No enrolled student with this email (" + email + ")";
        }
    }
}
