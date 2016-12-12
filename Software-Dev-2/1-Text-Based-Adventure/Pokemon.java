/**
 * A class for the Pokemon objects that players will use to play the 
 * Pokemon Game. Class builds the objects and contains the methods that
 * the Pokemon instances use to interact with each other. 
 * 
 * Assignment: Homework 1 - Pokemon reboot
 * Class: Pokemon
 * Author: Franklin D. Worrell
 * Date: 9/9/2015
 */ 
  
import java.util.Random; 

/**
 * A class for the Pokemon objects in the Pokemon game. Pure server class
 * that does not change anything besides instances of itself. 
 */ 
public class Pokemon {
	/**
	 * Declares instance variables and initializes a random number 
	 * generator for use in determining attack success. 
	 */ 
	private int tolerance, 	// @invariant tolerance >= 0 && tolerance <= 100
	            speed,     	// @invariant speed >= 1 && speed <= 100 
	            power;     	// @invariant power >= 1 && power <= 20 
	private String name;
	
	// If true, Pokemon is defending;if false, Pokemon is attacking. 
	private boolean isDefending; 	
	 
	// Creates random number generator for use in attack method. 
	Random randomGenerator = new Random(); 


	/** 
	 * Creates instance of Pokemon class. Validates inputted values for 
	 * tolerance, speed, and power. The arguments for the constructor are
	 * generated in the Game class. 
	 * @param	name					the Pokemon's name
	 * @param	initialTolerance	the Pokemon's health
	 * @param	initialSpeed		the Pokemon's likelihood of attacking first
	 * @param	initialPower		the Pokemon's attack force
	 */ 
	public Pokemon(String name, int initialTolerance, int initialSpeed, 
		int initialPower) {
		
		// Initializes name. 
		this.name = name; 
		
		// Validates and initializes tolerance. 
		if (initialTolerance < 1 || initialTolerance > 100) {
			throw new IllegalArgumentException("tolerance must be >= 1 and <= 100"); 
		} // end if
		else {
			this.tolerance = initialTolerance; 
		} // end else
		
		// Validates and initializes speed. 
		if (initialSpeed < 1 || initialSpeed > 100) {
			throw new IllegalArgumentException("speed must be >= 1 and <= 100"); 
		} // end if 
		else {
			this.speed = initialSpeed; 
		} // end else
		
		// Validates and initializes power. 
		if (initialPower < 1 || initialPower > 20) {
			throw new IllegalArgumentException("power must be >= 1 and <= 20"); 
		} // end if
		else {
			this.power = initialPower; 
		} // end else
	} // end constructor



	// Query methods. 

	/** 
	 * Gets tolerance of a Pokemon object.
	 * @return	Pokemon's tolerance
	 */ 
	public int tolerance() {
		return tolerance; 
	} // end method tolerance


	/** 
	 * Gets speed of a Pokemon object.
	 * @return	Pokemon's speed
	 */ 
	public int speed() {
		return speed; 
	} // end method speed


	/** 
	 * Gets power of a Pokemon object.
	 * @return	Pokemon's power
	 */ 
	public int power() {
		return power; 
	} // end method power


	/** 
	 * Gets name of a Pokemon object.
	 * @return	Pokemon's name
	 */  
	public String name() {
		return name; 
	} // end method name


	/** 
	 * Gets whether or not Pokemon is defending. 
	 * @return whether Pokemon is defending
	 */ 
	public boolean getIsDefending() { 
		return isDefending; 
	} // end method getIsDefending


	/** 
	 * Returns a String representation of a Pokemon object. 
	 * @return	description of a Pokemon object
	 */
	public String toString() { 
		return String.format("%s's tolerance: %d;   speed: %d;   power: %d", 
			this.name(), this.tolerance(), this.speed(), this.power()); 
	} // end method toString
 
 
 
	// Command methods
	
	/**
	 * Allows one Pokemon to attack another. Computes likelihood of successful
	 * attack and, if attack is successful damages the other player's tolerance. 
	 * @param	anotherAnimal		the Pokemon instance being attacked
	 * @require	tolerance() > 0 && anotherAnimal.tolerance() > 0
	 * @ensure	anotherAnimal.tolerance() <= old.anotherAnimal.tolerance()
	 */ 
	public void attack(Pokemon anotherAnimal) {
		// Sets isDefending to false. Initializes local variable for amount of damage. 
		isDefending = false; 
		int amountOfDamage = 0; 
		
		// Checks whether opponent is defending and stores it in a local variable.  
		boolean opponentDefends = anotherAnimal.getIsDefending(); 
		
		// If opponent is also attacking, percent chance of landing the 
		// hit is equal to this Pokemon's speed and damage is equal to the
		// attacker's power. 
		if (!opponentDefends) { 
			// Generates a random int between 1 and 100. 
			int oddsOfHit = randomGenerator.nextInt(100) + 1; 
			
			// Checks to see if hit landed by comparing random int and this Pokemon's
			// speed and, if hit lands, applies the damage. 
			if (oddsOfHit <= speed) {    			
				amountOfDamage = power;  
				anotherAnimal.getHit(amountOfDamage); 
			} // end if
		} // end if
		
		// If opponent is defending, nested if-statements handle possibilities. 
		else if (opponentDefends) {
			int vsDefendOutcome = randomGenerator.nextInt(4); 
			
			// In half the cases, neither Pokemon is hurt. So if vsDefendOutcome
			// is <= 1, nothing happens. 
			
			// In a quarter of cases, hit lands on opponent. Amount of damage
			// equals 25% of attacker's power. 
			if (vsDefendOutcome == 2) {
				amountOfDamage = power / 4; 
				anotherAnimal.getHit(amountOfDamage); 
			} // end else if
			
			// In a quarter of cases, the attacker hurts itself. Amount of 
			// damage equals 25% of attacker's power. 
			else if (vsDefendOutcome == 3) { 
				amountOfDamage = power / 4; 
				this.getHit(amountOfDamage); 
			} // end else if
		} // end else if
		
		// Restores isDefending to false for both Pokemon. 
		anotherAnimal.isDefending = false; 
		isDefending = false; 
	} // end method attack
	
 
	/**
	 * Reduces tolerance of a Pokemon after it has been the victim of
	 * a successful attack. 
	 * @param	amountOfHit		amount of damage to the Pokemon 
	 * @ensure	tolerance <= old.tolerance && tolerance >= 0
	 */ 
	private void getHit(int amountOfHit) {
		// Ensures that tolerance is never < 0. 
		if (tolerance - amountOfHit >= 0) {
			tolerance -= amountOfHit; 
		} // end if
		else {
			tolerance = 0;
		} // end else
	} // end method getHit
	
 
	/**
	 * Changes isDefending instance variable. If it is false, 
	 * it becomes true and vice versa. 
	 */ 
	public void defend() {
		isDefending = true; 
	} // end method defend
} // end class Pokemon