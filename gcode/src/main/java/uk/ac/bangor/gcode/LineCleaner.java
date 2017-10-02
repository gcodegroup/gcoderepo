package uk.ac.bangor.gcode;

/**
 *
 * @author Administrator
 */
public class LineCleaner {
    
    
    public String getCleanLine(String line) {
       return line.replaceFirst("G01", "G1").replaceFirst("G00", "G0").replaceFirst("X ", "X").replaceFirst("Y ", "Y").replaceFirst("Z ", "Z").replaceFirst("F ", "F");
    }
}
