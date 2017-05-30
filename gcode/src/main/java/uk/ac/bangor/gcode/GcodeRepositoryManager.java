package uk.ac.bangor.gcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * the GcodeRepositoryManager class manage the gcode translator repository.
 *
 * @author zc
 */
public final class GcodeRepositoryManager {

    private static final GcodeRepositoryManager GCODE_REPOSITORY_MANAGER = new GcodeRepositoryManager();
    private static final String USER_HOME = System.getProperty("user.home");

    static {
        System.setProperty("gcode.home", USER_HOME + File.separator + "gcode-translator");
    }

    private final Properties properties = new Properties();
    private final String gcodeTranslatorHome = System.getProperty("gcode.home");
    private final File gcodeTranslatorPropertyFile = new File(gcodeTranslatorHome + File.separator + "prop" + File.separator + "parameters.properties");
    private final RunningParameters runningParameters = RunningParameters.getInstance();
    private final String inputFilePathKey = "input.file.path";
    private final String outputFilePathKey = "output.file.path";
    private final String initialDealyTimeKey = "initial.dealy.time";
    private final String movingSpeedKey = "moving.speed";

    private GcodeRepositoryManager() {
    }

    public static final GcodeRepositoryManager getInstance() {
        return GCODE_REPOSITORY_MANAGER;
    }

     public synchronized void readRunningParameters() throws IOException {

         readRunningParameters(gcodeTranslatorPropertyFile);
    }   
    
    public synchronized void readRunningParameters(File gcodeTranslatorPropertyFile) throws IOException {

        if (!gcodeTranslatorPropertyFile.exists()) {
            gcodeTranslatorPropertyFile.createNewFile();
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(gcodeTranslatorPropertyFile)) {
            properties.load(fileInputStream);
        }

        String inputFilePath = properties.getProperty(inputFilePathKey);

        if (inputFilePath == null || inputFilePath.trim().isEmpty()) {
            runningParameters.setInputFilePath(inputFilePath);
        }

        String outputFilePath = properties.getProperty(outputFilePathKey);

        if (outputFilePath == null || outputFilePath.trim().isEmpty()) {
            runningParameters.setOutputFilePath(outputFilePath);
        }

        int initialDelayTime = Integer.parseInt(properties.getProperty(initialDealyTimeKey));
        runningParameters.setInitialDelayTime(initialDelayTime);

        int speed = Integer.parseInt(properties.getProperty(movingSpeedKey));
        runningParameters.setMovingSpeed(speed);
    }

    public synchronized void writeRunningParameters() throws IOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(gcodeTranslatorPropertyFile)) {
            properties.store(fileOutputStream, "All Gcode Translator parameters are saved here.\n\n");
        }
    }
   
    public String getUserHome() {
        return USER_HOME;
    }
    
    public <T> Logger getLogger(Class<T> klass) {
        return Logger.getLogger(klass);
    }
}
