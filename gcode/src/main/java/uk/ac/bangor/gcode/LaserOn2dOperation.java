package uk.ac.bangor.gcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The LaserOn2dOperation class represents a two-dimension movement from one
 * point to another with the laser turned on.
 *
 * @author zc
 */
public final class LaserOn2dOperation extends AbstractGcodeOperation {

    public LaserOn2dOperation(Point point1, Point point2, double speed) {

        super(getString(point1, point2, speed));
    }

    private static String getString(Point point1, Point point2, double speed) {
        String x = getString(point1.getX());
        String y = getString(point1.getY());

        double xDistance = point2.getX() - point1.getX();
        double yDistance = point2.getY() - point1.getY();

        String delayTime = getString(Math.sqrt(xDistance * xDistance + yDistance * yDistance) / speed);
        String vx = getString(speed * xDistance / Math.sqrt(xDistance * xDistance + yDistance * yDistance));
        String vy = getString(speed * yDistance / Math.sqrt(xDistance * xDistance + yDistance * yDistance));

        return "VEL A " + vx + " B " + vy + "\n"
                + "MOV A " + x + " B " + y + "\n"
                + "DEL " + delayTime;

    }

    private static String getString(double value) {
        return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).toPlainString();
    }
}
