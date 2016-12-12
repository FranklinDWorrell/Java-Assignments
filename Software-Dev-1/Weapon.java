/**
 * A class for weapons in a text-based adventure game. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Weapon
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 


/**
 * A class modelling weapons in a text-based adventure game. Each weapon has a name and 
 * an amount it increases a Player's attack power. 
 */
public class Weapon extends Item {
	private int attackBonus; 
	
	/**
	 * Constructor initializes the Weapon's name and its effect on the Player's attack
	 * power. 
	 *
	 * @param 	name		the Weapon's name
	 * @param	attackBonus	the amount the Weapon modifies the Player's attack power
	 */ 
	public Weapon(String name, int attackBonus) {
		super(name); 
		this.attackBonus = attackBonus; 
	} // end constructor
	
	
	/** 
	 * Returns the amount the Player's attack is modified by the Weapon. 
	 * 
	 * @return	the attacking bonus that the weapon provides
	 */ 
	public int getAttackBonus() {
		return this.attackBonus; 
	} // end method getAttackBonus
} // end class Weapon