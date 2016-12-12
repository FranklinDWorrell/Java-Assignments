/**
 * An abstract class modeling the basic features of the potions a player can use in the 
 * text-based adventure game. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Potion
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 


/**
 * An abstract class for all the potions used in the game. All potions have a name, and 
 * boost health, mana, or both; they also provide access to their names and their boosts. 
 */
public abstract class Potion extends Item {
	private int[] boosts; 
	
	
	/** 
	 * Constructor initializes the name of the potion.
	 * 
	 * @param	name	the name of the potion
	 */ 
	public Potion(String name, int[] boosts){
		super(name); 
		this.boosts = boosts; 
	} // end constructor
	
	
	/**
	 * Allows the user to gain the boost provided by the potion. 
	 *
	 * @return 	the boost(s) provided by the Potion
	 */ 
	public int[] getBoost() {
		return this.boosts; 
	} // end method getBoost
} // end class Potion