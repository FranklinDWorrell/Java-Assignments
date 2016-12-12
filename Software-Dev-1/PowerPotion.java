/**
 * A class for health potions that increase a player's health and mana points by 15. 
 *
 * Assignment: Homework 5: Adding Players and Items
 * Class: PowerPotion
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 


/**
 * A class for potions used to restore 15 of a player's health and mana points. 
 */
public class PowerPotion extends Potion {
	
	/**
	 * All health points have the same name and restorative power.
	 */ 
	private static final String NAME = "Power Potion"; 
	private static final int HEALTH_BOOST = 15; 
	private static final int MANA_BOOST = 15; 
	private static final int[] BOOSTS = {HEALTH_BOOST, MANA_BOOST}; 
	
	
	/**
	 * Constructor simply calls superclass constructor since all health potions have the 
	 * same health boost. 
	 */ 
	public PowerPotion() {
		super(NAME, BOOSTS); 
	} // end constructor 
} // end class Potion