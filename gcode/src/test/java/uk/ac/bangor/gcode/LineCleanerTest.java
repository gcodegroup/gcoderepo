package uk.ac.bangor.gcode;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class LineCleanerTest {
    
    private final String g0Line = "G00 X 30.2767 Y 25.8663 Z 5.0000";
    private final String g1Line = "G01 X 30.2767 Y 38.5866 Z -0.2500 F 300.0";
    private final LineCleaner instance = new LineCleaner();
    
    
    @Test
    public void testGetCleanLine() {

        assertEquals("G0 X30.2767 Y25.8663 Z5.0000", instance.getCleanLine(g0Line));
        
        assertEquals("G1 X30.2767 Y38.5866 Z-0.2500 F300.0", instance.getCleanLine(g1Line));
    }   
}
