package uk.ac.bangor.gcode.gui;

import java.awt.Color;

/**
 * The ResultSavingMessageStatus enum defines the message status for the result saving operation.
 * @author zc
 */
public enum ResultSavingMessageStatus {

    NO_TRANSLATED_RESULT("No translated result to be saved", false, Color.BLUE, false),
    INVALID_OUTPUT_FILE_PATH("Invalid output file path", true, Color.RED, false),
    OUTPUT_FILE_EXISTS("The outp file already exists", true, Color.ORANGE, true),
    RESULT_NOT_SAVED("Result has NOT been saved", true, Color.ORANGE, true),
    RESULT_SAVED("Result has been saved", true, Color.GREEN, false);
    
    private final String message;
    private final boolean visible;
    private final Color color;
    private final boolean canBeProcessed;    

    private ResultSavingMessageStatus(String message,boolean visible, Color color, boolean canBeProcessed) {
        this.message = message;
        this.visible = visible;
        this.color = color;
        this.canBeProcessed = canBeProcessed;
    }

    public String getMessage() {
        return message;
    }

    public boolean isVisible() {
        return visible;
    }

    public Color getColor() {
        return color;
    }

    public boolean canBeProcessed() {
        return canBeProcessed;
    }
}
