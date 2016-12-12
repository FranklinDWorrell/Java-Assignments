/**
 * A class that models a dungeon for a text-based adventure game. Holds instances of the 
 * Room class and sets their exits to construct a map through which the player can travel. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Dungeon
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 

/**
 * A class that models a dungeon for a text-based adventure game. Creates rooms and 
 * connects them via exits. 
 */ 
public class Dungeon { 
	// Declare Room instance variables for each room in the Dungeon. 
	private Room antechamber, tortureRoom, holdingCell, hall, chapel; 
	private Room passage1, greenRoom, robeRoom, passage2, altarRoom, statueRoom; 
	

	/**
	 * Instantiates an instance of the Dungeon class. Since the Dungeon's structure is 
	 * predefined, the constructor takes no arguments; instead, it initializes the 
	 * instance variables representing each room in the Dungeon and sets each room's 
	 * exits. 
	 */ 
	public Dungeon() {
		// Initialize each room with a description passed to Room's constructor. 
		this.antechamber = new Room(
			"You've entered a low antecamber covered in profane carvings and symbols. "); 
		this.tortureRoom = new Room(
			"Here, sacrificial victims were tortured to lower their resolve. "); 
		this.holdingCell = new Room(
			"This cell held the sacrificial victims before they were dragged to the altar. "); 
		this.hall = new Room(
			"A narrow hall with a low ceiling and an ominous, putrid smell. "); 
		this.chapel = new Room(
			"The unholy chapel is litered with statues of unspeakable blasphemies. "); 
		this.passage1 = new Room(
			"A claustrophic passage scattered with detritus and smelling of past profanities. "); 
		this.greenRoom = new Room(
			"Here, Cthulhu's priest prayed and readied themselves to sacrifice innocents. "); 
		this.robeRoom = new Room(
			"Walls covered with pegs; floor littered with mouldering, carmine garments. "); 
		this.passage2 = new Room(
			"An inky black and low-ceilinged passage--bearly wide enough for two to pass. "); 
		this.altarRoom = new Room(
			"The unnatural altar to the sleeping Cthulhu. Dark stains putresce the floor. "); 
		// Since this Room holds Asenath, Room's overload constructor version is called. 
		this.statueRoom = new Room(
			"A statue of Cthulhu towers above; insane Asenath stalks beneath him. ", true); 
		
		// Set each room's exits. 
		antechamber.setExits(this.chapel, null, null, null); 
		tortureRoom.setExits(this.holdingCell, null, null, null); 
		holdingCell.setExits(this.robeRoom, this.tortureRoom, this.hall, null); 
		hall.setExits(null, null, this.chapel, this.holdingCell); 
		chapel.setExits(this.altarRoom, this.antechamber, this.passage1, this.hall); 
		passage1.setExits(null, null, this.greenRoom, this.chapel); 
		greenRoom.setExits(this.passage2, null, null, this.passage1); 
		robeRoom.setExits(null, this.holdingCell, this.passage2, null); 
		passage2.setExits(this.altarRoom, this.greenRoom, null, this.robeRoom); 
		altarRoom.setExits(this.statueRoom, this.chapel, this.passage2, null); 
		statueRoom.setExits(null, this.altarRoom, null, null); 
	} // end constructor
	
	
	/**
	 * Returns the first room of the Dungeon, namely, the room in which the player starts 
	 * the game.
	 * 
	 * @return	the entrance Room of the Dungeon
	 */ 
	public Room getRoom0() {
		return this.antechamber; 
	} // end method getRoom0
} // end class Dungeon