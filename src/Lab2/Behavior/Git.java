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
    private String[] queueList;
    private List<File> deleteQueue;
    private List<File> resetQueue;
    private List<File> addQueue;
    private boolean isChecking;

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
        this.prevList = new String[0];
        this.isChecking = true;
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
                case "commit" -> manualCommit();
                case "info" -> info(filename);
                case "admin" -> displayFiles();
                default -> System.out.println("Invalid command.");
            }
        }
        this.isChecking = false;
        scanner.close();
    }

    public void backgroundRun() {
        this.checkDirectory();
        List<File> temp = new ArrayList<>();
        temp.addAll(this.files);
        temp.addAll(this.addQueue);
        boolean foundChange = false;
        String info = "";
        for (File file : temp) {
            String filename = file.getFilename();
            info = info.concat(filename);
            if (contains(prevList, filename) && contains(this.queueList, filename)) {
                if (file.isModified()) {
                    info = info.concat(" - Changed\n");
                    foundChange = true;
                    this.resetQueue.add(file);
                } else {
                    info = info.concat(" - Unchanged\n");
                }
            } else if (contains(prevList, filename)) {
                info = info.concat(" - Deleted\n");
                foundChange = true;
                this.deleteQueue.add(file);
            } else {
                info = info.concat(" - Added\n");
                foundChange = true;
            }
        }
        if (foundChange) {
            System.out.println("Auto commit at " + LocalDateTime.now() + ".");
            System.out.println(info);
            System.out.print("> ");
        }
        commit();
    }

    private void status() {
        this.checkDirectory();
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
        for (File file : this.resetQueue) {
            file.updateState();
        }
        this.files.addAll(this.addQueue);
        this.files.removeAll(this.deleteQueue);
        this.resetQueue.clear();
        this.deleteQueue.clear();
        this.currList = Arrays.copyOf(this.queueList, this.queueList.length);
    }

    private void manualCommit() {
        System.out.println("Commited at " + LocalDateTime.now() + ".");
        this.status();
        this.commit();
    }

    private void checkDirectory() {
        this.prevList = Arrays.copyOf(this.currList, this.currList.length);
        this.queueList = new String[0];
        this.addQueue.clear();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)){
            for (Path file: directoryStream) {
                String fileName = file.getFileName().toString();
                this.queueList = pushToArray(this.queueList, fileName);
                if (!contains(this.prevList, fileName)) {
                    String extension = fileName.substring(fileName.indexOf('.') );
                    String path = directory+"/"+fileName;
                    switch (extension) {
                        case ".jpeg", ".jpg", ".png" -> this.addQueue.add(new ImageFile(fileName, extension, path));
                        case ".txt" -> this.addQueue.add(new TextFile(fileName, extension, path));
                        case ".java", ".py" -> this.addQueue.add(new ProgramFile(fileName, extension, path));
                        default -> this.addQueue.add(new File(fileName, extension, path));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read directory.");
        }
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
