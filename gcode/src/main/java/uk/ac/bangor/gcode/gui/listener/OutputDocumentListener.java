package uk.ac.bangor.gcode.gui.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import uk.ac.bangor.gcode.gui.GcodeModel;

/**
 *
 * @author zc
 */
public class OutputDocumentListener implements DocumentListener {
    
    private final GcodeModel model;

    public OutputDocumentListener(GcodeModel model) {
        
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
            model.setOutputFilePath(e.getDocument().getText(0, e.getLength()));
        } catch (BadLocationException ex) {
            Logger.getLogger(InputFileDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
