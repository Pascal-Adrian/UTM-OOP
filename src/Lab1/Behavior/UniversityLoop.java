package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.StudyField;
import Lab1.Models.University;

import java.util.List;
import java.util.Scanner;

public class UniversityLoop {
    private Scanner scanner;
    private String command;
    private University university;
    private User user;
    private Parser parser;

    public UniversityLoop() {
        this.parser = new Parser();
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.command = "";
        this.user = new User();
    }

    public void run() {
        System.out.println("Loading data...");
        university = parser.getUniversityFromFile();
        System.out.println("(Enter h for help)");
        while (!this.command.equals("q")) {
            String[] commands = user.readInput("(Enter command) -> ").split("/");
            this.command = commands[0];
            switch (this.command) {
                case "h" -> {
                    System.out.print("""
                            cf    -  create a new faculty (data can be introduced through "/" after the command)
                            df    -  display faculties
                            dsfs  -  display the faculties of a study field
                            ssf   -  search student's faculty by its email
                            of    -  choose faculty and open faculty specific menu
                            q     -  quit
                            
                            h - help
                            """);
                }
                case "q" -> {}
                case "cf" -> handleCreateFaculty(commands);
                case "df" -> displayFaculties();
                case "dfsf" -> displayFacultiesByStudyField();
                case "ssf" -> System.out.println(searchStudentFaculty(user.readInput("(Enter email) -> ")));
                case "of" -> {
                    FacultyLoop facultyLoop = new FacultyLoop(this.university.getFaculties().get(getFacultyByAbbreviations()));
                    facultyLoop.run();
                }
                default -> System.out.println("(\"" + this.command +
                        "\" is not a valid command. Enter h for help.)");
            }
        }
        System.out.println("Saving data...");
        this.parser.saveUniversityToFile(this.university);
        this.user.closeScanner();
        scanner.close();
    }

    private StudyField selectStudyField() {
        StudyField[] temp = StudyField.values();
        String studyFields = " | ";
        for (int i = 0; i < temp.length; i++) {
            studyFields = studyFields.concat((i + 1) + ". " + temp[i] + " | ");
        }
        studyFields += "\nEnter the index of the field of study: ";
        System.out.print(studyFields);
        return temp[Integer.parseInt(scanner.nextLine()) - 1];
    }

    private void createFaculty() {
        String name = user.readInput("Enter name: ");
        String abbreviation = user.readInput("Enter abbreviation: ");
        Faculty faculty = new Faculty(name, abbreviation, selectStudyField());
        this.university.addFaculty(faculty);
    }

    private void handleCreateFaculty(String[] data) {
        if (data.length == 4) {
            this.university.addFaculty(new Faculty(data[1], data[2], StudyField.values()[Integer.parseInt(data[3]) - 1]));
        } else {
            createFaculty();
        }
    }

    private void displayFaculties() {
        List<Faculty> faculties = this.university.getFaculties();
        for (int i = 0; i < faculties.size(); i++) {
            displayFaculty(faculties.get(i), i + 1);
        }
    }

    private void displayFacultiesByStudyField() {
        List<Faculty> faculties = this.university.getFaculties();
        StudyField studyField = selectStudyField();
        int index = 0;
        for (Faculty faculty:
                faculties) {
            if (faculty.getStudyField().equals(studyField)) {
                index++;
                displayFaculty(faculty, index);
            }
        }
    }

    private void displayFaculty(Faculty faculty, int index) {
        System.out.print((index) + ".  ");
        System.out.println("Name: " + faculty.getName());
        System.out.println("\tAbbreviation: " + faculty.getAbbreviation());
        System.out.println("\tStudy field: " + faculty.getStudyField());
        System.out.println("\tNumber of students: " + faculty.getStudentNumber() + "\n");
    }

    private String searchStudentFaculty(String email) {
        for (Faculty faculty:
                university.getFaculties()) {
            if (faculty.checkStudentByEmail(email)) {
                return faculty.getAbbreviation();
            }
        }
        return "No student with such email.";
    }

    private int getFacultyByAbbreviations() {
       List<Faculty> faculties = this.university.getFaculties();
       String abbreviations = "| ";
        for (int i = 0; i < faculties.size(); i++) {
            abbreviations = abbreviations.concat((i + 1) + ". " + faculties.get(i).getAbbreviation() + " | ");
        }
        abbreviations = abbreviations.concat("\nEnter the index of the faculty you want to open: ");
        return Integer.parseInt(user.readInput(abbreviations)) - 1;
    }
}
