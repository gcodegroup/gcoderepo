package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import uk.ac.bangor.gcode.gui.GcodeModel;

public final class NumericPropertyChangeListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();
        model.setInputFileTranslated(false);
        model.setTranslatedText("");
    }

}
