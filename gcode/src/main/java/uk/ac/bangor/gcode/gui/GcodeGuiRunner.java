/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode.gui;

import org.apache.log4j.Logger;
import uk.ac.bangor.gcode.GcodeRepositoryManager;

/**
 *
 * @author zc
 */
public class GcodeGuiRunner {

    /**
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

            gcodeRepositoryManager.readRunningParameters();
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                new GcodeJFrame().setVisible(true);
            });

        } catch (Throwable th) {
            logger.error(th);
            System.exit(-1);
        }
        //</editor-fold>
    }
}
