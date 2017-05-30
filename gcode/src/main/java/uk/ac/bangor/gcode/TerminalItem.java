package uk.ac.bangor.gcode;

public final class TerminalItem extends AbstractGcodeItem {

    public TerminalItem() {
        super( "CTO 1 7 0\n" +
               "DEL 300\n" +
               "CTO 1 7 1");
    }
}
