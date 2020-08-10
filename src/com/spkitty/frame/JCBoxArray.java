package com.spkitty.frame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class JCBoxArray extends JPanel{
	
	/**
	 * umu
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox[] boxes;
	
	public JCBoxArray(int size, int init_wid) {
		this.setLayout(new GridLayout(init_wid, size / init_wid));
		boxes = new JCheckBox[size];
	}
	
	public boolean loadStrings(String[] items) {
		if(items.length > boxes.length)
			return false;
		for(int i = 0; i < items.length; i++) {
			boxes[i] = new JCheckBox(items[i]);
			this.add(boxes[i]);
		}
		return true;
	}
	
	public boolean[] getSelection() {
		if(boxes.length == 0)
			return null;
		boolean[] results = new boolean[boxes.length];
		for(int i = 0; i < boxes.length; i++)
			try {
				results[i] = boxes[i].isSelected();
			}catch(Exception e) {
				return results;
			}
		return results;
	}
	
}
