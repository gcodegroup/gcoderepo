package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import uk.ac.bangor.gcode.gui.GcodeModel;
import uk.ac.bangor.gcode.gui.MainStatus;

/**
 *
 * @author Zhipeng
 */
public class ResultSavedPropertyChangeListener implements PropertyChangeListener {
    
    private final JButton saveJButton;
    private final JLabel errorMessageJLabel;

    public ResultSavedPropertyChangeListener(JButton saveJButton, JLabel errorMessageJLabel) {
        this.saveJButton = saveJButton;
        this.errorMessageJLabel = errorMessageJLabel;        
    }
 
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GcodeModel model = (GcodeModel) evt.getSource();
        MainStatus mainStatus = model.getMainStatus();
        saveJButton.setEnabled(mainStatus.canBeProcessed()); 
        errorMessageJLabel.setVisible(mainStatus.isVisible());
        errorMessageJLabel.setText(mainStatus.getMessage());
        errorMessageJLabel.setForeground(mainStatus.getColor());    
    }
    
}
