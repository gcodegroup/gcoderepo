package uk.ac.bangor.gcode.newgui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.newgui.GcodeModel;

/**
 *
 * @author zc
 */
public class TranslationPropertyChangeListener implements PropertyChangeListener {
    
    private final GcodeTranslator translator = new GcodeTranslator();
    private final GcodeModel model;

     public TranslationPropertyChangeListener(GcodeModel model) {
        
        this.model = model;
    }   
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        translator.translate(model.getLines());
        model.setTranslatedText(translator.getResult());
    }  
}
