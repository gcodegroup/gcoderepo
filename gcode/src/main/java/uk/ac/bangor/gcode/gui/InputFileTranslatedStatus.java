package uk.ac.bangor.gcode.gui;

import java.awt.Color;

/**
 *
 * @author zc
 */
public enum InputFileTranslatedStatus {

    NO_INPUT_FILE("", false, Color.WHITE, false),
    INPUT_FILE_NOT_TRANSLATED ("The input file has NOT been translated", true, Color.ORANGE, true),
    INVALID_INPUT_FILE ("Invalid input file", true, Color.RED, false),
    INPUT_FILE_TRANSLATED ("The input file has been translated", true, Color.GREEN, false);

    private final String message;
    private final boolean visible;
    private final Color color;
    private final boolean canBeProcessed;

    private InputFileTranslatedStatus(String message, boolean visible, Color color, boolean canBeProcessed) {
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
