
package uk.ac.bangor.gcode;

/**
 *
 * @author Administrator
 */
public class LaserOffItem extends AbstractGcodeItem {
    
    public LaserOffItem(String string) {
        super("CTO 1 7 0\n"
            + "DEL 300\n"
            + "CTO 1 7 1");
    }
    
}
