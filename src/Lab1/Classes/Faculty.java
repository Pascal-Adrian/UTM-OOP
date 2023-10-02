package Lab1.Classes;

import java.util.ArrayList;
import java.util.List;


public class Faculty {
    private String name;
    private String abbreviation;
    private StudyField studyField;
    private List<Student> students;

    public Faculty (String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String[] getFacultyDetails() {
        String[] strings = {name, abbreviation, studyField.toString(), String.valueOf(students.size())};
        return strings;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
