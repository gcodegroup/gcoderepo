/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SelectInputFileActionListener implements ActionListener {

    private final JFileChooser jFileChooser;
    private final GcodeModel model;

    public SelectInputFileActionListener(GcodeModel model) {
        this.model = model;
        jFileChooser = new JFileChooser(new File(model.getInputFilePath()));
        jFileChooser.setDialogTitle("Select Input File");
        jFileChooser.setFileFilter(new InputFileFilter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jFileChooser.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
            model.setInputFilePath(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
