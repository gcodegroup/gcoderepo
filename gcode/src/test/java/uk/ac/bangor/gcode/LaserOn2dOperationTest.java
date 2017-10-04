package uk.ac.bangor.gcode;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test of class LaserOn2dOperation.
 *
 * @author zc
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LaserOn2dOperation.class, Point.class})
public final class LaserOn2dOperationTest {

    private final Point point1 = PowerMockito.mock(Point.class);
    private final Point point2 = PowerMockito.mock(Point.class);

    private LaserOn2dOperation instance;

    @Test
    public void testGetString() {

        Mockito.when(point1.getX()).thenReturn(437.887311);
        Mockito.when(point1.getY()).thenReturn(500.0);
        Mockito.when(point2.getX()).thenReturn(111.8836);
        Mockito.when(point2.getY()).thenReturn(42874.991212);
        instance = new LaserOn2dOperation(point1, point2, 300l);

        assertEquals("VEL A 2.3079 B 299.9911\n"
                   + "MOV A 437.8873 B 500.0000\n"
                   + "DEL 141.2542", instance.getString());
    }

    @Test
    public void testGetString_zeroX() {

        Mockito.when(point1.getX()).thenReturn(437.0);
        Mockito.when(point1.getY()).thenReturn(500.0);
        Mockito.when(point2.getX()).thenReturn(437.0);
        Mockito.when(point2.getY()).thenReturn(540.0);
        instance = new LaserOn2dOperation(point1, point2, 40);

        assertEquals("VEL A 0.0000 B 40.0000\n"
                  + "MOV A 437.0000 B 500.0000\n"
                  + "DEL 1.0000", instance.getString());
    }

    @Test
    public void testGetString_zeroY() {

        Mockito.when(point1.getX()).thenReturn(437.0);
        Mockito.when(point1.getY()).thenReturn(500.0);
        Mockito.when(point2.getX()).thenReturn(441.0);
        Mockito.when(point2.getY()).thenReturn(500.0);
        instance = new LaserOn2dOperation(point1, point2, 40);

        assertEquals("VEL A 40.0000 B 0.0000\n"
                   + "MOV A 437.0000 B 500.0000\n"
                   + "DEL 0.1000", instance.getString());
    }
}
