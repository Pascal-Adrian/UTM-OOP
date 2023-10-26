package Lab2.Models;

import java.time.LocalDate;

public class File {
    private java.io.File file;
    private String filename;
    private String extension;
    private LocalDate dateCreated;
    private LocalDate dateModified;

    public File(String filename, String extension, String path) {
        this.filename = filename;
        this.extension = extension;
        this.file = new java.io.File(path);
        this.dateCreated = LocalDate.now();
        this.dateModified = this.dateCreated;
    }

    public String getInfo() {
        return "Name: " + filename + "\nExtension: " + extension + "\nCreated: " + dateCreated + "\nModified: " + dateModified;
    }

}
