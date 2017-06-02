package uk.ac.bangor.gcode.gui.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import uk.ac.bangor.gcode.GcodeException;
import uk.ac.bangor.gcode.gui.GcodeModel;

/**
 *
 * @author zc
 */
public class InputFileDocumentListener implements DocumentListener {

    private final GcodeModel model;

    public InputFileDocumentListener(GcodeModel model) {

        this.model = model;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update(e);
    }

    private void update(DocumentEvent e) {
        try {
            int length = e.getLength();
            model.setInputFilePath(length <= 0 ? "" : e.getDocument().getText(0, length));
        } catch (BadLocationException ex) {
            throw new GcodeException(ex);
        }
    }
}
