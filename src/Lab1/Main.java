package Lab1;

import Lab1.Behavior.FacultyLoop;
import Lab1.Behavior.UniversityLoop;
import Lab1.Models.Faculty;
import Lab1.Models.StudyField;

public class Main {
    public static void main(String[] args) {
//        UniversityLoop universityLoop = new UniversityLoop();
//        universityLoop.run();
        Faculty faculty = new Faculty("Calculatoare, Infromatică și Microelectronică", "CIM", StudyField.SOFTWARE_ENGINEERING);
        FacultyLoop facultyLoop = new FacultyLoop(faculty);
        facultyLoop.run();
    }
}
