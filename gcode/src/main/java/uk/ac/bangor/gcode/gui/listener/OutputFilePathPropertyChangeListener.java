package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import uk.ac.bangor.gcode.gui.GcodeModel;

public final class OutputFilePathPropertyChangeListener implements PropertyChangeListener {

    private final JMenuItem saveParametersJMenuItem;

    public OutputFilePathPropertyChangeListener(JMenuItem saveParametersJMenuItem) {

        this.saveParametersJMenuItem = saveParametersJMenuItem;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved()); 
        model.setResultSaved(false);
    }
}
