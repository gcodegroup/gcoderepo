package uk.ac.bangor.gcode;

/**
 * The TerminateOperation class represents an operation which switches off the
 * laser.
 *
 * @author zc
 */
public final class TerminateOperation extends AbstractGcodeOperation {

    public TerminateOperation(int initialDelayTime) {
        super("CTO 1 7 0\n"
                + "DEL " + initialDelayTime + "\n"
                + "CTO 1 7 1");
    }
}
