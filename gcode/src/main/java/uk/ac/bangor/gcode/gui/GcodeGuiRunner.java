package uk.ac.bangor.gcode.gui;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.log4j.Logger;
import uk.ac.bangor.gcode.GcodeException;
import uk.ac.bangor.gcode.GcodeRepositoryManager;

/**
 * The GcodeGuiRunner class contains the main method to run the application in
 * GUI mode.
 *
 * @author zc
 */
public class GcodeGuiRunner {

    /**
     * The main method to run the application in GUI mode.
     *
     * @param args the command line arguments
     */
    @SuppressWarnings("UseSpecificCatch")
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        GcodeRepositoryManager gcodeRepositoryManager = GcodeRepositoryManager.getInstance();
        Logger logger = gcodeRepositoryManager.getLogger(GcodeGuiRunner.class);
        
        try {

            // Try to get LOCK //
            logger.debug("Try to get the application lock......");
            if (!AppLock.setLock("gcode.customer.lock.key")) {
                throw new GcodeException("Fail to set the application lock.\n"
                        + "Only one application instance may run at the same time!\n"
                        + "Check if you have another instance opened.\n "
                        + "The problem may persist if your applicaiton did NOT terminate normally last time.\n"
                        + "In this case you have to restart your computer.");
            }
            logger.debug("The application lock has been set OK.");
            
            logger.debug("Read the parameters......");
            gcodeRepositoryManager.readRunningParameters();
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                logger.debug("Start the GUI......");
                new GcodeJFrame().setVisible(true);
                logger.debug("The GUI starts OK.");
            });
            
        } catch (Throwable th) {
            logger.error(th);
            AppLock.releaseLock(); // Release lock
            JOptionPane.showMessageDialog(null, th.getMessage(), "Error", JOptionPane.OK_OPTION);
            System.exit(-1);
            
        } //</editor-fold>
    }
}
