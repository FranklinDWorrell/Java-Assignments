/**
 * An abstract class modelling the basic features of all characters in a text-based 
 * adventure game. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: GameCharacter
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 

/**
 * An abstract class that dictates the basic behaviors for all the characters necessary 
 * for a text-based adventure game. 
 */ 
public abstract class GameCharacter {
	
	// Declare instance variables common to all characters in the game. 
	private String name; 
	private int health; 	// @invariant health >=0
	private int attackPower; 
	private boolean isAlive; 
	
	
	/**
	 * Constructor method initializes the instance variables of a new instance of a 
	 * subtype of GameCharacter. 
	 *
	 * @param	name	the character's name
	 * @param	health	the character's health, namely, the amount of damage it can take
	 * @param	attack	the power of the character's attack
	 */ 
	public GameCharacter(String name, int health, int attack) {
		this.name = name; 
		this.health = health; 
		this.attackPower = attack;
		this.isAlive = true; 
	} // end constructor
	
	
	/**
	 * Returns the character's name. 
	 *
	 * @return	the character's name
	 */ 
	public String getName(){
		return this.name; 
	} // end method getName
	
	
	/**
	 * Returns the character's current amount of health points. 
	 * 
	 * @return	the character's health
	 */ 
	public int getHealth() {
		return this.health; 
	} // end method getHealth
	
	
	/**
	 * Returns the base amount of damage that a character's attack can inflict. 
	 * 
	 * @return 	the damage a character can inflict
	 */ 
	public int getAttackPower() {
		return this.attackPower; 
	} // end method getAttackPower 
	
	
	/**
	 * Returns whether the character is alive.
	 *
	 * @return	whether the character is alive
	 */ 
	public boolean isAlive() {
		return this.isAlive; 
	} // end method isAlive
	
	
	/**
	 * Sets the GameCharacter's health.
	 * 
	 * @param	healthBoost	the amount to increase the character's health
	 */ 
	public void setHealth(int healthBoost) {
		this.health += healthBoost; 
	} // end method setHealth
	
	
	/**
	 * Sets the GameCharacter's attackPower. 
	 * 
	 * @param	newAttack	the new value of attackPower
	 */ 
	public void setAttackPower(int newAttack) {
		this.attackPower = newAttack; 
	} // end method setAttackPower 
	
	
	/**
	 * Inflicts the specified amount of damage upon a character.
	 * 
	 * @param	damage	the amount of damage the character is taking
	 * @ensure	this.health >= 0
	 */ 
	public void takeDamage(int damage) {
		if (damage < this.health) {
			this.health -= damage; 
		} 
		else {
			this.health = 0; 
			this.isAlive = false; 
		} 
	} // end method takeDamage 
	
	
	/**
	 * Returns a String representation of a character in the game. 
	 *
	 * @return	a String representation of the character
	 */ 
	@Override
	public String toString() { 
		String characterString = this.name + " with " + this.health + " health "; 
		characterString += "and " + this.attackPower + " base attack power"; 
		return characterString;
	} // end method toString
	
	
	/**
	 * Implements the actions a character should perform during a turn of the game.
	 */ 
	public abstract void takeTurn(GameCharacter character); 
} // end class GameCharacter