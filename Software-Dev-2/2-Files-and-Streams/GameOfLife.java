/**
 * Runner class for Conway's Game of Life implementation. 
 * 
 * Assignment: Homework 2 - Files and Streams
 * Class: GameOfLife
 * Author: Franklin D. Worrell
 * Date: 9/18/2015
 */ 

/**
 * Runner class for Game of Life implementation.
 */ 
public class GameOfLife {

	/**
	 * Creates instance of GameOfLifeTUI and then calls its
	 * run method to begin.
	 */ 
	public static void main(String[] args) throws InterruptedException {
		GameOfLifeTUI theTUI = new GameOfLifeTUI(40,40);
		theTUI.run();
	} // end method main
} // end class GameOfLife
