/**
 * A class that runs the Pokemon game by creating instances
 * of the Game class and the PokemonTUI class.
 *
 * Assignment: Homework 1 - Pokemon reboot
 * Class: PokemonRunner
 * Author: Franklin D. Worrell 
 * Date: 9/9/2015 
 */ 

/**
 * A class to run the Pokemon game. Instead of merely running
 * everything in the main method, the main method instead calls
 * a start method. This allows the PokemonTUI class to call the
 * start method, which thereby allows the players to start a new
 * game from within the game they just finished playing. Thus,
 * will not have to run PokemonRunner again in the Terminal
 * window.
 */ 
public class PokemonRunner { 
	/**
	 * Main method calls the start method.
	 */
	public static void main(String[] args) { 
		start(); 
	} // end method main
	
	/**
	 * Initializes instance of PokemonTUI class and then calls
	 * the startGame method of PokeomonTUI to initiate the new
	 * game.
	 */
	public static void start() {
		// Creates instance of PokemonTUI class.
		PokemonTUI currentGame = new PokemonTUI(); 
		
		// Calls startGame method of PokemonTUI to initiate the new game.
		currentGame.startGame(); 
	} // end method start
} // end class PokemonRunner