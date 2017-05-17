package uk.ac.bangor.gcode;

import java.util.Objects;

/**
 * The FileLine class holds the information about a line in the gcode file.
 * 
 * @author zc
 */
public final class FileLine {

    private final String lineString;
    private final LineStatus lineStatus;

    public FileLine(String lineString) {
        this.lineString = lineString;

        if (lineString == null) {
            lineStatus = LineStatus.UNUSED_LINE;
            return;
        }

        if (lineString.startsWith(";")) {
            lineStatus = LineStatus.COMMENT;
            return;
        }

        if (lineString.contains("G") && !lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && lineString.contains("E")) {
            lineStatus = LineStatus.GXYE_LINE;
            return;
        }

        if (lineString.contains("G") && lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && lineString.contains("E")) {
            lineStatus = LineStatus.GFXYE_LINE;
            return;
        }

        if (lineString.contains("G") && lineString.contains("F") && lineString.contains("X") && lineString.contains("Y") && !lineString.contains("E")) {
            lineStatus = LineStatus.GFXY_LINE;
            return;
        }

        lineStatus = LineStatus.UNUSED_LINE;
    }

    public String getLineString() {
        return lineString;
    }

    public LineStatus getLineStatus() {
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
