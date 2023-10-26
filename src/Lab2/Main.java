package Lab2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path directoryPath = Paths.get("src/Lab2/Resources/MainDirectory");

        // Check if the specified path is a directory
        if (Files.isDirectory(directoryPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
                for (Path file : directoryStream) {
                    String fileName = file.getFileName().toString();
                    String fileType = Files.probeContentType(file);

                    if (fileType == null) {
                        fileType = "Unknown";
                    }

                    System.out.println("File: " + fileName + ", Type: " + fileType);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }
    }
}
