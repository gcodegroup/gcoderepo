package uk.ac.bangor.gcode;

import java.util.ArrayList;
import java.util.List;

public class GcodeTranslator {

    public String translate(List<FileLine> lines) {

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
                        items.add(new IntermediateItem(point1, point2, RunningParameters.getInstance().getSpeed()));
                        point1 = point2;
                    } else {
                        point1 = new Point(line);
                    }

                    break;

                case GFXY_LINE:
                    
                    if (point1 != null) {
                        point2 = new Point(line);
                        items.add(new IntermediateItem(point1, point2, RunningParameters.getInstance().getSpeed()));
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

        return new String(builder);
    }
}
