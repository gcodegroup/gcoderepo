package uk.ac.bangor.gcode.gui;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import uk.ac.bangor.gcode.GcodeException;
import uk.ac.bangor.gcode.GcodeFile;
import uk.ac.bangor.gcode.GcodeRepositoryManager;
import uk.ac.bangor.gcode.GcodeTranslator;
import uk.ac.bangor.gcode.OutputFileWriter;
import uk.ac.bangor.gcode.RunningParameters;
import uk.ac.bangor.gcode.gui.listener.InputFileDocumentListener;
import uk.ac.bangor.gcode.gui.listener.InputFileFilter;
import uk.ac.bangor.gcode.gui.listener.InputFilePathPropertyChangeListener;
import uk.ac.bangor.gcode.gui.listener.GcodeFilePropertyChangeListener;
import uk.ac.bangor.gcode.gui.listener.InputFileTranslatedPropertyChangeListener;
import uk.ac.bangor.gcode.gui.listener.NumericPropertyChangeListener;
import uk.ac.bangor.gcode.gui.listener.OutputDocumentListener;
import uk.ac.bangor.gcode.gui.listener.OutputFileFilter;
import uk.ac.bangor.gcode.gui.listener.OutputFilePathPropertyChangeListener;
import uk.ac.bangor.gcode.gui.listener.ResultSavedPropertyChangeListener;
import uk.ac.bangor.gcode.gui.listener.TranslationPropertyChangeListener;

/**
 * The GcodeJFrame class represents the main frame of this application.
 *
 * @author zc
 */
public final class GcodeJFrame extends JFrame {

    private static final long serialVersionUID = 4194304381471993483L;

    private final GcodeTranslator gcodeTranslator = new GcodeTranslator();
    private final OutputFileWriter writer = new OutputFileWriter();
    private final GcodeModel model = new GcodeModel();

    /**
     * Creates new form GcodeJFrame
     */
    public GcodeJFrame() {

        setTitle("Gcode Translator");
        initComponents();
        inputJFileChooser = new JFileChooser(new File(model.getDefaultInputFilePath()));
        inputJFileChooser.setDialogTitle("Select Input File");
        inputJFileChooser.setFileFilter(new InputFileFilter());

        outputJFileChooser = new JFileChooser(new File(model.getDefaultOutputFilePath()));
        outputJFileChooser.setDialogTitle("Select Output File");
        outputJFileChooser.setFileFilter(new OutputFileFilter());

        inputFileJTextField.setText(model.getInputFilePath());
        inputFileJTextField.getDocument().addDocumentListener(new InputFileDocumentListener(model));
        outputFileJTextField.setText(model.getOutputFilePath());
        outputFileJTextField.getDocument().addDocumentListener(new OutputDocumentListener(model));
        speedJSpinner.setValue(model.getMovingSpeed());
        SpinnerNumberModel speedSpinnerNumberModel = (SpinnerNumberModel) speedJSpinner.getModel();
        speedSpinnerNumberModel.setMinimum(1);
        initialDelayTimeJSpinner.setValue(model.getInitialDelayTime());
        SpinnerNumberModel initialDelayTimeSpinnerNumberModel = (SpinnerNumberModel) initialDelayTimeJSpinner.getModel();
        initialDelayTimeSpinnerNumberModel.setMinimum(0);

        inputFilePathMessageJLabel.setVisible(model.getInputFilePathMessageStatus().isVisible());
        inputFilePathMessageJLabel.setText(model.getInputFilePathMessageStatus().getMessage());
        fileTranslatedJLabel.setVisible(model.getInputFileTranslationMessageStatus().isVisible());
        fileTranslatedJLabel.setText(model.getInputFileTranslationMessageStatus().getMessage());
        outputErrorWarningMessageJLabel.setVisible(model.getResultSavingMessageStatus().isVisible());
        outputErrorWarningMessageJLabel.setText(model.getResultSavingMessageStatus().getMessage());
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved());

        model.addPropertyChangeListener(GcodeModel.INPUT_FILE_PATH_PROPERTY, new InputFilePathPropertyChangeListener(inputFilePathMessageJLabel));
        model.addPropertyChangeListener(GcodeModel.GCODE_FILE_PROPERTY, new GcodeFilePropertyChangeListener(inputJTextArea, saveParametersJMenuItem));
        model.addPropertyChangeListener(GcodeModel.TRANSLATED_TEXT_PROPERTY, new TranslationPropertyChangeListener(outputJTextArea));
        model.addPropertyChangeListener(GcodeModel.START_DELAY_TIME_PROPERTY, new NumericPropertyChangeListener());
        model.addPropertyChangeListener(GcodeModel.SPEED_PROPERTY, new NumericPropertyChangeListener());
        model.addPropertyChangeListener(GcodeModel.INPUT_FILE_TRANSLATED_STATUS_PROPERTY, new InputFileTranslatedPropertyChangeListener(translateJButton, fileTranslatedJLabel));
        model.addPropertyChangeListener(GcodeModel.OUTPUT_FILE_PATH_PROPERTY, new OutputFilePathPropertyChangeListener(saveParametersJMenuItem));
        model.addPropertyChangeListener(GcodeModel.RESULT_SAVED_STATUS_PROPERTY, new ResultSavedPropertyChangeListener(saveOutputJButton, outputErrorWarningMessageJLabel));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "Convert2Lambda"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gcodeTranslateJPanel = new javax.swing.JPanel();
        inputJScrollPane = new javax.swing.JScrollPane();
        inputJTextArea = new javax.swing.JTextArea();
        outputJScrollPane = new javax.swing.JScrollPane();
        outputJTextArea = new javax.swing.JTextArea();
        translateJButton = new javax.swing.JButton();
        inputFileJLabel = new javax.swing.JLabel();
        inputFileJTextField = new javax.swing.JTextField();
        inputFileJButton = new javax.swing.JButton();
        saveOutputJButton = new javax.swing.JButton();
        outputErrorWarningMessageJLabel = new javax.swing.JLabel();
        outputFileJLabel = new javax.swing.JLabel();
        outputFileJTextField = new javax.swing.JTextField();
        selectOutputFileJButton = new javax.swing.JButton();
        initialDelayTimeJLabel = new javax.swing.JLabel();
        initialDelayTimeJSpinner = new javax.swing.JSpinner();
        speedJLabel = new javax.swing.JLabel();
        speedJSpinner = new javax.swing.JSpinner();
        fileTranslatedJLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        inputFilePathMessageJLabel = new javax.swing.JLabel();
        mainJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        saveParametersJMenuItem = new javax.swing.JMenuItem();
        resetDefaultParametersJMenuItem = new javax.swing.JMenuItem();
        exitJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputJTextArea.setEditable(false);
        inputJTextArea.setColumns(20);
        inputJTextArea.setForeground(new java.awt.Color(51, 255, 255));
        inputJTextArea.setRows(5);
        inputJScrollPane.setViewportView(inputJTextArea);

        outputJTextArea.setEditable(false);
        outputJTextArea.setColumns(20);
        outputJTextArea.setForeground(new java.awt.Color(0, 0, 255));
        outputJTextArea.setRows(5);
        outputJTextArea.setEnabled(false);
        outputJScrollPane.setViewportView(outputJTextArea);

        translateJButton.setText("Translate");
        translateJButton.setEnabled(model.getInputFileTranslationMessageStatus().canBeProcessed());
        translateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                translateJButtonActionPerformed(evt);
            }
        });

        inputFileJLabel.setText("Input File:");

        inputFileJButton.setText("Select File");
        inputFileJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFileJButtonActionPerformed(evt);
            }
        });

        saveOutputJButton.setText("Save Output");
        saveOutputJButton.setEnabled(model.getResultSavingMessageStatus().canBeProcessed());
        saveOutputJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOutputJButtonActionPerformed(evt);
            }
        });

        outputErrorWarningMessageJLabel.setForeground(model.getResultSavingMessageStatus().getColor());
        outputErrorWarningMessageJLabel.setText(model.getResultSavingMessageStatus().getMessage());
        outputErrorWarningMessageJLabel.setFocusable(false);

        outputFileJLabel.setText("Output File:");

        selectOutputFileJButton.setText("Select File");
        selectOutputFileJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOutputFileJButtonActionPerformed(evt);
            }
        });

        initialDelayTimeJLabel.setText("Initial Delay Time:");

        initialDelayTimeJSpinner.setValue(model.getInitialDelayTime());
        initialDelayTimeJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                initialDelayTimeJSpinnerStateChanged(evt);
            }
        });

        speedJLabel.setText("Speed:");

        speedJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedJSpinnerStateChanged(evt);
            }
        });

        fileTranslatedJLabel.setForeground(model.getInputFileTranslationMessageStatus().getColor());
        fileTranslatedJLabel.setText(model.getInputFileTranslationMessageStatus().getMessage());

        inputFilePathMessageJLabel.setForeground(model.getInputFilePathMessageStatus().getColor());
        inputFilePathMessageJLabel.setText(model.getInputFilePathMessageStatus().getMessage());

        javax.swing.GroupLayout gcodeTranslateJPanelLayout = new javax.swing.GroupLayout(gcodeTranslateJPanel);
        gcodeTranslateJPanel.setLayout(gcodeTranslateJPanelLayout);
        gcodeTranslateJPanelLayout.setHorizontalGroup(
            gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gcodeTranslateJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputFilePathMessageJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gcodeTranslateJPanelLayout.createSequentialGroup()
                        .addComponent(outputFileJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(outputFileJTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectOutputFileJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gcodeTranslateJPanelLayout.createSequentialGroup()
                        .addComponent(inputFileJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputFileJTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputFileJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputJScrollPane)
                    .addComponent(outputJScrollPane)
                    .addGroup(gcodeTranslateJPanelLayout.createSequentialGroup()
                        .addComponent(saveOutputJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputErrorWarningMessageJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(gcodeTranslateJPanelLayout.createSequentialGroup()
                        .addGroup(gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gcodeTranslateJPanelLayout.createSequentialGroup()
                                .addComponent(initialDelayTimeJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(initialDelayTimeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(speedJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(speedJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(translateJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fileTranslatedJLabel))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        gcodeTranslateJPanelLayout.setVerticalGroup(
            gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gcodeTranslateJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFileJLabel)
                    .addComponent(inputFileJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputFileJButton))
                .addGap(5, 5, 5)
                .addComponent(inputFilePathMessageJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initialDelayTimeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(speedJLabel)
                    .addComponent(speedJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(translateJButton)
                    .addComponent(initialDelayTimeJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileTranslatedJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outputJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputFileJLabel)
                    .addComponent(outputFileJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectOutputFileJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gcodeTranslateJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveOutputJButton)
                    .addComponent(outputErrorWarningMessageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        fileJMenu.setText("File");

        saveParametersJMenuItem.setText("Save Parameters");
        saveParametersJMenuItem.setEnabled(model.isParametersOkToBeSaved());
        saveParametersJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveParametersJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(saveParametersJMenuItem);

        resetDefaultParametersJMenuItem.setText("Reset Default Parameters");
        resetDefaultParametersJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetDefaultParametersJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(resetDefaultParametersJMenuItem);

        exitJMenuItem.setText("Exit");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        mainJMenuBar.add(fileJMenu);

        setJMenuBar(mainJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gcodeTranslateJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gcodeTranslateJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFileJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFileJButtonActionPerformed

        if (inputJFileChooser.showOpenDialog((Component) evt.getSource()) == JFileChooser.APPROVE_OPTION) {
            inputFileJTextField.setText(inputJFileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_inputFileJButtonActionPerformed

    private void selectOutputFileJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOutputFileJButtonActionPerformed
        if (outputJFileChooser.showOpenDialog((Component) evt.getSource()) == JFileChooser.APPROVE_OPTION) {
            outputFileJTextField.setText(outputJFileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_selectOutputFileJButtonActionPerformed

    private void initialDelayTimeJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_initialDelayTimeJSpinnerStateChanged
        JSpinner jSpinner = (JSpinner) evt.getSource();
        model.setInitialDelayTime((int) jSpinner.getValue());
    }//GEN-LAST:event_initialDelayTimeJSpinnerStateChanged

    private void speedJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedJSpinnerStateChanged
        JSpinner jSpinner = (JSpinner) evt.getSource();
        model.setMovingSpeed((int) jSpinner.getValue());
    }//GEN-LAST:event_speedJSpinnerStateChanged

    private void translateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_translateJButtonActionPerformed

        GcodeFile gcodeFile = model.getGcodeFile();
        if (gcodeFile != null) {
            model.setTranslatedText(gcodeTranslator.translate(model.getGcodeFile().getFileLines()));
            model.setInputFileTranslated(true);
        }
    }//GEN-LAST:event_translateJButtonActionPerformed

    private void saveOutputJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOutputJButtonActionPerformed
        writer.write(model.getOutputFilePath(), model.getTranslatedText());
        model.setResultSaved(true);
    }//GEN-LAST:event_saveOutputJButtonActionPerformed

    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed

        String dialogTitle = "Exit";

        if (model.getResultSavingMessageStatus().canBeProcessed()) {
            int result = JOptionPane.showConfirmDialog(saveOutputJButton, "Result has not been saved. Do you want to save it?", dialogTitle, JOptionPane.YES_NO_CANCEL_OPTION);
            switch (result) {
                case JOptionPane.YES_OPTION:
                    writer.write(model.getOutputFilePath(), model.getTranslatedText());
                    this.dispose();
                    AppLock.releaseLock(); // Release lock
                    break;
                case JOptionPane.NO_OPTION:
                    this.dispose();
                    AppLock.releaseLock(); // Release lock
                    break;
                default:
                //Do the cancel operation.
            }
            return;
        }

        int result = JOptionPane.showConfirmDialog(saveOutputJButton, "Are you sure?", dialogTitle, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            this.dispose();
            AppLock.releaseLock(); // Release lock
        }
    }//GEN-LAST:event_exitJMenuItemActionPerformed

    private void saveParametersJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveParametersJMenuItemActionPerformed
        try {

            RunningParameters runningParameters = RunningParameters.getInstance();
            runningParameters.setInputFilePath(model.getInputFilePath());
            runningParameters.setOutputFilePath(model.getOutputFilePath());

            GcodeRepositoryManager.getInstance().writeRunningParameters();
        } catch (IOException ex) {
            throw new GcodeException(ex);
        }
    }//GEN-LAST:event_saveParametersJMenuItemActionPerformed

    private void resetDefaultParametersJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetDefaultParametersJMenuItemActionPerformed
        model.resetRunningParameters();
        RunningParameters parameters = RunningParameters.getInstance();
        inputFileJTextField.setText(null);
        initialDelayTimeJSpinner.setValue(parameters.getInitialDelayTime());
        speedJSpinner.setValue(parameters.getMovingSpeed());
        outputFileJTextField.setText(null);
    }//GEN-LAST:event_resetDefaultParametersJMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JLabel fileTranslatedJLabel;
    private javax.swing.JPanel gcodeTranslateJPanel;
    private javax.swing.JLabel initialDelayTimeJLabel;
    private javax.swing.JSpinner initialDelayTimeJSpinner;
    private javax.swing.JButton inputFileJButton;
    private javax.swing.JLabel inputFileJLabel;
    private javax.swing.JTextField inputFileJTextField;
    private javax.swing.JLabel inputFilePathMessageJLabel;
    private javax.swing.JScrollPane inputJScrollPane;
    private javax.swing.JTextArea inputJTextArea;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar mainJMenuBar;
    private javax.swing.JLabel outputErrorWarningMessageJLabel;
    private javax.swing.JLabel outputFileJLabel;
    private javax.swing.JTextField outputFileJTextField;
    private javax.swing.JScrollPane outputJScrollPane;
    private javax.swing.JTextArea outputJTextArea;
    private javax.swing.JMenuItem resetDefaultParametersJMenuItem;
    private javax.swing.JButton saveOutputJButton;
    private javax.swing.JMenuItem saveParametersJMenuItem;
    private javax.swing.JButton selectOutputFileJButton;
    private javax.swing.JLabel speedJLabel;
    private javax.swing.JSpinner speedJSpinner;
    private javax.swing.JButton translateJButton;
    // End of variables declaration//GEN-END:variables
    private final JFileChooser inputJFileChooser;
    private final JFileChooser outputJFileChooser;
}
