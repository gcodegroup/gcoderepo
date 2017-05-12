package uk.ac.bangor.gcode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.OutputFileWriter;

public class SaveActionListener implements ActionListener {

    private final GcodeTranslator translator;
    private final JFileChooser jFileChooser;
    private final JButton fileChooseButton;
    private final OutputFileWriter writer = new OutputFileWriter();

    public SaveActionListener(JFileChooser jFileChooser, JButton fileChooseButton, GcodeTranslator translator) {

        this.jFileChooser = jFileChooser;
        this.fileChooseButton = fileChooseButton;
        this.translator = translator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (jFileChooser.showOpenDialog(fileChooseButton) == JFileChooser.APPROVE_OPTION) {
            String saveToName = jFileChooser.getSelectedFile().getAbsolutePath();
            
            writer.write(saveToName, translator.getResult());
        }
    }
}
