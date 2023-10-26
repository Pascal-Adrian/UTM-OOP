package Lab2.Models;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;

public class File {
    private java.io.File file;
    private String filename;
    private String extension;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private byte[] previousState;
    private byte[] currentState;

    public File(String filename, String extension, String path) {
        this.filename = filename;
        this.extension = extension;
        this.file = new java.io.File(path);
        this.dateCreated = LocalDate.now();
        this.resetState();
    }

    public String getInfo() {
        return "Name: " + filename + "\nExtension: " + extension + "\nCreated: " + dateCreated + "\nModified: " + dateModified;
    }

    public java.io.File getFile() {
        return this.file;
    }

    private void updateState() {
        this.previousState = Arrays.copyOf(this.currentState, this.currentState.length);
        try {
            this.currentState = Files.readAllBytes(this.file.toPath());
        } catch (Exception e) {
            System.out.println("Failed to update file state.");
        }
    }

    private void initializeState() {
        try {
            this.currentState = Files.readAllBytes(this.file.toPath());
            this.previousState = Arrays.copyOf(this.currentState, this.currentState.length);
        } catch (Exception e) {
            System.out.println("Failed to initialize file state.");
        }
    }

    public boolean isModified() {
        this.updateState();
        return !Arrays.equals(this.previousState, this.currentState);
    }

    public void resetState() {
        this.dateModified = LocalDate.now();
        this.initializeState();
    }
}
