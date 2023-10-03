package Lab1.Behavior;

import Lab1.Models.Faculty;
import Lab1.Models.Student;
import Lab1.Models.University;

import java.io.*;

public class Parser {

    public void saveUniversityToFile(University university) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/Lab1/Resources/AppData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(university);
            out.close();
            fileOut.close();
            System.out.println("University saved successfully!");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("!!File Not Found!!");
        } catch (IOException ioException) {
            System.out.println("!!IO Exception!!");
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
            System.out.println("University loaded successfully!\n\n");
        } catch (IOException ioException) {
            System.out.println("!!IOException!!");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("!!University Class Not Found!!");
        }
        return university;
    }
}
