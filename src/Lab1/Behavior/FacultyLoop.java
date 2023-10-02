package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.Student;
import java.time.LocalDate;
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
        while (!this.command.equals("0")) {
            this.command = user.readInput("Enter command -> ");

            switch (command) {
                case "0":
                    break;
                case "1":
                    this.faculty.addStudent(readStudent());
                    break;
                case "2":
                    if (this.faculty.checkStudentByEmail(user.readInput("Enter email: "))) {
                        System.out.println("Student found.");
                    } else {
                        System.out.println("No such student with this email.");
                    }
                    break;
                case "3":
                    this.faculty.graduateStudent(user.readInput("Enter students email: "));
                    break;
                case "4":
                    List<Student> students = this.faculty.getStudents();
                    for (int i = 0; i < students.size(); i++) {
                        displayStudent(students.get(i), i + 1);
                    }
                case "5":
                    List<Student> graduatedStudents = this.faculty.getGraduatedStudents();
                    for (int i = 0; i < graduatedStudents.size(); i++) {
                        displayStudent(graduatedStudents.get(i), i + 1);
                    }
                default:
                    System.out.println("Invalid input");
                    break;
            }
            }
        }

    private Student readStudent() {
        String firstName = user.readInput("First name: ");
        String lastName = user.readInput("Last name: ");
        String email = user.readInput("Email: ");
        LocalDate enrollmentDate = user.readDate("Enrollment date (yyyy-mm-dd): ");
        LocalDate dateOfBirth = user.readDate("Date of birth (yyyy-mm-dd): ");
        return new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
    }

    private void displayStudent(Student student, int index) {
        System.out.print(index + ".  ");
        System.out.println("First name: " + student.getFirstName());
        System.out.println("Last name: " + student.getLastName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Enrollment date: " + student.getEnrollmentDate().toString());
        System.out.println("Date of birth: " + student.getDateOfBirth().toString());
    }
}
