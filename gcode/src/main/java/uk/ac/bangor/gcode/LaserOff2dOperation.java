package uk.ac.bangor.gcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The LaserOff2dOperation class represents a two-dimension movement with the
 * laser turned off.
 *
 * @author Administrator
 */
public final class LaserOff2dOperation extends AbstractGcodeOperation {

    public LaserOff2dOperation(Point point, int initialDelayTime) {
        super(getString(point, initialDelayTime));
    }

    private static String getString(Point point, int initialDelayTime) {

        String x = getString(point.getX());
        String y = getString(point.getY());

        return "CTO 1 7 0\n"
                + "DEL " + initialDelayTime + "\n"
                + "CTO 1 7 1\n"
                + "VCO A 0 B 0 C 0\n"
                + "MOV A " + x + " B " + y + "\n"
                + "VCO A 1 B 1 C 1";
    }

    private static String getString(double value) {
        return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).toPlainString();
    }

}
