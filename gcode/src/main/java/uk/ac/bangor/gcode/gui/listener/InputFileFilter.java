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
        
        String name = file.getName().toLowerCase();
        return name.endsWith(".gcode") || name.endsWith(".mmg");
    }
    
    @Override
    public String getDescription() {
        return "Show only folders, mmg and gcode files";
    }    
}
