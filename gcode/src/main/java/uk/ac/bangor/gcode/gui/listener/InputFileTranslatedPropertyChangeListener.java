/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import uk.ac.bangor.gcode.gui.GcodeModel;
import uk.ac.bangor.gcode.gui.InputFileTranslationMessageStatus;

/**
 *
 * @author zc
 */
public class InputFileTranslatedPropertyChangeListener implements PropertyChangeListener {
    
    private final JButton translateJButton;
    private final JLabel translateMessageJLabel;

    public InputFileTranslatedPropertyChangeListener(JButton saveJButton, JLabel translateMessageJLabel) {
        this.translateJButton = saveJButton;
        this.translateMessageJLabel = translateMessageJLabel;        
    }
 
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        GcodeModel model = (GcodeModel) evt.getSource();
        InputFileTranslationMessageStatus inputFileStatus = model.getInputFileTranslationMessageStatus();
        translateJButton.setEnabled(inputFileStatus.canBeProcessed()); 
        translateMessageJLabel.setVisible(inputFileStatus.isVisible());
        translateMessageJLabel.setText(inputFileStatus.getMessage());
        translateMessageJLabel.setForeground(inputFileStatus.getColor());    
    }
    
}
