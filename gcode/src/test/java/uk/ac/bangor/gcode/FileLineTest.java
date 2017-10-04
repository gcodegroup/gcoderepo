package uk.ac.bangor.gcode;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static uk.ac.bangor.gcode.LineType.*;

@RunWith(Parameterized.class)
public final class FileLineTest {

    @Parameterized.Parameters
    public static Collection<Object[]> paramData() {
        return Arrays.asList(new Object[][]{
            {null,                                  null,                                   UNUSED_LINE},
            {"        ",                            "",                                     UNUSED_LINE},
            {"M190 S70.000000",                     "M190 S70.000000",                      UNUSED_LINE},
            {";Sliced at: Mon 30-01-2017 15:20:25", "",                                     UNUSED_LINE},
            {"G0 X30.2767 Y38.5866 Z-0.2500 F300.0","G0 X30.2767 Y38.5866 Z-0.2500 F300.0", G0_FXYZ_LINE},
            {"G0 F9000 X42.035 Y43.814",            "G0 F9000 X42.035 Y43.814",             G0_FXY_LINE},
            {"G0 X30.2767 Y25.8663 Z5.0000",        "G0 X30.2767 Y25.8663 Z5.0000",         G0_XYZ_LINE},
            {"G0 X52.731 Y45.525",                  "G0 X52.731 Y45.525",                   G0_XY_LINE},
            {"G1 F1620 X42.503 Y44.081 E0.00236",   "G1 F1620 X42.503 Y44.081 E0.00236",    G1_EFXY_LINE},                         
            {"G1 F2400 E0.00000",                   "G1 F2400 E0.00000",                    G1_EF_LINE},
            {"G1 X42.636 Y44.013 E0.00471",         "G1 X42.636 Y44.013 E0.00471",          G1_EXY_LINE},         
            {"G1 X30.2767 Y38.5866 Z-0.2500 F300.0","G1 X30.2767 Y38.5866 Z-0.2500 F300.0", G1_FXYZ_LINE},
            {"G1 X30.2767 Y38.5866 Z-0.2500",       "G1 X30.2767 Y38.5866 Z-0.2500",        G1_XYZ_LINE},
            {"G1 X47.059 Y46.110",                  "G1 X47.059 Y46.110",                   G1_XY_LINE},              
            {"", "", UNUSED_LINE}
        });
    }

    private final String expectedLine;
    private final LineType expectedLineType;
    private final FileLine instance;

    public FileLineTest(String line, String expectedLine, LineType expectedLineType) {
        this.expectedLine = expectedLine;
        this.expectedLineType = expectedLineType;
        this.instance = new FileLine(line);
    }

    @Test
    public void testGetLineString() {
        assertEquals(expectedLine, instance.getLineString());
    }
    
    @Test
    public void testGetLineStatus() {
        assertEquals(expectedLineType, instance.getLineStatus());
    }

    @Test
    public void testHashCode() {
        
        assertEquals(new FileLine(expectedLine).hashCode(), instance.hashCode());
        assertNotEquals(new FileLine("Another Line").hashCode(), instance.hashCode());
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEquals() {
        
        assertTrue(instance.equals(instance));        
        assertTrue(instance.equals(new FileLine(expectedLine)));
        assertFalse(instance.equals(new FileLine("Another line")));
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(new Object()));        
    }
}
