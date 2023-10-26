package Lab2.Models;

public class TextFile extends File {
    private int lineCount;
    private int wordCount;
    private int characterCount;
    public TextFile(String filename, String extension, String path) {
        super(filename, extension, path);
        this.count();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "/Lines/" + this.lineCount + "/Words/" + this.wordCount + "/Characters/" + this.characterCount;
    }

    private void count() {
        try {
            java.io.FileReader fileReader = new java.io.FileReader(super.getFile());
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(fileReader);
            int lines = 0;
            int characters = 0;
            int words = 0;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines++;
                characters += line.length();
                String[] wordsInLine = line.split("\\s+");
                words += wordsInLine.length;
            }

            this.lineCount = lines;
            this.characterCount = characters;
            this.wordCount = words;
        } catch (Exception e) {
            System.out.println("Failed to count lines, characters, and words.");
        }
    }
}
