/**
 * A class defining a type of Monster GameCharacter in a text-based adventure game. This
 * subtype of Monster has an attack that reduces both a Player's health and her mana 
 * based on the level the Player has obtained in the game. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: HomuncularPriest
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */

import java.util.Random; 

/**
 * A class that defines a type of Monster in the game. Their attacks reduce the player's 
 * mana and health by a percentage. 
 */ 
public class HomuncularPriest extends Monster {
	
	/** 
	 * The traits common to all instances of the HomuncularPriest subclass of Monster.
	 */ 
	private static final String HOM_NAME = "Homuncular Priest"; 
	private static final int HOM_ATTACK = 20; 
	private static final int HOM_XP = 15; 
	private static Random randomGenerator = new Random(); 

	
	/**
	 * Initialize a new HomuncularPriest instance with the relevant fields set to the 
	 * appropriate values. Only health varies between instances of this class. All other 
	 * instance variables are set to the static values. The health of an individual 
	 * HomuncularPriest is set to a random integer between 25 and 65. 
	 */ 
	public HomuncularPriest() {
		super(HOM_NAME, randomGenerator.nextInt(41) + 25, HOM_ATTACK, HOM_XP); 
	} // end constructor


	/**
	 * Attacks an opponent with an attack that reduces both her mana and her health by a 
	 * percentage based on the Player's level. 
	 * 
	 * @param	player	the Player instance being attacked
	 */ 
	@Override
	public void attack(Player player) {
		int playerLevel = player.getLevel(); 
		int damage = (playerLevel % 5) + randomGenerator.nextInt(8) + 1;
		int manaBurn = (playerLevel % 5) + randomGenerator.nextInt(5) + 1;
		player.takeDamage(damage); 
		player.takeManaBurn(manaBurn); 
		System.out.printf("%s%d%s%d%s%n", "This unholy apostle deals you ", 
			damage, " damage and burns ", manaBurn, " of your mana."); 
	} // end method attack
} // end class HomuncularPriest