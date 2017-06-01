package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import uk.ac.bangor.gcode.gui.GcodeModel;
import uk.ac.bangor.gcode.gui.MainStatus;

public final class OutputFilePathPropertyChangeListener implements PropertyChangeListener {

    private final JLabel errorMessageJLabel;
    private final JButton saveJButton;
    private final JMenuItem saveParametersJMenuItem;

    public OutputFilePathPropertyChangeListener(JLabel errorMessageJLabel, JButton saveJButton, JMenuItem saveParametersJMenuItem) {
        this.errorMessageJLabel = errorMessageJLabel;
        this.saveJButton = saveJButton;
        this.saveParametersJMenuItem = saveParametersJMenuItem;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();

        MainStatus mainStatus = model.getMainStatus();
        errorMessageJLabel.setVisible(mainStatus.isVisible());
        errorMessageJLabel.setForeground(mainStatus.getColor());
        errorMessageJLabel.setText(mainStatus.getMessage());
        saveJButton.setEnabled(mainStatus.canBeProcessed());
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved());        
    }
}
