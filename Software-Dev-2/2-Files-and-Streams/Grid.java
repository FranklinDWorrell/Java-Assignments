/**
 * A class that represents a simulation of Conway's
 * Game of Life. A Grid is two two-dimensional arrays 
 * of Cell objects with methods that allow it to 
 * make Cells alive or not alive. One array represents
 * the simulation's current state, and the other is
 * the state it will be in next round. Contains the 
 * logic for when a Cell is born or dies. Also contains 
 * the default glider simulation. 
 *
 * Assignment: Homework 2 - Files and Streams
 * Class: Grid
 * Author: Franklin D. Worrell
 * Date: 9/18/2015
 */

import java.io.Serializable; 

/**
 * A class that represents each round of a simulation 
 * of Conway's Game of Life. 
 */ 
public class Grid implements Serializable {

	private Cell[][] itsCurrentState;
	private Cell[][] itsNextState;

	/**
	 * Constructor
	 * @param	numRows  	number of rows in 2D Cell array
	 * @param	numColumns	number of columns in 2D Cell array
	 */ 
	public Grid(int numRows, int numColumns) {
		itsCurrentState = new Cell[numRows][numColumns];
		itsNextState = new Cell[numRows][numColumns];
		for (int row=0; row< itsCurrentState.length; row++) {
			for (int column=0; column < itsCurrentState[row].length; column++) {
				itsCurrentState[row][column] = new Cell();
				itsNextState[row][column] = new Cell();
			}
		}
	} // end constructor

	/**
	 * Changes a Cell to make it "alive" in the Game of Life. 
	 * @param	x		the horizontal location of the Cell
	 * @param	y		the vertical location of the Cell
	 * @return		the value representing "alive" in the Game
	 */ 
	public boolean cellIsAlive(int x, int y) {
		return itsCurrentState[x][y].isAlive();
	} // end method cellIsAlive

	/**
	 * Updates the Grid to progress simulation to the next round.
	 */ 
	public void update() {
		// loop over rows
		for (int row=0; row < itsCurrentState.length; row++) {
			// loop over columns
			for (int column=0; column < itsCurrentState[row].length; column++) {
				boolean isAliveNextRound = aliveNextRound(row,column);
				itsNextState[row][column].setAlive(isAliveNextRound);
			}
		}
		swapStates();
	} // end method update


	/**
	 * Changes a cell to be alive in the next round based on the status 
	 * of its neighbors according to the rules of Conway's Game of Life.
	 * @param	row   	the horizontal location of the Cell
	 * @param	column	the vertical location of the Cell
	 * @return		the value representing "alive" in the Game
	 */ 
	private boolean aliveNextRound(int row, int column) {
		boolean aliveNextRound = false;;
		boolean currentAliveState = itsCurrentState[row][column].isAlive();
		int liveNeighbors = getCountOfLiveNeighbors(row,column);
		if (currentAliveState == true && liveNeighbors <2)
			aliveNextRound = false;
		else if (currentAliveState == true && (liveNeighbors == 2 || liveNeighbors == 3))
			aliveNextRound = true;
		else if (currentAliveState == true && liveNeighbors > 3)
			aliveNextRound = false;
		else if (currentAliveState == false && liveNeighbors == 3)
			aliveNextRound = true;
		return aliveNextRound;
	} // end method aliveNextRound

	/**
	 * Counts the number of adjacent cells that are "living" 
	 * in the Game. 
	 * @param	row   	the horizontal location of the Cell
	 * @param	column	the vertical location of the Cell
	 * @return		the number of living adjacent Cells
	 */ 
	private int getCountOfLiveNeighbors(int row, int column) {
		int numLiveNeighbors = 0;
		int up = row-1;
		int down = row+1;
		int right = column+1;
		int left = column-1;
		if (row == 0) { // top edge case
			up = itsCurrentState.length-1;
		} else if (row == itsCurrentState.length-1) { // bottom edge case
			down = 0;
		}

		if (column == 0) { // left edge case
			left = itsCurrentState[0].length-1;
		} else if (column == itsCurrentState[0].length-1) { // right edge case
			right = 0;
		}

		int[][] neighborsToConsider = { {up,left},   {up,column}, {up,right},
		                               {row,left},               {row,right},
		                               {down,left},{down,column},{down,right} };

		for (int neighborIndex = 0; neighborIndex < neighborsToConsider.length; neighborIndex++) {
			if (itsCurrentState[neighborsToConsider[neighborIndex][0]][neighborsToConsider[neighborIndex][1]].isAlive())
				numLiveNeighbors++;
		}

		return numLiveNeighbors;
	} // end method getCountOfLiveNeighbors

	/**
	 * Swaps current and next references so rounds in Game
	 * can progress. 
	 */ 
	private void swapStates() {
		Cell[][] temp = itsCurrentState;
		itsCurrentState = itsNextState;
		itsNextState = temp;
	} // end method swapStates

	/**
	 * Returns a String representation of a Grid object.
	 * @return		a String representation of Grid
	 */ 
	@Override
	public String toString() {
		String returnVal = "";
		for (int i=0; i<itsCurrentState.length; i++) {
			for (int j=0; j<itsCurrentState[i].length; j++) {
				returnVal += itsCurrentState[i][j];
				returnVal += " ";
			}
			returnVal += "\n";
		}
		return returnVal;
	}

	/**
	 * Loads a default simulation in the Game of Life that
	 * is a simple glider. 
	 */ 
	public void gliderSetup() {
		itsCurrentState[5][5].setAlive(true);
		itsCurrentState[6][5].setAlive(true);
		itsCurrentState[7][5].setAlive(true);
		itsCurrentState[7][4].setAlive(true);
		itsCurrentState[6][3].setAlive(true);
	} // end method gliderSetup

	/**
	 * Allows changes to be made to itsCurrentState so that the 
	 * GameOfLifeTUI can use Life 1.06 files to create new initial
	 * states of the game. Takes location of a cell and sets that
	 * cell to alive. 
	 * @param	xCoordinate 	the horizontal location of the Cell
	 * @param	yCoordinate 	the vertical location of the Cell
	 */ 
	public void setInitialState(int xCoordinate, int yCoordinate) { 
		itsCurrentState[xCoordinate][yCoordinate].setAlive(true); 
	} // end method setInitialState
}
