/**
 * A JUnit test class for Queue.java. 
 *
 * Assignment: Homework 2
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: April 4, 2016
 * Class: TestQueue
 */ 

import org.junit.Test;
import org.junit.Before; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.assertFalse; 
import static org.hamcrest.CoreMatchers.is;

public class TestQueue {
	
	// Initialize instance variables for you use in tests. 
	private Queue<Integer> intQueue = new Queue<Integer>(); 
	private Queue<String> stringQueue = new Queue<String>(); 
	private Queue<Character> charQueue = new Queue<Character>(); 
	private Queue<Double> doubleQueue = new Queue<Double>(); 
	private Queue<Integer> emptyQueue = new Queue<Integer>(); 
	
	
	/**
	 * Adds elements to the non-empty Queues used in tests. 
	 */ 
	@Before
	public void setUp() {
		// Add ints to Queue of Integers. 
		intQueue.enqueue(200); 
		intQueue.enqueue(101); 
		intQueue.enqueue(314);
		intQueue.enqueue(-13); 
		intQueue.enqueue(-267); 
		intQueue.enqueue(45); 
		intQueue.enqueue(0); 
		
		// Add Strings to Queue of Strings. 
		stringQueue.enqueue("Good morning!"); 
		stringQueue.enqueue("By the way"); 
		stringQueue.enqueue("Still going strong?"); 
		stringQueue.enqueue("Why?!?"); 
		stringQueue.enqueue("because..."); 

		// Add chars to Queue of Characters. 
		charQueue.enqueue('a'); 
		charQueue.enqueue('?'); 
		charQueue.enqueue('x'); 
		charQueue.enqueue('A'); 
		charQueue.enqueue('1'); 
		charQueue.enqueue('Z'); 

		// Add doubles to Queue of Doubles. 
		doubleQueue.enqueue(-0.234); 
		doubleQueue.enqueue(0.0); 
		doubleQueue.enqueue(1.5); 
		doubleQueue.enqueue(768.315); 
		doubleQueue.enqueue(9.999); 
		doubleQueue.enqueue(-10.7); 
		doubleQueue.enqueue(-0.1); 
		doubleQueue.enqueue(7.333); 
	} // end method setUp
	
	
	/**
	 * Tests the toString method of class Queue.
	 */ 
	@Test
	public void testToString() {
		assertEquals("200 101 314 -13 -267 45 0", intQueue.toString()); 
		assertEquals("Good morning! By the way Still going strong? Why?!? because...", stringQueue.toString()); 
		assertEquals("a ? x A 1 Z", charQueue.toString()); 
		assertEquals("-0.234 0.0 1.5 768.315 9.999 -10.7 -0.1 7.333", doubleQueue.toString()); 
		assertEquals("", emptyQueue.toString()); 
	} // end method testToString
	
	
	/**
	 * Tests Queue's dequeue method on Queues of Integers, Strings, and Characters. 
	 * Ultimately calls dequeue until Queues are empty and check to ensure that 
	 * correct exception is, in fact, being thrown. Additionally, method checked
	 * on already empty Queue. Remember that primitives are wrapped when added to a
	 * generic class. 
	 */ 
	@Test
	public void testDequeue() {
		// Tests on Queue of Integers. 
		assertEquals(200, intQueue.dequeue().intValue()); 
		assertEquals("101 314 -13 -267 45 0", intQueue.toString()); 
		assertEquals(101, intQueue.dequeue().intValue()); 
		assertEquals(314, intQueue.dequeue().intValue()); 
		assertEquals("-13 -267 45 0", intQueue.toString()); 
		assertEquals(-13, intQueue.dequeue().intValue()); 
		assertEquals(-267, intQueue.dequeue().intValue()); 
		assertEquals(45, intQueue.dequeue().intValue()); 
		assertEquals("0", intQueue.toString()); 
		assertEquals(0, intQueue.dequeue().intValue()); 
		try {
			intQueue.dequeue(); 
		}
		catch(IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("List is empty, cannot remove item.")); 
		}
		assertTrue(intQueue.isEmpty()); 
		
		// Tests on Queue of Strings
		assertEquals("Good morning!", stringQueue.dequeue()); 
		assertEquals("By the way", stringQueue.dequeue()); 
		assertEquals("Still going strong?", stringQueue.dequeue()); 
		assertEquals("Why?!? because...", stringQueue.toString()); 
		assertEquals("Why?!?", stringQueue.dequeue()); 
		assertEquals("because...", stringQueue.toString()); 
		assertEquals("because...", stringQueue.dequeue()); 
		assertEquals("", stringQueue.toString()); 
		try {
			stringQueue.dequeue(); 
		}
		catch(IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("List is empty, cannot remove item.")); 
		}
		assertTrue(stringQueue.isEmpty()); 
		
		// Tests on Queue of Characters
		assertEquals("a ? x A 1 Z", charQueue.toString()); 
		assertEquals('a', charQueue.dequeue().charValue());
		assertEquals("? x A 1 Z", charQueue.toString()); 
		assertEquals('?', charQueue.dequeue().charValue()); 
		assertEquals('x', charQueue.dequeue().charValue()); 
		assertEquals('A', charQueue.dequeue().charValue()); 
		assertEquals("1 Z", charQueue.toString()); 
		assertEquals('1', charQueue.dequeue().charValue()); 
		assertEquals('Z', charQueue.dequeue().charValue()); 
		try {
			charQueue.dequeue(); 
		} 
		catch (IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("List is empty, cannot remove item.")); 
		} 
		assertTrue(charQueue.isEmpty()); 
		
		// Test on queue that was already empty. 
		try {
			emptyQueue.dequeue(); 
		} 
		catch (IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("List is empty, cannot remove item.")); 
		} 
	} // end method testDequeue
	
	
	/**
	 * Tests peek method of class Queue. Checks to ensure possible exception
	 * correctly thrown on empty Queues. Otherwise, tests peek on Queues of 
	 * Characters and Doubles. Makes sure peek does not effect the actual
	 * constitution of the Queue. Return types should be checked here as well. 
	 * Remember that with generics, wrapper classes will be used in place of
	 * primitives. assertEquals(double, double) is deprecated in JUnit 4.12, so
	 * expected value will be cast as a Double (i.e., as a instance of the 
	 * wrapper class). 
	 */ 
	@Test
	public void testPeek() {
		// Test on initially empty Queue. 
		try {
			emptyQueue.peek(); 
		}
		catch (IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("Queue is empty; no element to view.")); 
		} 
		
		// Tests on Queue of Characters. 
		assertEquals('a', charQueue.peek().charValue()); 
		assertEquals("a ? x A 1 Z", charQueue.toString()); 
		charQueue.dequeue();
		charQueue.dequeue(); 
		charQueue.dequeue(); 
		assertEquals('A', charQueue.peek().charValue()); 
		assertEquals("A 1 Z", charQueue.toString()); 
		charQueue.dequeue(); 
		charQueue.dequeue();
		assertEquals('Z', charQueue.peek().charValue()); 
		charQueue.dequeue(); 
		assertEquals("", charQueue.toString()); 
		try {
			charQueue.peek(); 
		} 
		catch (IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("Queue is empty; no element to view.")); 
		} 
		
		// Tests on Queue of Doubles.  
		doubleQueue.dequeue(); 
		assertEquals((Double) 0.0, doubleQueue.peek()); 
		assertEquals("0.0 1.5 768.315 9.999 -10.7 -0.1 7.333", doubleQueue.toString()); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.enqueue(-96.423); 
		assertEquals("768.315 9.999 -10.7 -0.1 7.333 -96.423", doubleQueue.toString()); 
		assertEquals((Double) 768.315, doubleQueue.peek()); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		assertEquals((Double) 7.333, doubleQueue.peek()); 
		doubleQueue.dequeue(); 
		assertEquals((Double) (-96.423), doubleQueue.peek()); 	
		doubleQueue.dequeue(); 
		try {
			doubleQueue.peek(); 
		} 
		catch (IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("Queue is empty; no element to view.")); 
		} 
	} // end method testPeek
	
	
	/**
	 * Tests method enqueue of class Queue. setUp acts as a partial test
	 * of method enqueue--i.e., if it throws no errors, and queues work
	 * as expected in other tests, enqueue is working. Hence, this test
	 * method is short. 
	 */ 
	@Test
	public void testEnqueue() {
		// Tests on Queue of Integers. 
		assertEquals("200 101 314 -13 -267 45 0", intQueue.toString()); 
		intQueue.enqueue(-33); 
		assertEquals("200 101 314 -13 -267 45 0 -33", intQueue.toString()); 
		intQueue.dequeue(); 
		intQueue.dequeue(); 
		intQueue.enqueue(512); 
		intQueue.enqueue(-4); 
		assertEquals("314 -13 -267 45 0 -33 512 -4", intQueue.toString()); 
		
		// Tests on Queue of Doubles. 
		assertEquals("-0.234 0.0 1.5 768.315 9.999 -10.7 -0.1 7.333", doubleQueue.toString()); 
		doubleQueue.enqueue(34.0707); 
		assertEquals("-0.234 0.0 1.5 768.315 9.999 -10.7 -0.1 7.333 34.0707", doubleQueue.toString()); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		doubleQueue.enqueue(3.66666); 
		doubleQueue.enqueue(-42.0); 
		assertEquals("34.0707 3.66666 -42.0", doubleQueue.toString()); 
		doubleQueue.dequeue(); 
		assertEquals("3.66666 -42.0", doubleQueue.toString()); 
		doubleQueue.dequeue(); 
		doubleQueue.dequeue(); 
		assertEquals("", doubleQueue.toString()); 
		doubleQueue.enqueue(7.635); 
		assertEquals("7.635", doubleQueue.toString()); 

		// Tests adding an element to an empty queue (declared as Queue of Integers.
		assertEquals("", emptyQueue.toString()); 
		emptyQueue.enqueue(12); 
		assertEquals("12", emptyQueue.toString()); 
		emptyQueue.dequeue(); 
		assertEquals("", emptyQueue.toString());
	} // end method testEnqueue
	
	
	/**
	 * Tests method isEmpty of class Queue. Empties two Queues by different methods
	 * to ensure that calls are working. Tests call on initially empty Queue as 
	 * well. 
	 */ 
	@Test
	public void testIsEmpty() {
		// Tests on initially empty Queue of Integers. 
		assertTrue(emptyQueue.isEmpty()); 
		emptyQueue.enqueue(42); 
		assertFalse(emptyQueue.isEmpty()); 
		assertEquals(42, emptyQueue.dequeue().intValue()); 
		assertTrue(emptyQueue.isEmpty()); 
		
		
		// Tests on Queue of Doubles using a loop.
		while (!doubleQueue.isEmpty()) {
			assertFalse(doubleQueue.isEmpty()); 
			doubleQueue.dequeue(); 
		} 
		assertTrue(doubleQueue.isEmpty()); 
		
		// Tests on Queue of Characters
		assertFalse(charQueue.isEmpty()); 
		charQueue.dequeue(); 
		charQueue.dequeue(); 
		charQueue.dequeue(); 
		charQueue.dequeue(); 
		assertFalse(charQueue.isEmpty()); 
		charQueue.dequeue(); 
		assertFalse(charQueue.isEmpty()); 
		charQueue.dequeue(); 
		assertTrue(charQueue.isEmpty()); 
	} // end method testIsEmpty
} // end class TestQueue