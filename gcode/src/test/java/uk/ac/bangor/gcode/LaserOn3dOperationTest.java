package uk.ac.bangor.gcode;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test of class LaserOn3dOperation.
 *
 * @author zc
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LaserOn3dOperation.class, Point.class})
public final class LaserOn3dOperationTest {

    private final Point point1 = PowerMockito.mock(Point.class);
    private final Point point2 = PowerMockito.mock(Point.class);

    private LaserOn3dOperation instance;

    @Test
    public void testGetString() {

        Mockito.when(point1.getX()).thenReturn(437.887311);
        Mockito.when(point1.getY()).thenReturn(500.0);
        Mockito.when(point1.getZ()).thenReturn(6.4937674);
        Mockito.when(point2.getX()).thenReturn(111.8836);
        Mockito.when(point2.getY()).thenReturn(42874.991212);
        Mockito.when(point2.getZ()).thenReturn(99.3321);
        instance = new LaserOn3dOperation(point1, point2, 300l);

        assertEquals("VEL A 2.3079 B 299.9904 C 0.6572\n"
                + "MOV A 111.8836 B 42874.9912 C 99.3321\n"
                + "DEL 141.2545", instance.getString());
    }

    @Test
    public void testGetString_zeroXY() {

        Mockito.when(point1.getX()).thenReturn(437.0);
        Mockito.when(point1.getY()).thenReturn(500.0);
        Mockito.when(point1.getZ()).thenReturn(29.0);
        Mockito.when(point2.getX()).thenReturn(437.0);
        Mockito.when(point2.getY()).thenReturn(500.0);
        Mockito.when(point2.getZ()).thenReturn(33.0);
        instance = new LaserOn3dOperation(point1, point2, 40);

        assertEquals("VEL A 0.0000 B 0.0000 C 40.0000\n"
                + "MOV A 437.0000 B 500.0000 C 33.0000\n"
                + "DEL 0.1000", instance.getString());
    }

    @Test
    public void testGetString_aeroZ() {

        Mockito.when(point1.getX()).thenReturn(411.000);
        Mockito.when(point1.getY()).thenReturn(500.0);
        Mockito.when(point1.getZ()).thenReturn(99.3321);
        Mockito.when(point2.getX()).thenReturn(415.000);
        Mockito.when(point2.getY()).thenReturn(503.000);
        Mockito.when(point2.getZ()).thenReturn(99.3321);
        instance = new LaserOn3dOperation(point1, point2, 5);

        assertEquals("VEL A 4.0000 B 3.0000 C 0.0000\n"
                + "MOV A 415.0000 B 503.0000 C 99.3321\n"
                + "DEL 1.0000", instance.getString());
    }
}
