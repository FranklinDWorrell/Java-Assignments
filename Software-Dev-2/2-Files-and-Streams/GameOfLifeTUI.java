/**
 * A text-based user interface for Conway's Game of Life that allows
 * users to either run a default simulation featuring a single 
 * glider, load a pattern in Life 1.06 format, serialize a simulation 
 * to disk, or load (deserialize) a previously saved simulation. 
 *
 * Assignment: Homework 2 - Files and Streams
 * Class: GameOfLifeTUI
 * Author: Franklin D. Worrell
 * Date: 9/18/2015
 */

import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Iterator; 
import java.lang.Math; 
import java.io.IOException; 
import java.lang.IllegalStateException; 
import java.util.NoSuchElementException; 
import java.io.EOFException; 
import java.nio.file.Paths; 
import java.nio.file.Files; 
import java.io.ObjectOutputStream; 
import java.io.ObjectInputStream; 

/**
 * A TUI for our simulation of Conway's Game of Life. Must be called 
 * by runner class. Presents user with four options for how to proceed
 * with a simulation and responds accordingly. 
 */
public class GameOfLifeTUI {

	private Grid myGrid;
	int xDim;
	int yDim;
	Scanner input = new Scanner(System.in); 
	private ObjectOutputStream objectWriter; 
	private ObjectInputStream objectReader; 


	/**
	 * Constructor 
	 * Creates grid for the simulation to occur on. 
	 * @param	xDim  	the width of the Grid
	 * @param	yDim  	the length of the Grid
	 */ 
	public GameOfLifeTUI(int xDim, int yDim) {
		// Creates Grid for Game of Life simulation. 
		myGrid = new Grid(xDim,yDim);
	} // end constructor
	
	
	
	/**
	 * Prints the options menu for this version of Conway's Game
	 * of Life.
	 */ 
	public void printMenu() { 
		// Prints welcome screen and presents user with four options.
		System.out.printf("%nWelcome to Conway's Game of Life!%n"); 
		System.out.printf("%nWould you like to: %n"); 
		System.out.printf("    %s%n    %s%n    %s%n    %s%n", 
			"1) run the simulation?", 
			"2) load a pattern in Life 1.06 format?", 
			"3) serialize a simulation to disk?", 
			"4) load a previously saved simulation (deserialize)?"); 
	} // end method printMenu 
	
	
	
	/**
	 * Takes input from user's response to options menu and calls
	 * the appropriate method, given the user's request. 
	 * @param	inputChoice 	the user's choice of action
	 */ 
	public void executeInputChoice(int inputChoice) { 
		// User wishes to run default simulation. 
		if (inputChoice == 1) {
			myGrid.gliderSetup();
		} 
		
		// User wishes to load a Life 1.06 pattern. 
		else if (inputChoice == 2) { 
			loadLifeFile(); 
		} 
		
		// User wishes to serialize the current simulation. 
		else if (inputChoice == 3) { 
			serializeSimulation(); 
		} 
		
		// User wishes to load a previously serialized simulation. 
		else if (inputChoice == 4) {
			deserializeSimulation(); 
		}  
		
		// Validates user input. 
		else {
			System.out.printf("%nThat is not a valid option."); 
			System.exit(0); 
		} 
	} // end method executeInputChoice
	
	
	
	/**
	 * Opens a Life 1.06 file, reads the data, ensures the data can be 
	 * displayed in the Grid, and makes the required Cells alive.
	 * @param	patternFile 	name of the Life 1.06 file to be opened.
	 */ 
	private void loadLifeFile() { 
		// Creates an ArrayList to store and manipulate new pattern. 
		ArrayList<int[]> newPattern = new ArrayList<>();
		
		// Prompts user to input name of Life 1.06 file. 
		System.out.printf("%nPlease enter name of Life 1.06 file: "); 
		String patternFile = input.next(); 
		
		// Opens the Life 1.06 file and reads input from it.
		try (Scanner fileInput = new Scanner(Paths.get(patternFile))) {
		
			// Skips first line, which is a header in Life 1.06 files. 
			fileInput.nextLine(); 
			
			// Saves each file entry as a value in the ArrayList
			// Ensures there is still data in the file to read.
			while (fileInput.hasNext()) { 
				int xCoordinate = fileInput.nextInt(); 
				int yCoordinate = fileInput.nextInt(); 
				int [] liveCell = {xCoordinate, yCoordinate}; 
				newPattern.add(liveCell); 
			} // end while
		} // end try-with-resources
		
		catch (IOException e) {
			System.err.println("Error opening file."); 
			e.printStackTrace(); 
			System.exit(1); 
		} 
		
		catch (NoSuchElementException e) {
			System.err.println("File improperly formatted."); 
			e.printStackTrace(); 
			System.exit(1);
		} 
		
		catch (IllegalStateException e) {
			System.err.println("Error reading from file."); 
			e.printStackTrace(); 
			System.exit(1); 
		} 
		
		ArrayList<int[]> shiftedPattern = transformCoordinates(newPattern); 
		
		ArrayList<int[]> wrappedPattern = wrapCoordinates(shiftedPattern); 

		// Changes the value of isAlive to true for each of the cells that 
		// the pattern requires to be alive. 
		for (int cellCounter = 0; cellCounter < wrappedPattern.size(); cellCounter++) {
			int cellXCoordinate = wrappedPattern.get(cellCounter)[0]; 
			int cellYCoordinate = wrappedPattern.get(cellCounter)[1]; 
			myGrid.setInitialState(cellXCoordinate, cellYCoordinate);
		} 
	} // end method openLifeFile 
	
	
	
	/**
	 * Checks to see if x and y coordinates of live cells in a 
	 * loaded pattern are within the program's coordinate space. 
	 * If they are not, negative indices are eliminated by shifting
	 * all coordinates. Method is static solely for testing purposes. 
	 * @param	cellList 	the array list of coordinates in a pattern
	 * @return  	the array list of coordinates adjusted to eliminate negative indices. 
	 */ 
	public static ArrayList<int[]> transformCoordinates(ArrayList<int[]> cellList) { 
		
		int[] minimums = getMinimumXY(cellList); 
		int minXValue = minimums[0]; 
		int minYValue = minimums[1]; 

		// Determines if a x-shift is required. 
		if (minXValue < 0) {
			// Amount of horizontal transformation is absolute value of minimum x-value.
			int xShift = Math.abs(minXValue); 
			
			// Iterates through x-coordinates and performs needed transformation. 
			for (int cell = 0; cell < cellList.size(); cell++) {
				int[] row = cellList.get(cell); 
				row[0] += xShift; 
				cellList.set(cell, row); 
			} // end for
		} // end if 
		
		// Determines if a y-shift is needed. 
		if (minYValue < 0) {
			// Amount of vertical transformation is absolute value of minimum y-value. 
			int yShift = Math.abs(minYValue); 
			
			// Iterates through y-coordinates and performs needed transformation. 
			for (int cell = 0; cell < cellList.size(); cell++) {
				int[] row = cellList.get(cell); 
				row[1] += yShift; 
				cellList.set(cell, row); 
			} // end for 
		} // end if
		
		return cellList;
	} // end method transformCoordinates


	
	/**
	 * Wraps coordinates of patterns loaded as Life 1.06 files. After shifting
	 * to index properly, some indices may be out of the range [0,39], which is
	 * the range of both the height and the width of the Grid for Game of Life. 
	 * Method is static solely for testing purposes. 
	 * @param	cellList 	the coordinates of Cells to be made alive
	 * @return  	the coordinates of Cells wrapped to be in range [0, 39]
	 */ 
	public static ArrayList<int[]> wrapCoordinates(ArrayList<int[]> cellList) { 
		
		int[] maximums = getMaximumXY(cellList); 
		int maxXValue = maximums[0];
		int maxYValue = maximums[1]; 
		
		// Determines if a x text-wrapping is required. 
		if (maxXValue > 39) {
			// Iterates through x-coordinates and performs needed text-wrapping. 
			for (int cell = 0; cell < cellList.size(); cell++) {
				int[] row = cellList.get(cell); 
				row[0] %= 40; 
				cellList.set(cell, row); 
			} // end for
		} // end if 
		
		// Determines if a y text-wrapping is needed. 
		if (maxYValue > 39) {
			// Iterates through y-coordinates and performs needed text-wrapping. 
			for (int cell = 0; cell < cellList.size(); cell++) {
				int[] row = cellList.get(cell); 
				row[1] %= 40; 
				cellList.set(cell, row); 
			} // end for 
		} // end if
		
		return cellList;
	} // end method wrapCoordinates
	
	
	
	/**
	 * Calculates the minimum x- and y- coordinate values from a loaded 
	 * Life 1.06 file. Method is static solely for testing purposes. 
	 * @param	cellList 	the pattern loaded from the Life 1.06 file
	 * @return  	the minimum x-coordinate and the minimum y-coordinate
	 */ 
	public static int[] getMinimumXY(ArrayList<int[]> cellList) {
		int minXValue = 0; 
		int minYValue = 0; 

		// Finds minimum x-coordinate. 
		Iterator<int[]> cellIterator = cellList.iterator(); 
		while (cellIterator.hasNext()) {
			int[] currentCell = cellIterator.next(); 
			if (currentCell[0] < minXValue) {
				minXValue = currentCell[0];
			} 
		}  
		
		// Finds minimum y-coordinate. 
		cellIterator = cellList.iterator(); 
		while (cellIterator.hasNext()) {
			int[] currentCell = cellIterator.next(); 
			if (currentCell[1] < minYValue) {
				minYValue = currentCell[1];
			} 
		} 
		
		int[] minimums = {minXValue, minYValue}; 
		return minimums; 
	} // end method getMinimumXY
	
	
	
	/**
	 * Calculates the maximum x- and y- coordinate values from a loaded 
	 * Life 1.06 file. Method is static solely for testing purposes. 
	 * @param	cellList 	the pattern loaded from the Life 1.06 file
	 * @return  	the maximum x-coordinate and the maximum y-coordinate
	 */ 
	public static int[] getMaximumXY(ArrayList<int[]> cellList) {
		int maxXValue = 0; 
		int maxYValue = 0; 

		// Finds maximum x-coordinate. 
		Iterator<int[]> cellIterator = cellList.iterator(); 
		while (cellIterator.hasNext()) {
			int[] currentCell = cellIterator.next(); 
			if (currentCell[0] > maxXValue) {
				maxXValue = currentCell[0];
			} 
		} 
		
		// Finds maximum y-coordinate. 
		cellIterator = cellList.iterator(); 
		while (cellIterator.hasNext()) {
			int[] currentCell = cellIterator.next(); 
			if (currentCell[1] > maxYValue) {
				maxYValue = currentCell[1];
			} 
		} 
		
		int[] maximums = {maxXValue, maxYValue}; 
		return maximums; 
	} // end method getMaximumXY
	
	
	
	/**
	 * Serializes a Grid object to be saved to disk for later runs
	 * of Conway's Game of Life.
	 */ 
	public void serializeSimulation() { 
		System.out.printf("%n%s%n%s%n    %s%n    %s%n", 
			"Simulation will be stored as serialized_pattern.ser and then run.", 
			"Would you like to: ", 
			"1) Serialize the default simulation (a glider)?", 
			"2) Serialize from a Life 1.06 file simulation?");
		int serializeOption = input.nextInt(); 
		
		if (serializeOption == 1) { 
			myGrid.gliderSetup();
		} // end if
		
		else if (serializeOption == 2) {
			loadLifeFile();
		} // end else-if
		
		else {
			System.out.printf("%nThat is not a valid option."); 
			serializeSimulation(); 
		} // end else
		
		// Creates a new file to store the pattern as a serialized Grid object. 
		try {
			objectWriter = new ObjectOutputStream(
				Files.newOutputStream(Paths.get("serialized_pattern.ser")));
		}
		
		catch(IOException e) {
			System.out.println("Could not create file serialized_pattern.ser");
			e.printStackTrace(); 
			System.exit(1); 
		} 
		
		Grid patternToSave = myGrid; 
		
		// Serializes the Grid object. 
		try {
			objectWriter.writeObject(patternToSave); 
		} 
		
		catch (IOException e) {
			System.err.println("Error writing to file."); 
			e.printStackTrace(); 
			System.exit(1); 
		}
		
		// Closes the output stream. 
		try {
			objectWriter.close(); 
		} 
		
		catch (IOException e) {
			System.err.println("Error closing the file."); 
			e.printStackTrace(); 
			System.exit(1); 
		} 
	} // end method serializeSimulation
	
	
	
	/**
	 * Deserializes a saved simulation for use in Conway's Game of
	 * Life. 
	 */ 
	public void deserializeSimulation() {
		// Prompts user to enter name of file to deserialize. 
		System.out.printf("%nPlease enter name of file to deserialize: ");
		String deserializeName = input.next(); 
		
		// Opens file
		try {
			objectReader = new ObjectInputStream(
				Files.newInputStream(Paths.get(deserializeName))); 
		}
		
		catch (IOException e) {
			System.err.println("Error opening file containing serialized object."); 
			e.printStackTrace(); 
			System.exit(1); 
		} 
		
		// Reads from file until end of file is hit. 
		try {
			while (true) {
				myGrid = (Grid) objectReader.readObject(); 
			} // end while
		} 
		
		catch (EOFException e) {
			System.out.println("Deserialization complete."); 
		}
		
		catch (ClassNotFoundException e) {
			System.err.println("Error regarding object type."); 
			e.printStackTrace(); 
		} 
		
		catch (IOException e) {
			System.err.println("Error reading from file."); 
			e.printStackTrace(); 
		} 
		
		// Closes file. 
		try {
			objectReader.close(); 
		} 
		
		catch (IOException e) {
			System.err.println("Error closing file."); 
			System.exit(1); 
		} 
	} // end method deserializeSimulation
	
	
	
	/**
	 * Runs the simulation by performing an infinite loop. 
	 */ 
	public void run() throws InterruptedException {
		// Prints options menu. 
		printMenu(); 
		
		// Takes user input and stores it for use. 
		int inputChoice = input.nextInt(); 
		
		// Responds to user's choice from options menu. 
		executeInputChoice(inputChoice); 
		
		// Clears the screen for the simulation. 
		clearScreen();
		
		// Prints simulation. Purposefully an endless loop.
		while (true) {
			displayGrid();
			myGrid.update();
			Thread.sleep(50);
			clearScreen();
		} // end while
	} // end method run



	/** 
	 * Prints the Grid to the Terminal window.
	 */ 
	public void displayGrid() {
		System.out.print(myGrid);
	} // end method displayGrid



	/**
	 * Clears the terminal window so that simulation looks like
	 * an animation instead of a series of stills. 
	 */ 
	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	} // end method clearScreen
} // end class GameOfLifeTUI