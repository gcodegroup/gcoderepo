package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import uk.ac.bangor.gcode.gui.GcodeModel;
import uk.ac.bangor.gcode.gui.MainStatus;

public final class OutputFilePathPropertyChangeListener implements PropertyChangeListener {

    private final JLabel jLabel;
    private final JButton saveJButton;

    public OutputFilePathPropertyChangeListener(JLabel jLabel, JButton saveJButton) {
        this.jLabel = jLabel;
        this.saveJButton = saveJButton;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();

        MainStatus mainStatus = model.getMainStatus();
        jLabel.setVisible(mainStatus.isVisible());
        jLabel.setForeground(mainStatus.getColor());
        jLabel.setText(mainStatus.getMessage());
        saveJButton.setEnabled(mainStatus.canBeProcessed());
    }
}
