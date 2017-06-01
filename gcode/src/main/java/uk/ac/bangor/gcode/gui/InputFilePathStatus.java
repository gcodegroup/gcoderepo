package uk.ac.bangor.gcode.gui;

import java.awt.Color;

public enum InputFilePathStatus {

    
    EMPTY_PATH("", false, Color.WHITE, false),
    OK("OK", false, Color.WHITE, true), 
    POSSIBLE_WRONG_TYPE("The file may be invalid", true, Color.ORANGE, true),
    INVALID_INPUT_FILE_PATH("Invalid input file path", true, Color.RED, false);
    
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
