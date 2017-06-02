package uk.ac.bangor.gcode.gui.listener;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author zc
 */
public class OutputFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        
        if(file.isDirectory()) {
            return true;
        }
        
        return file.getName().toLowerCase().endsWith(".gout");
    }
    
    @Override
    public String getDescription() {
        return "Show only folders and gout files";
    }
    
}

