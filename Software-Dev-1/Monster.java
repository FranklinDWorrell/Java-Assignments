/**
 * An abstract class modelling the basic features of all monster characters in a 
 * text-based adventure game. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Monster
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 

import java.util.Random; 
import java.util.ArrayList; 

/**
 * An abstract class that dictates the basic behaviors for all the monsters (opponents) in 
 * a text-based adventure game. 
 */ 
public abstract class Monster extends GameCharacter {
	// The amount of experience points a Player gains by defeating a Monster. 
	private int xP; 
	private boolean hasWeapon; 
	private Weapon carriedWeapon; 
	private boolean hasPotion; 
	private Potion carriedPotion; 
	private Random randomGenerator = new Random();
	
	// A pool of weapons that can be carried by instances of Monster. 
	public static ArrayList<Weapon> monsterWeaponsCache = new ArrayList<Weapon>(7); 
	// Adds weapons to the pool of weapons a monster (and the player) can carry. 
	static {
		monsterWeaponsCache.add(new Weapon("Single-sided Axe", 5)); 
		monsterWeaponsCache.add(new Weapon("Double-sided Axe", 12)); 
		monsterWeaponsCache.add(new Weapon("Hell-Forged Blade", 25)); 
		monsterWeaponsCache.add(new Weapon("Steel Sword", 7)); 
		monsterWeaponsCache.add(new Weapon("Shoggoth Bone Club", 10)); 
		monsterWeaponsCache.add(new Weapon("Battle Axe", 15)); 
		monsterWeaponsCache.add(new Weapon("Long Sword", 17)); 
	} 
	
	
	/**
	 * Constructor initializes instance variables unique to the Monster class. 
	 *
	 * @param	name		the Monster's name
	 * @param	health		the Monster's health points
	 * @param	attackPower	the amount of damage a Monster can inflict when attacking
	 * @param	xP			the experience points gained by a victorious opponent
	 */ 
	public Monster(String name, int health, int attackPower, int xP) {
		super(name, health, attackPower); 
		this.xP = xP; 
		this.decideWeapon(); 
		this.decidePotion(); 
	} // end constructor 
	
	
	/**
	 * Returns the xP value of a Monster--the gain in xP a Player acquires by defeating 
	 * the monster. 
	 *
	 * @return	the Monster's xP value
	 */ 
	public int getXP() {
		return this.xP; 
	} // end method getXP
	
	/**
	 * Returns the weapon carried by the Monster if it will carry one.
	 * 
	 * @return 	the weapon the Monster is carrying
	 */ 
	public Weapon getWeapon() {
		return this.carriedWeapon; 
	} // end method getWeapon
	
	
	/** 
	 * Sets the potion carried by the Monster if it will carry one. 
	 * 
	 * @return 	the potion the Monster is carrying
	 */ 
	public Potion getPotion() {
		return this.carriedPotion; 
	} // end method getPotion 
	
	
	/** 
	 * Decides whether or not a potion should be added to a Monster's inventory and adds
	 * it if so. A weapon is added in 25% of cases. 
	 */ 
	private void decideWeapon() {
		// Generate a random number to decide whether or not to add a weapon. 
		int toAddOrNotToAdd = randomGenerator.nextInt(4); 
		
		// Add a weapon 25% of the time. 
		if (toAddOrNotToAdd == 0) {
			this.addWeapon(); 
		}
		
		// Otherwise set fields to indicate that there is no weapon carried. 
		else { 
			this.hasWeapon = false; 
			this.carriedWeapon = null; 
		}
	} // end method decideWeapon
	
	
	/** 
	 * Adds a weapon from the weapons cache to the Monster's inventory. The weapon added
	 * is chosen at random from the cache. 
	 */ 
	private void addWeapon() {
		int weaponIndex = randomGenerator.nextInt(7); 
		this.carriedWeapon = monsterWeaponsCache.get(weaponIndex); 
	} // end method addWeapon 


	/** 
	 * Decides whether or not a potion should be added to a Monster's inventory and adds 
	 * it if so. A potion is added in a third of the cases. 
	 */ 
	private void decidePotion() { 
		// Generate a random number to decide whether or not to add a potion.
		int toAddOrNotToAdd = randomGenerator.nextInt(3); 
		
		// Add a potion half the time. 
		if (toAddOrNotToAdd == 0) {
			this.addPotion(); 
		}
		
		// Otherwise, set to fields to indicate that there is no potion carried. 
		else {
			this.hasPotion = false; 
			this.carriedPotion = null; 
		} 
	} // end method decidePotion
		
	
	
	/** 
	 * Adds a Potion to a Monster's inventory randomly. In half of cases the potion will 
	 * be a health potion; in a quarter it will be a mana potion; in a quarter it will be 
	 * a health and mana potion. 
	 */ 
	private void addPotion() {
		// Generate a random number to decide which potion to add. 
		int whichPotion = randomGenerator.nextInt(4); 
		
		// Add a health potion half the time. 
		if (whichPotion == 0 || whichPotion == 1) {
			this.carriedPotion = new HealthPotion(); 
		} 
		
		// Add a mana potion 25% of the time. 
		else if (whichPotion == 2) {
			this.carriedPotion = new ManaPotion(); 
		}
		
		// Add a power potion 25% of the time. 
		else {
			this.carriedPotion = new PowerPotion(); 
		}
		
		this.hasPotion = true; 
	} // end method addPotion

	
	/**
	 * Implements the logic of a Monter's turn in the game. A Monster attacks, then the 
	 * results of its attack are printed. 
	 *
	 * @param	player	the Player instance under attack
	 */ 
	public void takeTurn(GameCharacter player) {
		this.attack((Player) player);  
	} // end method takeTurn
	
	
	/**
	 * Attacks the game player. Each subtype of Monster has its own unique method of 
	 * attacking. 
	 *
	 * @param	player	the Player instance under attack
	 */ 
	public abstract void attack(Player player); 
} // end class Monster