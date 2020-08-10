package com.spkitty.frame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AdvSelectFrame extends JFrame{
	
	/**
	 * 'o w  o'
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame grandParent;
	private SimulatorFrame parent;
	
	private JCBoxArray boxes;
	private JButton finishButton;
	
	public AdvSelectFrame(SimulatorFrame init_parent) {
		
		this.setSize(600, 600);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Advanced Selection");
		this.setLayout(new BorderLayout());	
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));

		
		parent = init_parent;
		grandParent = parent.getParent();
		boxes = new JCBoxArray(8, 5);
		String[] comboNames = new String[grandParent.getCombos().size()];
		for(int i = 0; i < comboNames.length; i++)
			comboNames[i] = grandParent.getCombos().get(i).getName();
		if(boxes.loadStrings(comboNames)) {
		}else {
			JOptionPane.showMessageDialog(this, "Error opening advanced form. More than likely you have too many combos; try removing some.\n");
			this.dispose();
		}
	}
	
	public void run() {
		finishButton = new JButton("Finish Selection");
		finishButton.addActionListener((ActionEvent e) -> {
			try {
				parent.loadResults(boxes.getSelection());
				parent.pushText("Advanced list: " + list(boxes.getSelection()));
			}catch(Exception err) {
				JOptionPane.showMessageDialog(parent, "Error with selection.\n" + err.toString());
			}
			this.dispose();
		});
		this.add(boxes, BorderLayout.CENTER);
		this.add(finishButton, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public String list(boolean[] yes) {
		String str = "";
		for(int i = 0; i < yes.length; i++)
			str += yes[i] ? grandParent.getCombos().get(i).getName() + "  " : "";
		return str;
	}
	
}
