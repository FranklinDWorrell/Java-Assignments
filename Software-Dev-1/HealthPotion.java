/**
 * A class for health potions that increase a player's health points by 20. 
 *
 * Assignment: Homework 5: Adding Players and Items
 * Class: HealthPotion
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 


/**
 * A class for potions used to restore 20 of a player's health points. 
 */
public class HealthPotion extends Potion {
	
	/**
	 * All health points have the same name and restorative power.
	 */ 
	private static final String NAME = "Health Potion"; 
	private static final int HEALTH_BOOST = 20; 
	private static final int MANA_BOOST = 0; 
	private static final int[] BOOSTS = {HEALTH_BOOST, MANA_BOOST}; 
	
	
	/**
	 * Constructor simply calls superclass constructor since all health potions have the 
	 * same health boost. 
	 */ 
	public HealthPotion() {
		super(NAME, BOOSTS); 
	} // end constructor 
} // end class Potion