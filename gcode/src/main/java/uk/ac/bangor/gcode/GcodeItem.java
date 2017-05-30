package uk.ac.bangor.gcode;

/**
 * The GcodeItem interface defines an item which represent the operation to be
 * proceeded in the following step.
 *
 * @author zc
 */
public interface GcodeItem {

    /**
     * Get the string description for the status. This string will be used as a
     * unit code in the 3D printer.
     *
     * @return the description string.
     */
    String getString();
}
