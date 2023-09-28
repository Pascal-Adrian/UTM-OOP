package Lab1.Classes;

import java.util.ArrayList;
import java.util.List;


public class Faculty {
    private String name;
    private String abbreviation;
    private StudyField studyField;

    public Faculty (String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
    }

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public String[] getFacultyDetails() {
        String[] strings = {this.name, this.abbreviation, this.studyField.toString(), String.valueOf(this.students.size())};
        return strings;
    }
}
