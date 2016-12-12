/**
 * A class modelling a room for use in a text-based adventure game. Rooms are constructed 
 * by the Dungeon class which also organizes them into a coherent map by setting their 
 * exits. 
 *
 * Assignment: Homework 5: Adding Players and Items
 * Class: Room
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 

import java.util.ArrayList; 
import java.util.Random; 

/**
 * A class that models a room for a text-based adventure game. Each instance of Room has 
 * a String description and exits in four directions which can either be other instances 
 * of Room or be null. 
 */ 
public class Room {
	private String description; 
	private Room north, south, east, west; 
	private Monster monsterHere; 
	private boolean hasMonster; 
	private Potion potionHere;
	private boolean hasPotion; 
	private Random randomGenerator = new Random(); 
	private boolean isBossRoom = false; 
	
	/**
	 * Initializes a new instance of the Room class. Sets the description to the String 
	 * provided and initializes the Room's exits to null--they will be set by the setter 
	 * methods below when they are called by class Dungeon's constructor. 
	 *
	 * @param	description		the Room's description
	 */ 
	public Room(String description) {
		this.description = description; 
		this.north = null; 
		this.south = null; 
		this.east = null; 
		this.west = null; 
	} // end constructor 
	
	
	/**
	 * Overloaded constructor simplifies adding boss monster to the appropriate room. Only
	 * the room where the boss should be is created by a call to this constructor from the 
	 * Dungeon class. Also facilitates greater code reuse--there may be other boss 
	 * monsters to choose from in future iterations of the game. 
	 *
	 * @param	description	the Room's description
	 * @param	isBossRoom	whether this is the Room that should contain a boss monster
	 */ 
	public Room(String description, boolean isBossRoom) {
		this(description);
		this.isBossRoom = isBossRoom; 
	} // end overload of constructor

	
	/**
	 * Returns the description of the room. 
	 *
	 * @return	the room's description
	 */ 
	public String getDescription() {
		return this.description; 
	} // end method getDescription 
	
	
	/**
	 * Returns the Room's north exit.
	 *
	 * @return 	the Room's north exit
	 */ 
	public Room getNorth() {
		return this.north; 
	} // end method getNorth
	
	
	/**
	 * Returns the Room's south exit.
	 *
	 * @return 	the Room's south exit
	 */ 
	public Room getSouth() {
		return this.south; 
	} // end method getSouth
	
	
	/**
	 * Returns the Room's east exit.
	 *
	 * @return 	the Room's east exit
	 */ 
	public Room getEast() {
		return this.east; 
	} // end method getEast
	
	
	/**
	 * Returns the Room's west exit.
	 *
	 * @return 	the Room's west exit
	 */ 
	public Room getWest() {
		return this.west; 
	} // end method getWest 
	
	
	/**
	 * Returns a String indicating in which directions there are exits. 
	 * 
	 * @return	a String explaining in which directions there are exits
	 */ 
	public String getExits() {
		// Initialize a String to hold the output. 
		String exitString = ""; 
		
		// Initialize ArrayList of Strings to hold the directions where there are exits. 
		ArrayList<String> exitDirections = new ArrayList<String>(); 
		
		// If there's an exit to a direction, add that direction to the ArrayList. 
		if (this.getNorth() != null) {
			exitDirections.add("north"); 
		}
		if (this.getSouth() != null) {
			exitDirections.add("south"); 
		}
		if (this.getEast() != null) {
			exitDirections.add("east"); 
		}
		if (this.getWest() != null) {
			exitDirections.add("west"); 
		}
		
		// Given size of the ArrayList (i.e., the number of exits), format output String.
		// There's only one exit. 
		if (exitDirections.size() == 1) { 
			exitString += "There is an exit to the "; 
			exitString += exitDirections.get(0) + "."; 
		} 
		
		// There's more than one exit. 
		else {
			exitString += "There are exits to the "; 
			if (exitDirections.size() == 2) {
				exitString += exitDirections.get(0) + " and "; 
				exitString += exitDirections.get(1) + ".";
			} 
			else if (exitDirections.size() == 3) {
				exitString += exitDirections.get(0) + ", " + exitDirections.get(1);
				exitString += ", and " + exitDirections.get(2) + ".";
			}
			else {
				exitString += exitDirections.get(0) + ", " + exitDirections.get(1);
				exitString += ", " + exitDirections.get(2) + ", and "; 
				exitString += exitDirections.get(3) + ".";
			}
		} 
		return exitString; 
	} // end method getExits 
	
	
	/**
	 * Returns the Monster instance in the Room.
	 * 
	 * @return	the Monster in this Room
	 */ 
	public Monster getMonster() {
		return this.monsterHere; 
	} // end method getMonster
	
	
	/**
	 * Returns whether or not there is a Monster in the room.
	 * 
	 * @return	whether there is a Monster in this room.
	 */ 
	public boolean hasMonster() {
		return this.hasMonster; 
	} // end method isThereAMonster
	
	
	/** 
	 * Returns the Potion in the Room.
	 * 
	 * @return	the Potion in this Room
	 */ 
	public Potion getPotion() {
		return this.potionHere; 
	} // end method getPotion
	
	
	/** 
	 * Returns whether or not there is a Potion in the room.
	 * 
	 * @return	whether there is a Potion in this room.
	 */ 
	public boolean hasPotion() {
		return this.hasPotion; 
	} // end method isThereAPotion
	
		
	/**
	 * Sets all the Room's exits with one method. 
	 * 
	 * @param	north 	the Room to the north
	 * @param	south	the Room to the south
	 * @param	east	the Room to the east
	 * @param	west	the Room to the west
	 */ 
	public void setExits(Room north, Room south, Room east, Room west) {
		this.north = north; 
		this.south = south; 
		this.east = east; 
		this.west = west; 
	} // end method setSExits 
	
	
	/**
	 * Decides whether or not a monster should be added to a room and adds it if so. 
	 * 
	 * @param	playerLevel	the current level attained by the Player
	 */ 
	public void decideMonster(int playerLevel) {
		// Adds the boss monster to the statue room.
		if (this.isBossRoom) {
			this.monsterHere = Asenath.getInstance(); 
			this.hasMonster = true; 
		} 
		
		// In all other rooms, it must be decided whether or not to add a Monster. 
		else {
			// Generate a random number to decide whether or not to add a monster to the Room.
			int toAddOrNotToAdd = randomGenerator.nextInt(10); 
			
			// Add a Monster 90% of the time. 
			if (toAddOrNotToAdd < 9) {
				this.addMonster(playerLevel); 
			}
			
			// Otherwise, set to fields to indicate that there is no monster here. 
			else {
				this.monsterHere = null; 
				this.hasMonster = false; 
			} 
		}
	} // end method decideMonster
	
	
	/**
	 * Adds a Monster to a Room from one of three subtypes of Monster based on the level
	 * that the player has obtained. 
	 *
	 * @param	playerLevel	the current level attained by the Player
	 */ 
	private void addMonster(int playerLevel) {		
		// If the player is just starting, the weakest type of Monster is spawned. 
		if (playerLevel <= 5) {
			monsterHere = new Amphiboid(); 
		} 
		
		// As player strengthens, the monsters become more powerful. 
		else if (playerLevel > 5 && playerLevel <= 10) {
			// Generate a random number to decide which monster to add. 
			int whichMonster = randomGenerator.nextInt(2); 
			// Spawn a weak monster in half of cases. 
			if (whichMonster == 0) {
				monsterHere = new Amphiboid(); 
			} 
			// Spawn a medium strength monster in half of cases. 
			else {
				monsterHere = new HomuncularPriest(); 
			} 
		}
		// And they continue becoming even more powerful. 
		else {
			// Generate a random number to decide which monster to add. 
			int whichMonster = randomGenerator.nextInt(2); 
			// Spawn a medium strength monster in half of cases. 
			if (whichMonster == 0) {
				monsterHere = new HomuncularPriest(); 
			} 
			// Spawn a strong monster in half of cases. 
			else {
				monsterHere = new AncientOne(); 
			} 
		}
		// Set boolean value indicating Room contains a monster. 
		this.hasMonster = true; 
	} // end method addMonster
	
	
	/**
	 * Sets the reference to the Room's monster to null after it has been defeated by the 
	 * Player. This is needed so that a new Monster can be spawned when the Player 
	 * reenters the Room later. 
	 */ 
	public void nullifyMonster() {
		this.monsterHere = null; 
		this.hasMonster = false; 
	} // end method nullifyMonster
	
	
	/** 
	 * Decides whether or not a potion should be added to a room and adds it if so. A 
	 * potion is added in a third of the cases. 
	 */ 
	public void decidePotion() { 
		// Generate a random number to decide whether or not to add a potion to the Room.
		int toAddOrNotToAdd = randomGenerator.nextInt(3); 
		
		// Add a potion half the time. 
		if (toAddOrNotToAdd == 0) {
			this.addPotion(); 
		}
		
		// Otherwise, set to fields to indicate that there is no potion here. 
		else {
			this.potionHere = null; 
			this.hasPotion = false; 
		} 
	} // end method decidePotion
		
	
	
	/** 
	 * Adds a Potion to a Room randomly. In half of cases the potion will be a health 
	 * potion; in a quarter it will be a mana potion; in a quarter it will be a health 
	 * and mana potion. 
	 */ 
	private void addPotion() {
		// Generate a random number to decide which potion to add. 
		int whichPotion = randomGenerator.nextInt(4); 
		
		// Add a health potion half the time. 
		if (whichPotion == 0 || whichPotion == 1) {
			this.potionHere = new HealthPotion(); 
		} 
		
		// Add a mana potion 25% of the time. 
		else if (whichPotion == 2) {
			this.potionHere = new ManaPotion(); 
		}
		
		// Add a power potion 25% of the time. 
		else {
			this.potionHere = new PowerPotion(); 
		}
		
		this.hasPotion = true; 
	} // end method addPotion
} // end class Room