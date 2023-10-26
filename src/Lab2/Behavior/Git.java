package Lab2.Behavior;

import Lab2.Models.File;
import Lab2.Models.ImageFile;
import Lab2.Models.ProgramFile;
import Lab2.Models.TextFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Git {
    private List<File> files;
    private String directory;
    private Path directoryPath;
    private Scanner scanner;

    public Git() {
        this.files = new ArrayList<>();
        this.directory = "src/Lab2/Resources/MainDirectory";
        this.directoryPath = Paths.get(this.directory);
        this.scanner = new Scanner(System.in);
    }
    public void run() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
            for (Path file : directoryStream) {
                String fileName = file.getFileName().toString();
                String fileType = Files.probeContentType(file);

                if (fileType == null) {
                    fileType = "Unknown";
                }
                String extension = fileName.substring(fileName.indexOf('.') + 1);
                String path = directory+"/"+fileName;
                if (extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png")) {
                    files.add(new ImageFile(fileName, extension, path));
                } else if (extension.equals("txt")) {
                    files.add(new TextFile(fileName, extension, path));
                } else if (extension.equals("java")) {
                    files.add(new ProgramFile(fileName, extension, path));
                }   else {
                    files.add(new File(fileName, extension, path));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read directory.");
        }
        String input = "";
        while (!input.equals("q")) {
            System.out.print("> ");
            input = scanner.nextLine();
            switch (input) {
                case "q" -> System.out.println("Exiting...");
                case "status" -> status();
                case "commit" -> commit();
                default -> System.out.println("Invalid command.");
            }
        }
    }

    private void status() {
        for (File file : files) {
            System.out.println(file.getInfo());
            if (file.isModified()) {
                System.out.println("----File has been modified.");
            }
        }
    }

    private void commit() {
        for (File file : files) {
            System.out.println(file.getInfo());
            if (file.isModified()) {
                file.updateState();
            }
        }
    }
}
