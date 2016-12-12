/**
 * A JUnit test class for the Recursion class, the three static methods it contains, and the 
 * associated helper methods implemented in that class. A test method is also implemented for the 
 * increment method of class DigitCounter, which is the recursive data structure that allows for 
 * the indirect object recursion in method multiply of class Recursion.
 *
 * Assignment: Homework 3 - Recursion (more practice)
 * Class: RecursionTest
 * Author: Franklin D. Worrell
 * Date: 11/3/2015
 */ 

import static org.junit.Assert.assertEquals; 
import org.junit.Test; 

/**
 * A JUnit test class for the static methods of class Recursion. 
 */ 
public class RecursionTest {
	
	/**
	 * Test methods for methods of class Recursion. 
	 */ 
	
	/**
	 * Tests the compareTo method of class Recursion. The edge-ier cases are those that involve 
	 * comparing empty Strings, empty Strings with non-empty Strings, and Strings that are only one 
	 * char long compared with each other and with Strings that are longer than one char.
	 */ 
	@Test
	public void testCompareTo() {
		// The edge-ier cases. 
		assertEquals(-1, Recursion.compareTo("", "x")); 
		assertEquals(0, Recursion.compareTo("", "")); 
		assertEquals(1, Recursion.compareTo("Y", "")); 
		assertEquals(1, Recursion.compareTo("Jack", "")); 
		assertEquals(0, Recursion.compareTo("andHello", "andHello")); 
		assertEquals(0, Recursion.compareTo("a", "a")); 
		assertEquals(-1, Recursion.compareTo("C", "d")); 
		assertEquals(1, Recursion.compareTo("z", "H")); 
		assertEquals(-1, Recursion.compareTo("A", "aa")); 
		assertEquals(1, Recursion.compareTo("x", "JK"));
		
		// The more general cases. 
		assertEquals(-1, Recursion.compareTo("Goodwin", "Tom")); 
		assertEquals(0, Recursion.compareTo("Johnny", "JoHNNy")); 
		assertEquals(1, Recursion.compareTo("Johnny", "John")); 
		assertEquals(1, Recursion.compareTo("Quoththeraven", "MyAnnabelLee")); 
		assertEquals(-1, Recursion.compareTo("andtheylived", "Onceuponatime")); 
	} // end method testCompareTo
	
	
	/**
	 * Tests the removeFirst method of class Recursion. Converts the returned value of removeFirst
	 * to a String and compares that String with the expected output given as a String. The 
	 * argument passed to removeFirst is a String with the method toCharArray of class String 
	 * called on it. The output of removeFirst is converted to a String with the static method 
	 * copyValueOf of class String. The methods of class Recursion make liberal use of toCharArray 
	 * and copyValueOf, so their use here helps guarantee that they perform as expected. Cases need
	 * to test Strings that are just spaces, and that are only one char long. 
	 */ 
	@Test
	public void testRemoveFirst() {
		assertEquals("alloween Night", 
			String.copyValueOf(Recursion.removeFirst("Halloween Night".toCharArray()))); 
		assertEquals("  ", String.copyValueOf(Recursion.removeFirst("   ".toCharArray()))); 
		assertEquals("klm", String.copyValueOf(Recursion.removeFirst("jklm".toCharArray()))); 
		assertEquals("mK2QH", String.copyValueOf(Recursion.removeFirst("YmK2QH".toCharArray()))); 
		assertEquals("2345", String.copyValueOf(Recursion.removeFirst("12345".toCharArray()))); 
		assertEquals("t h U s", String.copyValueOf(Recursion.removeFirst(" t h U s".toCharArray()))); 
		assertEquals(" B U t", String.copyValueOf(Recursion.removeFirst("A B U t".toCharArray())));
		assertEquals("", String.copyValueOf(Recursion.removeFirst("X".toCharArray()))); 
		assertEquals("", String.copyValueOf(Recursion.removeFirst(" ".toCharArray()))); 
		assertEquals("!3$CV", String.copyValueOf(Recursion.removeFirst("*!3$CV".toCharArray()))); 
	} // end method testRemoveFirst
	
	
	/**
	 * Tests the findMinimum method of class Recursion. Because the findMinimum method includes a 
	 * call to the compareTo method, the Strings in the test arrays should contain only alphabetical
	 * characters and the arrays should contain at least one non-null element (to meet relevant
	 * preconditions). Edge-ier cases are when the array contains only one element and when some of 
	 * the elements are the empty String. Instances in which the array contains null elements and
	 * when the Strings contain uppercase letters should also be tested. 
	 */ 
	@Test
	public void testFindMinimum() {
		String[] minTestArray1 = {"maps"}; 
		assertEquals("maps", Recursion.findMinimum(minTestArray1, 1)); 
		
		String[] minTestArray2 = {""}; 
		assertEquals("", Recursion.findMinimum(minTestArray2, minTestArray2.length)); 
		
		String[] minTestArray3 = {null, "maps", ""}; 
		assertEquals("", Recursion.findMinimum(minTestArray3, 2)); 
		
		String[] minTestArray4 = {"JOHN", "jacob"}; 
		assertEquals("jacob", Recursion.findMinimum(minTestArray4, 2)); 
		
		String[] minTestArray5 = {"Hello", "jACk", "alpha", "aaa", "penguin"}; 
		assertEquals("aaa", Recursion.findMinimum(minTestArray5, minTestArray5.length)); 
		
		String[] minTestArray6 = {null, "jack", "be", null, "nimble", "be", "quick", null}; 
		assertEquals("be", Recursion.findMinimum(minTestArray6, 5)); 
		
		String[] minTestArray7 = {"AwEsOmE", null, "sauce", "Beavis", "also", "Butthead", null, ""}; 
		assertEquals("", Recursion.findMinimum(minTestArray7, 6)); 
		
		String[] minTestArray8 = {"Z", "h", "j", "Y", "Q", null, null, null, "U", "U", "u", "i", 
			"O", "m", "N", "n", "y", "R", null, null, null}; 
		assertEquals("h", Recursion.findMinimum(minTestArray8, 15)); 
		
		String[] minTestArray9 = {"Two", "roads", "diverged", "in", "a", "yellow", "wood", "and", 
			"I", "sorry", "that", "I", "could", "not", "travel", "both", "and", "be", "one", 
			"traveler", "stood", "and", "looked", "down", "one", "as", "far", "as", "I", "could"}; 
		assertEquals("a", Recursion.findMinimum(minTestArray9, 30)); 
		
		String[] minTestArray10 = {"Thence", null, "to", null, "Carthage", null, "I", null, "came", 
			null, "Oh", null, "Lord", null, "thou", null, "pluckest"}; 
		assertEquals("came", Recursion.findMinimum(minTestArray10, 9)); 
	} // end method testFindMinimum
	
	
	/**
	 * Tests the getMinString method of class Recursion. Edge cases are when there is only one 
	 * element in the array and it is empty, when there is only one element that is not empty, 
	 * when there are two elements, and when there is an empty element and a not empty element. 
	 * Otherwise, be sure to test cases in which the start index is not the initial index in the 
	 * array. 
	 */ 
	@Test
	public void testGetMinString() {
		String[] getMinTest1 = {""}; 
		assertEquals("", Recursion.getMinString(getMinTest1, "", 0)); 
		
		String[] getMinTest2 = {"Bobby", ""}; 
		assertEquals("", Recursion.getMinString(getMinTest2, "Bobby", 0)); 
		
		String[] getMinTest3 = {"", "Bobby"}; 
		assertEquals("", Recursion.getMinString(getMinTest3, "", 1)); 
		
		String[] getMinTest4 = {"Why", "not"};
		assertEquals("not", Recursion.getMinString(getMinTest4, "Why", 0)); 
		
		String[] getMinTest5 = {"even", "AFTER", "all", "these", "years"};
		assertEquals("even", Recursion.getMinString(getMinTest5, "even", 3)); 
		
		String[] getMinTest6 = {"Bela", "Legosi", "is", "dead"};
		assertEquals("dead", Recursion.getMinString(getMinTest6, "is", 3)); 
		
		String[] getMinTest7 = {"On", "my", "way", "up", "north", "Up", "on", "the", "Ventura"};
		assertEquals("my", Recursion.getMinString(getMinTest7, "my", 4)); 
		
		String[] getMinTest8 = {"I", "pulled", "back", "the", "hood", "and", "I", "was", "atalkin",
			"to", "ya", "and", "I", "knew", "then", "it", "would", "be"};
		assertEquals("be", Recursion.getMinString(getMinTest8, "I", 13)); 
		
		String[] getMinTest9 = {"Do", "you", "remember", "the", "time", "that", "we", "that", "we", 
			"we", "fell", "in", "love", "Do", "you", "remember", "THE", "tIme", "WHEN", "WE", 
			"fIRST", "met"};
		assertEquals("Do", Recursion.getMinString(getMinTest9, "Do", 0)); 
		
		String[] getMinTest10 = {"Act", "only", "in", "such", "a", "manner", "that", "the", 
			"maxim", "of", "your", "action", "could", "be", "applied", "as", "a", "universal", 
			"law", "of", "nature"};
		assertEquals("a", Recursion.getMinString(getMinTest10, "manner", 6)); 
	} // end method testGetMinString
	
	
	/** 
	 * Tests the removeNullElements method of class Recursion. The only edge cases are when 
	 * numStrings == 1 and when the array contains no null elements. 
	 */ 
	@Test
	public void testRemoveNullElements() {
		String[] testNullArray1 = {"hi", null, "bye"}; 
		String[] testNullArray1Post = {"hi", "bye"}; 
		assertEquals(testNullArray1Post, Recursion.removeNullElements(testNullArray1, 2)); 
		
		String[] testNullArray2 = {"Frankie", null, null, null, null, null, null, null, null, null}; 
		String[] testNullArray2Post = {"Frankie"}; 
		assertEquals(testNullArray2Post, Recursion.removeNullElements(testNullArray2, 1)); 
		
		String[] testNullArray3 = {null, null, null, null, null, null, null, null, "1?2! C harles"}; 
		String[] testNullArray3Post = {"1?2! C harles"}; 
		assertEquals(testNullArray3Post, Recursion.removeNullElements(testNullArray3, 1)); 
		
		String[] testNullArray4 = {"Once", null, " upon ", null, "a time."}; 
		String[] testNullArray4Post = {"Once", " upon ", "a time."}; 
		assertEquals(testNullArray4Post, Recursion.removeNullElements(testNullArray4, 3)); 
		
		String[] testNullArray5 = {null, " ... ", null, "ever after", null}; 
		String[] testNullArray5Post = {" ... ", "ever after"}; 
		assertEquals(testNullArray5Post, Recursion.removeNullElements(testNullArray5, 2)); 
		
		String[] testNullArray6 = {"She", "Sells", "Sea", "Shells"}; 
		String[] testNullArray6Post = {"She", "Sells", "Sea", "Shells"};
		assertEquals(testNullArray6Post, Recursion.removeNullElements(testNullArray6, 4)); 
		
		String[] testNullArray7 = {"Here"}; 
		String[] testNullArray7Post = {"Here"}; 
		assertEquals(testNullArray7Post, Recursion.removeNullElements(testNullArray7, 1)); 
	} // end method testRemoveNullElements
	
	
	/**
	 * Tests the multiply method of class Recursion. Edge cases are when either x or y is 0 and 
	 * instances in which either x or y is negative. Tests should also include multiplication by
	 * 1 just to check. Large numbers should be tested to ensure that stack overflow is avoided by
	 * the method. 
	 */ 
	@Test
	public void testMultiply() {
		// Edge cases. 
		assertEquals(0, Recursion.multiply(0, 2)); 
		assertEquals(0, Recursion.multiply(2348961, 0)); 
		assertEquals(0, Recursion.multiply(0, 0)); 
		assertEquals(1, Recursion.multiply(1, 1)); 
		assertEquals(2, Recursion.multiply(2, 1)); 
		assertEquals(7482, Recursion.multiply(1, 7482)); 
		assertEquals(-32, Recursion.multiply(8, -4)); 
		assertEquals(-3588, Recursion.multiply(-276, 13)); 
		assertEquals(81345, Recursion.multiply(-87, -935)); 
		assertEquals(-237, Recursion.multiply(-1, 237)); 
		assertEquals(-237, Recursion.multiply(237, -1)); 
		
		// General cases. 
		assertEquals(6, Recursion.multiply(2, 3)); 
		assertEquals(90, Recursion.multiply(10, 9)); 
		assertEquals(100, Recursion.multiply(10, 10)); 
		assertEquals(42336, Recursion.multiply(112, 378)); 
		assertEquals(55263, Recursion.multiply(327, 169));
		assertEquals(9066734, Recursion.multiply(12934, 701)); 
		assertEquals(42559176, Recursion.multiply(9768, 4357)); 
	} // end method testMultiply
	
	
	/**
	 * Test method for methods of class DigitCounter.
	 */ 
	
	/**
	 * Tests the increment method of DigitCounter. Given that access to the count is available only
	 * through getter methods, this test also de facto tests those methods (namely, toString and 
	 * getCount of DigitCounter). Additionally, if these function correctly, the methods of inner
	 * class Digit also perform sufficiently well. Only real edge cases here are when increment has
	 * been called only once or not at all. 
	 */ 
	@Test
	public void testIncrement() {
		// Edge case: increment is never called. 
		DigitCounter countTest1 = new DigitCounter(); 
		assertEquals(0, countTest1.getCount()); 
		
		// Edge case: increment is called only once. 
		DigitCounter countTest2 = new DigitCounter();
		countTest2.increment(); 
		assertEquals(1, countTest2.getCount()); 
		
		// General case: increment called 72 times. 
		DigitCounter countTest3 = new DigitCounter(); 
		// Iteratively calls increment. 
		for (int call = 0; call < 72; call++) {
			countTest3.increment(); 
		} 
		assertEquals(72, countTest3.getCount()); 
		
		// General case: increment called 296 times. 
		DigitCounter countTest4 = new DigitCounter(); 
		// Iteratively calls increment. 
		for (int call = 0; call < 296; call++) {
			countTest4.increment(); 
		} 
		assertEquals(296, countTest4.getCount()); 
		
		// General case: increment called 12,527
		DigitCounter countTest5 = new DigitCounter(); 
		// Iteratively calls increment. 
		for (int call = 0; call < 12527; call++) {
			countTest5.increment(); 
		} 
		assertEquals(12527, countTest5.getCount()); 
	} // end method testIncrement
} // end class RecursionTest