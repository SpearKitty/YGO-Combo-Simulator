package com.spkitty.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.spkitty.combo.Simulator;
import com.spkitty.combo.Combo;

public class SimulatorFrame extends JFrame  {
	
	/**
	 * ;w;
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	
	private boolean[] selectorResults;
	
	private JScrollPane outputScroller;
	private JTextArea output;
	private JPanel operatorPanel;
	private JButton run;
	private JButton advSelect;
	private JComboBox<String> comboSelect;
	private JCheckBox useAll;
	private JCheckBox firstSecond;
	
	public SimulatorFrame(MainFrame init_parent) {
		this.setSize(600, 400);
		this.setTitle("Simulator Window");
		this.setLocationRelativeTo(init_parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
		this.setResizable(false);
		parent = init_parent;
	}
	
	public void run() {
		//init
		output = new JTextArea(); output.setEditable(false);
		outputScroller = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		operatorPanel = new JPanel(new GridLayout(1, 5));
		run = new JButton("Run");
		comboSelect = new JComboBox<String>(); comboSelect.setMaximumRowCount(5);
		useAll = new JCheckBox("Use All");
		firstSecond = new JCheckBox("Going Second");
		advSelect = new JButton("Advanced");
		selectorResults = new boolean[0];

		for(int i = 0; i < parent.getCombos().size(); i++)
			comboSelect.addItem(parent.getCombos().get(i).getName());
		
		//adding listeners
		run.addActionListener((ActionEvent e) -> {
			try {
				if(selectorResults.length == 0) {
					Simulator simObj = new Simulator(parent.getDeckObj(), firstSecond.isSelected() ? 6 : 5);
					String str = useAll.isSelected() ? "All Combo Distrib: " : (comboSelect.getItemAt(comboSelect.getSelectedIndex()).toString() + " Distrib: ");
					double[] distribution = useAll.isSelected() ? simObj.createMassDistrib(parent.getCombos(), SettingManager.count, SettingManager.distribSize) : simObj.createComboDistrib(parent.getCombos().get(this.comboSelect.getSelectedIndex()), SettingManager.count, SettingManager.distribSize);
					for(int i = 0; i < distribution.length; i++) 
						str += String.format("%2.4f%s  ", distribution[i]*100, "%");
					str += String.format("\n\tAverage: %2.4f%s\n", simObj.collapseDistrib(distribution)*100, "%");
					output.append(str);
				}else {
					Simulator simObj = new Simulator(parent.getDeckObj(), firstSecond.isSelected() ? 6 : 5);
					String str = "Custom Distrib: ";
					ArrayList<Combo> temp = new ArrayList<Combo>();
					for(int i = 0; i < selectorResults.length; i++)
						if(selectorResults[i])
							temp.add(parent.getCombos().get(i));
					double[] distribution = simObj.createMassDistrib(temp, SettingManager.count, SettingManager.distribSize);
					for(int i = 0; i < distribution.length; i++) 
						str += String.format("%2.4f%s  ", distribution[i]*100, "%");
					str += String.format("\n\tAverage: %2.4f%s\n", simObj.collapseDistrib(distribution)*100, "%");
					output.append(str);
				}
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error running sim.\n" + err.toString());
			}
			selectorResults = new boolean[0];
		});
		
		useAll.addActionListener((ActionEvent e) -> {
			comboSelect.setEnabled(!comboSelect.isEnabled());
		});
		
		advSelect.addActionListener((ActionEvent e) -> {
			try {
				AdvSelectFrame select = new AdvSelectFrame(this);
				select.run();
			}catch(Exception err) {
				
			}
		});
		
		//addint and whatnot
		operatorPanel.add(run);
		operatorPanel.add(comboSelect);
		operatorPanel.add(useAll);
		operatorPanel.add(firstSecond);
		operatorPanel.add(advSelect);
		
		this.add(outputScroller, BorderLayout.CENTER);
		this.add(operatorPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void loadResults(boolean[] results) {
		selectorResults = results;
	}
	
	public void pushText(String text) {
		this.output.append(text + "\n");
	}
	
	public MainFrame getParent() {
		return this.parent;
	}
}
