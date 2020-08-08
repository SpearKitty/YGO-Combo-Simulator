package com.spkitty.combo;

import java.util.ArrayList;

/**
 * Object for simulating test hands of a given deck
 * @author SpearKitty
 */

public class Simulator {
	
	private Deck deck;
	private Hand hand;
	private int drawCount;
	
	/**
	 * initiates simulation of given deck, drawing drawCount number of cards for each opening hand
	 * @param init_deck
	 * @param init_hand
	 * @param init_drawCount
	 */
	
	public Simulator(Deck init_deck, int init_drawCount) {
		deck = init_deck;
		hand = new Hand();
		drawCount = init_drawCount;
		deck.shuffle();
	}
	
	/**
	 * simulates a single instance of drawing a hand and checking if combo c is present
	 * @param c
	 * @return
	 */
	
	public boolean simulateCombo(Combo c) {
		hand.drawCards(deck, drawCount);
		boolean ans = hand.passCombo(c);
		hand.resetHand(deck);
		deck.shuffle();
		return ans;
	}
	
	/**
	 * simulates a single instance of drawing a hand and checking if ANY combo in list is present
	 * @param list
	 * @return
	 */
	
	public boolean simulateList(ArrayList<Combo> list) {
		hand.drawCards(deck, drawCount);
		boolean ret = false;
		for(int i = 0; i < list.size(); i++) 
			if(hand.passCombo(list.get(i))) {
				ret = true;
				break;
			}
		hand.resetHand(deck);
		deck.shuffle();
		return ret;
	}
	
	/**
	 * simulates count instances of drawing/resetting hands after checking if combo c is present
	 * @param c
	 * @param count
	 * @return
	 */
	
	public double simulateComboProb(Combo c, int count) {
		double pass = 0;
		for(int i = 0; i < count; i++)
			pass += (simulateCombo(c) ? 1 : 0);
		return pass / (double) count;
	}
	
	/**
	 * simulates count instances of drawing/resetting hands after checking if ANY of combos in list are present
	 * @param list
	 * @param count
	 * @return
	 */
	
	public double simulateMassProb(ArrayList<Combo> list, int count) {
		double pass = 0;
		for(int i = 0; i < count; i++)
			pass += simulateList(list) ? 1 : 0;
		return pass / ((double) (count));
	}
	
	/**
	 * creates a sorted distribution of probabilities of [size] checking [depth] number of randomized hands for combo c
	 * @param c
	 * @param depth
	 * @param size
	 * @return
	 */
	
	public double[] createComboDistrib(Combo c, int depth, int size) {
		double[] dist = new double[size];
		for(int i = 0; i < size; i++) 
			dist[i] = simulateComboProb(c, depth);
		return sortAscending(dist);
	}
	
	/**
	 * creates a sorted distribution of probabilities of [size] checking [depth] number of randomized hands for any combo in list
	 * @param list
	 * @param depth
	 * @param size
	 * @return
	 */
	
	public double[] createMassDistrib(ArrayList<Combo> list, int depth, int size) {
		double[] dist = new double[size];
		for(int i = 0; i < size; i++) 
			dist[i] = simulateMassProb(list, depth);
		return sortAscending(dist);
	}
	
	/**
	 * collapses distribution to a single average 
	 * @param arr
	 * @return
	 */
	
	public double collapseDistrib(double[] arr) {
		double d = 0;
		
		for(int i = 0; i < arr.length; i++)
			d += arr[i];
		
		return d / (double) arr.length;
	}
	
	/**
	 * private method to poorly sort distributions created by the createXDistrib functions
	 * @param input
	 * @return
	 */
	
	private double[] sortAscending(double[] input) {
		double[] temp = new double[input.length];
		for(int t = 0; t < temp.length; t++) {
			double hold = 2;
			int index = -1;
			for(int i = 0; i < input.length; i++) {
				double oHold = hold;
				hold = Math.min(input[i], hold);
				if(oHold != hold)
					index = i;
			}
			temp[t] = hold;
			input[index] = 2;
		}
		return temp;
	}
}
