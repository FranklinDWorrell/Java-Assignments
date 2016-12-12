/**
 * A class that represents an individual organism in 
 * Conway's Game of Life. Each Cell is associated with 
 * a boolean value determining whether it should be 
 * treated as alive or dead. The toString method returns
 * a representation of the Cell that is appropriate for 
 * display in the Terminal window during a simulation
 * of the Game of Life. 
 *
 * Assignment: Homework 2 - Files and Streams
 * Class: Cell
 * Author: Franklin D. Worrell
 * Date: 9/18/2015
 */ 

import java.io.Serializable; 

/**
 * A class that represents an individual organism in 
 * a simulation of Conway's Game of Life. 
 */ 
public class Cell implements Serializable {

	private boolean isAlive;

	/**
	 * Constructor
	 * Initializes instance variable to reflect a dead Cell.
	 */ 
	public Cell() {
		this.isAlive = false;
	} // end constructor


	/**
	 * Returns whether or not cell is currently alive.
	 * @return		true if cell is alive
	 */ 
	public boolean isAlive() {
		return this.isAlive;
	} // end method isAlive

	
	/**
	 * Sets isAlive to specified value. Allows user
	 * to tell a cell to be alive or dead. Important
	 * for setting up simulations.
	 * @param	val	whether or not cell should be alive
	 */ 
	public void setAlive(boolean val) {
		this.isAlive = val;
	} // end method setAlive
	
	
	/**
	 * Returns a String representation of a Cell object
	 * that is appropriate for display in the Terminal
	 * window during a simulation.
	 * @return		"0" if Cell is alive, otherwise "-"
	 */ 
	public String toString() {
		String returnVal = "-";
		if (isAlive)
			returnVal = "O"; 
		return returnVal;
	} // end method toString


	/**
	 * Compares Cells to see if they are both alive or 
	 * not. 
	 * @param	o  	Cell being investigated
	 * @return		whether Cells being compared are both alive
	 */ 
	public boolean equals(Object o) {
		boolean returnVal = false;
			if (o instanceof Cell)
				returnVal = (this.isAlive == ((Cell)o).isAlive());
			return returnVal;
	} // end method equals
}
