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
    public static final String INPUT_FILE_PATH_STATUS_PROPERTY = "INPUT_FILE_PATH_STATUS_PROPERTY";
    public static final String INPUT_FILE_TRANSLATED_STATUS_PROPERTY = "INPUT_FILE_TRANSLATED_PROPERTY";
    public static final String RESULT_SAVED_STATUS_PROPERTY = "RESULT_SAVED_PROPERTY";

    private final RunningParameters runningParameters = RunningParameters.getInstance();
    
    private String inputFilePath;
    private GcodeFile gcodeFile;  
    private String translatedText;    
    private String outputFilePath;
    private int movingSpeed = runningParameters.getMovingSpeed();
    private int initialDelayTime = runningParameters.getInitialDelayTime();
    private InputFilePathStatus inputFilePathStatus = InputFilePathStatus.EMPTY_INPUT_FILE_PATH;
    private InputFileTranslatedStatus inputFileStatus = InputFileTranslatedStatus.NO_INPUT_FILE;
    private MainStatus mainStatus = MainStatus.NO_TRANSLATED_RESULT;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public synchronized String getInputFilePath() {
        return inputFilePath;
    }

    public synchronized void setInputFilePath(String inputFilePath) {
        
        String oldValue = this.inputFilePath;
        this.inputFilePath = inputFilePath;
        inputFilePathStatus = (inputFilePath == null || inputFilePath.trim().isEmpty()) ? 
                InputFilePathStatus.EMPTY_INPUT_FILE_PATH : (new File(inputFilePath).isFile() ? 
                (inputFilePath.toLowerCase().endsWith(".gcode") ? InputFilePathStatus.VALID_INPUT_FILE_PATH : InputFilePathStatus.POSSIBLE_WRONG_TYPE_FILE) 
               : InputFilePathStatus.INVALID_INPUT_FILE_PATH);
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

    public synchronized int getMovingSpeed() {
        return movingSpeed;
    }

    public synchronized void setMovingSpeed(int movingSpeed) {

        int oldValue = this.movingSpeed;
        this.movingSpeed = movingSpeed;
        RunningParameters.getInstance().setMovingSpeed(movingSpeed);
        propertyChangeSupport.firePropertyChange(SPEED_PROPERTY, oldValue, movingSpeed);
    }

    public synchronized int getInitialDelayTime() {
        return initialDelayTime;
    }

    public synchronized void setInitialDelayTime(int initialDelayTime) {

        int oldValue = this.initialDelayTime;
        this.initialDelayTime = initialDelayTime;
        runningParameters.setInitialDelayTime(initialDelayTime);
        propertyChangeSupport.firePropertyChange(START_DELAY_TIME_PROPERTY, oldValue, initialDelayTime);
    }

    public synchronized void setResultSaved(boolean resultSaved) {
        
        mainStatus = createMainMessageStatus(resultSaved);
        propertyChangeSupport.firePropertyChange(RESULT_SAVED_STATUS_PROPERTY, true, false);        
    }


    public synchronized InputFilePathStatus getInputFilePathStatus() {
        return inputFilePathStatus;
    }

    public synchronized MainStatus getMainStatus() {
        return mainStatus;
    }

    public synchronized String getDefaultInputFilePath() {
        
        return runningParameters.getInputFilePath();
    }
    
    public synchronized String getDefaultOutputFilePath() {
        
        return runningParameters.getOutputFilePath();
    }
    
    public synchronized void setInputFileTranslated(boolean translated) {
        
        InputFileTranslatedStatus oldValue = inputFileStatus;
        
        if(gcodeFile == null || gcodeFile.getFileString() == null || gcodeFile.getFileString().trim().isEmpty()) {
            inputFileStatus = InputFileTranslatedStatus.NO_INPUT_FILE;
        } else {
            if(translated) {
                inputFileStatus = InputFileTranslatedStatus.INPUT_FILE_TRANSLATED;
            } else {
                inputFileStatus = InputFileTranslatedStatus.INPUT_FILE_NOT_TRANSLATED;
            }
        }
        
        propertyChangeSupport.firePropertyChange(INPUT_FILE_TRANSLATED_STATUS_PROPERTY, oldValue, inputFileStatus);           
    }

    public synchronized InputFileTranslatedStatus getInputFileStatus() {
        return inputFileStatus;
    }
    
    private MainStatus createMainMessageStatus(boolean resultSaved) {

        if(translatedText == null || translatedText.trim().isEmpty()) {
            return MainStatus.NO_TRANSLATED_RESULT;
        }
        
        if(outputFilePath == null || new File(outputFilePath).isDirectory()) {
            return MainStatus.INVALID_OUTPUT_FILE_PATH;

        }
        
        return resultSaved ? MainStatus.RESULT_SAVED : (new File(outputFilePath).isFile() ? MainStatus.OUTPUT_FILE_EXISTS : MainStatus.RESULT_NOT_SAVED);
    }
    
    public synchronized boolean isParametersOkToBeSaved() {
        
        return inputFilePathStatus.canBeProcessed() && mainStatus.canBeProcessed();
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
