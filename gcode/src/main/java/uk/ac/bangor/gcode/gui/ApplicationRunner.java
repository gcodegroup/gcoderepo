package uk.ac.bangor.gcode.gui;

public class ApplicationRunner {

    public static void main(String[] args) {
        try {
            new GcodeController().startGui();
            
        } catch(Throwable th) {
            
            //TODO Logging
            
            throw th;
        }      
    }
}
