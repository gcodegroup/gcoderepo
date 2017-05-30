package uk.ac.bangor.gcode;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommandLineRunner.class, GcodeRepositoryManager.class})
public final class CommandLineRunnerTest {
    
    private final GcodeRepositoryManager gcodeRepositoryManager = PowerMockito.mock(GcodeRepositoryManager.class);
    private final Logger logger = Mockito.mock(Logger.class);
    private final String[] args = {"", "", "", "", ""};
    
    
    @Before
    public void setUp() {
    }

    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        CommandLineRunner.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
