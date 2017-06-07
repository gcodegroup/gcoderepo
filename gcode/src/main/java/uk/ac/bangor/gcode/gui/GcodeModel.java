package uk.ac.bangor.gcode.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import uk.ac.bangor.gcode.GcodeFile;
import uk.ac.bangor.gcode.GcodeRepositoryManager;
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
    private InputFilePathMessageStatus inputFilePathMessageStatus = InputFilePathMessageStatus.EMPTY_INPUT_FILE_PATH;
    private InputFileTranslationMessageStatus inputFileTranslationMessageStatus = InputFileTranslationMessageStatus.NO_INPUT_FILE;
    private ResultSavingMessageStatus resultSavingMessageStatus = ResultSavingMessageStatus.NO_TRANSLATED_RESULT;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public synchronized String getInputFilePath() {
        return inputFilePath;
    }

    public synchronized void setInputFilePath(String inputFilePath) {
        
        String oldValue = this.inputFilePath;
        this.inputFilePath = inputFilePath;
        inputFilePathMessageStatus = (inputFilePath == null || inputFilePath.trim().isEmpty()) ? 
                InputFilePathMessageStatus.EMPTY_INPUT_FILE_PATH : (new File(inputFilePath).isFile() ? 
                (inputFilePath.toLowerCase().endsWith(".gcode") ? InputFilePathMessageStatus.VALID_INPUT_FILE_PATH : InputFilePathMessageStatus.POSSIBLE_WRONG_TYPE_FILE) 
               : InputFilePathMessageStatus.FILE_DOES_NOT_EXIST);
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
        
        resultSavingMessageStatus = createMainMessageStatus(resultSaved);
        propertyChangeSupport.firePropertyChange(RESULT_SAVED_STATUS_PROPERTY, true, false);        
    }


    public synchronized InputFilePathMessageStatus getInputFilePathMessageStatus() {
        return inputFilePathMessageStatus;
    }

    public synchronized ResultSavingMessageStatus getResultSavingMessageStatus() {
        return resultSavingMessageStatus;
    }

    public synchronized String getDefaultInputFilePath() {
        
        return runningParameters.getInputFilePath();
    }
    
    public synchronized String getDefaultOutputFilePath() {
        
        return runningParameters.getOutputFilePath();
    }
    
    public synchronized void setInputFileTranslated(boolean translated) {
        
        InputFileTranslationMessageStatus oldValue = inputFileTranslationMessageStatus;
        
        if(gcodeFile == null || gcodeFile.getFileString() == null || gcodeFile.getFileString().trim().isEmpty()) {
            inputFileTranslationMessageStatus = InputFileTranslationMessageStatus.NO_INPUT_FILE;
        } else {
            if(translated) {
                inputFileTranslationMessageStatus = InputFileTranslationMessageStatus.INPUT_FILE_TRANSLATED;
            } else {
                inputFileTranslationMessageStatus = InputFileTranslationMessageStatus.INPUT_FILE_NOT_TRANSLATED;
            }
        }
        
        propertyChangeSupport.firePropertyChange(INPUT_FILE_TRANSLATED_STATUS_PROPERTY, oldValue, inputFileTranslationMessageStatus);           
    }

    public synchronized InputFileTranslationMessageStatus getInputFileTranslationMessageStatus() {
        return inputFileTranslationMessageStatus;
    }
    
    private ResultSavingMessageStatus createMainMessageStatus(boolean resultSaved) {

        if(translatedText == null || translatedText.trim().isEmpty()) {
            return ResultSavingMessageStatus.NO_TRANSLATED_RESULT;
        }
        
        if(outputFilePath == null || new File(outputFilePath).isDirectory()) {
            return ResultSavingMessageStatus.INVALID_OUTPUT_FILE_PATH;

        }
        
        return resultSaved ? ResultSavingMessageStatus.RESULT_SAVED : (new File(outputFilePath).isFile() ? ResultSavingMessageStatus.OUTPUT_FILE_EXISTS : ResultSavingMessageStatus.RESULT_NOT_SAVED);
    }
    
    public synchronized boolean isParametersOkToBeSaved() {
        
        return inputFilePathMessageStatus.canBeProcessed() && outputFilePath != null && !outputFilePath.trim().isEmpty();
    }
    
    public synchronized void resetRunningParameters() {
        GcodeRepositoryManager.getInstance().resetRunningParameters();
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
