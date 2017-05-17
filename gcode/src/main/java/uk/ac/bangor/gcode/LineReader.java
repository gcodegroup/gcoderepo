package uk.ac.bangor.gcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {

    public GcodeFile load(String inputFilePath) throws IOException {

        List<FileLine> lines = new ArrayList<>();
        
        StringBuilder builder = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {

            String line = br.readLine();

            while (line != null) {
                lines.add(new FileLine(line));
                builder.append(line).append("\n");
                line = br.readLine();
            }
        }
        return new GcodeFile(new String(builder).trim(), lines);
    }    
}
