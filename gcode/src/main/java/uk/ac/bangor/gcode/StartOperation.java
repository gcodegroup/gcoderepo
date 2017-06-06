package uk.ac.bangor.gcode;

/**
 * The StartOperation class represents the operation which switches on the
 * laser.
 *
 * @author zc
 */
public final class StartOperation extends AbstractGcodeOperation {

    /**
     * Construct a StartOperation.
     *
     * @param initialDelayTime - The initial delay time in seconds.
     */
    public StartOperation(int initialDelayTime) {

        super("CT0 3 7 0\n"
                + "DEL " + initialDelayTime + "\n"
                + "CTO 3 7 1");
    }
}
