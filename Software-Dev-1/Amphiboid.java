/**
 * A class defining a type of Monster GameCharacter in a text-based adventure game. This 
 * subtype of Monster is the simplest and weakest with a merely physical attack that fails
 * in 50 percent of cases. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Amphiboid
 * Author: Franklin D. Worrell
 * Date: 12/8/2015
 */

import java.util.Random; 

/**
 * A class that defines a type of Monster in the game. Attacks with a basic attack. This 
 * is the weakest type of monster in the game and is worth the least experience points. 
 * This monster has a health of between 12 and 25 hit points. 
 */ 
public class Amphiboid extends Monster { 
	/**
	 * The traits common to all instances of the Amphiboid subclass of Monster.
	 */ 
	private static final String AMPH_NAME = "Amphiboid Slave"; 
	private static final int AMPH_ATTACK = 10; 
	private static final int AMPH_XP = 7; 
	private static Random randomGenerator = new Random(); 
	
	
	/**
	 * Initialize a new Amphiboid instance with the relevant fields set to the appropriate
	 * values. Only health varies between instances of this class. All other instance
	 * variables are set to the static values. The health of an individual Amphiboid is
	 * set to a random integer between 12 and 25. 
	 */ 
	public Amphiboid() {
		super(AMPH_NAME, randomGenerator.nextInt(14) + 12, AMPH_ATTACK, AMPH_XP); 
	} // end constructor
	
	
	/**
	 * Attacks an opponent with a basical physical assault that fails in 50% of cases. 
	 * Amount of damage is a random integer between 5 and 10 health points. Prints a short 
	 * update on damage dealt by the attack. 
	 *
	 * @param	player	the Player instance being attacked
	 */
	@Override
	public void attack(Player player) { 
		// Decide whether hit will land or not. 
		boolean hitLanded = randomGenerator.nextBoolean(); 
		
		// Attack was successful and landed. 
		if (hitLanded) { 
			int damage = this.getAttackPower() - randomGenerator.nextInt(6);
			player.takeDamage(damage); 
			System.out.printf("%s%d%s%n", "The filthy slave strikes you and deals you ", 
				damage, " damage."); 
		} 
		
		// Attack failed. Let the Player hear the good news! 
		else {
			this.printFailedAttackMessage();
		}
	} // end method attack
	
	
	/**
	 * Prints a message indicating that the Amphiboid's attack failed to land damage.
	 */ 
	private void printFailedAttackMessage() {
		System.out.println("The Amphiboid lashes out with its slimy fists, but misses!");
	} // end method printFailedAttackMessage
} // end class Amphiboid