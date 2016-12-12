/**
 * A class for the boss monster of a text-based adventure game. This class follows the 
 * singleton design pattern, since there should only ever be one of her in the game at a
 * time. Really though, I just need to practice this design pattern for the Java 2 final, 
 * and this seemed a good class to do it. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Asenath
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */

import java.util.Random; 

/** 
 * A singleton class for the "boss monster" in a text-based adventure game. She's a 
 * singleton, because only one of her should be created at a time. 
 */ 
public class Asenath extends Monster {
	// A reference to the existing Asenath instance is required for Singleton.
	private static Asenath instance = null; 
	
	// Asenath's name and health points will only ever be these values. 
	private static final String AS_NAME = "Asenath"; 
	private static final int AS_HEALTH = 350; 
	
	private Random randomGenerator = new Random(); 
	
	
	/**
	 * Constructor calls Monster's constructor with the relevant properties of the
	 * singleton Asenath. Asenath does not have a set attack power, because each of
	 * her three attacks do different things and the damage they deal varies widely.
	 * Additionally, killing her gives the Player no experience points, because 
	 * killing her ends the game. 
	 */ 
	private Asenath() {
		super(AS_NAME, AS_HEALTH, 0, 0); 
	} // end constructor 
	
	
	/**
	 * Returns a reference to the only instantiation of the class. If the reference 
	 * is null, a new Asenath instance is created. 
	 *
	 * @return	a reference to the only Asenath instance
	 */ 
	public static Asenath getInstance() {
		if (instance == null) {
			instance = new Asenath(); 
		}
		return instance; 
	} // end method getInstance
	
	/**
	 * Attacks the player. Randomly calls one of three different style attack helper 
	 * methods.
	 *
	 * @param	player	the Player being attacked
	 */ 
	@Override
	public void attack(Player player) {
		int attackStyle = randomGenerator.nextInt(3); 
		
		if (attackStyle == 0) {
			this.lifeStealAttack(player); 
		} 
		
		else if (attackStyle == 1) {
			this.manaBurnAttack(player); 
		} 
		
		else {
			this.levelBasedAttack(player); 
		} 
	} // end method attack
	
	
	/**
	 * Attacks a player in such a way that she loses 20% of her health points and Asenath 
	 * gains the same number of health points. Prints a statement of what happened in the 
	 * attack. 
	 * 
	 * @param	player	the Player under attack
	 */ 
	public void lifeStealAttack(Player player) {
		int damage = player.getHealth() / 5; 
		player.takeDamage(damage); 
		super.setHealth(damage); 
		System.out.printf("The arcane witch steals %d%s%n", damage, 
			" points of your health."); 
	} // end method lifeStealAttack
	
	
	/**
	 * Attacks a player in such a way that she loses 33% of her mana points and 15 health
	 * points. Prints a statement of what happened in the attack. 
	 * 
	 * @param 	player	the Player under attack
	 */ 
	public void manaBurnAttack(Player player) {
		int manaBurn = player.getMana() / 3; 
		player.takeManaBurn(manaBurn); 
		player.takeDamage(15);  
		System.out.printf("This prophetess of Cthulhu deals you 15 points of%s%d%s%n", 
			" physical damage and burns ", manaBurn, " of your mana."); 
	} // end method manaBurnAttack
	
	
	/**
	 * Attacks a player in such a way that she loses health and mana equal to her current
	 * level. Prints a statement of what happened in the attack. 
	 * 
	 * @param	player	the Player under attack
	 */ 
	public void levelBasedAttack(Player player) {
		int damage = player.getLevel(); 
		player.takeDamage(damage); 
		player.takeManaBurn(damage); 
		System.out.printf("The priestess of discord deals you %d%s%d%s%n", damage, 
			" damage and burns ", damage, "of your mana."); 
	} // end method levelBasedAttack
} // end class Asenath