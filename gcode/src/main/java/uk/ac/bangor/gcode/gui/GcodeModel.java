package uk.ac.bangor.gcode.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import uk.ac.bangor.gcode.GcodeFile;
import uk.ac.bangor.gcode.RunningParameters;

public class GcodeModel {

    public static final String INPUT_FILE_PATH_PROPERTY = "INPUT_FILE_PATH_PROPERTY";
    public static final String OUTPUT_FILE_PATH_PROPERTY = "OUTPUT_FILE_PATH_PROPERTY";
    public static final String GCODE_FILE_PROPERTY = "GCODE_FILE_PROPERTY";
    public static final String TRANSLATED_TEXT_PROPERTY = "TRANSLATED_TEXT_PROPERTY";
    public static final String SPEED_PROPERTY = "SPEED_PROPERTY";
    public static final String START_DELAY_TIME_PROPERTY = "START_DELAY_TIME_PROPERTY";
    public static final String RESULT_SAVED_PROPERTY = "RESULT_SAVED_PROPERTY";

    private String inputFilePath;
    private String outputFilePath;
    private String translatedText;
    private GcodeFile gcodeFile;
    private int speed;
    private int startDelayTime;
    private InputFilePathStatus inputFilePathStatus = InputFilePathStatus.OK;
    private boolean resultSaved;
    private MainStatus mainStatus = MainStatus.NO_INPUT_FILE;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public synchronized String getInputFilePath() {
        return inputFilePath;
    }

    public synchronized void setInputFilePath(String inputFilePath) {
        
        String oldValue = this.inputFilePath;
        this.inputFilePath = inputFilePath;
        inputFilePathStatus = new File(inputFilePath).isFile() ? InputFilePathStatus.OK : InputFilePathStatus.INVALID_INPUT_FILE_PATH;
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

    public synchronized String getTranslatedText() {
        return translatedText;
    }

    public synchronized void setTranslatedText(String translatedText) {
        String oldValue = this.translatedText;
        this.translatedText = translatedText;
        mainStatus = createMainMessageStatus();
        propertyChangeSupport.firePropertyChange(TRANSLATED_TEXT_PROPERTY, oldValue, translatedText);
    }

    public GcodeFile getGcodeFile() {
        return gcodeFile;
    }

    public void setGcodeFile(GcodeFile gcodeFile) {

        GcodeFile oldValue = this.gcodeFile;
        this.gcodeFile = gcodeFile;
        propertyChangeSupport.firePropertyChange(GCODE_FILE_PROPERTY, oldValue, gcodeFile);
    }

    public synchronized int getSpeed() {
        return speed;
    }

    public synchronized void setSpeed(int speed) {

        int oldValue = this.speed;
        this.speed = speed;
        RunningParameters.getInstance().setMovingSpeed(speed);
        propertyChangeSupport.firePropertyChange(SPEED_PROPERTY, oldValue, speed);
    }

    public synchronized int getStartDelayTime() {
        return startDelayTime;
    }

    public synchronized void setStartDelayTime(int startDelayTime) {

        int oldValue = this.startDelayTime;
        this.startDelayTime = startDelayTime;
        RunningParameters.getInstance().setInitialDelayTime(startDelayTime);
        propertyChangeSupport.firePropertyChange(START_DELAY_TIME_PROPERTY, oldValue, startDelayTime);
    }

    public synchronized boolean isResultSaved() {
        return resultSaved;
    }

    public synchronized void setResultSaved(boolean resultSaved) {
        
        boolean oldValue = this.resultSaved;
        this.resultSaved = resultSaved;
        mainStatus = createMainMessageStatus();
        propertyChangeSupport.firePropertyChange(RESULT_SAVED_PROPERTY, oldValue, resultSaved);        
    }


    public synchronized InputFilePathStatus getInputFilePathStatus() {
        return inputFilePathStatus;
    }

    public synchronized MainStatus getMainStatus() {
        return mainStatus;
    }

    private MainStatus createMainMessageStatus() {

        if(gcodeFile == null || gcodeFile.getFileString() == null || gcodeFile.getFileString().isEmpty()) {
            return MainStatus.NO_INPUT_FILE;
        }
        
        if(outputFilePath == null || new File(outputFilePath).isDirectory()) {
            return MainStatus.INVALID_OUTPUT_FILE_PATH;

        }
        
        if( new File(outputFilePath).isFile()) {
            return MainStatus.OUTPUT_FILE_EXISTS;

        }
        
        return resultSaved ? MainStatus.RESULT_SAVED : MainStatus.RESULT_NOT_SAVED;
    }
    
    public synchronized boolean isValidOriginalText() {
        return gcodeFile != null && gcodeFile.getFileString() != null && !gcodeFile.getFileString().trim().isEmpty();
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
