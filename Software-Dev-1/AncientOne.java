/**
 * A class defining a type of Monster GameCharacter in a text-based adventure game. This 
 * subtype of Monster is armoured and alternates between two different types of attack. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: AncientOne
 * Author: Franklin D. Worrell
 * Date: 12/8/2015
 */

import java.util.Random;

/**
 * A class that defines a type of Monster in the game. Ancient Ones alternate attacks 
 * between draining a player's mana and a physical attack. They are armored, so a player's
 * attack damage is reduced before it is applied to an Ancient One. 
 */
public class AncientOne extends Monster {
	
	/**
	 * The traits common to all instances of the AncientOne subclass of Monster.
	 */ 
	private static final String ANC_NAME = "Ancient One"; 
	private static final int ANC_ATTACK = 35; 
	private static final int ANC_XP = 30; 
	private static Random randomGenerator = new Random(); 

	// A boolean value controls the type of attack an AncientOne will use. 
	private boolean isManaAttacking = true; 
	
	/**
	 * Initialize a new AncientOne instance with the relevant fields set to the 
	 * appropriate values. Only health varies between instances of this class. All other 
	 * instance variables are set to the static values. The health of an individual 
	 * AncientOne is set to a random integer between 75 and 100. 
	 */ 
	public AncientOne() {
		super(ANC_NAME, randomGenerator.nextInt(26) + 75, ANC_ATTACK, ANC_XP); 
	} // end constructor
	
	
	/**
	 * Attacks the player with either a mana-reducing attack or a physical attack and 
	 * calls the appropriate helper method. If the player's mana level is zero, then the 
	 * AncientOne will only physically attack--the AncientOne can tell if the Player has
	 * any magical ability at the time. Boolean value flipped to change attack strategy
	 * every turn. 
	 * 
	 * @param	player	the Player instance being attacked
	 */ 
	@Override
	public void attack(Player player) {
		// The AncientOne instance attacks with a mana-reducing attack.
		if (player.getMana() > 1 && this.isManaAttacking) {
			this.manaAttack(player); 
			this.isManaAttacking = false; 			
		} 
		
		// The AncientOne instance attacks with a physical attack. 
		else {
			this.isManaAttacking = true; 
			this.physicallyAttack(player); 
		} 
	} // end method attack 
	
	
	/**
	 * Attacks an opponent with a mana-reducing attack. Each mana-reducing attack cuts the
	 * Player's available mana in half. 
	 * 
	 * @param	player	the Player instance being attacked
	 */ 
	private void manaAttack(Player player) {
		int burn = player.getMana() / 2; 
		player.takeManaBurn(burn); 
		System.out.printf("The armored blasphemy burns %d%s%n", burn, 
			" of your mana."); 
	} // end method manaAttack
	
	
	/**
	 * Attacks an opponent with a physical attack that maxes out at 35 points of damage.
	 *
	 * @param	player	the Player instance being attacked
	 */ 
	private void	physicallyAttack(Player player) {
		int damage = ANC_ATTACK - randomGenerator.nextInt(15); 
		player.takeDamage(damage); 
		System.out.printf("The unnatural behemoth strikes you and deals you %d%s%n", 
			damage, " damage."); 
	} // end method physicallyAttack 
	
	
	/**
	 * Inflicts a reduced amount of damage on an AncientOne instance. Since they are 
	 * armored, only a fraction of the Player's attack damage will actually get through. 
	 * The amount of damage taken is 25-75 percent of what the Player would normally deal.
	 * 
	 * @param	damage	the amount of damage the character is taking
	 * @ensure	this.health >= 0
	 */ 
	@Override
	public void takeDamage(int damage) {
		int reducedDamage = (damage * (randomGenerator.nextInt(3) + 1)) / 4; 
		if (reducedDamage <= this.getHealth()) {
			super.takeDamage(reducedDamage); 
		} 
		else {
			super.takeDamage(this.getHealth()); 
		} 
	} // end method takeDamage 
} // end class AncientOne