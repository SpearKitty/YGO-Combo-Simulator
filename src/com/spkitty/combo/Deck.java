package com.spkitty.combo;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.spkitty.frame.SettingManager;

/**
 * Object for storing card values of all cards currently in the deck
 * @author SpearKitty
 */

public class Deck {
	
	private ArrayList<Card> cards;
	
	/**
	 * Initializes a new deck with an maximum limit of 60 cards.
	 */
	
	public Deck() {
		cards = new ArrayList<Card>(60);
	}
	
	/**
	 * Adds given card object to the end of the card list
	 * @param obj
	 * @return
	 */
	
	public boolean addCard(Card obj) {
		return cards.add(obj);
	}
	
	/**
	 * randomizes order of all cards in the deck
	 */
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * takes top card of deck and returns it; THIS DOES AFFECT THE CARDS *IN THE DECK* AS THE CARD IS ACTUALLY REMOVED
	 * @return
	 */
	
	public Card draw() {
		return cards.remove(cards.size() - 1);
	}
	
	/**
	 * takes the top card of deck and places it into the given hand; THIS ALSO AFFECTS THE CARDS IN THE DECK, THE CARD IS ACTUALLY REMOVED
	 * @param target
	 */
	
	public void draw(Hand target) {
		target.add(cards.remove(cards.size() - 1));
	}
	
	public int size() {
		return cards.size();
	}
	
	public String toString() {
		String s = "";
		for(Card c : cards) {
			s += c + "\n";
		}
		return s;
	}
	
	
	/**
	 * imports deck from .ydk file of given name
	 * @param name
	 * @return
	 */
	public boolean importDeck(String name) {
		String ydkName = name + ".ydk";
		try (Scanner ydkReader = new Scanner(new FileReader(SettingManager.buildCorePath() + "deck\\" + ydkName))) {
			while(ydkReader.hasNextLine()) {
				String obj = ydkReader.nextLine();
				if(obj.startsWith("#") && cards.size() == 0)
					continue;
				if(obj.startsWith("#") && cards.size() > 0)
					break;
				cards.add(new Card(obj));
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * returns count of occurrences of given id 
	 * @param id
	 * @return
	 */
	
	public int countOf(int id) {
		int count = 0;
		for(int i = 0; i < cards.size(); i++)
			count += (cards.get(i).getID() == id) ? 1 : 0;
		return count;
	}
	
	/**
	 * returns an array of unique cards in the deck
	 * @return
	 */
	
	public Card[] uniqueCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int i = 0; i < cards.size(); i++) 
			if(!temp.contains(cards.get(i)))
				temp.add(cards.get(i));
		Card[] ans = new Card[temp.size()];
		for(int i = 0; i < cards.size(); i++)
			ans[i] = temp.get(i);
		return ans;
	}
	
}
