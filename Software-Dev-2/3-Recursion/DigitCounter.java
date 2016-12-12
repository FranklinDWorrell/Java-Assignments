/**
 * A class that facilitates the multiply method of class Recursion. Hence, multiply is an example 
 * of indirect object recursion. Namely, this class creates Digits that create more of themselves 
 * as needed to represent larger and larger numbers. Allows the multiply method to simply call an 
 * increment method in nested for loops while still recursively solving the problem--kind of a 
 * dirty trick, I know. This class is a variation of the DigitCounter class discussed in the 
 * odometer example in class from Nino and Hosch's "Introduction to Programming and Object Oriented 
 * Design Using Java." Some functionality has been removed from the example while more has been 
 * inserted in other ways. 
 *
 * Assignment: Homework 3 - Recursion (more practice)
 * Class: DigitCounter
 * Author: Franklin D. Worrell
 * Date: 11/3/2015
 */ 

/**
 * A class that creates a set of digits that can be incremented. Each digit can contain an int 
 * 0-9. When a digit needs to be greater than 9, it creates a new digit to its left to hold its
 * carry numbers. This class is an adaption of the DigitCounter class from Nino and Hosch's 
 * "Introduction to Programming and Object Oriented Design Using Java" that was used in the 
 * odometer example in lecture. 
 */ 
public class DigitCounter {
	// Declare first Digit. 
	private Digit onesPlace; 
	// Declare instance variable to keep track of the number of Digits. 
	private int numberOfDigits; 
	
	/**
	 * Constructor
	 *
	 * Takes no arguments, because the multiply method of class Recursion does not know anything
	 * about the size of the product before inquiring this class. Creates a new DigitCounter object
	 * containing a single Digit. 
	 */ 
	public DigitCounter() {
		this.onesPlace = new Digit(); 
		this.numberOfDigits = 1; 
	} // end constructor 
	
	
	/**
	 * Increments the DigitCounter by 1. To do so, it must call the increment method of the first
	 * Digit in the DigitCounter. 
	 */ 
	public void increment() {
		this.onesPlace.incrementDigit(); 
	} // end method increment
	
	/** 
	 * Creates a String representation of a DigitCounter object. Specifically implemented here as a
	 * helper method for getCount. It is easier to get a String representing the count and then 
	 * convert that to an int than simply trying to pull out an int. 
	 *
	 * @return		a String representation of the DigitCounter object
	 */ 
	@Override
	public String toString() {
		// Initialize an empty String to use. 
		String countString = ""; 
		
		// Get the first Digit in the DigitCounter instance. 
		Digit currentDigit = this.onesPlace; 
		
		// Iterate through the Digits in the DigitCounter and concatenate their values to the String.
		while (currentDigit.getNext() != null) {
			countString = currentDigit.getNumber() + countString; 
			currentDigit = currentDigit.getNext(); 
		} 
		
		// The final Digit will not have been concatenated given the while loop's structure. 
		countString = currentDigit.getNumber() + countString; 
		
		return countString; 
	} // end method toString 
	
	
	/**
	 * Returns an int representing the count of the DigitCounter. Uses toString as a helper method. 
	 *
	 * @return		the int representation of the DigitCounter's count. 
	 */ 
	public int getCount() {
		// Gets the String representation of the count. 
		String stringCount = this.toString(); 
		
		// Converts the String into an int. 
		int total = Integer.parseInt(stringCount); 
		
		return total; 
	} // end method getCount
	
	
	/**
	 * A class that implements a Digit within the DigitCounter. Each Digit can contain a value 0-9 
	 * and can increment itself. Each Digit can also increment the Digit to its left when its own
	 * value would otherwise exceed 9. 
	 */ 
	private class Digit { 
		// The number 0-9 that a Digit contains. 
		// @invariant (number >= 0) && (number <= 9) 
		private int number; 
		
		// A reference to the Digit to the left, if there is one. 
		private Digit nextDigit; 
		
		/**
		 * Constructor
		 *
		 * Takes no argument, because the first Digit created for a DigitCounter will need to be
		 * initialized to 0 and have no left Digit. Creates an instance of Digit containing the 
		 * int 0 with no left neighbor Digit. 
		 */ 
		public Digit() {
			this.number = 0; 
			this.nextDigit = null; 
		} // end constructor
		
		
		/**
		 * Returns the int contained in a Digit instance. 
		 *
		 * @return	the int that the Digit contains
		 */ 
		public int getNumber() {
			return this.number; 
		} // end method getNumber
		
		
		/**
		 * Returns the next Digit in the DigitCounter. 
		 *
		 * @return		the next Digit in the DigitCounter instance
		 */ 
		public Digit getNext() {
			return this.nextDigit; 
		} // end method getNext
		
		
		/**
		 * Increments the integer a Digit contains. If the Digit holds a value 0-8, then the number
		 * is incremented. If the Digit contains the number 9, then it rolls over to 0 and 
		 * increments the Digit to its left. If there is no Digit to itself, one is created. This
		 * method is where the structure becomes recursive. 
		 */ 
		public void incrementDigit() {
			// If the Digit can hold a larger number, increment it. 
			if (this.number < 9) {
				this.number++; 
			} 
			
			// The digit cannot hold a higher number and must roll over to 0.
			else {
				this.number = 0; 
				
				// There is no left Digit to increment. 
				if (this.getNext() == null) {
					// Creates a new Digit to the left of the current one. 
					Digit newDigit = new Digit(); 
					this.setNext(newDigit); 
					// Increments the new Digit. 
					newDigit.incrementDigit(); 
					// Includes this new Digit in the count of Digits in the DigitCounter. 
					DigitCounter.this.numberOfDigits++; 
				} 
				
				// There is a Digit to the left, so it is incremented. 
				else {
					this.getNext().incrementDigit(); 
				} 
			}
		} // end method incrementDigit
		
		
		/**
		 * Sets the Digit to the left of the current one. This next Digit will be the one that 
		 * increments when the current Digit rolls over from 9 to 0. 
		 *
		 * @param	nextDigit	the Digit that needs to be to the left of the current Digit
		 */ 
		public void setNext(Digit nextDigit) {
			this.nextDigit = nextDigit; 
		} // end method setNext
	} // end class Digit
} // end class DigitCounter