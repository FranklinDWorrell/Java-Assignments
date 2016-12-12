/**
 * A textual user interface that allows players sitting
 * at the terminal to play the Pokemon Game. Calls methods
 * from the Game class which in turn interacts with the
 * Pokemon class. TUI never directly interacts with the 
 * Pokemon class. 
 * 
 * Assignment: Homework 1 - Pokemon reboot
 * Class: PokemonTUI
 * Author: Franklin D. Worrell
 * Date: 9/9/2015
 */ 
 
import java.util.Scanner; 

/**
 * A class that implements a textual user interface to 
 * allow two people to play the Pokemon Game in the 
 * Terminal window. 
 */ 
public class PokemonTUI { 
	/**
	 * Implements a textual user interface that allows players
	 * to interact with the Game class. 
	 */ 
	public static void startGame() {
		// Declares new Scanner object to receive input from users.
		Scanner input = new Scanner(System.in); 
		
		// Prints spiffy intro banner. 
		System.out.println("*************************************************************");
		System.out.println("*                                                           *"); 
		System.out.println("*  *****  *****  *    *  *****  *       *  ******  *     *  *"); 
		System.out.println("*  *   *  *   *  *   *   *      * *   * *  *    *  * *   *  *"); 
		System.out.println("*  *****  *   *  ***     *****  *  * *  *  *    *  *  *  *  *"); 
		System.out.println("*  *      *   *  *   *   *      *   *   *  *    *  *   * *  *");
		System.out.println("*  *      *****  *    *  *****  *   *   *  ******  *     *  *");
		System.out.println("*                                                           *");
		System.out.println("*************************************************************");
		
		// Asks player 1 for name and stores it. 
		System.out.println("Player 1, please name your Pokemon: "); 
		String player1Name = input.nextLine(); 
		
		// Asks player 2 for name and stores it. 
		System.out.println("Player 2, please name your Pokemon: "); 
		String player2Name = input.nextLine(); 
		
		// Calls constructor of class Game to initiate a new game and 
		// thereby create two new instances of Pokemon class. 
		Game newGame = new Game(player1Name, player2Name); 
		
		// Prints each player's initial stats so everyone knows what 
		// they're working with in the game. 
		System.out.printf("%nPlayers, here are your monsters: %s%n", 
			newGame.getStats()); 
		
		// Until one player has won, asks each player to attack or defend,
		// calls the method from Game that executes a turn. 
		while (!newGame.isGameWon()) {
			// Asks each player whether Pokemon should attack or defend. 
			System.out.printf(
				"%nPlayer 1, do you want %s to attack (enter A) or defend (enter D)?", 
				player1Name); 
			String player1Stance = input.next(); 
				
			System.out.printf(
				"Player 2, do you want %s to attack (enter A) or defend (enter D)?", 
				player2Name); 
			String player2Stance = input.next(); 
			
			// Validates players' input.
			if ((!player1Stance.equals("A") && !player1Stance.equals("D")) || 
				(!player2Stance.equals("A") && !player2Stance.equals("D"))){
				System.out.printf("%nPlease only enter A or D. Thanks!%n");
			} // end if

			// If players entered valid input, turn is accounted for. 
			else {
				// If both players defend, then no changes are made to the 
				// Pokemon objects and the loop repeats. 
				if (player1Stance.equals("D") && player2Stance.equals("D")) {
					System.out.printf("%nWell, that was NOT exciting.%n"); 
				} // end if 
			
				// If it is not the case that both players defended, then the 
				// turn gets performed. 
				else {
					// Performs the turn based on the players' choices of attack/defend. 
					newGame.performTurn(player1Stance, player2Stance); 
				} // end else
			
				// Prints results of turn with updated player stats. 
				System.out.printf("%n%s%s%n", "After this epic round of battle:", 
					newGame.getStats()); 
			} // end else 
		} // end while
		
		// If the game is over, congratulates and prints the winner and 
		// publicly humiliates the loser. 
		if (newGame.isGameWon()) { 
			System.out.printf("%nCongrats! %s is victorious!", newGame.getWhoWon()); 
			System.out.printf("%n%s has lost and brought shame upon you.%n", 
				newGame.getWhoLost()); 
		} // end if
		
		// Asks if players would like to play again. If so, restarts. 
		System.out.printf("%nWould y'all like to play again? Enter Y or N: "); 
		if (input.next().equals("Y")) {
			PokemonRunner.start(); 
		} // end if
	} // end method startGame
} // end class PokemonTUI