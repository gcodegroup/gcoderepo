package uk.ac.bangor.gcode;

import java.io.IOException;
import java.io.PrintWriter;

public class OutputFileWriter {

    public void write(String outPutFilePath, String result) {
        try (PrintWriter writer = new PrintWriter(outPutFilePath, "UTF-8")) {
            writer.println(result);
            writer.flush();
        } catch (IOException ex) {
            //TODO
        }
    }
}
