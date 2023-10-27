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
    private String[] currList;
    private String[] queueList;
    private List<File> deleteQueue;
    private List<File> resetQueue;
    private List<File> addQueue;

    public Git() {
        this.files = new ArrayList<>();
        this.directory = "src/Lab2/Resources/MainDirectory";
        this.directoryPath = Paths.get(this.directory);
        this.scanner = new Scanner(System.in);
        this.currList = new String[0];
        this.resetQueue = new ArrayList<>();
        this.deleteQueue = new ArrayList<>();
        this.addQueue = new ArrayList<>();
        this.queueList = new String[0];
    }
    public void run() {
        this.status();
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
                case "admin" -> displayFiles();
                default -> System.out.println("Invalid command.");
            }
        }
        scanner.close();
    }

    private void status() {
        String[] prevList = Arrays.copyOf(this.currList, this.currList.length);
        this.queueList = new String[0];
        this.addQueue.clear();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)){
            for (Path file: directoryStream) {
                String fileName = file.getFileName().toString();
                this.queueList = pushToArray(this.queueList, fileName);
                if (!contains(prevList, fileName)) {
                    String extension = fileName.substring(fileName.indexOf('.') );
                    String path = directory+"/"+fileName;
                    switch (extension) {
                        case ".jpeg", ".jpg", ".png" -> this.addQueue.add(new ImageFile(fileName, extension, path));
                        case ".txt" -> this.addQueue.add(new TextFile(fileName, extension, path));
                        case ".java" -> this.addQueue.add(new ProgramFile(fileName, extension, path));
                        default -> this.addQueue.add(new File(fileName, extension, path));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read directory.");
        }
        List<File> temp = new ArrayList<>();
        temp.addAll(this.files);
        temp.addAll(this.addQueue);
        for (File file : temp) {
            String filename = file.getFilename();
            System.out.print(filename);
            if (contains(prevList, filename) && contains(this.queueList, filename)) {
                if (file.isModified()) {
                    System.out.println(" - Changed");
                    this.resetQueue.add(file);
                } else {
                    System.out.println(" - Unchanged");
                }
            } else if (contains(prevList, filename)) {
                System.out.println(" - Deleted");
                this.deleteQueue.add(file);
            } else {
                System.out.println(" - Added");
            }
        }
    }

    private void commit() {
        System.out.println("Commited at " + LocalDateTime.now());
        this.status();
        for (File file : this.resetQueue) {
            file.updateState();
        }
        this.files.addAll(this.addQueue);
        this.files.removeAll(this.deleteQueue);
        resetQueue.clear();
        deleteQueue.clear();
        this.currList = Arrays.copyOf(this.queueList, this.queueList.length);
    }

    private void info(String filename) {
        List<File> temp = new ArrayList<>();
        temp.addAll(this.files);
        temp.addAll(this.addQueue);
        for (File file : temp) {
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

    private void displayFiles() {
        for (File file : this.files) {
           info(file.getFilename());
        }
    }
}
