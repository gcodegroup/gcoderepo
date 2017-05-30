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

                case GFXYE_LINE:
                    items.add(new StartItem());
                    point1 = new Point(line);
                    break;
                case GXYE_LINE:

                    if (point1 != null) {
                        point2 = new Point(line);
                        if (!point2.equals(point1)) {
                            items.add(new IntermediateItem(point1, point2, speed));
                            point1 = point2;
                        }
                    } else {
                        point1 = new Point(line);
                    }

                    break;

                case GFXY_LINE:

                    if (point1 != null) {
                        point2 = new Point(line);
                        if (!point2.equals(point1)) {
                            items.add(new IntermediateItem(point1, point2, speed));
                        }
                    }

                    items.add(new TerminalItem());
                    point1 = null;
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
