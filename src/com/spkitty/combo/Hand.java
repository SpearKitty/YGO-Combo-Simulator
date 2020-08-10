package com.spkitty.combo;

import java.util.ArrayList;

/**
 * Object for storing values of cards in the player's hand
 * @author SpearKitty
 */

public class Hand {
	
	private ArrayList<Card> cards;
	
	/**
	 * initializes a new hand object
	 */
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	/**
	 * adds given card to hand, returns false if it cannot add for some reason
	 * @param obj
	 * @return
	 */
	
	public boolean add(Card obj) {
		return cards.add(obj);
	}
	
	/**
	 * checks if the hand currently passes the given combo; presence of combo's garnet(s) or lack of 
	 * combo's starter pieces will cause a failure condition
	 * @param c
	 * @return
	 */
	
	public boolean passCombo(Combo c) {
		Card[] comboCards = c.getComboCards();
		Card[] garnets = c.getGarnets();
		for(int i = 0; i < comboCards.length; i++)
			if(!contains(comboCards[i]))
				return false;
		for(int i = 0; i < garnets.length; i++)
			if(contains(garnets[i]))
				return false;
		return true;
	}
	
	/**
	 * draws count cards from the given deck
	 * @param deck
	 * @param count
	 */
	
	public void drawCards(Deck deck, int count) {
		for(int i = 0; i < count; i++) 
			this.add(deck.draw());
	}
	
	/**
	 * returns all cards currently in hand to target deck; THIS IS IMPORTANT FOR RESETTING THE DECK BEFORE DRAWING ADDITIONAL HANDS
	 * @param target
	 */
	
	public void resetHand(Deck target) {
		int m = cards.size();
		for(int i = 0; i < m; i++)
			target.addCard(cards.remove(0));
	}
	
	/**
	 * checks if hand currently contains card c, this does not account for multiple instances of the card
	 * @param c
	 * @return
	 */
	
	public boolean contains(Card c) {
		for(int i = 0; i < cards.size(); i++)
			if(cards.get(i).equals(c))
				return true;
		return false;
	}
	
	public int size() {
		return cards.size();
	}
	
	public String toString() {
		String str = "hand: " + cards.get(0).getID();
		for(int i = 1; i < cards.size(); i++)
			str += "  " + cards.get(i).getID();
		return str;
	}
}
