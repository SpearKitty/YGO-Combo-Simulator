package com.spkitty.combo;

import java.util.ArrayList;

/**
 * Object to store the required card ids and garnet ids to start a specific combo line
 * @author SpearKitty
 */

public class Combo {
	
	private ArrayList<Card> cards;
	private ArrayList<Card> garnets;

	/**
	 * initializes new empty combo
	 */
	
	public Combo() {
		cards = new ArrayList<Card>();
		garnets = new ArrayList<Card>();
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
	 * adds a garnet as an additional fail condition for the combo check
	 * @param obj
	 * @return
	 */
	
	public boolean addGarnet(Card obj) {
		return garnets.add(obj);
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
	
}
