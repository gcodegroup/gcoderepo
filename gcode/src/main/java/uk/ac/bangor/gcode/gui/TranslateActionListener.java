package uk.ac.bangor.gcode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.GcodeTranslator;

public class TranslateActionListener implements ActionListener {

    private final JTextArea outputArea;
    private final GcodeTranslator gcodeTranslator;
    
    public TranslateActionListener(JTextArea outputArea, GcodeTranslator gcodeTranslator) {
        
        this.outputArea = outputArea;
        this.gcodeTranslator = gcodeTranslator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gcodeTranslator.translate();
        outputArea.setText(gcodeTranslator.getResult());
    }
}
