package uk.ac.bangor.gcode;

import java.io.IOException;

public class CommandLineRunner
{
    public static void main(String... args) throws IOException {
        
        LineReader reader = new LineReader();
        
        reader.load(RunningParameters.getInputPath());
        GcodeTranslator translator = new GcodeTranslator(reader);
        translator.translate();
        OutputFileWriter writer = new OutputFileWriter();
        writer.write(RunningParameters.getOutputPath(), translator.getResult());
    }
            
}
