package uk.ac.bangor.gcode.newgui.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import uk.ac.bangor.gcode.newgui.GcodeModel;

/**
 *
 * @author zc
 */
public class SelectOuptFileActionListener  implements ActionListener {

    private final JFileChooser jFileChooser;
    private final GcodeModel model;

    public SelectOuptFileActionListener(GcodeModel model) {
        this.model = model;
        jFileChooser = new JFileChooser(new File(model.getInputFilePath()));
        jFileChooser.setDialogTitle("Select Output File");
        jFileChooser.setFileFilter(new InputFileFilter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jFileChooser.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
            model.setOutputFilePath(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
