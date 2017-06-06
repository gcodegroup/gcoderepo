package uk.ac.bangor.gcode;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The GcodeFile class holds all the information of an input file.
 *
 * @author zc
 */
public class GcodeFile {

    private final String fileString;
    private final List<FileLine> fileLines;

    public GcodeFile(String fileString, List<FileLine> fileLines) {
        this.fileString = fileString;
        this.fileLines = Collections.unmodifiableList(fileLines);
    }

    /**
     * Get the file contents as a single string.
     *
     * @return the file contents as a single string.
     */
    public String getFileString() {
        return fileString;
    }

    /**
     * Get a list of lines inside this file.
     *
     * @return the list of FileLine objects.
     */
    public List<FileLine> getFileLines() {
        return fileLines;
    }

    @Override
    public int hashCode() {

        return 31 * 5 + Objects.hashCode(this.fileString);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final GcodeFile other = (GcodeFile) obj;
        return Objects.equals(this.fileString, other.fileString);
    }
}
