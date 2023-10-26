package Lab2;

import Lab2.Models.File;
import Lab2.Models.ImageFile;
import Lab2.Models.ProgramFile;
import Lab2.Models.TextFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        String directory = "src/Lab2/Resources/MainDirectory";
        Path directoryPath = Paths.get(directory);

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

        for (File file : files) {
            System.out.println(file.getInfo());
        }
    }
}
