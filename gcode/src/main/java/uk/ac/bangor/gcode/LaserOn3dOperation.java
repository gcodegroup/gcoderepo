package uk.ac.bangor.gcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The LaserOn3dItem class represents a three-dimension movement from one point
 * to another with the laser switched on.
 *
 * @author zc
 */
public final class LaserOn3dOperation extends AbstractGcodeOperation {

    /**
     * Construct a LaserOn3dItem object.
     *
     * @param point1 - The start point.
     * @param point2 - The finish point.
     * @param speed - the moving speed.
     */
    public LaserOn3dOperation(Point point1, Point point2, double speed) {

        super(getString(point1, point2, speed));
    }

    private static String getString(Point point1, Point point2, double speed) {

        String x = getString(point1.getX());
        String y = getString(point1.getY());

        String z = getString(point2.getZ());

        double xDistance = Math.abs(point2.getX() - point1.getX());
        double yDistance = Math.abs(point2.getY() - point1.getY());

        String delayTime = getString(Math.sqrt(xDistance * xDistance + yDistance * yDistance) / speed);
        String vx = getString(speed * xDistance / Math.sqrt(xDistance * xDistance + yDistance * yDistance));
        String vy = getString(speed * yDistance / Math.sqrt(xDistance * xDistance + yDistance * yDistance));

        return "VEL A " + vx + " B " + vy + "\n"
                + "MOV A " + x + " B " + y + " C " + z + "\n"
                + "DEL " + delayTime;

    }

    private static String getString(double value) {
        return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).toPlainString();
    }
}
