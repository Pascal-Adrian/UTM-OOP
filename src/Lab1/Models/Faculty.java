package Lab1.Models;

import java.util.ArrayList;
import java.util.List;


public class Faculty {
    private String name;
    private String abbreviation;
    private StudyField studyField;
    private List<Student> students;
    private List<Student> graduatedStudents;

    public Faculty (String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
        this.graduatedStudents = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getName() {
        return name;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public int getStudentNumber() {
        return students.size();
    }

    public List<Student> getStudents() {
        return this.students;
    }


    public void graduateStudent(String email) {
        for (Student student:
                students) {
            if (student.getEmail().equals(email)) {
                graduatedStudents.add(student);
                students.remove(student);
                return;
            }
        }
        System.out.println("No student with such email.");
    }
    
    public boolean checkStudentByEmail(String email) {
        for (Student student:
                students) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Student> getGraduatedStudents() {
        return graduatedStudents;
    }
}
