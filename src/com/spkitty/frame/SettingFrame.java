package com.spkitty.frame;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SettingFrame extends JFrame  {

	/**
	 * 0w0
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private MainFrame parent;
	
	private JTextField path;
	
	private JLabel pathLabel;
	
	private JButton saveButton;
	private JButton quitButton;
	
	public SettingFrame(MainFrame init_parent) {
		this.setSize(400, 300);
		this.setTitle("Settings");
		this.setLocationRelativeTo(init_parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(4, 1));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
		this.setResizable(false);
		parent = init_parent;
	}
	
	public void run() {
		path = new JTextField(SettingManager.getPath());
		pathLabel = new JLabel("\tProjectIgnis Path:");
		
		quitButton = new JButton("Cancel");
		saveButton = new JButton("Save");
		
		quitButton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		
		saveButton.addActionListener((ActionEvent e) -> {
			try {
				SettingManager.path = path.getText().trim();
				SettingManager.saveSettings();
				this.dispose();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error saving settings.\n" + err.toString());
				this.dispose();
			}
		});
		
		this.add(pathLabel);
		this.add(path);
		this.add(quitButton);
		this.add(saveButton);
		
		this.setVisible(true);
	}
	
}
