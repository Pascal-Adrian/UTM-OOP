package Lab2.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return super.getInfo() + "/Lines/" + this.lineCount + "/Classes/" + this.classCount + "/Methods/" + this.methodCount;
    }

    @Override
    public void updateState() {
        super.updateState();
        this.count();
    }

    private void countJava() {
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
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Failed to count lines, classes, and methods.");
        }
    }

    private void countPython() {
        try {
            FileReader fileReader = new FileReader(super.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lines = 0;
            int classes = 0;
            int methods = 0;
            String line;

            Pattern classPattern = Pattern.compile("^\\s*class\\s+([\\w_]+):");
            Pattern methodPattern = Pattern.compile("^\\s*def\\s+([\\w_]+)\\s*\\(");

            while ((line = bufferedReader.readLine()) != null) {
                lines++;
                Matcher classMatcher = classPattern.matcher(line);
                if (classMatcher.find()) {
                    classes++;
                }
                Matcher methodMatcher = methodPattern.matcher(line);
                if (methodMatcher.find()) {
                    methods++;
                }
            }

            this.lineCount = lines;
            this.classCount = classes;
            this.methodCount = methods;
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Failed to count lines, classes, and methods.");
        }
    }

    public void count() {
        if (super.getExtension().equals(".java")) {
            this.countJava();
        } else if (super.getExtension().equals(".py")) {
            this.countPython();
        } else {
            System.out.println("Could not count lines, classes, and methods.");
        }
    }
}
