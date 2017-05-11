package uk.ac.bangor.gcode.gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
public class FilledFrame extends JFrame {

    private static final long serialVersionUID = 13414296239305400L;    
    
	private static final int FRAME_WIDTH=1280;
	private static final int FRAME_HEIGHT=700;
	
	private static final int AREA_ROWS = 10;
	private static final int AREA_COLUMNS = 30;
	
	private static final int buttonX = 110;
	private static final int buttonY = 25;

	
	private JLabel fileLabel, saveLabel;
	private JTextArea inputArea, outputArea;
	private JButton fileChooseButton;
	private JButton translateButton;
	private JButton saveButton;
	private final JFileChooser fc;
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
	public FilledFrame() {
		createFileChooseButton();
		createTranslateButton();
		createSaveButton();
		createInputArea();
		createOutputArea();
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		createPanel();
		fc = new JFileChooser();
	}
	
	private void createInputArea() {
		inputArea = new JTextArea(AREA_ROWS,AREA_COLUMNS);
		inputArea.setEditable(false);
	}
	
	private void createOutputArea() {
		outputArea = new JTextArea(AREA_ROWS,AREA_COLUMNS);
		outputArea.setEditable(false);
	}
	
	private void createFileChooseButton() {
		fileChooseButton = new JButton("choose file");
		fileChooseButton.setPreferredSize(new Dimension(buttonX,buttonY));
	}
	
	public JLabel getSaveLabel() {
		return saveLabel;
	}

	public void setSaveLabel(JLabel saveLabel) {
		this.saveLabel = saveLabel;
	}

	private void createTranslateButton() {
		translateButton = new JButton("Preview>>");
		translateButton.setPreferredSize(new Dimension(buttonX,buttonY));
	}
	
	private void createSaveButton() {
		saveButton = new JButton("Save As");
		saveButton.setPreferredSize(new Dimension(buttonX,buttonY));
	}
	
	
	private void createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel nPanel = createNorthPanel();
		JPanel cPanel = createGBPanel();
		
		panel.add(nPanel, BorderLayout.NORTH);
		panel.add(cPanel, BorderLayout.CENTER);
		add(panel);
	}
	
	private JPanel createGBPanel() {
		JPanel panel=new JPanel();
		
		JScrollPane inAreaScroll = new JScrollPane(inputArea);
		inAreaScroll.setPreferredSize(new Dimension(500,600));
		
		JScrollPane outAreaScroll = new JScrollPane(outputArea);
		outAreaScroll.setPreferredSize(new Dimension(500,600));
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
		                //natural height, maximum width
		                c.fill = GridBagConstraints.HORIZONTAL;
		}
                
		c.gridwidth=1;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx=0.5;
		c.weighty=0.5;
		
		c.gridx=0; c.gridy=0;
		panel.add(inAreaScroll,c);
		
		c.gridx=1; c.gridy=0;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx=0;
		panel.add(translateButton, c);
		
		c.gridx=2; c.gridy=0;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx=0.5;
		c.weighty=0.5;
		panel.add(outAreaScroll, c);

		return panel;
	}
		
	private JPanel createNorthPanelTop() {
		JPanel panel = new JPanel();
		fileLabel=new JLabel("");
		panel.setLayout(new BorderLayout());
		panel.add(fileChooseButton, BorderLayout.WEST);
		panel.add(fileLabel, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createNorthPanelBottom() {
		JPanel panel = new JPanel();
		saveLabel=new JLabel("");
		panel.setLayout(new BorderLayout());
		panel.add(saveButton, BorderLayout.WEST);
		panel.add(saveLabel, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		JPanel top = createNorthPanelTop();
		JPanel bottom = createNorthPanelBottom();
		panel.setLayout(new GridLayout(2,1));
		panel.add(top);
		panel.add(bottom);
		return panel;
	}
		
	public JFileChooser getFc() {
		return fc;
	}

	public JLabel getFileLabel() {
		return fileLabel;
	}

	public JTextArea getInputArea() {
		return inputArea;
	}

	public JTextArea getOutputArea() {
		return outputArea;
	}

	public JButton getFileChooseButton() {
		return fileChooseButton;
	}

	public JButton getTranslateButton() {
		return translateButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}	
	
	
}
