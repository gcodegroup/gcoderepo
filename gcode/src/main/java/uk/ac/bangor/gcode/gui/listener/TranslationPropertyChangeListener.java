package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.gui.GcodeModel;

public class TranslationPropertyChangeListener implements PropertyChangeListener {
    
    private final JTextArea jTextArea;
    private final JButton jButton;

     public TranslationPropertyChangeListener(JTextArea jTextArea, JButton jButton) {
        
        this.jTextArea = jTextArea;
        this.jButton = jButton;
    }   
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        GcodeModel model = (GcodeModel) evt.getSource();
        jTextArea.setText(model.getTranslatedText());
        jButton.setEnabled(model.getMainStatus().canBeProcessed());        
    }  
}
