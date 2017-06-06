package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.gui.GcodeModel;

public class TranslationPropertyChangeListener implements PropertyChangeListener {
    
    private final JTextArea translateJTextArea;


     public TranslationPropertyChangeListener(JTextArea translateJTextArea) {
        
        this.translateJTextArea = translateJTextArea;
    }   
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        GcodeModel model = (GcodeModel) evt.getSource();
        String text = model.getTranslatedText();
        translateJTextArea.setText(text == null ? "" : text);
        translateJTextArea.setCaretPosition(0);
        
        model.setResultSaved(false);        
    }  
}
