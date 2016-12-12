/**
 * A class for health potions that increase a player's mana points by 20. 
 *
 * Assignment: Homework 5: Adding Players and Items
 * Class: ManaPotion
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 


/**
 * A class for potions used to restore 20 of a player's mana points. 
 */
public class ManaPotion extends Potion {
	
	/**
	 * All health points have the same name and restorative power.
	 */ 
	private static final String NAME = "Mana Potion"; 
	private static final int HEALTH_BOOST = 0; 
	private static final int MANA_BOOST = 15; 
	private static final int[] BOOSTS = {HEALTH_BOOST, MANA_BOOST}; 
	
	
	/**
	 * Constructor simply calls superclass constructor since all mana potions have the 
	 * same mana boost. 
	 */ 
	public ManaPotion() {
		super(NAME, BOOSTS); 
	} // end constructor 
} // end class Potion