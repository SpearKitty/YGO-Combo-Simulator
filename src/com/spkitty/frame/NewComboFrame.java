package com.spkitty.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.spkitty.combo.Card;
import com.spkitty.combo.Combo;

public class NewComboFrame extends JFrame  {

	/**
	 * uwu
	 */
	private static final long serialVersionUID = 1L;

	private MainFrame parent; 
	
	private Card[] loadedCards;
	
	private Combo combo;
	
	private JTextField nameArea;
	private JTextField previewText;
	private JCheckBox isGarnetBox;
	private JButton addCard, removeCard, finishCombo;
	private JPanel buttonPanel;
	private JLabel idLabel;
	private JComboBox<ImageIcon> dropDown;
	
	private boolean isEditMode;
	
	public NewComboFrame(MainFrame init_parent) {
		parent = init_parent;
		this.setSize(400, 300);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
		this.setTitle("New Combo");
		this.setLayout(new BorderLayout());
		combo = new Combo();
		loadedCards = parent.getUniqueCards();
		isEditMode = false;
	}
	
	public NewComboFrame(MainFrame init_parent, Combo passed_combo) {
		parent = init_parent;
		this.setSize(400, 300);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
		this.setTitle("Edit Combo");
		this.setLayout(new BorderLayout());
		combo = passed_combo;
		loadedCards = parent.getUniqueCards();
		isEditMode = true;
	}
	
	public void run() {
		//init general components
		nameArea = new JTextField(isEditMode ? combo.getName() : "unnamed_combo");
		nameArea.setEditable(!isEditMode);
		previewText = new JTextField(""); previewText.setEnabled(false);
		
		isGarnetBox = new JCheckBox("Garnet");
		
		buttonPanel = new JPanel();
		addCard = new JButton("Add Card");
		removeCard = new JButton("Remove Card");
		finishCombo = new JButton("Finish Combo");
		
		addCard.addActionListener((ActionEvent e) -> {
			try {
				combo.setName(nameArea.getText());
				if(isGarnetBox.isSelected())
					combo.addGarnet(loadedCards[dropDown.getSelectedIndex()]);
				else
					combo.addCard(loadedCards[dropDown.getSelectedIndex()]);
				previewText.setText(combo.toString());
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error adding card.");
			}
		});
		
		removeCard.addActionListener((ActionEvent e) -> {
			try {
				combo.setName(nameArea.getText());
				if(isGarnetBox.isSelected())
					combo.removeGarnet(loadedCards[dropDown.getSelectedIndex()]);
				else
					combo.removeCard(loadedCards[dropDown.getSelectedIndex()]);
				previewText.setText(combo.toString());
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error removing card.");
			}
		});
		
		finishCombo.addActionListener((ActionEvent e) ->{
			try {
				if(parent.pushCombo(combo) || (isEditMode && parent.updateCombo(combo))) {
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "Could not " + (isEditMode ? "edit" : "save") + " combo.");
				}
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error pushing combo to parent list.");
			}
		});
		
		//init images, set up dropdown
		ImageIcon[] img = new ImageIcon[loadedCards.length];
		for(int i = 0; i < loadedCards.length; i++)
			img[i] = new ImageIcon(JDeckDisplay.resize(loadedCards[i].getImage(), 100, 144));
		dropDown = new JComboBox<ImageIcon>(img);
		dropDown.setMaximumRowCount(4);
		dropDown.addActionListener((ActionEvent e) -> {
			idLabel.setText("Card ID: " + loadedCards[dropDown.getSelectedIndex()]);
		});
		
		idLabel = new JLabel("Card ID: " + loadedCards[dropDown.getSelectedIndex()]);

		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.add(idLabel);
		buttonPanel.add(addCard);
		buttonPanel.add(removeCard);
		buttonPanel.add(finishCombo);
		
		previewText.setText(combo.toString());
		
		this.add(nameArea, BorderLayout.NORTH);
		this.add(previewText, BorderLayout.SOUTH);
		this.add(isGarnetBox, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.LINE_END);
		this.add(dropDown, BorderLayout.LINE_START);
		this.setVisible(true);
	}

}
