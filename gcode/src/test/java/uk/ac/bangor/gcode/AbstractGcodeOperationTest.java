package uk.ac.bangor.gcode;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zc
 */
public class AbstractGcodeOperationTest {
    
    private final AbstractGcodeOperation instance1 = new AbstractGcodeOperationImpl("mystring");
    private final AbstractGcodeOperation instance2 = new AbstractGcodeOperationImpl("mystring");
    private final AbstractGcodeOperation instance3 = new AbstractGcodeOperationImpl("newstring");    
    
    
    @Test
    public void testGetString() {
        assertEquals("mystring", instance1.getString());
    }

    @Test
    public void testHashCode() {
        
        assertEquals(instance2.hashCode(), instance1.hashCode());
        assertNotEquals(instance3.hashCode(), instance1.hashCode());
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEquals() {
        assertTrue(instance1.equals(instance1));
        assertTrue(instance1.equals(instance2));
        assertFalse(instance1.equals(instance3));
        assertFalse(instance1.equals(new Object()));
        assertFalse(instance1.equals(null));        
    }

    private static final class AbstractGcodeOperationImpl extends AbstractGcodeOperation {

        public AbstractGcodeOperationImpl(String string) {
            super(string);
        }
    }
    
}
