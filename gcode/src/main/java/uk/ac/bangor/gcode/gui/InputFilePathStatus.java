package uk.ac.bangor.gcode.gui;

import java.awt.Color;

public enum InputFilePathStatus {

    
    EMPTY_INPUT_FILE_PATH("", false, Color.WHITE, false), 
    POSSIBLE_WRONG_TYPE_FILE("The file may be invalid", true, Color.ORANGE, true),
    INVALID_INPUT_FILE_PATH("Invalid input file path", true, Color.RED, false),
    VALID_INPUT_FILE_PATH("", false, Color.WHITE, true);
          
    private final String message;
    private final boolean visible;
    private final Color color;
    private final boolean canBeProcessed;

    private InputFilePathStatus(String message,boolean visible, Color color, boolean canBeProcessed) {
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
