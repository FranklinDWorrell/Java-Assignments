/**
 * An abstract class that encapsulates the behaviors common to items that can be used by
 * the Player and carried by Monsters. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: Item
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 


/**
 * An abstract superclass for things that can be used by the Player and carried by a 
 * Monster. 
 */
public abstract class Item {
	private String name; 
	
	/**
	 * Constructor initializes the name of the Item.
	 */ 
	public Item(String name) {
		this.name = name; 
	} // end constructor
	
	
	/**
	 * Returns the name of the Item.
	 * 
	 * @return 	the Item's name
	 */ 
	public String getName() {
		return this.name; 
	} // end method getName
} // end class Item