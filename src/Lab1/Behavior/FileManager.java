package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.Student;
import Lab1.Models.University;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class FileManager {

    private String lastMessage;
    private String directory;
    private BufferedReader reader;

    public FileManager() {
        this.lastMessage = "";
        this.directory = "src/Lab1/Resources/";
    }

    public void saveUniversityToFile(University university) {
        try {
            FileOutputStream fileOut = new FileOutputStream(directory + "AppData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(university);
            out.close();
            fileOut.close();
            this.lastMessage = "[FileManager] (University saved successfully.)";
        } catch (Exception e) {
            this.lastMessage = "[FileManager Error] (Could not access the file.)";
        }
    }
    public University getUniversityFromFile() {
        University university = new University();
        try {
            FileInputStream fileIn = new FileInputStream(directory + "AppData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            university = (University) in.readObject();
            in.close();
            fileIn.close();
            this.lastMessage = "[FileManager] University loaded successfully!";
        } catch (Exception e) {
            this.lastMessage = "[FileManager Error] (Could not access the file.)";
        }
        return university;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void getStudentsFromTxt(Faculty faculty, String file) {
        try {
            reader = new BufferedReader(new FileReader(directory + file));
            String line = reader.readLine();
            String exceptions = "";
            int count = 0;
            while (line != null) {
                count++;
                String[] data = line.split("/");
                try {
                    faculty.addStudent(new Student(data[0], data[1], data[2], LocalDate.parse(data[3]), LocalDate.parse(data[4])));
                } catch (Exception e) {
                    exceptions = exceptions.concat(Integer.toString(count) + ", ");
                }
                line = reader.readLine();
            }
            if (exceptions.isEmpty()) {
                this.lastMessage = "(Enrolled all students.)";
            } else {
                this.lastMessage = "(Enrolled all but unexpected values at lines " + exceptions + ")";
            }
            reader.close();
        } catch (Exception e) {
            this.lastMessage = "[FileManager Error] (Failed to read data.)";
        }
    }

    public void graduateStudentsFromTxt(Faculty faculty, String file) {
        try {
            reader = new BufferedReader(new FileReader(directory + file));
            String line = reader.readLine();
            String exceptions = "";
            int count = 0;
            while (line != null) {
                count++;
                if (faculty.checkEnrolledStudentByEmail(line)) {
                    faculty.graduateStudent(line);
                } else {
                    exceptions = exceptions.concat(count + ", ");
                }
                line = reader.readLine();
            }
            if (exceptions.isEmpty()) {
                this.lastMessage = "(Graduated all students.)";
            } else {
                this.lastMessage = "(Graduated all but unexpected values at " + exceptions + ")";
            }
            reader.close();
        } catch (Exception e) {
            this.lastMessage = "[FileManager Error] (Failed to read data.)";
        }
    }
}
