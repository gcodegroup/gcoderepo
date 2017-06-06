package uk.ac.bangor.gcode;

/**
 * The GcodeOperation interface defines an operation to be proceeded.
 *
 * @author zc
 */
public interface GcodeOperation {

    /**
     * Get the string description for the status. This string will be used as a
     * unit code in the 3D printer.
     *
     * @return the description string.
     */
    String getString();
}
