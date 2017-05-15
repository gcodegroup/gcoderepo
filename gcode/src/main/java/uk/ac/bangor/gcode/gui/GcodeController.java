package uk.ac.bangor.gcode.gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.LineReader;

public class GcodeController {
    
    private final LineReader lineReader = new LineReader();
    private final GcodeTranslator gcodeTranslator = new GcodeTranslator();
    private BrowseAcitonListener browseAcitonListener;
    private TranslateActionListener translateActionListener;
    private SaveActionListener saveActionListener;
    
    public void startGui() {
        
        FilledFrame frame = new FilledFrame();
        frame.setTitle("gcode to GCS Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getFc().setCurrentDirectory(new java.io.File("."));
        frame.getFc().setDialogTitle("Choose File");
        frame.getFc().setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);   
        
        browseAcitonListener = new BrowseAcitonListener(frame, lineReader, gcodeTranslator);
        frame.getFileChooseButton().addActionListener(browseAcitonListener);
        
        translateActionListener = new TranslateActionListener(frame.getOutputArea(), lineReader, gcodeTranslator);
        frame.getTranslateButton().addActionListener(translateActionListener);
        
        saveActionListener = new SaveActionListener(frame.getFc(), frame.getFileChooseButton(), gcodeTranslator);
        frame.getSaveButton().addActionListener(saveActionListener);
        
        frame.setVisible(true);        
    }
    
}
