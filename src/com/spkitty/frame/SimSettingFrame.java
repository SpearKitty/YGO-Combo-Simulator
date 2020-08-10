package com.spkitty.frame;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class SimSettingFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private MainFrame parent;
	
	private JSlider countSlider;
	private JSlider sizeSlider;
	private JLabel countLabel;
	private JLabel sizeLabel;
	
	public SimSettingFrame(MainFrame init_parent) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(250, 160);
		this.setTitle("Sim Settings");
		this.setLayout(new GridLayout(4, 1));
		this.setResizable(false);
		this.setLocationRelativeTo(init_parent);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));

		parent = init_parent;
	}
	
	public void run() {
		countLabel = new JLabel("Distrib. Depth [" + SettingManager.count + "]");
		sizeLabel = new JLabel("Distrib. Size [" + SettingManager.distribSize + "]");
		
		countSlider = new JSlider(1, 100000, SettingManager.count);
		sizeSlider = new JSlider(1, 50, SettingManager.distribSize);

		countSlider.addChangeListener((ChangeEvent e) -> {
			SettingManager.count = countSlider.getValue();
			countLabel.setText("Distrib. Depth [" + SettingManager.count + "]");
		});
		
		sizeSlider.addChangeListener((ChangeEvent e) -> {
			SettingManager.distribSize = sizeSlider.getValue();
			sizeLabel.setText("Distrib. Size [" + SettingManager.distribSize + "]");
		});
		
		this.add(countLabel);
		this.add(countSlider);
		this.add(sizeLabel);
		this.add(sizeSlider);
		
		this.setVisible(true);
	}
	
	public void dispose() {
		SettingManager.saveSettings();
		super.dispose();
	}
	
}
