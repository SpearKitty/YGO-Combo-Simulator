package com.spkitty.combo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.spkitty.frame.SettingManager;

/**
 * Object to store both the id and bufferedimage of the given card
 * @author SpearKitty
 */

public class Card {
	
	private int id;
	private BufferedImage img;
	
	/**
	 * Initializes new card object with given ID; Attempts to find .jpg image in IGNIS_PATH with given ID name, 
	 * @param init_id String to convert to int ID
	 */
	
	public Card(String init_id) {
		id = Integer.parseInt(init_id);
		try{
			img = ImageIO.read(new File(SettingManager.getPath() + "pics\\" + init_id + ".jpg"));
		}catch(Exception e) {
			img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		}
	}
	
	/**
	 * @return integer form of given ID in initialization
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return BufferedImage of the image pulled from [id].jpg in Path->pics
	 */
	
	public BufferedImage getImage() {
		return img;
	}
	
	/**
	 * Compares this and another card objects and returns true if they are equal or false if they are not; depends on compareTo
	 * @param obj
	 * @return
	 */
	
	public boolean equals(Card obj) {
		return compareTo(obj) == 0;
	}
	
	/**
	 * Compares this and another card object and returns 0 if they are equal or -1/1 if they are not; id comparisons are meaningless otherwise
	 * @param obj
	 * @return
	 */
	
	public int compareTo(Card obj) {
		return (int) Math.signum(id - obj.id);
	}
	
	/**
	 * Returns a resized image of the card image pulled in initialization
	 * @param wid
	 * @param hgt
	 * @return
	 */
	
	public BufferedImage getResizedImage(int wid, int hgt) {
		BufferedImage ret = new BufferedImage(wid, hgt, BufferedImage.TYPE_INT_ARGB);
		Image temp = img.getScaledInstance(wid, hgt, Image.SCALE_SMOOTH);
		ret.getGraphics().drawImage(temp, 0, 0, null);
		return ret;
	}
	
	public String toString() {
		return ""+id;
	}
	
}
