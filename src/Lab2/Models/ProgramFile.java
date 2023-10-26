package Lab2.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProgramFile extends File{
    private int lineCount;
    private int classCount;
    private int methodCount;
    public ProgramFile(String filename, String extension, String path) {
        super(filename, extension, path);
        this.count();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nLines: " + this.lineCount + "\nClasses: " + this.classCount + "\nMethods: " + this.methodCount;
    }

    private void count() {
        try {
            FileReader fileReader = new FileReader(super.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lines = 0;
            int classes = 0;
            int methods = 0;
            String line;
            boolean insideMethod = false;

            while ((line = bufferedReader.readLine()) != null) {
                lines++;
                line = line.trim();
                if (line.startsWith("public ") || line.startsWith("private ") || line.startsWith("protected ")) {
                    if (line.contains(" class ")) {
                        classes++;
                    } else if (line.contains(" (") || line.contains(") ")) {
                        methods++;
                        insideMethod = true;
                    }
                } else if (insideMethod && line.startsWith("}")) {
                    insideMethod = false;
                }
            }

            this.lineCount = lines;
            this.classCount = classes;
            this.methodCount = methods;
        } catch (IOException e) {
            System.out.println("Failed to count lines, classes, and methods.");
        }
    }
}
