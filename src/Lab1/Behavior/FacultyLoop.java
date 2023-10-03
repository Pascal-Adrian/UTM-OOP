package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.Student;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class FacultyLoop {
    private final User user;
    private final Faculty faculty;
    private String command;

    public FacultyLoop(Faculty faculty) {
        this.faculty = faculty;
        this.user = new User();
        this.command = "";
    }

    public void run() {
        String facultyLoopIdentifier = "[" + faculty.getAbbreviation() + "] ";
        System.out.print(facultyLoopIdentifier);
        System.out.println("(Enter fh for help)");
        while (!this.command.equals("qf")) {
            String[] commands = user.readInput(facultyLoopIdentifier + "(Enter command) -> ")
                    .split("/");
            this.command = commands[0];
            switch (this.command) {
                case "qf" -> {}
                case "fh" -> System.out.println("""
                        es   -  add student to faculty (data can be introduced through "/" after the command)
                        cse  -  check a student's presence in this faculty by its email
                        gs   -  graduate a student from this faculty
                        ds   -  display current students
                        dgs  -  display graduated students
                        q    -  quit
                        
                        fh   -  help
                        """);
                case "es" -> handleStudentEnroll(commands);
                case "cse" -> checkStudentByEmail(user.readInput("Enter email: "));
                case "gs" -> this.faculty.graduateStudent(user.readInput("Enter students email: "));
                case "ds" -> displayStudents();
                case "dgs"-> displayGraduatedStudents();
                default -> System.out.println(facultyLoopIdentifier +
                        "(\"" + this.command + "\" is not a valid command. Enter fh for help)");
            }
            }
        user.closeScanner();
    }

    private void enrollStudent() {
        String firstName = user.readInput("First name: ");
        String lastName = user.readInput("Last name: ");
        String email = user.readInput("Email: ");
        LocalDate enrollmentDate = user.readDate("Enrollment date (yyyy-mm-dd): ");
        LocalDate dateOfBirth = user.readDate("Date of birth (yyyy-mm-dd): ");
        faculty.addStudent(new Student(firstName, lastName, email, enrollmentDate, dateOfBirth));
    }

    public void handleStudentEnroll(String[] data) {
        if (data.length == 6) {
            try {
                faculty.addStudent(new Student(data[1], data[2], data[3],
                        LocalDate.parse(data[4]), LocalDate.parse(data[5])));
            } catch (DateTimeParseException e) {
                System.out.println("(Found invalid data. Please introduce manually)");
                enrollStudent();
            }
        } else {
            System.out.println("(Found invalid data. Please introduce manually)");
            enrollStudent();
        }
    }

    private void displayStudent(Student student, int index) {
        System.out.print(index + ".  ");
        System.out.println("\tFirst name: " + student.getFirstName());
        System.out.println("\tLast name: " + student.getLastName());
        System.out.println("\tEmail: " + student.getEmail());
        System.out.println("\tEnrollment date: " + student.getEnrollmentDate().toString());
        System.out.println("\tDate of birth: " + student.getDateOfBirth().toString());
    }

    private void displayGraduatedStudents() {
        List<Student> graduatedStudents = this.faculty.getGraduatedStudents();
        for (int i = 0; i < graduatedStudents.size(); i++) {
            displayStudent(graduatedStudents.get(i), i + 1);
        }
    }

    private void displayStudents() {
        List<Student> students = this.faculty.getStudents();
        for (int i = 0; i < students.size(); i++) {
            displayStudent(students.get(i), i + 1);
        }
    }

    private void checkStudentByEmail(String email) {
        if (this.faculty.checkStudentByEmail(email)) {
            System.out.println("(Student found)");
        } else {
            System.out.println("(No such student with this email)");
        }
    }
}
