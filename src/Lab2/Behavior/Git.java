package Lab2.Behavior;

import Lab2.Models.File;
import Lab2.Models.ImageFile;
import Lab2.Models.ProgramFile;
import Lab2.Models.TextFile;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Git {
    private List<File> files;
    private String directory;
    private Path directoryPath;
    private Scanner scanner;
    private String[] prevList;
    private String[] currList;
    private List<File> deleteQueue;

    public Git() {
        this.files = new ArrayList<>();
        this.directory = "src/Lab2/Resources/MainDirectory";
        this.directoryPath = Paths.get(this.directory);
        this.scanner = new Scanner(System.in);
        this.prevList = new String[0];
        this.currList = new String[0];

    }
    public void run() {
        String message = "";
        String[] input;
        while (!message.equals("q")) {
            System.out.print("> ");
            input = scanner.nextLine().split(" ");
            message = input[0];
            String filename = "_";
            if (input.length > 1) {
                filename = input[1].substring(1, input[1].length() - 1);
            }
            switch (message) {
                case "q" -> System.out.println("Exiting...");
                case "status" -> status();
                case "commit" -> commit();
                case "info" -> info(filename);
                default -> System.out.println("Invalid command.");
            }
        }
    }

    private void status() {
        this.prevList = Arrays.copyOf(this.currList, this.currList.length);
        this.currList = new String[0];
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)){
            for (Path file: directoryStream) {
                String fileName = file.getFileName().toString();
                this.currList = pushToArray(this.currList, fileName);
                if (!contains(this.prevList, fileName)) {
                    String extension = fileName.substring(fileName.indexOf('.') );
                    String path = directory+"/"+fileName;
                    switch (extension) {
                        case ".jpeg", ".jpg", ".png" -> files.add(new ImageFile(fileName, extension, path));
                        case ".txt" -> files.add(new TextFile(fileName, extension, path));
                        case ".java" -> files.add(new ProgramFile(fileName, extension, path));
                        default -> files.add(new File(fileName, extension, path));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read directory.");
        }
        for (File file : files) {
            String filename = file.getFilename();
            System.out.print(filename);
            if (contains(this.prevList, filename) && contains(this.currList, filename)) {
                if (file.isModified()) {
                    System.out.println(" - Changed");
                } else {
                    System.out.println(" - Unchanged");
                }
            } else if (contains(this.prevList, filename)) {
                System.out.println(" - Deleted");
            } else {
                System.out.println(" - Added");
            }
        }
    }

    private void commit() {
        System.out.println("Commited at " + LocalDateTime.now());
        for (File file : files) {
            System.out.print(file.getFilename());
            if (file.isModified()) {
                System.out.println(" - Changed");
                file.updateState();
            } else {
                System.out.println(" - Unchanged");
            }
        }
    }

    private void info(String filename) {
        for (File file : files) {
            if (file.getFilename().equals(filename)) {
                String[] info = file.getInfo().split("/");
                for (int i = 0; i < info.length; i += 2) {
                    System.out.println(info[i] + ": " + info[i + 1]);
                }
                return;
            }
        }
        if (filename.equals("_")) {
            System.out.println("No file specified.");
        } else {
            System.out.println("File not found.");
        }
    }

    private String[] pushToArray(String[] array, String element) {
        String[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = element;
        return newArray;
    }

    private boolean contains(String[] array, String element) {
        for (String filename : array) {
            if (filename.equals(element)) {
                return true;
            }
        }
        return false;
    }
}
