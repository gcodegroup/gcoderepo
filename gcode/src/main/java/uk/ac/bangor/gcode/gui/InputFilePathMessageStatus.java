package uk.ac.bangor.gcode.gui;

import java.awt.Color;

/**
 * The InputFilePathMessageStatus enum defines the message status for the input file path.
 *
 * @author zc
 */
public enum InputFilePathMessageStatus {

    /**
     * No input file path provided.
     */
    EMPTY_INPUT_FILE_PATH("Empty", false, Color.WHITE, false),
    
    /**
     * The file does NOT have the extension of ".gcode". It may be point to an
     * invalid file.
     */
    POSSIBLE_WRONG_TYPE_FILE("The file may be invalid", true, Color.ORANGE, true),
    
    /**
     * The input file does not exist.
     */
    FILE_DOES_NOT_EXIST("The input file does not exist", true, Color.RED, false),
    
    /**
     * The input file is valid.
     */
    VALID_INPUT_FILE_PATH("OK", false, Color.WHITE, true);

    private final String message;
    private final boolean visible;
    private final Color color;
    private final boolean canBeProcessed;

    private InputFilePathMessageStatus(String message, boolean visible, Color color, boolean canBeProcessed) {
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
