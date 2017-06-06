package uk.ac.bangor.gcode;

/**
 * The GcodeException class is used when any exception or error needed to be
 * thrown out. It is a runtime exception so some checked exception can be
 * converted to this exception to allow some implemented method to by-pass its
 * restriction without handling the checked exception itself.
 *
 * @author zc
 */
public class GcodeException extends RuntimeException {

    private static final long serialVersionUID = 182728956914772071L;

    public GcodeException() {
    }

    public GcodeException(String message) {
        super(message);
    }

    public GcodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GcodeException(Throwable cause) {
        super(cause);
    }
}
