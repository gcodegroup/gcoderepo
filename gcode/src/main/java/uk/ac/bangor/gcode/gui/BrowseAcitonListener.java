package uk.ac.bangor.gcode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import uk.ac.bangor.gcode.GcodeException;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.LineReader;

public class BrowseAcitonListener implements ActionListener {

    private final FilledFrame frame;
    private final LineReader lineReader;
    private final GcodeTranslator gcodeTranslator;    

    public BrowseAcitonListener(FilledFrame frame, LineReader lineReader, GcodeTranslator gcodeTranslator) {
        
        this.frame = frame;
        this.lineReader = lineReader;
        this.gcodeTranslator = gcodeTranslator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jFileChooser = frame.getFc();
        if (jFileChooser.showOpenDialog(frame.getFileChooseButton()) == JFileChooser.APPROVE_OPTION) {
            String filename = jFileChooser.getSelectedFile().getAbsolutePath();
            try {
                lineReader.load(filename);
                frame.getInputArea().setText(lineReader.getAllLines());                
                gcodeTranslator.reset();
                frame.getOutputArea().setText("");
            } catch (IOException ex) {
                throw new GcodeException(ex);
            }

        }
    }
}
