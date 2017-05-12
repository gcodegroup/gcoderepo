package uk.ac.bangor.gcode.newgui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class GcodeModel {

    public static final String INPUT_FILE_PATH_PROPERTY = "INPUT_FILE_PATH_PROPERTY";
    public static final String OUTPUT_FILE_PATH_PROPERTY = "OUTPUT_FILE_PATH_PROPERTY";
    public static final String ORIGINAL_TEXT_PROPERTY = "ORIGINAL_TEXT_PROPERTY";
    public static final String TRANSLATED_TEXT_PROPERTY = "TRANSLATED_TEXT_PROPERTY";

    private String inputFilePath;
    private String outputFilePath;
    private String originalText;
    private String translatedText;

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
