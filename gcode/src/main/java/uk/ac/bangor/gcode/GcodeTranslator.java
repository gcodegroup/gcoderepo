package uk.ac.bangor.gcode;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * The GcodeTranslator class contains a method to translate the Gcode to GCS
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

        Logger logger = GcodeRepositoryManager.getInstance().getLogger(getClass());

        RunningParameters runningParameters = RunningParameters.getInstance();
        int speed = runningParameters.getMovingSpeed();
        int initialDelayTime = runningParameters.getInitialDelayTime();

        List<GcodeOperation> items = new ArrayList<>();

        Point point1 = null;
        Point point2;

        for (FileLine line : lines) {

            switch (line.getLineStatus()) {

                case G0_XYZ_LINE:
                case G0_FXYZ_LINE:
                    point1 = new Point(line, false);
                    items.add(new LaserOff3dOperation(point1, initialDelayTime));
                    break;

                case G1_EFXY_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1.\n" + line.getLineString());
                    }

                    if (!point1.isLaserOn()) {
                        items.add(new StartOperation(initialDelayTime));
                    }

                    point2 = new Point(line, true);
                    items.add(new LaserOn2dOperation(point1, point2, speed));
                    point1 = point2;
                    logger.trace("Line translated: " + line.getLineString());
                    break;
                case G1_EXY_LINE:

                    if (point1 == null) {
                        break;
                    }

                    point2 = new Point(line, true);
                    if (!point2.equals(point1)) {
                        items.add(new LaserOn2dOperation(point1, point2, speed));
                    }
                    point1 = point2;
                    logger.trace("Line translated: " + line.getLineString());
                    break;

                case G0_XY_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1..\n" + line.getLineString());
                    }

                    point2 = new Point(line, false);
                    items.add(new LaserOff2dOperation(point2, initialDelayTime));
                    point1 = point2;
                    logger.trace("Line translated: " + line.getLineString());
                    break;

                case G0_FXY_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1..\n" + line.getLineString());
                    }

                    point2 = new Point(line, false);
                    items.add(new LaserOff2dOperation(point2, initialDelayTime));
                    point1 = point2;
                    logger.trace("Line translated: " + line.getLineString());
                    break;

                case G1_FXYZ_LINE:
                case G1_XYZ_LINE:

                    if (point1 == null) {
                        throw new GcodeException("Invalid point1..\n" + line.getLineString());
                    }

                    if (!point1.isLaserOn()) {
                        items.add(new StartOperation(initialDelayTime));
                    }

                    point2 = new Point(line, true);
                    if (!point2.equals(point1)) {
                        items.add(new LaserOn3dOperation(point1, point2, speed));
                        point1 = point2;
                        logger.trace("Line translated: " + line.getLineString());
                    } else {
                        logger.debug("Line NOT translated: " + line.getLineString());
                    }
                    break;

                default:
                    logger.debug("Line NOT translated: " + line.getLineString());
            }
        }

        StringBuilder builder = new StringBuilder();

        items.stream().forEach((item) -> {
            builder.append(item.getString()).append("\n");
        });

        return new String(builder).trim();
    }
}
