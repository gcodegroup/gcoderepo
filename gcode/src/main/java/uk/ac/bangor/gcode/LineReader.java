package uk.ac.bangor.gcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineReader {

    private List<String> lines = new ArrayList<>();
    private String allLines = "";

    public synchronized void load(String fileName) throws IOException {

        lines = new ArrayList<>();
        
        StringBuilder builder = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line = br.readLine();

            while (line != null) {
                lines.add(line);
                builder.append(line).append("\n");
                line = br.readLine();
            }
        }
        allLines = new String(builder).trim();
    }

    public synchronized List<String> getLines() {

        return Collections.unmodifiableList(lines);
    }

    public synchronized String getAllLines() {
        return allLines;
    }
    
    
}
