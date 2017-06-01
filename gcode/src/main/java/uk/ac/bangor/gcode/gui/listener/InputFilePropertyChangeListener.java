package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.gui.GcodeModel;

/**
 *
 * @author zc
 */
public final class InputFilePropertyChangeListener implements PropertyChangeListener {

    private final JTextArea inputFileJTextArea;
    private final JButton translateJButton;
    private final JMenuItem saveParametersJMenuItem;

    public InputFilePropertyChangeListener(JTextArea inputFileJTextArea, JButton translateJButton, JMenuItem saveParametersJMenuItem) {
        this.inputFileJTextArea = inputFileJTextArea;
        this.translateJButton = translateJButton;
        this.saveParametersJMenuItem = saveParametersJMenuItem;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        GcodeModel model = (GcodeModel) evt.getSource();
        inputFileJTextArea.setText(model.getGcodeFile().getFileString());
        translateJButton.setEnabled(model.isValidOriginalText());
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved());
    }

}
