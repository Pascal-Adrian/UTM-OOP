package Lab2.Models;

import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class File {
    private java.io.File file;
    private String filename;
    private String extension;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdtated;
    private byte[] currentState;
    private boolean isNew;

    public File(String filename, String extension, String path) {
        this.filename = filename;
        this.extension = extension;
        this.file = new java.io.File(path);
        this.dateCreated = LocalDateTime.now();
        this.updateState();
        this.isNew = true;
    }

    public String getInfo() {
        return "File name/" + this.filename + "/Extension/" + this.extension + this.extensionInfo(this.extension) + "/Created/" + this.dateCreated + "/Updated/" + this.dateUpdtated;
    }

    public java.io.File getFile() {
        return this.file;
    }

    public void updateState() {
        try {
            this.currentState = Files.readAllBytes(this.file.toPath());
            this.dateUpdtated = LocalDateTime.now();
            this.isNew = false;
        } catch (Exception e) {
            System.out.println("Failed to update file state.");
        }
    }

    public boolean isModified() {
        try {
            if (Arrays.equals(this.currentState, Files.readAllBytes(this.file.toPath()))) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Failed to check if file is modified.");
            return false;
        }
    }

    public String getFilename() {
        return this.filename;
    }

    private String extensionInfo(String extension) {
        switch (extension) {
            case ".txt" -> {return " (plain text file)";}
            case ".jpg", ".jpeg", ".png" -> {return " (image file)";}
            case ".java" -> {return " (program file)";}
            default -> {return " (unknown file type)";}
        }
    }
}
