/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bangor.gcode.install.gui;

/**
 *
 * @author zc
 */
public class InstallerModel {
    
    private final String defaultInstallPath = "\"C:\\Program Files\\Gcode Translator\"";
    private String intallPath = defaultInstallPath;
    private boolean addShotCutToDesktop = false;

    public String getIntallPath() {
        return intallPath;
    }

    public void setIntallPath(String intallPath) {
        this.intallPath = intallPath;
    }

    public boolean isAddShotCutToDesktop() {
        return addShotCutToDesktop;
    }

    public void setAddShotCutToDesktop(boolean addShotCutToDesktop) {
        this.addShotCutToDesktop = addShotCutToDesktop;
    }
    
    
}
