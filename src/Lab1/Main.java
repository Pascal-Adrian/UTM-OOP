package Lab1;

import Lab1.Behavior.FacultyLoop;
import Lab1.Behavior.UniversityLoop;
import Lab1.Models.Faculty;
import Lab1.Models.StudyField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UniversityLoop universityLoop = new UniversityLoop();
        universityLoop.run();
    }
}
