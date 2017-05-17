package uk.ac.bangor.gcode.gui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.gui.GcodeModel;

public final class NumericPropertyChangeListener implements PropertyChangeListener {

    private final GcodeTranslator translator;

    public NumericPropertyChangeListener(GcodeTranslator translator) {
        this.translator = translator;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GcodeModel model = (GcodeModel) evt.getSource();
        if (model.isValidOriginalText()) {
            model.setTranslatedText(translator.translate(model.getGcodeFile().getFileLines()));
        }
    }

}
