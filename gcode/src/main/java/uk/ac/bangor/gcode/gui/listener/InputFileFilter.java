/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode.gui.listener;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author zc
 */
public class InputFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        
        if(file.isDirectory()) {
            return true;
        }
        
        return file.getName().toLowerCase().endsWith(".gcode");
    }
    
    @Override
    public String getDescription() {
        return "Only directories and gcode files will be displayed.";
    }    
}
