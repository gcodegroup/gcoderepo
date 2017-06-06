package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.GcodeFile;
import uk.ac.bangor.gcode.gui.GcodeModel;

/**
 * The GcodeFilePropertyChangeListener class defines a listener listen to the
 * change of GcodeFile field in a GcodeModel.
 *
 * @author zc
 */
public final class GcodeFilePropertyChangeListener implements PropertyChangeListener {

    private final JTextArea inputFileJTextArea;
    private final JMenuItem saveParametersJMenuItem;

    public GcodeFilePropertyChangeListener(JTextArea inputFileJTextArea, JMenuItem saveParametersJMenuItem) {
        this.inputFileJTextArea = inputFileJTextArea;
        this.saveParametersJMenuItem = saveParametersJMenuItem;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();
        GcodeFile gcodeFile = model.getGcodeFile();
        inputFileJTextArea.setText(gcodeFile == null ? "" : gcodeFile.getFileString());
        inputFileJTextArea.setCaretPosition(0);
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved());
        model.setInputFileTranslated(false);
        model.setTranslatedText("");
    }
}
