package uk.ac.bangor.gcode;

import java.io.IOException;
import java.util.List;

public class CommandLineRunner
{
    public static void main(String... args) throws IOException {
        
        LineReader reader = new LineReader();   
        GcodeTranslator translator = new GcodeTranslator();        
        OutputFileWriter writer = new OutputFileWriter();        
        
        List<FileLine> lines = reader.load(RunningParameters.getInstance().getInputPath()).getFileLines();
        String result = translator.translate(lines);
        writer.write(RunningParameters.getInstance().getOutputPath(), result);
    }
            
}
