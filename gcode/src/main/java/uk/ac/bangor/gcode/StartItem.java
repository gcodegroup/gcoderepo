package uk.ac.bangor.gcode;


public final class StartItem implements GcodeItem {

    @Override
    public String getString() {
        return  "CT0 3 7 0\n" + 
                "DEL " + RunningParameters.getInstance().getStartDelayTime() + "\n" + 
                "CTO 3 7 1";
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
        return 353592699;
    }
}
