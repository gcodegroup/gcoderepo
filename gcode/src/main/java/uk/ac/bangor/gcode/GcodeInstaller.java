/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author zc
 */
public class GcodeInstaller {

    public void install(String path, boolean addShotCutToDesktop) {
        GcodeRepositoryManager gcodeRepositoryManager = GcodeRepositoryManager.getInstance();
        Logger logger = gcodeRepositoryManager.getLogger(CommandLineRunner.class);
        try {
            logger.info("Start to install Gcode Translator. Please wait......");
            logger.info("Copy files......");
            FileUtils.copyDirectory(new File ("."), new File(path));
        } catch (Throwable th) {
            logger.info("Failed to install Gcode Translator.", th);
        }
    }
    
    
}
