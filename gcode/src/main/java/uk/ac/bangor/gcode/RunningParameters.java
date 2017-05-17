package uk.ac.bangor.gcode;

public class RunningParameters {
    
    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\Temp\\Test.gcode";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\Temp\\output.gout";
    private static final int DEFAULT_SPEED = 3;
    private static final int DEFAULT_START_DELAY_TIME = 300;
   
    private static final RunningParameters RUNNING_PARAMETERS = new RunningParameters();

    private String inputPath = DEFAULT_INPUT_FILE_PATH;
    private String outputPath = DEFAULT_OUTPUT_FILE_PATH;
    private int speed = DEFAULT_SPEED;
    private int startDelayTime = DEFAULT_START_DELAY_TIME;
    
    private RunningParameters() {
    }

    public static RunningParameters getInstance() {
        return RUNNING_PARAMETERS;
    }
    
    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStartDelayTime() {
        return startDelayTime;
    }

    public void setStartDelayTime(int startDelayTime) {
        this.startDelayTime = startDelayTime;
    }
}
