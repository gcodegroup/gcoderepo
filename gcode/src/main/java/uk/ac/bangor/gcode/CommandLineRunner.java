package uk.ac.bangor.gcode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * The CommandLineRunner class contains the main method which run the application via the command-line command.
 *
 * @author zc
 */
public final class CommandLineRunner {

    @SuppressWarnings("UseSpecificCatch")
    public static void main(String... args) throws IOException {

        GcodeRepositoryManager gcodeRepositoryManager = GcodeRepositoryManager.getInstance();
        Logger logger = gcodeRepositoryManager.getLogger(CommandLineRunner.class);

        try {
            RunningParameters runningParameters = RunningParameters.getInstance();

            if (args != null) {

                String inputFileKey = "--input.file.path=";
                String outputFileKey = "--output.file.path=";
                String initialDelayTimeKey = "--initial.delay.time=";
                String movingSpeedKey = "--moving.speed=";
                String propertiesFileKey = "--properties.file=";

                for (String arg : args) {

                    String argl = arg.toLowerCase().trim();

                    if (argl.startsWith(propertiesFileKey)) {
                        gcodeRepositoryManager.readRunningParameters(new File(argl.substring(propertiesFileKey.length())));
                    }

                    if (argl.startsWith(inputFileKey)) {
                        runningParameters.setInputFilePath(argl.substring(inputFileKey.length()).trim());
                    }

                    if (argl.startsWith(outputFileKey)) {
                        runningParameters.setOutputFilePath(argl.substring(outputFileKey.length()).trim());
                    }

                    if (argl.startsWith(initialDelayTimeKey)) {
                        runningParameters.setInitialDelayTime(Integer.parseInt(argl.substring(initialDelayTimeKey.length()).trim()));
                    }

                    if (argl.startsWith(movingSpeedKey)) {
                        runningParameters.setMovingSpeed(Integer.parseInt(argl.substring(movingSpeedKey.length()).trim()));
                    }
                }
            }

            LineReader reader = new LineReader();
            GcodeTranslator translator = new GcodeTranslator();
            OutputFileWriter writer = new OutputFileWriter();

            String inputFilePath = runningParameters.getInputFilePath();
            logger.info("Start to load source......");
            List<FileLine> lines = reader.load(inputFilePath).getFileLines();
            logger.info("The source has been successfully loaded from: " + inputFilePath);
            logger.info("Start to translate......");
            String result = translator.translate(lines);
            logger.info("The source has been successfully translated.");
            logger.trace(result);
            String outputFilePath = runningParameters.getOutputFilePath();
            logger.info("Start to write the result onto disk......");
            writer.write(outputFilePath, result);
            logger.info("The result has been successfully written to: " + outputFilePath);
            logger.info("The Gcode Translator has successfully operated.");

        } catch (Throwable th) {
            logger.error("Failed to run Gcode Translator", th);
        }
    }

}
