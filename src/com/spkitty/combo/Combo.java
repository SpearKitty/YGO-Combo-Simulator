package com.spkitty.combo;

import java.util.ArrayList;

/**
 * Object to store the required card ids and garnet ids to start a specific combo line
 * @author SpearKitty
 */

public class Combo {
	
	private ArrayList<Card> cards;
	private ArrayList<Card> garnets;
	private String name;

	/**
	 * initializes new empty combo
	 */
	
	public Combo() {
		cards = new ArrayList<Card>();
		garnets = new ArrayList<Card>();
		name = "untitled_combo";
	}
	
	/**
	 * initializes new combo containing required all cards given in the instantiation, garnets must be added manually
	 * @param objs
	 */
	
	public Combo(Card... objs) {
		cards = new ArrayList<Card>();
		for(Card c : objs)
			cards.add(c);
		garnets = new ArrayList<Card>();
	}
	
	/**
	 * adds a required card for the combo
	 * @param obj
	 * @return
	 */
	public boolean addCard(Card obj) {
		return cards.add(obj);
	}
	
	/**
	 * removes a card, returns true if it was removed, false if it was not 
	 * @param obj
	 * @return
	 */
	public boolean removeCard(Card obj) {
		for(int i = 0; i < cards.size(); i++)
			if(cards.get(i).equals(obj)) {
				cards.remove(i);
				return true;
			}
		return false;
	}
	
	/**
	 * adds a garnet as an additional fail condition for the combo check
	 * @param obj
	 * @return
	 */
	
	public boolean addGarnet(Card obj) {
		return garnets.add(obj);
	}
	
	/**
	 * removes a garnet, returns true if it was removed, false if it was not 
	 * @param obj
	 * @return
	 */
	public boolean removeGarnet(Card obj) {
		for(int i = 0; i < garnets.size(); i++)
			if(garnets.get(i).equals(obj)) {
				garnets.remove(i);
				return true;
			}
		return false;
	}
	
	/**
	 * returns all the required Card objects in the combo
	 * @return
	 */
	public Card[] getComboCards() {
		if(cards.size() == 0)
			return new Card[0];
		Card[] obj = new Card[cards.size()];
		for(int i = 0; i < obj.length; i++)
			obj[i] = cards.get(i);
		return obj;
	}
	
	/**
	 * returns all garnets involved in the combo
	 * @return
	 */
	
	public Card[] getGarnets() {
		if(garnets.size() == 0)
			return new Card[0];
		Card[] obj = new Card[garnets.size()];
		for(int i = 0; i < obj.length; i++)
			obj[i] = garnets.get(i);
		return obj;
	}

	/**
	 * sets name value
	 * @param nName
	 */
	public void setName(String nName) {
		name = nName;
	}
	
	/**
	 * gets name value :farfaSmiley:
	 * @param nName
	 */
	public String getName() {
		return name.trim();
	}
	
	public String toString() {
		String str = name;
		for(int i = 0; i < cards.size(); i++)
			str += " | " + cards.get(i);
		str += " [garnetDiv]";
		for(int i = 0; i < garnets.size(); i++)
			str += " | " + garnets.get(i);
		return str;
	}
	
}
