package com.spkitty.frame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.spkitty.combo.Card;
import com.spkitty.combo.Combo;
import com.spkitty.combo.Deck;

public class MainFrame extends JFrame {
	
	//"i'll never use swing again" -me 3 swing projects ago
	//"okay, swing isn't _that_ bad" -me before starting this project
	//"i want to fucking gouge my eyes out" -me, currently
	
	/**
	 * e
	 */
	private static final long serialVersionUID = 1L;
	
	private Deck loadedDeck;
	private ArrayList<Combo> combos;
	
	private JMenuBar bar;
	private JMenu fileMenu, comboMenu, simMenu, helpMenu;
	private JMenuItem loadDeck, settings, exit, 
					  newCombo, viewCombos,
					  simSettings, runSim,
					  about, how_to_use;
	
	private JDeckDisplay display;

	public MainFrame() {
		this.setSize(600, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("YGO Combo Simulator");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
		//this.setResizable(false);
	}
	
	public void displayDeck() {
		display.clearImages();
		display.loadDeck(this.loadedDeck);
	}
	
	public void encodeCombos(String destination) {
		
	}
	
	public String decodeCombos(String target) {
		return "";
	}
	
	public void run() {
		//general init
		loadedDeck = new Deck();
		combos = new ArrayList<Combo>();
		display = new JDeckDisplay();
		
		//displayScroll.setLayout(new BorderLayout());
		display.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.getContentPane().add(display, BorderLayout.CENTER);
		
		//dropdown top bar initializations 
		bar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		comboMenu = new JMenu("Combo");
		simMenu = new JMenu("Simulator");
		helpMenu = new JMenu("Help");
		
		//file bar item init/event listeners 
		loadDeck = new JMenuItem("Load Deck");
		settings = new JMenuItem("Settings");
		exit = new JMenuItem("Exit");
		
		loadDeck.addActionListener((ActionEvent e) -> {
			try {
				JFileChooser open = new JFileChooser();
				open.setFileFilter(new FileNameExtensionFilter("YuGiOh Deck Files (.ydk)", "ydk"));
				open.setCurrentDirectory(new File(SettingManager.buildDeckPath()));
				if(open.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					loadedDeck.importDeck(open.getSelectedFile().getName());
					displayDeck();
				}
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error loading deck.\n" + err.toString());
			}
		});
		
		settings.addActionListener((ActionEvent e) -> {
			try {
				SettingFrame set = new SettingFrame(this);
				set.run();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error opening settings.\n" + err.toString());
			}
		});
		
		exit.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		
		//combo item init/event listeners
		newCombo = new JMenuItem("New Combo");
		viewCombos = new JMenuItem("View Combos");
		
		newCombo.addActionListener((ActionEvent e)->{
			try {
				NewComboFrame combo = new NewComboFrame(this);
				combo.run();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error opening new combo.\n" + err.toString());
			}
		});
		
		viewCombos.addActionListener((ActionEvent e)->{
			try {
				ViewCombosFrame vCombo = new ViewCombosFrame(this);
				vCombo.run();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error in viewing combos.\n" + err.toString());
			}
		});
		
		//sim item init/event listeners
		runSim = new JMenuItem("Run Simulation");
		simSettings = new JMenuItem("Sim Settings");
		
		runSim.addActionListener((ActionEvent e) -> {
			try {
				SimulatorFrame sim = new SimulatorFrame(this);
				sim.run();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error in viewing combos.\n" + err.toString());
			}
		});
		
		simSettings.addActionListener((ActionEvent e) -> {
			try {
				SimSettingFrame set = new SimSettingFrame(this);
				set.run();
			}catch(Exception err) {
				JOptionPane.showMessageDialog(this, "Error with sim setting frame.\n" + err.toString());
			}
		});
		
		//help item init/event listeners
		about = new JMenuItem("About");
		how_to_use = new JMenuItem("How to use");

		about.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(this, "Yu-Gi-Oh! combo probability simulator.\n"
											  + "This application was made by SpearKitty#9999 as a\n"
											  + "practice project for testing sets of combo openings.\n"
											  + "\n\nVersion " + SettingManager.VERSION + "\n"
											  + "All code is open source and under the MIT License, and can be found at:\n\thttps://github.com/SpearKitty/YGO-Combo-Simulator");
		});
		
		how_to_use.addActionListener((ActionEvent e) ->{
			JOptionPane.showMessageDialog(this, "Cards images and decks are imported from the given ProjectIgnis folder.\n"
											  + "Combos are added under the combo tab and tested under the sim tab.\n"
											  + "Combos can be tested alone, as a batch, or in totality.\n"
											  + "Combos can be edited or deleted from the View Combo window.\n"
											  + "In Simulator Settings,\n\t"
											  + "\"Distrib. Depth\" refers to how many times each member of\n\t\t"
											  + "the simulation will be iterated.\n\t"
											  + "\"Distrib. Size\" refers to how many times the simulation\n\t\t"
											  + "will be run through to depth.");
		});
		
		//add all items to frame
		bar.add(fileMenu);
		
		fileMenu.add(loadDeck);
		fileMenu.add(settings);
		fileMenu.add(exit);
		
		bar.add(comboMenu);
		
		comboMenu.add(newCombo);
		comboMenu.add(viewCombos);
		
		bar.add(simMenu);
		
		simMenu.add(runSim);
		simMenu.add(simSettings);
		
		bar.add(helpMenu);

		helpMenu.add(about);
		helpMenu.add(how_to_use);
		
		this.setJMenuBar(bar);
		
		//finally set visible
		this.setVisible(true);
	}
	
	/**
	 * @return all unique card objects within the deck
	 */
	public Card[] getUniqueCards() {
		return loadedDeck.uniqueCards();
	}
	
	public Deck getDeckObj() {
		return this.loadedDeck;
	}
	
	public boolean deleteCombo(Combo obj) {
		for(int i = 0; i < combos.size(); i++) 
			if(combos.get(i).getName().equals(obj.getName())) {
				combos.remove(i);
				return true;
			}
		return false;
	}
	
	public boolean pushCombo(Combo obj) {
		for(int i = 0; i < combos.size(); i++) 
			if(combos.get(i).getName().equals(obj.getName()))
				return false;
		combos.add(obj);
		return true;
	}
	
	public boolean updateCombo(Combo obj) {
		int index = -1;
		for(int i = 0; i <= combos.size(); i++) {
			if(i == combos.size())
				return false;
			if(combos.get(i).getName().equals(obj.getName())) {
				index = i;
				break;
			}
		}
		combos.remove(index);
		combos.add(obj);
		return true;
	}
	
	public ArrayList<Combo> getCombos(){
		return combos;
	}
}
