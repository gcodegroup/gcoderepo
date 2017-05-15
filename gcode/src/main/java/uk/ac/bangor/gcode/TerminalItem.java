package uk.ac.bangor.gcode;

public class TerminalItem implements GcodeItem {
    

    @Override
    public String getString() {
        return "CTO 1 7 0\n" +
               "DEL " + RunningParameters.getStartDelayTime() + "\n" + 
               "CTO 1 7 1";
    }    
    
    @Override
    public boolean equals(Object obj) {
        
        if(obj == null) {
            return false;
        }
            
        return obj instanceof StartItem;
    }

    @Override
    public int hashCode() {
        return 554185525;
    }
}
