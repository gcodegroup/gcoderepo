/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode.newgui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.bangor.gcode.LineReader;
import uk.ac.bangor.gcode.newgui.GcodeModel;

/**
 *
 * @author zc
 */
public class ReadFilePropertyChangeListner implements PropertyChangeListener {

    private final LineReader lineReader = new LineReader();
    private final GcodeModel model;

    public ReadFilePropertyChangeListner(GcodeModel model) {
        
        this.model = model;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        try {
            lineReader.load(model.getInputFilePath());
            model.setOriginalText(lineReader.getAllLines());
        } catch (IOException ex) {
            Logger.getLogger(ReadFilePropertyChangeListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
