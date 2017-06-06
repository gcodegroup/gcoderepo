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

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String GCODE_HOME = USER_HOME + File.separator + "gcode-translator";

    static {
        System.setProperty("gcode.home", GCODE_HOME);
    }

    private static final GcodeRepositoryManager GCODE_REPOSITORY_MANAGER = new GcodeRepositoryManager();

    private final Properties properties = new Properties();
    private final String gcodeTranslatorHome = GCODE_HOME;
    private final File gcodeTranslatorPropertyFile = new File(gcodeTranslatorHome + File.separator + "prop" + File.separator + "gcode-parameters.properties");
    private final RunningParameters runningParameters = RunningParameters.getInstance();
    private final String inputFilePathKey = "input.file.path";
    private final String outputFilePathKey = "output.file.path";
    private final String initialDealyTimeKey = "initial.dealy.time";
    private final String movingSpeedKey = "moving.speed";
    private final String useDefaultConfigKey = "use.default.config";

    private GcodeRepositoryManager() {
    }

    public static final GcodeRepositoryManager getInstance() {
        return GCODE_REPOSITORY_MANAGER;
    }

    /**
     * Read the parameters from the file located at [User
     * Home]/gcode-translator/prop/gcode-parameters.properties
     *
     * @throws IOException if the file cannot be accessed.
     */
    public synchronized void readRunningParameters() throws IOException {

        readRunningParameters(gcodeTranslatorPropertyFile);
    }

    /**
     * Read the parameters from the given file.
     *
     * @param gcodeTranslatorPropertyFile - The specified file.
     * @throws IOException if the file cannot be accessed.
     * @throws NullPointerException if specified file object is null.
     */
    public synchronized void readRunningParameters(File gcodeTranslatorPropertyFile) throws IOException {

        if (!gcodeTranslatorPropertyFile.exists()) {
            gcodeTranslatorPropertyFile.getParentFile().mkdirs();
            gcodeTranslatorPropertyFile.createNewFile();
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(gcodeTranslatorPropertyFile)) {
            properties.load(fileInputStream);
        }

        boolean useDefaultConfig = Boolean.parseBoolean(properties.getProperty(useDefaultConfigKey));

        if (useDefaultConfig) {
            runningParameters.setUseDefaultConfig(useDefaultConfig);
            properties.clear();
        }

        String inputFilePath = properties.getProperty(inputFilePathKey);

        if (inputFilePath != null && !inputFilePath.trim().isEmpty()) {
            runningParameters.setInputFilePath(inputFilePath);
        }

        String outputFilePath = properties.getProperty(outputFilePathKey);
        if (outputFilePath != null && !outputFilePath.trim().isEmpty()) {
            runningParameters.setOutputFilePath(outputFilePath);
        }

        String initialDelayTimeString = properties.getProperty(initialDealyTimeKey);
        if (initialDelayTimeString != null && !initialDelayTimeString.trim().isEmpty()) {
            int initialDelayTime = Integer.parseInt(initialDelayTimeString);
            runningParameters.setInitialDelayTime(initialDelayTime);
        }

        String speedString = properties.getProperty(movingSpeedKey);
        if (speedString != null && !speedString.trim().isEmpty()) {
            int speed = Integer.parseInt(speedString);
            runningParameters.setMovingSpeed(speed);
        }
    }

    /**
     * Store the parameters to [User
     * Home]/gcode-translator/prop/gcode-parameters.properties
     *
     * @throws IOException
     */
    public synchronized void writeRunningParameters() throws IOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(gcodeTranslatorPropertyFile)) {
            properties.store(fileOutputStream, "All Gcode Translator parameters are saved here.\n\n");
        }
    }

    /**
     * Get a logger for the given class.
     * @param <T> - The class type.
     * @param klass - The specified class object.
     * @return the created logger.
     */
    public <T> Logger getLogger(Class<T> klass) {
        return Logger.getLogger(klass);
    }
}
