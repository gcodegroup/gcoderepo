/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.gui.GcodeModel;

/**
 *
 * @author zc
 */
public final class InputFilePropertyChangeListener implements PropertyChangeListener {

    private final JTextArea jTextArea;
    private final JButton jButton;

    public InputFilePropertyChangeListener(JTextArea jTextArea, JButton jButton) {
        this.jTextArea = jTextArea;
        this.jButton = jButton;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        GcodeModel model = (GcodeModel) evt.getSource();
        jTextArea.setText(model.getGcodeFile().getFileString());
        jButton.setEnabled(model.isValidOriginalText());
    }

}
