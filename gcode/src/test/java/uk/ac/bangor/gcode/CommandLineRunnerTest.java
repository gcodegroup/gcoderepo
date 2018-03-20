package uk.ac.bangor.gcode;

import java.io.File;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test of class CommandLineRunner.
 *
 * @author zc
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CommandLineRunner.class, GcodeRepositoryManager.class, Logger.class, RunningParameters.class})
public final class CommandLineRunnerTest {

    private final GcodeRepositoryManager gcodeRepositoryManager = PowerMockito.mock(GcodeRepositoryManager.class);
    private final Logger logger = Mockito.mock(Logger.class);
    private final RunningParameters runningParameters = PowerMockito.mock(RunningParameters.class);    
    private final String[] args = {"    --inPut.fiLe.patH=myinputFile/aaa", "   --initIal.DELAY.time=57", " --properties.filE=myhome/ppp.prop", " --moVing.speeD=12", "--ouTput.filE.pSth=C:\\temp\\test"};
    private final File inputFile = Mockito.mock(File.class);
    
    @Before
    public void setUp() throws Exception {

        PowerMockito.mockStatic(GcodeRepositoryManager.class);
        Mockito.when(GcodeRepositoryManager.getInstance()).thenReturn(gcodeRepositoryManager);
        
        PowerMockito.mockStatic(Logger.class);
        Mockito.when(Logger.getLogger(CommandLineRunner.class)).thenReturn(logger);
        
        PowerMockito.mockStatic(RunningParameters.class);
        Mockito.when(RunningParameters.getInstance()).thenReturn(runningParameters);
        PowerMockito.whenNew(File.class).withArguments("myinputfile/aaa").thenReturn(inputFile);
    }

    @Test
    public void testMain() throws Exception {
        String[] args = null;
        CommandLineRunner.main(args);
        
        
        Mockito.verify(logger).info("Start Gcode Translator......");
        Mockito.verify(runningParameters).setUseDefaultConfig(false);
    }

}
