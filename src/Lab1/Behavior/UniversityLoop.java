package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.StudyField;
import Lab1.Models.University;

import javax.lang.model.type.NullType;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UniversityLoop {
    private Scanner scanner;
    private String command;
    private University university;
    private User user;
    private FileManager fileManager;
    private LogManager logManager;
    private String[] logData;

    public UniversityLoop() {
        this.fileManager = new FileManager();
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.user = new User();
        this.logManager = new LogManager();
        this.logData = new String[3]; Arrays.fill(this.logData, "");
        this.command = "";
    }

    public void run() {
        this.logData[0] = "[UniversityLoop]";
        System.out.println("Loading data...");
        this.logData[2] = "Loading data...";
        logManager.addLog(logData);
        university = fileManager.getUniversityFromFile();
        System.out.println(fileManager.getLastMessage());
        this.logData[2] = fileManager.getLastMessage();
        logManager.addLog(logData);
        System.out.println("\n\n(Enter h for help)");
        while (!this.command.equals("q")) {
            String input = user.readInput("(Enter command) -> ");
            String[] commands = input.split("/");
            this.command = commands[0];
            this.logData[1] = input;
                    switch (this.command) {
                case "h" -> {
                    System.out.print("""
                            
                            cf    -  create a new faculty (data can be introduced through "/" after the command)
                            df    -  display faculties
                            dfsf  -  display the faculties of a study field
                            ssf   -  search student's faculty by its email
                            of    -  choose faculty and open faculty specific menu
                            q     -  quit
                            
                            h     -  help
                            
                            """);
                    this.logData[2] = "Display help string";
                }
                case "q" -> this.logData[2] = "Quit program";
                case "cf" -> handleCreateFaculty(commands);
                case "df" -> displayFaculties();
                case "dfsf" -> displayFacultiesByStudyField();
                case "ssf" -> searchStudentFaculty();
                case "of" -> openFaculty();
                default -> {
                    System.out.println("\n(\"" + command + "\" is not a valid command. Enter h for help.)\n");
                    logData[2] = "Incorrect command";
                }
            }
            logManager.addLog(logData);
        }
        logData[2] = "Saving data...";
        logManager.addLog(logData);
        System.out.println("\n\nSaving data...");
        this.fileManager.saveUniversityToFile(this.university);
        System.out.println(fileManager.getLastMessage());
        logData[2] = fileManager.getLastMessage();
        logManager.addLog(logData);
        this.user.closeScanner();
        scanner.close();
    }

    private String getStudyFields() {
        StudyField[] temp = StudyField.values();
        String studyFields = " | ";
        for (int i = 0; i < temp.length; i++) {
            studyFields = studyFields.concat((i + 1) + ". " + temp[i] + " | ");
        }
        return studyFields;
    }

    private void createFaculty() {
        Faculty faculty;
        String name = user.readInput("\nEnter name: ");
        String abbreviation = user.readInput("Enter abbreviation: ");
        System.out.println(getStudyFields());
        try {
            faculty = new Faculty(name, abbreviation,
                    StudyField.values()[Integer.parseInt(user.readInput("Enter the index of the field of study: ")) - 1]);
        } catch (Exception e) {
            logData[2] = "Tried and could not create faculty.";
            System.out.println("\n(Could not create faculty.)\n");
            return;
        }
        this.university.addFaculty(faculty);
        logData[2] = "Created faculty manually: " + abbreviation;
        System.out.println("\n(Faculty created successfully.)\n");
    }

    private void handleCreateFaculty(String[] data) {
        if (data.length == 4) {
            try {
                this.university.addFaculty(new Faculty(data[1], data[2], StudyField.values()[Integer.parseInt(data[3]) - 1]));
                logData[2] = "Created faculty through quick method: " + data[2];
                System.out.println("\n(Faculty created successfully.)\n");
            } catch (Exception e) {
                logData[2] = "Tried and could not create faculty.";
                System.out.println("\n(Could not create faculty.)\n");
            }
        } else {
            createFaculty();
        }
    }

    private void displayFaculties() {
        List<Faculty> faculties = this.university.getFaculties();
        if (!faculties.isEmpty()) {
            for (int i = 0; i < faculties.size(); i++) {
                displayFaculty(faculties.get(i), i + 1);
            }
            logData[2] = "Displayed faculties";
        } else {
            System.out.println("\n(No faculties yet.)\n");
            logData[2] = "Tried to display faculties, but found none";
        }
    }

    private void displayFacultiesByStudyField() {
        List<Faculty> faculties = this.university.getFaculties();
        System.out.println(getStudyFields());
        StudyField studyField;
        try {
            studyField = StudyField.values()
                    [Integer.parseInt(user.readInput("Enter the index of the field of study: ")) - 1];
        } catch (Exception e) {
            logData[2] = "Tried displaying faculties by study field and could not find study field.";
            System.out.println("\n(Could not find study field)\n");
            return;
        }
        int index = 0;
        if (!faculties.isEmpty()) {
            for (Faculty faculty :
                    faculties) {
                if (faculty.getStudyField().equals(studyField)) {
                    index++;
                    displayFaculty(faculty, index);
                }
            }
            logData[2] = "Displayed " + studyField.toString() + " faculties";
        } else {
            System.out.println("\n(No faculties yet.)\n");
            logData[2] = "Tried to display " + studyField.toString() + " faculties, but found none.";
        }
    }

    private void displayFaculty(Faculty faculty, int index) {
        System.out.print((index) + ".  ");
        System.out.println("Name: " + faculty.getName());
        System.out.println("\tAbbreviation: " + faculty.getAbbreviation());
        System.out.println("\tStudy field: " + faculty.getStudyField());
        System.out.println("\tNumber of students: " + faculty.getStudentNumber() + "\n");
    }

    private void searchStudentFaculty() {
        String email = user.readInput("Enter email: ");
        List<Faculty> faculties = university.getFaculties();
        for (Faculty faculty:
                faculties) {
            if (faculty.checkStudentByEmail(email)) {
                System.out.println("\n(Found student in " + faculty.getAbbreviation() + ")\n");
                logData[2] = "Search and found student in " + faculty.getAbbreviation();
                return;
            }
        }
        logData[2] = "Tried and did not find student with such email";
        System.out.println("\n(No student with such email.)\n");

    }

    private void openFaculty() {
       List<Faculty> faculties = this.university.getFaculties();
       String abbreviations = "| ";
        for (int i = 0; i < faculties.size(); i++) {
            abbreviations = abbreviations.concat((i + 1) + ". " + faculties.get(i).getAbbreviation() + " | ");
        }
        System.out.println(abbreviations);
        Faculty faculty;
        try {
            faculty = faculties.get(
                    Integer.parseInt(user.readInput("Enter the index of the faculty you want to open: ")) - 1);
        } catch (Exception e) {
            logData[2] = "Tried and could not open faculty";
            System.out.println("\n(Could not open faculty.)\n");
            return;
        }
        FacultyLoop facultyLoop = new FacultyLoop(faculty);
        facultyLoop.run();
        logData[2] = "Exited faculty "  + faculty.getAbbreviation();
    }
}
