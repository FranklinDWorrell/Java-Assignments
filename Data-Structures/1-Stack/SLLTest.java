/**
 * A JUnit test class for the SinglyLinkedList class for Assignment 1 in 2125
 *
 * Assignment 1
 * Author: Franklin D. Worrell
 * Date: February 24, 2016
 */ 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import org.junit.Before; 
import java.util.NoSuchElementException; 

public class SLLTest {
	// List of ints
	private SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>(); 
	// List of Strings
	private SinglyLinkedList<String> list2 = new SinglyLinkedList<String>();
	// List of chars
	private SinglyLinkedList<Character> list3 = new SinglyLinkedList<Character>(); 
	// List of doubles
	private SinglyLinkedList<Double> list4 = new SinglyLinkedList<Double>(); 
	// List of booleans
	private SinglyLinkedList<Boolean> list5 = new SinglyLinkedList<Boolean>();
	// Empty list
	private SinglyLinkedList<String> list6 = new SinglyLinkedList<String>(); 
	
	
	/**
	 * Populates the five lists that will contain elements for each set
	 * of tests below. 
	 */ 
	@Before
	public void setUp() {
		// Add int elements to list1. 
		list1.add(1); 
		list1.add(-13); 
		list1.add(90576); 
		list1.add(1); 
		list1.add(-1); 
		list1.add(0); 
		list1.add(-567382); 
		
		// Add String elements to list2. 
		list2.add("Hello, world!"); 
		list2.add("Hello, world!"); 
		list2.add("Berlin"); 
		list2.add("Tears for Fears"); 
		list2.add("Blondie"); 
		list2.add("!# bang sharp"); 
		list2.add("!# sharp bang"); 
		
		// Add char elements to list3. 
		list3.add('g'); 
		list3.add('Z'); 
		list3.add('P'); 
		list3.add('p'); 
		list3.add('t'); 
		list3.add('f'); 
		list3.add('u'); 
		list3.add('N'); 
		list3.add('n'); 
		list3.add('Y'); 
		
		// Add double elements to list4. 
		list4.add(3.14159); 
		list4.add(0.0); 
		list4.add(1.0); 
		list4.add(0.3333333333); 
		list4.add(-9546802.5000437); 
		list4.add(-1.1); 
		list4.add(-467.300001); 
		list4.add(1234124.090091); 
		list4.add(200003.0001001); 
		list4.add(2344234.0); 
		list4.add(0.001); 

		// Add boolean elements to list5. 
		list5.add(true); 
		list5.add(false);
		list5.add(false);
		list5.add(false);
		list5.add(true); 
		list5.add(false);
		list5.add(true); 
		list5.add(true); 
	} // end method setUp 
	
	
	/**
	 * Tests the insertAt method of SinglyLinkedList
	 */ 
	@Test
	public void testInsertAt() {
		// List of ints
		list1.insertAt(15, 2); 
		list1.insertAt(1980, 0); 
		list1.insertAt(-636, 9); 
		assertEquals("1980, 1, -13, 15, 90576, 1, -1, 0, -567382, -636", list1.toString()); 
		
		// List of Strings
		list2.insertAt("The Judds", 5); 
		list2.insertAt("&*@^@!", 8); 
		assertEquals("Hello, world!, Hello, world!, Berlin, Tears for Fears, Blondie, The Judds, !# bang sharp, !# sharp bang, &*@^@!", list2.toString()); 
		list2.insertAt("5+17==67", 1); 
		assertEquals("Hello, world!, 5+17==67, Hello, world!, Berlin, Tears for Fears, Blondie, The Judds, !# bang sharp, !# sharp bang, &*@^@!", list2.toString()); 
		
		// List of chars
		list3.insertAt('&', 10); 
		list3.insertAt('c', 3); 
		list3.insertAt('c', 3); 
		list3.insertAt('c', 3); 
		list3.insertAt('(', 0); 
		list3.insertAt('3', 2); 
		list3.insertAt('+', 8); 
		assertEquals("(, g, 3, Z, P, c, c, c, +, p, t, f, u, N, n, Y, &", list3.toString()); 
		
		// List of doubles
		list4.insertAt(-0.25, 1); 
		list4.insertAt(333333.0, 12); 
		list4.insertAt(6.135, 12); 
		assertEquals("3.14159, -0.25, 0.0, 1.0, 0.3333333333, -9546802.5000437, -1.1, -467.300001, 1234124.090091, 200003.0001001, 2344234.0, 0.001, 6.135, 333333.0", list4.toString()); 
		
		// List of booleans
		list5.insertAt(false, 8); 
		list5.insertAt(false, 9); 
		list5.insertAt(true, 1); 
		try {
			list5.insertAt(false, -3); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("Invalid index.")); 
		} 
		list5.insertAt(true, 3); 
		list5.insertAt(false, 0); 
		assertEquals("false, true, true, false, true, false, false, true, false, true, true, false, false", list5.toString()); 
		
		// Empty list of Strings
		try {
			list6.insertAt("Will Fail", 2); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("Invalid index.")); 
		}
		list6.insertAt("Solo", 0); 
		list6.insertAt("Duo", 1); 
		list6.insertAt("Trio", 0); 
		assertEquals("Trio, Solo, Duo", list6.toString()); 
	} // end method testInsertAt
	
	
	/**
	 * Tests the remove method of SinglyLinkedList
	 */ 
	@Test
	public void testRemove() {
		list1.remove(1); 
		assertEquals("-13, 90576, 1, -1, 0, -567382", list1.toString()); 
		list1.remove(1); 
		assertEquals("-13, 90576, -1, 0, -567382", list1.toString()); 
		list1.remove(-567382); 
		assertEquals("-13, 90576, -1, 0", list1.toString()); 
		try {
			list1.remove(-6); 
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Element not contained in list."));
		}
		assertEquals("-13, 90576, -1, 0", list1.toString()); 
	} // end method testRemove
	
	
	/**
	 * Tests the clear method of SinglyLinkedList
	 */ 
	@Test
	public void testClear() {
		list1.clear();
		assertEquals("", list1.toString()); 
		
		list2.clear();
		assertEquals("", list2.toString()); 

		list3.clear();
		assertEquals("", list3.toString()); 

		list4.clear();
		assertEquals("", list4.toString()); 

		list5.clear();
		assertEquals("", list5.toString()); 

		list6.clear();
		assertEquals("", list6.toString()); 
	} // end method testClear
	
	
	/**
	 * Tests the isEmpty method of SinglyLinkedList
	 */ 
	@Test
	public void testIsEmpty() {
		assertEquals(true, list6.isEmpty()); 
		list6.add("Again, again"); 
		assertEquals(false, list6.isEmpty()); 
		list6.remove("Again, again"); 
		assertEquals(true, list6.isEmpty()); 
		assertEquals(false, list1.isEmpty()); 
		list1.clear(); 
		assertEquals(true, list1.isEmpty()); 
		assertEquals(false, list4.isEmpty()); 
		list4.clear(); 
		assertEquals(true, list4.isEmpty()); 
	} // end method testIsEmpty
	
	
	/**
	 * Tests the size method of SinglyLinkedList.
	 */ 
	@Test
	public void testSize() {
		assertEquals(7, list1.size()); 
		list1.remove(1); 
		assertEquals(6, list1.size()); 
		list1.remove(1); 
		assertEquals(5, list1.size()); 
		assertEquals(7, list2.size()); 
		assertEquals(10, list3.size()); 
		assertEquals(11, list4.size()); 
		assertEquals(8, list5.size()); 
		list5.remove(true); 
		list5.remove(false); 
		list5.remove(true); 
		assertEquals(5, list5.size()); 
		assertEquals(0, list6.size()); 
	} // end method testSize
	
	
	/**
	 * Tests getNthFromFirst method of class SinglyLinkedList
	 */ 
	@Test
	public void testGetNthFromFirst() {
		assertEquals(1, (int) list1.getNthFromFirst(3)); 
		assertEquals(-13, (int) list1.getNthFromFirst(1)); 
		assertEquals(-567382, (int) list1.getNthFromFirst(6)); 
		try {
			list1.getNthFromFirst(18); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("Invalid index.")); 
		} 
		
		assertEquals("Berlin", list2.getNthFromFirst(2)); 
		assertEquals("Blondie", list2.getNthFromFirst(4)); 
		
		assertEquals('g', (char) list3.getNthFromFirst(0)); 
		assertEquals('N', (char) list3.getNthFromFirst(7));
		assertEquals('n', (char) list3.getNthFromFirst(8)); 
		assertEquals('Y', (char) list3.getNthFromFirst(9)); 
		
		assertEquals(3.14159, list4.getNthFromFirst(0), 0.0001); 
		assertEquals(0.001, list4.getNthFromFirst(10), 0.0001); 
		
		assertEquals(false, list5.getNthFromFirst(1)); 
		assertEquals(true, list5.getNthFromFirst(6)); 
		
		try {
			list6.getNthFromFirst(3); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("Invalid index.")); 
		} 
	} // end method testGetNthFromFirst
	
	
	/**
	 * Tests getNthFromLast method of class SinglyLinkedList.
	 */ 
	@Test
	public void testGetNthFromLast() {
		try {
			list1.getNthFromLast(8); 
		}
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("Invalid index.")); 
		} 
		assertEquals(1, (int) list1.getNthFromLast(6)); 
		assertEquals(-567382, (int) list1.getNthFromLast(0)); 
		assertEquals(-1, (int) list1.getNthFromLast(2)); 
		
		assertEquals("Hello, world!", list2.getNthFromLast(6)); 
		assertEquals("Tears for Fears", list2.getNthFromLast(3)); 
		assertEquals("!# bang sharp", list2.getNthFromLast(1)); 
		assertEquals("!# sharp bang", list2.getNthFromLast(0)); 
		
		assertEquals('Z', (char) list3.getNthFromLast(8)); 
		assertEquals('g', (char) list3.getNthFromLast(9)); 
		assertEquals('f', (char) list3.getNthFromLast(4)); 
		
		assertEquals(0.0, list4.getNthFromLast(9), 0.0001); 
		assertEquals(3.14159, list4.getNthFromLast(10), 0.0001); 
		assertEquals(2344234.0, list4.getNthFromLast(1), 0.0001); 
		
		assertEquals(false, list5.getNthFromLast(2)); 
		assertEquals(true, list5.getNthFromLast(1)); 
		
		try {
			list6.getNthFromLast(0); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is ("Invalid index.")); 
		} 
		list6.add("SoloDuo"); 
		assertEquals("SoloDuo", list6.getNthFromLast(0)); 
	} // end method testGetNthFromLast
	
	
	@Test
	public void testToString() {
		/* Stubbed */ 
		assertEquals("1, -13, 90576, 1, -1, 0, -567382", list1.toString()); 
		assertEquals("Hello, world!, Hello, world!, Berlin, Tears for Fears, Blondie, !# bang sharp, !# sharp bang", list2.toString()); 
		assertEquals("g, Z, P, p, t, f, u, N, n, Y", list3.toString()); 
		assertEquals("3.14159, 0.0, 1.0, 0.3333333333, -9546802.5000437, -1.1, -467.300001, 1234124.090091, 200003.0001001, 2344234.0, 0.001", list4.toString()); 
		assertEquals("true, false, false, false, true, false, true, true", list5.toString()); 
		assertEquals("", list6.toString()); 
	} // end method testToString
	
	
	@Test
	public void testRemoveFromFront() {
		list1.removeFromFront(); 
		assertEquals("-13, 90576, 1, -1, 0, -567382", list1.toString()); 
		list1.removeFromFront(); 
		assertEquals("90576, 1, -1, 0, -567382", list1.toString()); 
		list1.removeFromFront(); 
		list1.removeFromFront(); 
		list1.removeFromFront(); 
		list1.removeFromFront(); 
		list1.removeFromFront(); 
		assertEquals("", list1.toString()); 
		try {
			list1.removeFromFront(); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("List is empty, cannot remove item.")); 
		} 
		
		list2.removeFromFront(); 
		assertEquals("Hello, world!, Berlin, Tears for Fears, Blondie, !# bang sharp, !# sharp bang", list2.toString()); 
		
		list3.removeFromFront(); 
		list3.removeFromFront(); 
		list3.removeFromFront(); 
		list3.removeFromFront(); 
		assertEquals("t, f, u, N, n, Y", list3.toString()); 		
		
		try {
			list6.removeFromFront(); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is("List is empty, cannot remove item.")); 
		} 
	} // end testRemoveFromFront
	
	
	@Test
	public void testIteratorHasNext() {
		// Create iterators for tested lists. 
		SinglyLinkedList.SinglyLinkedListIterator list1Iterator = list1.iterator(); 
		SinglyLinkedList.SinglyLinkedListIterator list6Iterator = list6.iterator(); 

		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(true, list1Iterator.hasNext()); 
		list1Iterator.next(); 
		assertEquals(false, list1Iterator.hasNext()); 
		
		assertEquals(false, list6Iterator.hasNext()); 
	} // end method testIteratorHasNext
	
	
	@Test
	public void testIteratorNext() {
		// Create iterators for tested lists. 
		SinglyLinkedList.SinglyLinkedListIterator list5Iterator = list5.iterator(); 
		SinglyLinkedList.SinglyLinkedListIterator list6Iterator = list6.iterator(); 
		
		assertEquals(true, list5Iterator.next()); 
		assertEquals(false, list5Iterator.next()); 
		assertEquals(false, list5Iterator.next()); 
		assertEquals(false, list5Iterator.next()); 
		assertEquals(true, list5Iterator.next()); 
		assertEquals(false, list5Iterator.next()); 
		assertEquals(true, list5Iterator.next()); 
		assertEquals(true, list5Iterator.next()); 
		try {
			list5Iterator.next(); 
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is ("List is empty")); 
		} 
		
		try {
			list6Iterator.next(); 
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is ("List is empty")); 
		} 
	} // end method testIteratorNext
	
	
	@Test
	public void testIteratorRemove() {
		// Create iterators for tested lists. 
		SinglyLinkedList.SinglyLinkedListIterator list2Iterator = list2.iterator(); 
		SinglyLinkedList.SinglyLinkedListIterator list3Iterator = list3.iterator(); 
		
		try {
			list2Iterator.remove(); 
		} 
		catch (UnsupportedOperationException exception) {
			assertThat(exception.getMessage(), is ("remove operation is not supported by this iterator")); 
		} 
		
		try {
			list3Iterator.remove(); 
		} 
		catch (UnsupportedOperationException exception) {
			assertThat(exception.getMessage(), is ("remove operation is not supported by this iterator")); 
		} 
	} // end method testIteratorRemove
} // end class SLLTest