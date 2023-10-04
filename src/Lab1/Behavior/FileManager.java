package Lab1.Behavior;

import Lab1.Models.University;

import java.io.*;

public class FileManager {

    private String lastMessage;
    private String directory;

    public FileManager() {
        this.lastMessage = "";
        this.directory = "src/Lab1/Resources/";
    }

    public void saveUniversityToFile(University university) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/Lab1/Resources/AppData.ser");
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
            FileInputStream fileIn = new FileInputStream("src/Lab1/Resources/AppData.ser");
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
}
