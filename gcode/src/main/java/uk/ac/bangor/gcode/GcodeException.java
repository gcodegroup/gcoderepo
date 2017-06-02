package uk.ac.bangor.gcode;

/**
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
