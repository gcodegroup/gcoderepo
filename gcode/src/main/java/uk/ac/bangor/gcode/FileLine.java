package uk.ac.bangor.gcode;

import java.util.Objects;

/**
 * The FileLine class holds the information about a line in the gcode file.
 *
 * @author zc
 */
public final class FileLine {

    private final String lineString;
    private final LineType lineStatus;

    public FileLine(String line) {

        lineString = line == null ? null : (line.contains(";") ? line.split(";")[0].trim() : line.trim());

        if (lineString == null || lineString.isEmpty()) {
            lineStatus = LineType.UNUSED_LINE;
            return;
        }
        
        if (lineString.contains("G0") && !lineString.contains("E") && lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && lineString.contains("Z")) {
            lineStatus = LineType.G0_FXYZ_LINE;
            return;
        }

        if (lineString.contains("G0") && !lineString.contains("E")  && lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && !lineString.contains("Z")) {
            lineStatus = LineType.G0_FXY_LINE;
            return;
        }

        if (lineString.contains("G0") && !lineString.contains("E")  && !lineString.contains("F") && lineString.contains("X") && lineString.contains("Y")) {
            lineStatus = LineType.G0_XY_LINE;
            return;
        }        
        
        if (lineString.contains("G1") && lineString.contains("E") && lineString.contains("F") &&  !lineString.contains("X") && !lineString.contains("Y") && !lineString.contains("Z")) {
            lineStatus = LineType.G1_EF_LINE;
            return;
        }
        
        if (lineString.contains("G1") && lineString.contains("E") && lineString.contains("F") && lineString.contains("X") &&  lineString.contains("Y") &&  !lineString.contains("Z")) {
            lineStatus = LineType.G1_EFXY_LINE;
            return;
        }
        
        if (lineString.contains("G1") && lineString.contains("E") && !lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && !lineString.contains("Z")) {
            lineStatus = LineType.G1_EXY_LINE;
            return;
        }        

        if (lineString.contains("G1") && !lineString.contains("E") && !lineString.contains("T") && lineString.contains("X") && lineString.contains("Y") && !lineString.contains("Z")) {
            lineStatus = LineType.G1_XY_LINE;
            return;
        }          
        
        
        if (lineString.contains("G1") && lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && !lineString.contains("Z")) {
            lineStatus = LineType.G1_FXYZ_LINE;
            return;
        }        
        
        lineStatus = LineType.UNUSED_LINE;
    }

    /**
     * Get the string content of the line.
     *
     * @return line string.
     */
    public String getLineString() {
        return lineString;
    }

    /**
     * Get the line status. This method will not return null. A line with the null string content is treated as an unused line. 
     *
     * @return the LineType object.
     */
    public LineType getLineStatus() {
        return lineStatus;
    }

    @Override
    public int hashCode() {
        return 89 * 5 + Objects.hashCode(this.lineString);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final FileLine other = (FileLine) obj;

        return Objects.equals(this.lineString, other.lineString);
    }
}
