/**
 * Three recursive static methods implemented in a single class. The first implements a compareTo 
 * method for Strings that uses alphabetical order as its natural ordering. The second finds the 
 * minimum String of an array of Strings based on the natural ordering implemented in the previous 
 * compareTo method. Finally, the third method recursively multiplies without using Java's 
 * multiplication operation. 
 *
 * Assignment: Homework 3 - Recursion (more practice)
 * Class: Recursion
 * Author: Franklin D. Worrell
 * Date: 11/3/2015
 */ 

/**
 * A class that implements three recursive methods. One implements a compareTo method for Strings
 * that does not rely on Java's String.compareTo method. The second uses this new, recursive
 * compareTo method to find the "minimum" String in an array of Strings. Finally, the third 
 * attempts to recursively implement a multiplication method without making use of Java's * 
 * operator and without simply adding an integer x to itself y times. 
 */ 
public class Recursion {

	/**
	 * Compares two Strings recursively using alphabetical order as the natural ordering. Ignores
	 * case (i.e., is case insensitive). Returns -1 if the first String is prior to the second 
	 * given the natural order, 0 if they are equal given the natural order, and 1 if the first 
	 * String is after the second in the natural order. Base cases are when the first letters 
	 * differ and when the Strings are one letter long and those letters are the same. More complex 
	 * cases are when the first letter(s) of the Strings are the same; recursion is required to 
	 * order them beyond the first letter of each. Method only handles Strings that are composed of 
	 * alphabetical characters or that are the empty String.  An empty String will always be 
	 * ranked as prior to a non-empty String and as equal to another empty String. 
	 * 
	 * @param	s1		a String for comparison 
	 * @param	s2		the String that the first is being compared against
	 * @require		(s1 != null && (s1 == "" || all s1 chars are alphabetical))
	 * @require 	(s2 != null && (s2 == "" || all s2 chars are alphabetical))
	 * @return		an integer representing the ordering of s1 and s2 given the natural order
	 */ 
	public static int compareTo(String s1, String s2) {
		// Converts the Strings to lowercase char[], which are easier to compare and manipulate. 
		char[] s1CharArray = s1.toLowerCase().toCharArray(); 
		char[] s2CharArray = s2.toLowerCase().toCharArray(); 
		
		/* First three cases handle empty Strings. */
		// Base case: both Strings are empty. 
		if (s1CharArray.length == 0 && s2CharArray.length == 0) {
			return 0; 
		}
		
		// Base case: s1 is empty and s2 is not. 
		else if (s1CharArray.length == 0 && s2CharArray.length != 0) {
			return -1; 
		}
		
		// Base case: s2 is empty and s1 is not. 
		else if (s1CharArray.length != 0 && s2CharArray.length == 0) {
			return 1; 
		}
		
		// Neither s1 nor s2 are empty. 
		else {
		
			// Base case: the first char of s1 is prior to the first char of s2. 
			if (s1CharArray[0] < s2CharArray[0]) {
				return -1; 
			} 
			
			// Base case: the first char of s1 is after the first char of s2. 
			else if (s1CharArray[0] > s2CharArray[0]){
				return 1; 
			} 
			
			// The first chars of both s1 and s2 are the same. 
			else {
				
				// Base case: s1 and s2 only contain one char and are the same. 
				if (s1CharArray.length == 1 && s2CharArray.length == 1) {
					return 0; 
				}
				
				// Complex case: the equal chars are not the last chars in both of the Strings. 
				else {
					// Removes the first element from the char[]. 
					char[] s1NewCharArray = removeFirst(s1CharArray); 
					char[] s2NewCharArray = removeFirst(s2CharArray); 
					
					// Coverts the char[] back to Strings for recursive call. 
					String s1New = String.copyValueOf(s1NewCharArray); 
					String s2New = String.copyValueOf(s2NewCharArray); 
					
					// Compares the next elements by recursive call on shorter Strings. 
					return compareTo(s1New, s2New); 
				}
			} // end long else that handles equal chars
		} // end long else that handles non-empty String cases
	} // end method compareTo
	
	
	/**
	 * Returns a char[] that is the inputted char[] with the first element removed. The first 
	 * element of the new char[] is the second element of the old char[], i.e., no empty space
	 * is left in first position of the new array. Helper method for compareTo. 
	 *
	 * @param	oldArray	the char[] being shortened
	 * @require		oldArray.length > 0
	 * @return		the new, shortened char[]
	 */ 
	public static char[] removeFirst(char[] oldArray) {
		// Creates a new char[] that is one element shorter than the original array. 
		char[] newArray = new char[oldArray.length - 1]; 

		// Iterates through the original array and copies the elements into the new array. 
		for (int index = 1; index < oldArray.length; index++) {
			newArray[index - 1] = oldArray[index]; 
		}
		
		return newArray; 
	} // end method removeFirst
	
	
	/**
	 * Returns the minimum element of a String[]. First, calls a helper method to remove null
	 * elements from the array (using the user-provided number of Strings contained in the array).
	 * Next, calls an additional helper method that actually pulls the minimum out of the copy
	 * of the array that contains no null elements. Removing the null elements is required based 
	 * on the preconditions of the compareTo method. This method is indirectly recursive as it 
	 * calls a recursive method instead of calling itself. All characters in the Strings in the 
	 * input array must contain only alphabetical characters because of compareTo's preconditions.
	 *
	 * @param	stringArray		the array of Strings being searched
	 * @param	numStrings		the number of Strings (non-null elements) stringArray contains 
	 * @require		numStrings > 0
	 * @require		all chars in each String in stringArray are alphabetical
	 * @return		the minimum String in the array as determined by the compareTo method
	 */ 
	public static String findMinimum(String[] stringArray, int numStrings) {
		// Removes any null elements from the String[] to meet compareTo's precondition. 
		String[] arrayNoNulls = removeNullElements(stringArray, numStrings); 
		// Calls the recursive method that actually pulls out the minimum String. 
		return getMinString(arrayNoNulls, arrayNoNulls[0], 0); 
	} // end method findMinimum		
	

	/**
	 * Returns the minimum element in an array of Strings beginning at the index specified by the
	 * user. The base case for the recusion is when a subarray of only one element is being 
	 * searched, namely, when the index provided by the user is equal the length of the array minus
	 * 1. In the complex case, the provided index is not the last element in the array, and the 
	 * previously established minimum is compared against the index provided by the user. The 
	 * complex case moves forward through the array until the previously established minimum is 
	 * compared against each element up to and including the final element. The precondition that 
	 * the array contain no null elements is there to satisfy the preconditions of the compareTo 
	 * method. Likewise for the precondition that all Strings contain only alphabetical characters. 
	 * 
	 * @param	stringArray		the array being searced for the minimum
	 * @param	currentMin		the minimum previously found
	 * @param	startIndex		where in the array to begin looking for the minimum
	 * @require		all elements in stringArray are non-null
	 * @require		all chars in each String in stringArray are alphabetical
	 * @require		(startIndex >= 0) && (startIndex < stringArray.length) 
	 * @return		the String that is lowest given the natural ordering of the compareTo method
	 */ 
	public static String getMinString(String[] stringArray, String currentMin, int startIndex) { 
		// Complex case: more than one element being examined. 
		if (startIndex < stringArray.length) {
			// The current minimum is less than or equal to the element being examined. 
			if (compareTo(currentMin, stringArray[startIndex]) <= 0) {
				return getMinString(stringArray, currentMin, startIndex + 1); 
			}
			// The item being examined is less than the current minimum. 
			else {
				return getMinString(stringArray, stringArray[startIndex], startIndex + 1); 
			}
		}
		
		// Base case: only finding the minimum of a single element (the last element of the array). 
		else {
			return currentMin; 
		}
	} // end method getMinString
	
		
	/**
	 * Removes the null elements from a String array by creating a new array without the null 
	 * elements included. Helper method for findMinimum method. Facilitates the findMinimum and 
	 * getMinString methods satisfying the preconditions for the compareTo method. 
	 * 
	 * @param	stringArray		the array from which null elements are to be removed
	 * @param	numStrings		the number of non-null elements in the String array
	 * @require		numStrings > 0
	 * @return		a new array that is stringArray minus the null elements
	 */ 
	public static String[] removeNullElements(String[] stringArray, int numStrings) {
		// Creates new array to hold String values from stringArray. 
		String[] newArray = new String[numStrings]; 
		
		// Initializes counters for loop. 
		int oldArrayIndex = 0; 
		int newArrayIndex = 0; 
		
		// Finds Strings in the old array and adds them to the new array. 
		while (oldArrayIndex < stringArray.length) {
			if (stringArray[oldArrayIndex] != null) {
				newArray[newArrayIndex] = stringArray[oldArrayIndex]; 
				// Only increment the new array index if an element was added to it. 
				newArrayIndex++; 
			} 
			// Increment the old array index irrespective of its content at that index. 
			oldArrayIndex++; 
		}
		
		return newArray; 
	} // end method removeNull Elements

	
	/**
	 * Multiplies two integers without using Java's * operator. Reduces multiplication to the basic 
	 * increment operation. Indirect object recursion is used instead of a straightforwardly 
	 * recursive method. The increment operation is outsourced to a a recursive object, a 
	 * DigitCounter based on the code for the odometer constructed in class. The object is 
	 * recursive, because it contains Digits that can create additional instances of themselves to
	 * help solve the problem. The class DigitCounter constructs a series of Digits that can each 
	 * be 0-9. Each digit has an increment method, and the ones digit is the only one that can be 
	 * incremented by the DigitCounter object. The structure is recursive, because Digits create 
	 * other Digits when needed and increment the next Digit when they must become greater than 9. 
	 * 
	 * @param	x		the multiplier
	 * @param	y		the multiplicand 
	 * @return		the product of x and y
	 */ 
	public static int multiply(int x, int y) {
		int product = 0; 
		
		// Determines whether or not product will be a negative integer. 
		boolean isNegative = false; 
		if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
			isNegative = true; 
		}
		
		// Make the factors positive--it's easier to work with. 
		x = Math.abs(x); 
		y = Math.abs(y); 
		
		// Initialize the DigitCounter that finds the product through object recursion. 
		DigitCounter multIncrementer = new DigitCounter(); 
		
		// Increments the multIncrementer to the product of x and y through nested for loops. 
		for (int times = 0; times < x; times++) {
			for (int factor = 0; factor < y; factor++) {
				multIncrementer.increment(); 
			}
		}
		
		// Gets the product from the Digit Counter. 
		product = multIncrementer.getCount(); 
		
		// Handles negative products. 
		if (isNegative) {
			product = 0 - product; 
		} 
		
		return product; 
	} // end method multiply
} // end class Recursion