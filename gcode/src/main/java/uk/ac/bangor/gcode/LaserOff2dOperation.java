
package uk.ac.bangor.gcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Administrator
 */
public final class LaserOff2dOperation extends AbstractGcodeOperation {
    
    public LaserOff2dOperation(Point point) {
        super(getString(point));
    }
  

    private static String getString(Point point) {

        String x = getString(point.getX());
        String y = getString(point.getY());
        
        return "CTO 1 7 0\n" +
               "DEL " + RunningParameters.getInstance().getInitialDelayTime() + "\n" +
               "CTO 1 7 1\n" + 
               "MOV A " + x + " B " + y;
    }

    private static String getString(double value) {
        return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).toPlainString();
    }    
    
}
