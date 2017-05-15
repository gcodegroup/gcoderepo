package uk.ac.bangor.gcode;

import java.util.ArrayList;
import java.util.List;

public class GcodeTranslator {
    
    private String result = "Not translated";

    public synchronized String getResult() {
        return result;
    }
    
    public synchronized void reset() {
        result = "Not translated";
    }
    
    public synchronized void translate(List<String> lines) {

        List<GcodeItem> items = new ArrayList<>();
        
        Point point1 = null;
        Point point2;
        
        for (String line : lines) {
            LineStatus lineStatus = getLineStatus(line);

            switch (lineStatus) {

                case GFXYE_LINE:
                    items.add(new StartItem());
                    point1 = new Point(line);
                    break;
                case GXYE_LINE:
                    
                    if(point1 != null) {
                        point2 = new Point(line);
                        items.add(new IntermediateItem(point1, point2, RunningParameters.getSpeed()));
                        point1 = point2; 
                    } else {
                        point1 = new Point(line);
                    }

                    break;
                    
                case GFXY_LINE:
                    items.add(new TerminalItem());
                    point1 = null;
                    break;
                default:    //Do nothing for the rest.
                }
        }

        StringBuilder builder = new StringBuilder();
        
        items.stream().forEach((item) -> {
            builder.append(item.getString()).append("\n");
        });
        
        result = new String(builder).trim();
    }

    private LineStatus getLineStatus(String line) {

        if(line == null ) {
            return LineStatus.UNUSED_LINE;
        }
        
        if(line.trim().isEmpty())
        {
            return LineStatus.EMPTY;
        }
        
        if(line.startsWith(";")) {
            return LineStatus.COMMENT;
        }
            
        
        
        if(line.contains("G") && !line.contains("F") && line.contains("X") && line.contains("Y") && line.contains("E")) {
            return LineStatus.GXYE_LINE;
        }   
        
        if(line.contains("G") && line.contains("F") && line.contains("X") && line.contains("Y") && line.contains("E")) {
            return LineStatus.GFXYE_LINE;
        }

        if(line.contains("G") && line.contains("F") && line.contains("X") && line.contains("Y") && !line.contains("E")) {
            return LineStatus.GFXY_LINE;
        }     
           
        return LineStatus.UNUSED_LINE;    
    }
}
