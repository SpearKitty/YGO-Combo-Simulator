package com.spkitty.frame;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.spkitty.combo.Deck;

public class JDeckDisplay extends JPanel{
	
	/**
	 * =uwu=
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel[][] labels;
	
	/**
	 * jpanel to display deck images in the frame
	 */
	public JDeckDisplay() {
		
		labels = new JLabel[6][10];
		
		this.setLayout(new GridLayout(6, 10, 2, 2));
		
		for(int i = 0; i < labels.length; i++) {
			for(int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				this.add(labels[i][j]);
			}
		}
	}
	
	/**
	 * loads deck images into jlabel icon slots
	 * @param deck
	 */
	public void loadDeck(Deck deck) {
		for(int i = 0; i < labels.length; i++) {
			for(int j = 0; j < labels[i].length; j++) {
				try {
					labels[i][j].setIcon(new ImageIcon(resize(deck.getCard(i*10+j).getImage(), 60, 80)));
				}catch(Exception e) {
					labels[i][j].setIcon(null);
				}
			}
		}
	}
	
	/**
	 * sets all label icons to null
	 */
	public void clearImages() {
		for(int i = 0; i < labels.length; i++) 
			for(int j = 0; j < labels[i].length; j++) 
				labels[i][j].setIcon(null);
	}
	
	/**
	 * resizes given image to an image of nWid and nHgt
	 * @param img
	 * @param nWid
	 * @param nHgt
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int nWid, int nHgt) {
		BufferedImage ret = new BufferedImage(nWid, nHgt, BufferedImage.TYPE_INT_ARGB);
		Image temp = img.getScaledInstance(nWid, nHgt, Image.SCALE_SMOOTH);
		
		ret.getGraphics().drawImage(temp, 0, 0, null);
		
		return ret;
	}
	
}
