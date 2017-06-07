package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.JLabel;
import uk.ac.bangor.gcode.GcodeException;
import uk.ac.bangor.gcode.LineReader;
import uk.ac.bangor.gcode.gui.GcodeModel;
import uk.ac.bangor.gcode.gui.InputFilePathMessageStatus;

/**
 * The InputFilePathPropertyChangeListener class listen to the change of input
 * file path. The related property is GcodeModel.INPUT_FILE_PATH_PROPERTY.
 *
 * @author zc
 */
public final class InputFilePathPropertyChangeListener implements PropertyChangeListener {

    private final LineReader lineReader = new LineReader();
    private final JLabel errorJLabel;

    public InputFilePathPropertyChangeListener(JLabel errorJLabel) {
        this.errorJLabel = errorJLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();
        InputFilePathMessageStatus inputFilePathStatus = model.getInputFilePathMessageStatus();
        
        errorJLabel.setVisible(inputFilePathStatus.isVisible());
        errorJLabel.setText(inputFilePathStatus.getMessage());
        
        if (inputFilePathStatus.canBeProcessed()) {
            try {
                model.setGcodeFile(lineReader.load(model.getInputFilePath()));
            } catch (IOException ex) {
                throw new GcodeException(ex);
            }
        } else {
            model.setGcodeFile(null);
        }
    }
}
