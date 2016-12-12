/**
 * A class that holds the game play logic for a text-based adventure game that takes 
 * place in the setting created by the Dungeon class. 
 * 
 * Assignment: Homework 5: Adding Players and Items
 * Class: GameRunner
 * Author: Franklin D. Worrell
 * Date: 12/7/2015
 */ 

import java.util.Scanner; 

/**
 * A runner class containing the game logic for a text-based adventure game. Initializes 
 * an instance of class Dungeon, gets user input, and executes the user's choice until 
 * the user quits or the game ends. Handles basically all of the input/output for the 
 * game with the exception of a few print statements. If I had more time, I would have 
 * written a separate TUI class for the game as practice. 
 */ 
public class GameRunner {
	private static boolean gameOver = false; 
	private static Scanner input = new Scanner(System.in); 
	private static Dungeon dungeon = new Dungeon(); 
	private static Room currentRoom = dungeon.getRoom0(); 
	private static Player player = null; 
	
	
	/**
	 * Executes the text-based adventure game. Informs user of her options, gets input 
	 * from her, and responds accordingly until the user either quits, is killed by a
	 * monster, or defeats the boss monster to win. 
	 */ 
	public static void main(String[] args) {
		// Prints welcome message and intial instructions. 
		printIntro(); 
		printInstructions(); 
		
		// Asks user to name her player, then creates that Player. 
		String name = nameHero(); 
		player = new Player(name); 
		System.out.println(); 
		
		// Basic Game Loop. 
		while (!gameOver) { 
			// Add a monster to the currentRoom.
			currentRoom.decideMonster(player.getLevel()); 
			currentRoom.decidePotion(); 
			Monster currentMonster = currentRoom.getMonster(); 
			
			// Print the room description and exit choices. 
			System.out.printf("%n%s%n%n", currentRoom.getDescription()); 
			System.out.println(player.toString()); 
			
			// Basic combat loop. 
			while (currentRoom.hasMonster() && player.isAlive()) {
				printOpponent(currentMonster); 
				
				// Determine whether the Player wants to drink a potion before combat. 
				boolean potionOrFight = printInitialTurnOptions(); 
				if (potionOrFight) {
					usePotionOptions(); 
				} 
				
				// Determine how the Player wants to attack the Monster. 
				boolean combatChoice = printCombatOptions(currentMonster); 
				player.setAttackStyle(combatChoice); 
				
				// Let Player attack Monster in chosen fashion. 
				player.takeTurn(currentMonster); 
				printCombatUpdate(player, currentMonster); 
				
				// If the Monster didn't die, it strikes back--you know, like the Empire.
				if (currentMonster.isAlive()) {
					currentMonster.takeTurn(player); 
					printCombatUpdate(currentMonster, player); 
					
					// The player died valiantly in combat. 
					if (!player.isAlive()) {
						gameOver = true; 
						printGameLostMessage(currentMonster); 
						return; 
					} 
				} 
				
				// The player killed the monster! 
				else if (!currentMonster.isAlive()) {
					// The player has defeated the boss monster and won the game. 
					if (currentMonster.getName().equals("Asenath")) {
						gameOver = true; 
						printGameWonMessage(); 
						return; 
					} 
					
					// The monster defeated was just an everyday no-big-deal monster. 
					player.acquireXP(currentMonster.getXP()); 
					plunderCorpse(currentMonster); 
					currentRoom.nullifyMonster(); 
				} 
			} // end Basic Combat Loop
			
			// If there's a potion in the Room, ask the player if she wants to take it. 
			if (currentRoom.hasPotion()) {
				handleRoomPotion(currentRoom.getPotion()); 
			} 
			
			// After monster is defeated and loot acquired, the player can change rooms. 
			System.out.printf("%n%s%n", currentRoom.getExits()); 
			handleMovement(); 
		} // end Game Loop 
	} // end method main 
	
	
	/**
	 * Prints an introductory message telling the user the game scenario and the goal of
	 * the game. 
	 */ 
	private static void printIntro() {
		System.out.println("You have ventured into the unholy temple to Cthulhu."); 
		System.out.println("It is infested with his blasphemous legions."); 
		System.out.println("Fight them to increase your strength and prowess."); 
		System.out.println("If you survive the lesser horrors, make your way to the Statue Room."); 
		System.out.println("There, you can battle the witch priestess Asenath. But Beware!"); 
		System.out.println("You should avoid that room until you are strong enough to face her!"); 
		System.out.println("Asenath waits in the Statue Room just north of the Altar to Cthulhu."); 
		System.out.println(); 
	} // end method printIntro
	
	
	/**
	 * Prints basic instructions for how the user will interact with the game during play.
	 */ 
	private static void printInstructions() {
		System.out.println("As you explore Cthulhu's Temple, you will find Items"); 
		System.out.println("and face Monsters with which you must do battle."); 
		System.out.println("In each room you will be presented with a set of choices."); 
		System.out.println("Choose carefully and hone your skills to face Asenath."); 
		System.out.println(); 
	} // end method printInstructions
	
	
	/**
	 * Prompts the user for a name for her character and takes the response from input.
	 * 
	 * @return	a String of the name entered by the user
	 */ 
	private static String nameHero() {
		System.out.println("What would you like to name your hero?");
		String name = input.nextLine(); 
		return name; 
	} // end method getHeroName
	
	
	/**
	 * Asks the player whether or not she would like to drink a potion before attacking
	 * the current Monster. 
	 *
	 * @return 	whether the Player wants to drink a potion before attacking the monster
	 */ 
	private static boolean printInitialTurnOptions() {
		System.out.println("Would like to drink a potion before attacking the monster?"); 
		System.out.println("y or n"); 
		return parseYesNoInput(); 
	} // end method printInitialTurnOptions
	
	
	/**
	 * Allows player to drink a potion of her choice from her inventory. Presents the 
	 * Player with a list of the available potions, asks for her choice of which to 
	 * drink, and then has the Player drink the potion. 
	 */
	private static void usePotionOptions() {
		printPotionOptions(); 
		Potion potionToUse = getPotionChoice(); 
		if (potionToUse != null) {
			player.drinkPotion(potionToUse); 
			postPotionUpdate(potionToUse); 
		} 
	} // end method usePotionOptions
	
	
	/**
	 * Prints the potions the player is carrying and then prints the options for drinking 
	 * a potion. 
	 */ 
	private static void printPotionOptions() {
		System.out.println(); 
		System.out.println(player.getPotionInventory()); 
		System.out.println(); 
		System.out.println("Which type of potion would you like to use?"); 
		System.out.println("   1 a Health Potion, "); 
		System.out.println("   2 a Mana Potion, or "); 
		System.out.println("   3 a Power Potion? "); 
	} // end method printPotionOptions
	
	
	/**
	 * Asks the Player what potion she wants to drink and returns a potion of that type.
	 * Returns null if the player does not have a potion of the requested type and does 
	 * not want to drink a different potion instead. 
	 *
	 * @return	the Potion the Player wishes to drink
	 */ 
	private static Potion getPotionChoice() {
		String potionChoice = input.next(); 
		if (potionChoice.equals("1")) {
			if (player.hasPotionType("Health Potion")) {
				return new HealthPotion();
			} 
			else {
				return tryPotionsAgain(); 
			}
		} 
		else if (potionChoice.equals("2")) {
			if (player.hasPotionType("Mana Potion")) {
				return new ManaPotion(); 
			} 
			else { 
				return tryPotionsAgain(); 
			} 
		} 
		else if (potionChoice.equals("3")) { 
			if (player.hasPotionType("Power Potion")) {
				return new PowerPotion(); 
			} 
			else {
				return tryPotionsAgain(); 
			}
		} 
		else {
			System.out.println("Please only enter an integer 1, 2, or 3. "); 
			printPotionOptions(); 
			return getPotionChoice(); 
		} 
	} // end method getPotionChoice 
	
	
	/**
	 * Allows player to drink a different type of potion if she did not have one of her
	 * first choice. Returns null if she does not want to use a different Potion. 
	 *
	 * @return	the Player's second choice in Potion use
	 */ 
	private static Potion tryPotionsAgain() {
		System.out.println("You do not have any potions of that type. "); 
		System.out.println("Would you like to drink a different type of potion? "); 
		if (parseYesNoInput()) {
			printPotionOptions(); 
			return getPotionChoice(); 
		} 
		else {
			return null; 
		} 
	} // end method tryPotionsAgain
	
	
	/**
	 * Prints an update on the Player's stats after she drinks a Potion. 
	 * 
	 * @param	potionDrank	the potion the Player used
	 */ 
	private static void postPotionUpdate(Potion potionDrank) {
		System.out.printf("%n%s%s%s%s%s%s%s%s%s%n", "After drinking the ", 
			potionDrank.getName(), ", ", player.getName(), " has ", player.getHealth(), 
			" health and ", player.getMana(), " mana."); 
	} // end method postPotionUpdate


	/**
	 * Prints a quick line detailing the Monster the Player is currently fighting.
	 * 
	 * @param 	monster	the monster fighting the Player
	 */ 
	private static void printOpponent(Monster monster) {
		System.out.println("You are fighting " + monster.toString()); 
		System.out.println(); 
	} // end method printOpponent

	/**
	 * Prints the Player's choice of moves in combat. Calls helper method to collect the
	 * Player's choice. 
	 * 
	 * @param	monster	the Monster that the Player is fighting
	 * @return	whether the Player wants to attack magically or physically
	 */ 
	private static boolean printCombatOptions(Monster monster) {  
		System.out.println(); 
		System.out.println("Would you like to: "); 
		System.out.printf("   1 Attack the %s%s%s, or %n", monster.getName(), 
			" with your ", player.getWeapon().getName()); 
		System.out.printf("   2 Cast your attack spell on the %s?%n", monster.getName()); 
		System.out.println("Please enter 1 or 2"); 
		return getCombatChoice(monster); 
	} // end method printCombatOptions 
	
	
	/**
	 * Records the Player's choice in attack styles and parses it as a boolean value to 
	 * sent to the Player's setAttackStyle method. Also provides validation for the 
	 * user's input. 
	 * 
	 * @param	monster	the Monster the Player is fighting
	 * @return	the Player's choice of physical or magical attack, validated
	 */ 
	private static boolean getCombatChoice(Monster monster) {
		String choiceNumber = input.next();
		boolean choice = false; 
		
		// Set the boolean value appropriately.
		if (choiceNumber.equals("1")) {
			choice = false; 
		}
		else if (choiceNumber.equals("2")) {
			choice = true; 
		} 
		
		// Validate the inputted integer. 
		else {
			System.out.println("Please enter only an integer 1 or 2.");
			printCombatOptions(monster); 
		}
		
		return choice; 
	} // end method getCombatChoice
	
	
	/**
	 * Prints the results of a GameCharacter's attack on another GameCharacter. 
	 * 
	 * @param	attacker	the attacking GameCharacter
	 * @param	defender	the defending GameCharacter
	 */ 
	private static void printCombatUpdate(GameCharacter attacker, GameCharacter defender) {
		System.out.printf("%n%s%s%s%s%s%s%n", defender.getName(), " now has ", 
			defender.getHealth(), " health after ", attacker.getName(), "'s attack. "); 
		System.out.println(); 
	}
	
	
	/**
	 * Allows player to take items carried by a defeated Monster.
	 *
	 * @param 	monster	the defeated Monster being looted
	 */ 
	private static void plunderCorpse(Monster monster) {
		// Capture references to what the Monster was carrying and get a total of Items. 
		Weapon lootedWeapon = monster.getWeapon(); 
		Potion lootedPotion = monster.getPotion(); 
		int amountOfPlunder = 0; 
		if (lootedWeapon != null) {
			amountOfPlunder++; 
		} 
		if (lootedPotion != null) {
			amountOfPlunder++; 
		} 
		
		// Inform the player what the Monster was carrying, if anything. 
		printPlunderString(amountOfPlunder, lootedWeapon, lootedPotion, monster); 
		System.out.println(); 
		
		// If there's something the player can take, ask the player if she wants it. 
		if (amountOfPlunder != 0) {
			System.out.printf("%n%s%s%s%n%s%n", "Would you like to plunder what the ", 
				monster.getName(), " carried? ", "Please enter y or n."); 
			
			// Player wants to take the item(s), so appropriate method(s) called. 
			if (parseYesNoInput()) {
				if (lootedWeapon != null) {
					if (canWeaponBeEquipped(lootedWeapon)) {
						player.equipWeapon(lootedWeapon); 
					} 
					else {
						System.out.println("Your current weapon is superior."); 
						System.out.println("You leave the monster's weapon behind."); 
						System.out.println(); 
					}
				} 
				if (lootedPotion != null) {
					player.addToPotionInventory(lootedPotion); 
				} 
			}
			// Do nothing if Player doesn't want the Items. 
			System.out.println(); 
		} 
		// Do nothing if there were no Items carried by the Monster. 
	} // end method plunderCorpse
	
	
	/**
	 * Formats and prints a String explaining what Items a dead Monster was carrying and
	 * that are available for the Player to take. 
	 * 
	 * @param	amountOfPlunder	the number of items carried by the Monster
	 * @param	lootedWeapon	the Weapon carried by the Monster
	 * @param 	lootedPotion	the Potion carried by the Monster
	 * @param	monster			the Monster whose corpse is being looted
	 */ 
	private static void printPlunderString(int amountOfPlunder, Weapon lootedWeapon, 
		Potion lootedPotion, Monster monster) { 
		// Initialize the String.
		String plunderString = "The " + monster.getName() + " carried "; 
		
		// The Monster wasn't carrying anything. 
		if (amountOfPlunder == 0) {
			plunderString += "nothing of value."; 
		} 
		
		// The Monster carried only a Weapon. 
		else if (amountOfPlunder == 1 && lootedWeapon != null) {
			plunderString += "a " + lootedWeapon.getName() + ". "; 
		} 
		
		// The Monster carried only a Potion. 
		else if (amountOfPlunder == 1 && lootedPotion != null) {
			plunderString += "a " + lootedPotion.getName() + ". "; 
		} 
		
		// The Monster carried both a Weapon and a Potion. 
		else {
			plunderString += "a " + lootedWeapon.getName() + " and a "; 
			plunderString += lootedPotion.getName() + ". "; 
		} 
		
		System.out.println(plunderString); 
	} // end method printPlunderString
	
	
	/**
	 * Returns whether or not a looted weapon is better than the weapon with which the 
	 * Player is currently attacking. 
	 *
	 * @param	lootedWeapon	the weapon the player pulled from a Monster's corpse
	 * @return	whether the looted weapon is better than the player's current Weapon
	 */ 
	private static boolean canWeaponBeEquipped(Weapon lootedWeapon) {
		if (lootedWeapon.getAttackBonus() > player.getWeapon().getAttackBonus()) {
			return true; 
		} 
		else {
			return false; 
		} 
	} // end method canWeaponBeEquipped

	
	
	/**
	 * Deals with Potions that a room may contain. If there is a potion, it asks the user
	 * whether or not she would like to take the potion. Her answer is parsed and 
	 * responded to appropriately.
	 */ 
	private static void handleRoomPotion(Potion roomPotion) { 
		System.out.println("You found a " + roomPotion.getName() + ". "); 
		System.out.println("Would you like to take it?"); 
		System.out.println("y or n"); 
		boolean takePotion = parseYesNoInput();  
		if (takePotion) {
			player.addToPotionInventory(roomPotion); 
		} 
	} // end method handleRoomPotion
	
	
	/**
	 * Parses a user's response to a yes or no question into a boolean value. Validates 
	 * the input and asks for a proper answer if response was invalid. 
	 * 
	 * @return	whether the user wants the Player to do something
	 */ 
	private static boolean parseYesNoInput() {
		String answer = input.next(); 
		if (answer.equals("Y") || answer.equals("y")) {
			return true; 
		} 
		else if (answer.equals("N") || answer.equals("n")) {
			return false; 
		} 
		
		// User entered invalid input. 
		else {
			System.out.println("Please only enter either y or n:"); 
			return parseYesNoInput(); 
		} 
	} // end method parseYesNoInput
	
	
	/**
	 * Prompts the user to enter the direction in which she wants the Player to move. 
	 * Calls the appropriate method to get the ball rolling on making that move happen. 
	 */ 
	private static void handleMovement() {
		System.out.println(); 
		System.out.println("In which direction would you like to go?"); 
		System.out.println("Please enter the first letter of your chosen direction."); 
		System.out.println("Or you may enter Q to quit the game."); 
		String choice = input.next(); 
		getPlayerChoice(choice); 
	} // end method handleMovement
	
	
	/**
	 * Takes the user's input of choice, parses it, and responds appropriately. 
	 */ 
	private static void getPlayerChoice(String choice) {
		
		// User quits like a quitter. 
		if (choice.equals("q") || choice.equals("Q")) {
			System.out.println("Your cowardice has been noted."); 
			System.out.println("Please play again when you're feeling braver."); 
			gameOver = true; 
		}
		
		// User entered a valid direction. 
		else if (((choice.equals("n") || choice.equals("N")) || 
				  (choice.equals("s") || choice.equals("S"))) || 
			     ((choice.equals("e") || choice.equals("E")) ||
			      (choice.equals("w") || choice.equals("W")))) {
			exitRoom(choice); 
		}

		// User entered invalid input. 
		else {
			System.out.println("Sorry, I don't understand that."); 
			handleMovement(); 
		} 
	} // end method getPlayerChoice
	
	
	/**
	 * Assuming the player's input was valid, changes the current room to the room beyond the exit
	 * chosen by the user. If the user picked a direction in which there is no exit, a message is 
	 * printed and the getPlayerChoice method is called again. 
	 *
	 * @param	choice	the player's choice of direction
	 */
	private static void exitRoom(String choice) {
		// The user entered a direction in which there is an exit. 
		if ((choice.equals("n") || choice.equals("N")) && currentRoom.getNorth() != null) {
			currentRoom = currentRoom.getNorth(); 
		} 
		else if ((choice.equals("s") || choice.equals("S")) && currentRoom.getSouth() != null) {
			currentRoom = currentRoom.getSouth(); 
		}
		else if ((choice.equals("e") || choice.equals("E")) && currentRoom.getEast() != null) {
			currentRoom = currentRoom.getEast(); 
		}
		else if ((choice.equals("w") || choice.equals("W")) && currentRoom.getWest() != null) {
			currentRoom = currentRoom.getWest(); 
		}
		
		// The user entered a direction in which there is no exit. 
		else {
			System.out.println("Only a cold, stone wall lays in that direction."); 
			handleMovement(); 
		}
	} // end method exitRoom
	
	
	/**
	 * Prints a message saying that the Player lost like a loser.
	 */ 
	public static void printGameLostMessage(Monster monster) {
		System.out.printf("%n%s%s%s%s%n", player.getName(), " was defeated by ", 
			monster.getName(), ". Your corpse will never be found in this unholy place.");
		System.out.println(); 
	} // end method printGameLostMessage
	
	
	/**
	 * Prints a message saying that the Player was victorious and won the game.
	 */ 
	public static void printGameWonMessage() {
		System.out.println(); 
		System.out.println("You have defeated the witch priestess Asenath!"); 
		System.out.println("Congratulations, you have prevented the freeing of Cthulhu..."); 
		System.out.println("...for now..."); 
		System.out.println(); 
	} // end method printGameWonMessage
} // end class GameRunner