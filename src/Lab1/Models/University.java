package Lab1.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class University implements Serializable {
    private List<Faculty> faculties;

    public University () {
        faculties = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public List<Faculty> getFaculties() {
        return this.faculties;
    }
}
