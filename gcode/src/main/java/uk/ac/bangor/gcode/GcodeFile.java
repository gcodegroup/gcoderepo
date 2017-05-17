/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author zc
 */
public class GcodeFile {
    
    private final String fileString;
    private final List<FileLine> fileLines;

    public GcodeFile(String fileString, List<FileLine> fileLines) {
        this.fileString = fileString;
        this.fileLines = fileLines;
    }

    public String getFileString() {
        return fileString;
    }

    public List<FileLine> getFileLines() {
        return fileLines;
    }   

    @Override
    public int hashCode() {
        
        return 31 * 5 + Objects.hashCode(this.fileString);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final GcodeFile other = (GcodeFile) obj;
        return Objects.equals(this.fileString, other.fileString);
    }
}
