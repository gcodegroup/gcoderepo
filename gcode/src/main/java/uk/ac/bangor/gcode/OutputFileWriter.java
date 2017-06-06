package uk.ac.bangor.gcode;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * The OutputFileWriter class contains a method to write the result to a file.
 *
 * @author zc
 */
public final class OutputFileWriter {

    /**
     * Write the result to the given file.
     * @param outPutFilePath - The output file path.
     * @param result - The result to be written.
     */
    public void write(String outPutFilePath, String result) {
        try (PrintWriter writer = new PrintWriter(outPutFilePath, "UTF-8")) {
            writer.println(result);
            writer.flush();
        } catch (IOException ex) {
            throw new GcodeException(ex);
        }
    }
}
