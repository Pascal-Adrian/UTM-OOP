package Lab1.Behavior;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogManager {
    private LocalDateTime operationTime;
    private String log;

    public void addLog(String[] data) {
        operationTime = LocalDateTime.now();
        this.log = "[";
        this.log = this.log.concat(operationTime.toString());
        this.log = this.log.concat("]");
        addSpaces(5);
        this.log = this.log.concat(data[0]);
        addSpaces(21 - data[0].length());
        this.log = this.log.concat("[App response]-> ");
        this.log = this.log.concat(data[2]);
        addSpaces(100 - data[2].length());
        this.log = this.log.concat("[Console input]-> ");
        this.log = this.log.concat(data[1]);
        addSpaces(80 - data[1].length());
        this.log = this.log.concat(System.lineSeparator());
        try {
            FileWriter myWriter = new FileWriter("src/Lab1/Resources/LogFile.txt", true);
            myWriter.write(this.log);
            myWriter.close();
        } catch (Exception e) {
            System.out.println("[LogManager Error]");
        }
    }

    private void addSpaces(int number) {
        for (int i = 0; i < number; i++) {
            this.log = this.log.concat(" ");
        }
    }
}
