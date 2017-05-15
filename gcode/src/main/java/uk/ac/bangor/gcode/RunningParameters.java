package uk.ac.bangor.gcode;

public class RunningParameters {
    
    private static final     String DEFAULT_INPUT_FILE_PATH = "C:\\Temp\\Test.gcode";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\Temp\\output.gout";
    private static final double DEFAULT_SPEED = 0.1;
    private static final int DEFAULT_START_DELAY_TIME = 300;
    
    public static String getInputPath() {
        return DEFAULT_INPUT_FILE_PATH;
    }
    
    public static String getOutputPath() {
        return DEFAULT_OUTPUT_FILE_PATH;
    }
    
    public static double getSpeed () {
        return DEFAULT_SPEED;
    }
    
    public static int getStartDelayTime () {
        return DEFAULT_START_DELAY_TIME;
    }
}
