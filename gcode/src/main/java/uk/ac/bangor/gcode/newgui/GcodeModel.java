package uk.ac.bangor.gcode.newgui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;

public class GcodeModel {

    public static final String INPUT_FILE_PATH_PROPERTY = "INPUT_FILE_PATH_PROPERTY";
    public static final String OUTPUT_FILE_PATH_PROPERTY = "OUTPUT_FILE_PATH_PROPERTY";
    public static final String ORIGINAL_TEXT_PROPERTY = "ORIGINAL_TEXT_PROPERTY";
    public static final String TRANSLATED_TEXT_PROPERTY = "TRANSLATED_TEXT_PROPERTY";
    public static final String SPEED_PROPERTY = "SPEED_PROPERTY";
    public static final String START_DELAY_TIME_PROPERTY = "START_DELAY_TIME_PROPERTY";

    private String inputFilePath;
    private String outputFilePath;
    private String originalText;
    private String translatedText;
    private List<String> lines;
    private int speed;
    private int startDelayTime;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public synchronized String getInputFilePath() {
        return inputFilePath;
    }

    public synchronized void setInputFilePath(String inputFilePath) {
        String oldValue = this.inputFilePath;
        this.inputFilePath = inputFilePath;
        propertyChangeSupport.firePropertyChange(INPUT_FILE_PATH_PROPERTY, oldValue, inputFilePath);
    }

    public synchronized String getOutputFilePath() {
        return outputFilePath;
    }

    public synchronized void setOutputFilePath(String outputFilePath) {
        String oldValue = this.outputFilePath;
        this.outputFilePath = outputFilePath;
        propertyChangeSupport.firePropertyChange(OUTPUT_FILE_PATH_PROPERTY, oldValue, outputFilePath);
    }

    public synchronized String getOriginalText() {
        return originalText;
    }

    public synchronized void setOriginalText(String originalText) {
        String oldValue = this.originalText;
        this.originalText = originalText;
        propertyChangeSupport.firePropertyChange(ORIGINAL_TEXT_PROPERTY, oldValue, originalText);
    }

    public synchronized String getTranslatedText() {
        return translatedText;
    }

    public synchronized void setTranslatedText(String translatedText) {
        String oldValue = this.translatedText;
        this.translatedText = translatedText;
        propertyChangeSupport.firePropertyChange(TRANSLATED_TEXT_PROPERTY, oldValue, translatedText);
    }

    public synchronized boolean isValidInputFilePath() {
        return new File(inputFilePath).isFile();
    }

    public synchronized boolean isValidTranslatedText() {
        return translatedText != null && !translatedText.trim().isEmpty();
    }

    public synchronized List<String> getLines() {
        return lines;
    }

    public synchronized void setLines(List<String> lines) {
        this.lines = lines;
    }

    public synchronized int getSpeed() {
        return speed;
    }

    public synchronized void setSpeed(int speed) {
        
        int oldValue = this.speed;
        this.speed = speed;
        propertyChangeSupport.firePropertyChange(SPEED_PROPERTY, oldValue, speed);        
    }

    public synchronized int getStartDelayTime() {
        return startDelayTime;
    }

    public synchronized void setStartDelayTime(int startDelayTime) {
        
        int oldValue = this.startDelayTime;
        this.startDelayTime = startDelayTime;
         propertyChangeSupport.firePropertyChange(START_DELAY_TIME_PROPERTY, oldValue, startDelayTime);       
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public synchronized boolean hasListeners(String propertyName) {
        return propertyChangeSupport.hasListeners(propertyName);
    }
}
