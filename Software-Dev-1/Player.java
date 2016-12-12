/**
 * A class that represents the player's character in a text-based adventure game. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Player
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 

import java.util.ArrayList;
import java.util.HashMap; 

/**
 * A class that represents the player's character in a text-based adventure game. Players 
 * carry Weapons and Potions, have physical and magical attacks, and can increase in 
 * level as they play the game. 
 */ 
public class Player extends GameCharacter {
	private int mana; 				// @invariant mana >= 0
	private int level; 				// @invariant level >= 0
	private int xPUnspent; 			// @invariant xPUnspent >=0
	private Weapon currentWeapon; 	// @invariant currentWeapon != null
	private boolean isMagicAttacking = false; 
	// Initializes a simple starting weapon for the player to use. 
	private static final Weapon BASIC_SWORD = new Weapon("Iron Sword", 0); 
	// Initializes a HashMap to hold potions--no unique potions; only track number held.
	private HashMap<String, Integer> potionInventory = new HashMap<String, Integer>(); 

	
	/**
	 * Constructor calls constructor of GameCharacter with the relevant fields and then 
	 * initializes the fields unique to Player. 
	 *
	 * @param	name	the Player's name
	 */ 
	public Player(String name) {
		super(name, 35, 10); 
		this.mana = 5; 
		this.level = 0; 
		this.xPUnspent = 0; 
		this.currentWeapon = BASIC_SWORD; 
		// Add keys for each type of Potion to the potionInventory. 
		this.potionInventory.put("Health Potion", 1); 
		this.potionInventory.put("Mana Potion", 1); 
		this.potionInventory.put("Power Potion", 1); 
	} // end constructor
	
	
	/**
	 * Returns the player's level.
	 * 
	 * @return	the player's level
	 */ 
	public int getLevel() {
		return this.level; 
	} // end method getLevel
	
	
	/**
	 * Returns the player's mana amount.
	 * 
	 * @return 	the player's mana level
	 */ 
	public int getMana() {
		return this.mana; 
	} // end method getMana
	
	
	/**
	 * Returns a reference to the Weapon the Player is currently attacking with.
	 *
	 * @return	the Player's current Weapon
	 */ 
	public Weapon getWeapon() {
		return this.currentWeapon;
	} // end method getWeapon
	
	
	/**
	 * Returns whether or not a player has a Potion of the requested type.
	 * 
	 * @return	whether the player has a potion of a certain type
	 */ 
	public boolean hasPotionType(String potionType) {
		if (this.potionInventory.get(potionType) > 0) {
			return true; 
		} 
		else {
			return false; 
		} 
	} // end method hasPotionType
	
	
	/**
	 * Returns a String representation of the Player's potion inventory for use in the 
	 * TUI. 
	 *
	 * @return	a String detailing the contents of the Player's potion inventory
	 */ 
	public String getPotionInventory() { 
		String potionString = "You have " + this.potionInventory.get("Health Potion"); 
		potionString += " Health Potions, " + this.potionInventory.get("Mana Potion"); 
		potionString += " Mana Potions, and " + this.potionInventory.get("Power Potion"); 
		potionString += " Power Potions available."; 
		return potionString; 
	} // end method getPotionInventory
	
	
	/**
	 * Adds the xP from a defeated Monster to the Player's current total of unspent 
	 * experience points. Automatically checks to see if the Player should level up after
	 * this addition of experience points. 
	 *
	 * @param	xPAcquired	the amount of experience points gained this combat round
	 */ 
	public void acquireXP(int xPAcquired) {
		this.xPUnspent += xPAcquired; 
		this.levelUp(); 
	} // end method acquireXP
	
	
	/**
	 * Levels-up the Player when her xPAttained have hit the appropriate level. The 
	 * amount of xPUnspent required to level up increases with each level attained. Each 
	 * increase in level adds 2 to the player's base attackPower. 
	 */ 
	private void levelUp() {
		if (this.xPUnspent >= ((this.level % 5 + 1) * 10)) {
			this.level++; 
			this.setAttackPower(this.getAttackPower() + 2); 
			printLevelUpMessage(); 
		} 
	} // end method levelUp
	
	
	/**
	 * Prints a message informing the player when they reach a new level. 
	 */ 
	private void printLevelUpMessage() {
		System.out.printf("%n%s%n%s%d%s%d%s%n%n", "You reached a higher level!", 
			"You are now level ", this.level, " with a base attack power of ", 
			this.getAttackPower(), ". "); 
		System.out.println(); 
	} // end method printLevelUpMessage
		
	
	/**
	 * Adds a potion to the Player's potionInventory. 
	 * 
	 * @param	potion	the potion to be added to the Inventory
	 */ 
	public void addToPotionInventory(Potion potion) { 
		String key = potion.getName(); 
		int oldTotal = this.potionInventory.get(key); 
		this.potionInventory.put(key, oldTotal + 1); 
	} // end method addToPotionInventory
	
	
	/**
	 * Equips a Player with a different weapon and factors its attack bonus into the 
	 * Player's attack power.
	 * 
	 * @param	newWeapon	the new weapon being equipped
	 */ 
	public void equipWeapon(Weapon newWeapon) {
		this.storeWeapon(); 
		this.currentWeapon = newWeapon; 
		int attackBonus = newWeapon.getAttackBonus(); 
		this.setAttackPower(this.getAttackPower() + attackBonus); 
	} // end method equipWeapon
	
	
	/**
	 * Removes the Player's current weapon from use. Since weapons cannot be downgraded,
	 * the Player does not retain a reference to the removed weapon. 
	 */ 
	public void storeWeapon() {
		int oldBonus = this.currentWeapon.getAttackBonus(); 
		this.setAttackPower(this.getAttackPower() - oldBonus); 
		this.currentWeapon = null; 
	} // end method storeWeapon
	
	
	/**
	 * Excutes the appropriate attack method given what the user has previously selected. 
	 * The bulk of what a Player does in a turn is cycled through in the Basic Game Loop 
	 * in the GameRunner class; hence, the minimal implementation in this class.
	 * 
	 * @param	monster	the GameCharacter that the Player is attacking
	 */ 
	public void takeTurn(GameCharacter monster) {
		// Player chose to use a magic attack. 
		if (isMagicAttacking) {
			this.castSpell(monster); 
		} 
		
		// Player chose to use a physical attack. 
		else {
			this.attack(monster); 
		} 
	} // end method takeTurn

	
	/**
	 * Executes the Player's choice of combat options. 
	 *
	 * @param	choiceOfAttack	whether the Player is using a magic-based attack
	 */ 
	public void setAttackStyle(boolean choiceOfAttack) {
		isMagicAttacking = choiceOfAttack; 
	} // end method executeCombatOptions
	
	
	/**
	 * Damages the attacked opponent. The amount of damage increases as the Player's 
	 * level increases. Likewise the mana cost of casting the spell increases as the 
	 * Player's level increases. 
	 *
	 * @param	monster	the GameCharacter the Player is attacking
	 */ 
	private void castSpell(GameCharacter monster) {
		// Calculate mana cost of casting spell. 
		int manaCost = 10 + this.getLevel() * 2; 
		
		// Ensure that Player has enough mana to cast the spell. 
		if (this.getMana() >= manaCost) {
			// Calculate amount of damage and apply it to the opponent. 
			int damage = 5 + this.getLevel() * 3; 
			monster.takeDamage(damage);  
			this.takeManaBurn(manaCost); 
			// Print an update on the Player's mana remaining after casting. 
			System.out.printf("%nAfter casting your spell you have %d%s.%n", this.mana, 
				" mana remaining"); 
		}
		
		// Inform user if Player doesn't have enough mana to cast spell. 
		else {
			System.out.println("You don't have enough mana to cast a spell!"); 
			System.out.println("Drink a Mana or Power Potion and try again!"); 
			System.out.println(); 
		}
	} // end method castSpell
	
	
	/**
	 * Applies the boost(s) provided by a potion to the player's health and/or mana. The 
	 * boosts provided increase as the Player's level increases. 
	 * 
	 * @param	potion	the potion being drank
	 */ 
	public void drinkPotion(Potion potion) {
		// Apply the boosts to the player's stats. 
		int[] boosts = potion.getBoost(); 
		this.setHealth(boosts[0] + this.getLevel()); 
		this.mana += boosts[1] + this.getLevel(); 
		
		// Remove the potion from the player' inventory. 
		String key = potion.getName(); 
		int oldTotal = potionInventory.get(key); 
		potionInventory.put(key, oldTotal - 1); 
	} // end method drinkPotion
	
	
	/**
	 * Reduces the player's mana in response to a mana-burning attack. 
	 *
	 * @param	burn	the amount to subtract from the player's mana total
	 * @ensure	this.mana >= 0
	 */ 
	public void takeManaBurn(int burn) {
		if (burn <= this.mana) {
			this.mana -= burn; 
		} 
		else {
			this.mana = 0; 
		}
	} // end method takeManaBurn 
	
	
	/**
	 * Deals damage to an opponent equal to the Player's base attack power plus any mods
	 * from the weapon she is using. 
	 * 
	 * @param	monster	the Monster being attacked
	 */
	public void attack(GameCharacter monster) {
		monster.takeDamage(this.getAttackPower()); 
	} // end method attack
	
	
	/**
	 * Returns a String representation of the player's character.
	 *
	 * @return	a String representation of the player's character
	 */ 
	@Override
	public String toString() {
		String playerString = super.toString(); 
		playerString += " has " + this.mana + " mana and is level " + this.level + ". "; 
		return playerString; 
	} // end method toString
} // end class Player	