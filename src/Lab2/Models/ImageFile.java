package Lab2.Models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageFile extends File{
    private int width;
    private int height;
    public ImageFile(String filename, String extension, String path) {
        super(filename, extension, path);
        this.getImageDimensions();
    }

    private void getImageDimensions() {
        try {
            BufferedImage image = ImageIO.read(super.getFile());
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (Exception e) {
            System.out.println("  Failed to read image dimensions.");
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nSize: " + this.width + "x" + this.height + "px";
    }
}
