/**
 * A class that implements the game logic for the 
 * Pokemon Game. Receives calls from the TUI class 
 * calls methods of Pokemon class. 
 * 
 * Assignment: Homework 1 - Pokemon reboot
 * Class: Game
 * Author: Franklin D. Worrell
 * Date: 9/9/2015 
 */ 

import java.util.Random; 

/**
 * A class to implement the game logic for Pokemon. 
 */ 
public class Game {
	/**
	 * Creates random number generator for use in creating 
	 * Pokemon objects. Declares two new Pokemon objects. 
	 * Initializes boolean instance variable to track whether
	 * game is over or not. 
	 */ 
	Random randomGenerator = new Random(); 
	private Pokemon player1, 
	                player2; 
	private boolean gameWon = false; 
	
	
	/**
	 * Creates an instance of game class. Generates random
	 * values for instance variables of two Pokemon objects.
	 * Initializes two Pokemon objects based on these values
	 * and the names inputted by users. Names fed into this
	 * constructor by call from PokemonTUI. The two Pokemon
	 * cannot have the same initial speed, because this would 
	 * overly complicate determining who strikes first when 
	 * both attack.
	 * @param	namePlayer1		name of first player's Pokemon
	 * @param	namePlayer2		name of second player's Pokemon
	 * @ensure		1 <= player1InitialTolerance <= 100
	 * @ensure		1 <= player1InitialSpeed <= 100
	 * @ensure		1 <= player1InitialPower <= 20
	 * @ensure		1 <= player2InitialTolerance <= 100
	 * @ensure		1 <= player2InitialSpeed <= 100
	 * @ensure		1 <= player2InitialPower <= 20
	 * @ensure		player1InitialSpeed != player2InitialSpeed
	 */ 
	public Game(String namePlayer1, String namePlayer2) { 
		// Generates instance variables for player1's Pokemon.
		int player1InitialTolerance = randomGenerator.nextInt(100) + 1;
		int player1InitialSpeed = randomGenerator.nextInt(100) + 1;
		int player1InitialPower = randomGenerator.nextInt(20) + 1;
		
		// Creates player1's Pokemon.
		player1 = new Pokemon(namePlayer1, player1InitialTolerance, 
			player1InitialSpeed, player1InitialPower); 
		
		// Generates instance variables for player2's Pokemon. 
		int player2InitialTolerance = randomGenerator.nextInt(100) + 1;
		int player2InitialSpeed = randomGenerator.nextInt(100) + 1;
		int player2InitialPower = randomGenerator.nextInt(20) + 1; 
		
		// Checks that both Pokemon will not have the same speed and 
		// changes player2InitialSpeed if necessary. 
		while (player1InitialSpeed == player2InitialSpeed) {
			player2InitialSpeed = randomGenerator.nextInt(100) + 1;
		} // end while
		
		// Creates player2's Pokemon. 
		player2 = new Pokemon(namePlayer2, player2InitialTolerance, 
			player2InitialSpeed, player2InitialPower); 
	} // end constructor
	
	
	/**
	 * Returns string representation of both player's Pokemon
	 * @return 	descriptions of both Pokemon objects
	 */ 
	public String getStats() {
		return String.format("%n%s%n%s", player1.toString(), player2.toString()); 
	} // end method getStats
	
	
	/**
	 * Calls defend method of defending Pokemon and calls attack method of 
	 * attacking Pokemon to handle cases when one Pokemon attacks and the 
	 * other defends. 
	 * @param 	attacker		the attacking Pokemon
	 * @param	defender		the defending Pokemon
	 */ 
	public void attackDefender(Pokemon attacker, Pokemon defender) { 
		defender.defend(); 
		attacker.attack(defender); 
	} // end method attackDefender
	
	
	/**
	 * Calls attack method of both Pokemon when they both attack and 
	 * neither defends. Determines whether or not the faster Pokemon strikes 
	 * first. If the Pokemon that was attacked first survived the attack, 
	 * then it attacks the first attacker. 
	 * @param	fasterAttacker		the faster attacking Pokemon
	 * @param	slowerAttacker		the slower attacking Pokemon
	 */ 
	public void attackAttacker(Pokemon fasterAttacker, Pokemon slowerAttacker) {
		// If a Pokemon is faster, it strikes first 80% of the time. 
		int oddsOfFirstStrike = randomGenerator.nextInt(10); 				
		if (oddsOfFirstStrike <= 7) {
			fasterAttacker.attack(slowerAttacker); 
				
			// If both players survived faster Pokemon's attack, then 
			// the slower player attacks. 
			if (!this.isGameWon()) {
				slowerAttacker.attack(fasterAttacker); 
			} // end if
		} // end if
			
		// The 20% of cases in which faster player does not strike first. 
		else if (oddsOfFirstStrike >= 8) {
			slowerAttacker.attack(fasterAttacker); 
				
			// If both players survived slower player's attack, then 
			// faster player attacks. 
			if (!this.isGameWon()) { 
				fasterAttacker.attack(slowerAttacker); 
			} // end if
		} // end else if
	} // end method attackAttacker
	
	
	/**
	 * Executes a turn in the Pokemon game. Given each player's decision
	 * to either attack or defend, the relevant methods are called.
	 * @param	player1Stance	player 1's decision to attack or defend
	 * @param	player2Stance	player 2's decision to attack or defend
	 * @require		player1Stance.equals("A") || player1Stance.equals("D")
	 * @require		player2Stance.equals("A") || player2Stance.equals("D") 
	 * @require		player1.speed() != player2.speed()
	 */ 
	public void performTurn(String player1Stance, String player2Stance) {
		// Since nothing changes when both players defend, this is handled
		// by the TUI, and a statement explaining what happened is printed. 
		
		// When player 1 attacks and player 2 defends. 
		if (player1Stance.equals("A") && player2Stance.equals("D")) {
			this.attackDefender(player1, player2);  
		} // end if 
		
		// When player 1 defends and player 2 attacks. 
		else if (player1Stance.equals("D") && player2Stance.equals("A")) {
			this.attackDefender(player2, player1); 
		} // end else if
		
		// When both players attack. Determines which Pokemon is faster 
		// and calls attackAttacker method with the two Pokemon in the 
		// correct order. 
		else if (player1Stance.equals("A") && player2Stance.equals("A")) { 			
			// Determines whether player 1 is faster. 
			if (player1.speed() > player2.speed()) {
				this.attackAttacker(player1, player2); 
			} // end if
			
			// Determines whether player 2 is faster. 
			else if (player2.speed() > player1.speed()) {
				this.attackAttacker(player2, player1); 
			} // end else if
		} // end else if
	} // end method performTurn
	
	
	/**
	 * Determines whether or not game is won and returns a boolean
	 * value reflecting that.  
	 * @return		true if game is over and false if still in play
	 */
	public boolean isGameWon() {
		// Checks whether either Pokemon's tolerance is zero. 
		if (player1.tolerance() == 0 || player2.tolerance() == 0) {
			gameWon = true; 
		} // end if
		return gameWon; 
	} // end method isGameWon
	
	
	/**
	 * Determines who won the game if it is over and returns the name
	 * of the Pokemon that won. 
	 * @return		name of player's Pokemon that won
	 * @require		gameWon == true
	 */ 
	public String getWhoWon() { 
		// Winning Pokemon will have higher tolerance than loser. 
		if (player1.tolerance() > player2.tolerance()) {
			return player1.name(); 
		} // end if
		else {
			return player2.name(); 
		} // end else
	} // end method whoWon
	
	
	/**
	 * Determines who lost the game if it is over and returns the 
	 * name of the Pokemon that lost. 
	 * @return		name of player's Pokemon that lost
	 * @require		gameWon == true
	 */ 
	public String getWhoLost() {
		// Losing Pokemon will have lower tolerance than winner. 
		if (player1.tolerance() > player2.tolerance()) {
			return player2.name(); 
		} // end if
		else {
			return player1.name(); 
		} // end else
	} // end method getWhoLost
} // end class Game