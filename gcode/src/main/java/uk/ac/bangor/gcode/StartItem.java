package uk.ac.bangor.gcode;

public final class StartItem extends AbstractGcodeItem {

    public StartItem() {
        
        super ("CT0 3 7 0\n" + 
                "DEL " + RunningParameters.getInstance().getInitialDelayTime() + "\n" + 
                "CTO 3 7 1");
    }
}
