package uk.ac.bangor.gcode;

public final class RunningParameters {
    
    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\Temp\\Test.gcode";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\Temp\\output.gout";
    private static final int DEFAULT_SPEED = 3;
    private static final int DEFAULT_INITIAL_DELAY_TIME = 300;
    private static final boolean DEFAULT_USE_DEFAULT_CONFIG = true;
    
    private static final RunningParameters RUNNING_PARAMETERS = new RunningParameters();

    private String inputFilePath;
    private String outputFilePath;
    private int movingSpeed;
    private int initialDelayTime;
    private boolean useDefaultConfig = DEFAULT_USE_DEFAULT_CONFIG;
    
    private RunningParameters() {
        reset();
    }

    private void reset() {
        setInputFilePath(DEFAULT_INPUT_FILE_PATH);
        setOutputFilePath(DEFAULT_OUTPUT_FILE_PATH);
        setMovingSpeed(DEFAULT_SPEED);
        setInitialDelayTime(DEFAULT_INITIAL_DELAY_TIME);
    }
    
    public static RunningParameters getInstance() {
        return RUNNING_PARAMETERS;
    }
    
    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public int getMovingSpeed() {
        return movingSpeed;
    }

    public void setMovingSpeed(int movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    public int getInitialDelayTime() {
        return initialDelayTime;
    }

    public void setInitialDelayTime(int initialDelayTime) {
        this.initialDelayTime = initialDelayTime;
    }

    public boolean isUseDefaultConfig() {
        return useDefaultConfig;
    }

    public void setUseDefaultConfig(boolean useDefaultConfig) {
        this.useDefaultConfig = useDefaultConfig;
        if(useDefaultConfig) {
            reset();
        }
    }    
}
