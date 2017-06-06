package uk.ac.bangor.gcode;

/**
 * The TerminateOperation class represents an operation which switches off the
 * laser.
 *
 * @author zc
 */
public final class TerminateOperation extends AbstractGcodeOperation {

    /**
     * Construct an object with the given initial delay time.
     *
     * @param initialDelayTime - The initial delay time in seconds.
     */
    public TerminateOperation(int initialDelayTime) {
        super("CTO 1 7 0\n"
                + "DEL " + initialDelayTime + "\n"
                + "CTO 1 7 1");
    }
}
