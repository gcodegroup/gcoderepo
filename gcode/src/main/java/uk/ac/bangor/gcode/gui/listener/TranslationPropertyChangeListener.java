package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import uk.ac.bangor.gcode.gui.GcodeModel;
import uk.ac.bangor.gcode.gui.MainStatus;

public class TranslationPropertyChangeListener implements PropertyChangeListener {
    
    private final JTextArea translateJTextArea;
    private final JButton saveJButton;
    private final JLabel errorMessageJLabel;
    private final JMenuItem saveParametersJMenuItem;

     public TranslationPropertyChangeListener(JTextArea translateJTextArea, JButton saveJButton, JLabel errorMessageJLabel, JMenuItem saveParametersJMenuItem) {
        
        this.translateJTextArea = translateJTextArea;
        this.saveJButton = saveJButton;
        this.errorMessageJLabel = errorMessageJLabel;
        this.saveParametersJMenuItem = saveParametersJMenuItem;
    }   
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        GcodeModel model = (GcodeModel) evt.getSource();
        translateJTextArea.setText(model.getTranslatedText());
        
        MainStatus mainStatus = model.getMainStatus();
        saveJButton.setEnabled(mainStatus.canBeProcessed()); 
        errorMessageJLabel.setVisible(mainStatus.isVisible());
        errorMessageJLabel.setText(mainStatus.getMessage());
        errorMessageJLabel.setForeground(mainStatus.getColor());
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved());        
    }  
}
