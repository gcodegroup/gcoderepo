package uk.ac.bangor.gcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * The LineReader class reads all lines from an input file.
 *
 * @author zc
 */
public class LineReader {

    /**
     * Load the input file.
     *
     * @param inputFilePath - The absolute path of the input file.
     * @return the GcodeFile object which contains information of the input file.
     * @throws IOException if an IO error occurs.
     */
    public GcodeFile load(String inputFilePath) throws IOException {

        Logger logger = GcodeRepositoryManager.getInstance().getLogger(getClass());
        
        List<FileLine> lines = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {

            String line = br.readLine();

            while (line != null) {
                
                FileLine fileLine = new FileLine(line);
                String validLine = fileLine.getLineString();
                if(validLine != null && !validLine.trim().isEmpty()) {
                    lines.add(fileLine);
            //        logger.debug("Added to process: " + line);
                } else {
                    logger.debug("Dropped line:     " + line);
                }

                builder.append(line).append("\n");
                line = br.readLine();
            }
        }

        return new GcodeFile(new String(builder).trim(), lines);
    }
}
