package uk.ac.bangor.gcode;

import java.util.ArrayList;
import java.util.List;

/**
 * The GcodeTranslator class contains a method to translate the Gcode to new
 * code.
 *
 * @author zc
 */
public final class GcodeTranslator {

    /**
     * Translate the Gcode file lines.
     *
     * @param lines - The given Gcode lines.
     * @return the new code in a string format.
     */
    public String translate(List<FileLine> lines) {

        int speed = RunningParameters.getInstance().getMovingSpeed();
        List<GcodeItem> items = new ArrayList<>();

        Point point1 = null;
        Point point2;

        for (FileLine line : lines) {

            switch (line.getLineStatus()) {

                case G0_FXYZ_LINE:
                    point1 = new Point(line, false);
                    items.add(new LaserOff3DItem(point1));
                    break;

                case G1_EFXY_LINE:
                    
                    if (point1 == null) {
                        throw new GcodeException("Invalid point1.\n" + line.getLineString());
                    }

                    if(!point1.isLaserOn()) {
                        items.add(new StartItem());
                    }
                    
                    point2 = new Point(line, true);
                    items.add(new LaserOn2dItem(point1, point2, speed));
                    point1 = point2;
                    break;
                case G1_EXY_LINE:

                    if (point1 == null) {
                        break;
                    }

                    point2 = new Point(line, true);
                    if (!point2.equals(point1)) {
                        items.add(new LaserOn2dItem(point1, point2, speed));
                    }
                    point1 = point2;
                    break;

                case G0_XY_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1..\n" + line.getLineString());
                    }
                    
                    point2 = new Point(line, false);
                    items.add(new LaserOff2DItem(point2));
                    point1 = point2;
                    break;
                    
                case G0_FXY_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1..\n" + line.getLineString());
                    }

                    point2 = new Point(line, false);
                    items.add(new LaserOff2DItem(point2));
                    point1 = point2;
                    break;

                case G1_FXYZ_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1..\n" + line.getLineString());
                    }


                    if(!point1.isLaserOn()) {
                        items.add(new StartItem());
                    }                    
                    
                    point2 = new Point(line, true);
                    if (!point2.equals(point1)) {
                        items.add(new LaserOn3DItem(point1, point2, speed));
                        point1 = point2;
                    }
                    break;
                default:    //Do nothing for the rest.   
            }
        }
        
        StringBuilder builder = new StringBuilder();

        items.stream().forEach((item) -> {
            builder.append(item.getString()).append("\n");
        });

        return new String(builder).trim();
    }
}
