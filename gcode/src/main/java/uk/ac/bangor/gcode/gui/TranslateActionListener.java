package uk.ac.bangor.gcode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.LineReader;

public class TranslateActionListener implements ActionListener {

    private final JTextArea outputArea;
    private final GcodeTranslator gcodeTranslator;
    private final LineReader lineReader;
    
    public TranslateActionListener(JTextArea outputArea, LineReader lineReader, GcodeTranslator gcodeTranslator) {
        
        this.outputArea = outputArea;
        this.gcodeTranslator = gcodeTranslator;
        this.lineReader = lineReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gcodeTranslator.translate(lineReader.getLines());
        outputArea.setText(gcodeTranslator.getResult());
    }
}
