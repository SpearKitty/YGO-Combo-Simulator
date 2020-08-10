package com.spkitty.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewCombosFrame extends JFrame{

	/**
	 * >:(
	 */
	private static final long serialVersionUID = 1L;

	private MainFrame parent;
	
	private JTextField text;
	private JComboBox<String> combos;
	private JPanel buttonPanel;
	private JButton editButton;
	private JButton deleteButton;
	
	public ViewCombosFrame(MainFrame init_parent) {
		parent = init_parent;
		this.setSize(400, 300);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Current Combos");
	}
	
	public void run() {
		//init all
		text = new JTextField(parent.getCombos().size() == 0 ? "" : parent.getCombos().get(0).toString()); text.setEnabled(false);
		combos = new JComboBox<String>(); combos.setMaximumRowCount(5);
		buttonPanel = new JPanel(); buttonPanel.setLayout(new GridLayout(1, 2));
		editButton = new JButton("Edit Combo");
		deleteButton = new JButton("Delete Combo");
		buttonPanel.add(editButton); buttonPanel.add(deleteButton);
		
		//load combo names into dropdown
		for(int i = 0; i < parent.getCombos().size(); i++)
			combos.addItem(parent.getCombos().get(i).getName());
		
		//listeners
		combos.addActionListener((ActionEvent e) -> {
			try {
				text.setText(parent.getCombos().size() == 0 ? "" : parent.getCombos().get(combos.getSelectedIndex()).toString());
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error with combo dropdown.");
			}
		});
		
		editButton.addActionListener((ActionEvent e) -> {
			try {
				NewComboFrame combo = new NewComboFrame(parent, parent.getCombos().get(combos.getSelectedIndex()));
				combo.run();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error with edit frame.");
			}
		});
		
		deleteButton.addActionListener((ActionEvent e) -> {
			try {
				parent.deleteCombo(parent.getCombos().get(combos.getSelectedIndex()));
				combos.removeItemAt(combos.getSelectedIndex());
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error deleting combo.");
			}
		});
		
		//adding items & finishing
		this.add(text, BorderLayout.NORTH);
		this.add(combos, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
}
