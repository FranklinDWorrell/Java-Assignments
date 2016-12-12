/**
 * A JUnit test class for MinHeap.java.
 *
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Assignment: Homework 3
 * Date Due: April 25, 2016
 * Revision Date: April 24, 2016
 * Class: TestMinHeap
 */ 
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import org.junit.Before; 
import java.util.NoSuchElementException; 

/**
 * This test class only performs tests on MinHeaps of Integers. Since 
 * MinHeap is generic and the ints inserted into the MinHeap get 
 * wrapped as Integers, if the tests work on them, they will work on
 * any class that implements Comparable.
 */ 
public class TestMinHeap {
	/* Declare test heaps. */ 
	// A MinHeap that is initialized as empty with the default size. 
	private MinHeap<Integer> startEmptyHeap; 
	// A MinHeap that is initialized with a small capacity to test resizing. 
	private MinHeap<Integer> integerHeap; 
	// A MinHeap that is initialized with an array of ints for general testing. 
	private MinHeap<Integer> smallCapacityHeap; 
	
	/* Initialize arrays that will be used to initialize heaps. */ 
	private Integer[] integerArray = {199, 87, -123, 0, 1, -18, 37, 4496}; 
	
	
	/**
	 * Initializes the three test heaps according to how they will be used 
	 * for testing (see comments above). 
	 */ 
	@Before
	public void setUpTestHeaps() {
		startEmptyHeap = new MinHeap<Integer>(); 
		integerHeap = new MinHeap<Integer>(integerArray); 
		smallCapacityHeap = new MinHeap<Integer>(2); 
	} // end method setUpTestHeaps
	
	
	/**
	 * Tests the insert method of class MinHeap. corner cases are when array
	 * needs to be resized. Initial tests with the heap that starts empty 
	 * and adding elements up to the resize threshold are normal cases. A
	 * further case that is important to test is the insertion of identical 
	 * elements into the MinHeap. 
	 */ 
	@Test
	public void testInsert() {
		/* Normal cases */ 
		// Normal cases with MinHeap that is initialized empty. 
		startEmptyHeap.insert(199); 
		assertEquals("199", startEmptyHeap.toString()); 
		startEmptyHeap.insert(201); 
		assertEquals("199 201", startEmptyHeap.toString()); 
		startEmptyHeap.insert(12); 
		assertEquals("12 201 199", startEmptyHeap.toString()); 
		startEmptyHeap.insert(-18); 
		assertEquals("-18 12 199 201", startEmptyHeap.toString()); 
		startEmptyHeap.insert(36); 
		startEmptyHeap.insert(87); 
		startEmptyHeap.insert(-3); 
		assertEquals("-18 12 -3 201 36 199 87", startEmptyHeap.toString()); 
		// Insertions of duplicate elements into heap. 
		startEmptyHeap.insert(-18); 
		assertEquals("-18 -18 -3 12 36 199 87 201", startEmptyHeap.toString()); 
		startEmptyHeap.insert(-18); 
		assertEquals("-18 -18 -3 -18 36 199 87 201 12", startEmptyHeap.toString()); 
		startEmptyHeap.insert(36); 
		startEmptyHeap.insert(12); 
		startEmptyHeap.insert(-3); 
		assertEquals("-18 -18 -3 -18 12 -3 87 201 12 36 36 199", startEmptyHeap.toString()); 
		// Normal cases with MinHeap that is initialized with a small capacity. 
		smallCapacityHeap.insert(4); 
		smallCapacityHeap.insert(321); 
		assertEquals("4 321", smallCapacityHeap.toString()); 
		
		
		/* corner cases */ 
		// Resizing MinHeap initialized to default size of 12. 
		assertTrue(startEmptyHeap.isFull()); 
		startEmptyHeap.insert(1); 
		assertEquals("-18 -18 -3 -18 12 -3 87 201 12 36 36 199 1", startEmptyHeap.toString()); 
		assertFalse(startEmptyHeap.isFull()); 
		// Resizing MinHeap initialized to smaller capacity of 2. 
		assertTrue(smallCapacityHeap.isFull()); 
		smallCapacityHeap.insert(697); 
		assertEquals("4 321 697", smallCapacityHeap.toString()); 
		assertFalse(smallCapacityHeap.isFull()); 
		// Check to make sure a second resize works on the smaller capacity heap. 
		smallCapacityHeap.insert(701); 
		assertTrue(smallCapacityHeap.isFull()); 
		smallCapacityHeap.insert(9001); 
		assertFalse(smallCapacityHeap.isFull()); 
		assertEquals("4 321 697 701 9001", smallCapacityHeap.toString()); 
	} // end method testInsert
	
	
	/**
	 * Tests the remove method of MinHeap.java. corner cases are ensuring that
	 * the exception handling functions properly when attempts to remove an
	 * element from an empty MinHeap occur. Normal cases are removals from a
	 * previously populated MinHeap. Casting return value of remove to int is
	 * necessary for JUnit's assertEquals method. 
	 */ 
	@Test
	public void testRemove() {
		/* Normal cases */ 
		assertEquals("-123 0 -18 199 1 87 37 4496", integerHeap.toString()); 
		assertEquals(-123, (int) integerHeap.remove()); 
		assertEquals("-18 0 37 199 1 87 4496", integerHeap.toString()); 
		assertEquals(-18, (int) integerHeap.remove()); 
		assertEquals("0 1 37 199 4496 87", integerHeap.toString()); 
		assertEquals(0, (int) integerHeap.remove()); 
		assertEquals("1 87 37 199 4496", integerHeap.toString()); 
		assertEquals(1, (int) integerHeap.remove()); 
		assertEquals("37 87 4496 199", integerHeap.toString()); 
		assertEquals(37, (int) integerHeap.remove()); 
		assertEquals("87 199 4496", integerHeap.toString()); 
		assertEquals(87, (int) integerHeap.remove()); 
		assertEquals("199 4496", integerHeap.toString()); 
		assertEquals(199, (int) integerHeap.remove()); 
		assertEquals("4496", integerHeap.toString()); 
		assertEquals(4496, (int) integerHeap.remove()); 
		assertEquals("", integerHeap.toString()); 
		
		/* corner cases */ 
		// With MinHeap that previously contained elements. 
		try {
			integerHeap.remove(); 
		} 
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is ("MinHeap empty; no element to remove.")); 
		}
		
		// With MinHeap that was initialized empty. 
		try {
			startEmptyHeap.remove(); 
		} 
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is ("MinHeap empty; no element to remove.")); 
		} 
	} // end method testRemove
	
	
	/**
	 * Tests the isEmpty method of class MinHeap. No real corner cases here 
	 * besides when the heap is empty, ironically.
	 */ 
	@Test
	public void testIsEmpty() {
		assertTrue(startEmptyHeap.isEmpty()); 
		startEmptyHeap.insert(0); 
		assertFalse(startEmptyHeap.isEmpty()); 
		assertEquals(0, (int) startEmptyHeap.remove()); 
		assertTrue(startEmptyHeap.isEmpty()); 
		assertFalse(integerHeap.isEmpty()); 
		assertTrue(smallCapacityHeap.isEmpty()); 
		smallCapacityHeap.insert(-111); 
		assertFalse(smallCapacityHeap.isEmpty()); 
	} // end method testIsEmpty
	
	
	/**
	 * Tests the isFull method of class MinHeap. As with isEmpty, the only
	 * real corner cases are when the heap is full. 
	 */ 
	@Test
	public void testIsFull() {
		// Test arrays known to not be at capacity. 
		assertFalse(startEmptyHeap.isFull()); 
		assertFalse(smallCapacityHeap.isFull()); 
		assertFalse(integerHeap.isFull()); 
		
		// Fill arrays to capacity and test. 
		smallCapacityHeap.insert(-1); 
		smallCapacityHeap.insert(0); 
		assertTrue(smallCapacityHeap.isFull()); 
		smallCapacityHeap.insert(2); 
		assertFalse(smallCapacityHeap.isFull()); 
		int[] addIntegers = {44, -21, 45, 87}; 
		for (int i = 0; i < addIntegers.length; i++) {
			integerHeap.insert(addIntegers[i]); 
		} 
		assertTrue(integerHeap.isFull()); 
		integerHeap.insert(97); 
		assertFalse(integerHeap.isFull()); 
		
		// Ensure that method works as expected on second resize of a heap. 
		smallCapacityHeap.insert(3); 
		assertTrue(smallCapacityHeap.isFull()); 
		smallCapacityHeap.insert(4); 
		assertFalse(smallCapacityHeap.isFull()); 
	} // end method testIsFull

	
	/**
	 * Tests the toString method of class MinHeap. corner case is an empty heap.
	 * Since all other tests make use of this method, minimal testing done here.
	 */ 
	@Test
	public void testToString() {
		assertEquals("-123 0 -18 199 1 87 37 4496", integerHeap.toString()); 
		assertEquals("", startEmptyHeap.toString()); 
		assertEquals("", smallCapacityHeap.toString()); 
		smallCapacityHeap.insert(66); 
		assertEquals("66", smallCapacityHeap.toString()); 
		smallCapacityHeap.insert(123); 
		assertEquals("66 123", smallCapacityHeap.toString()); 
		smallCapacityHeap.insert(245); 
		assertEquals("66 123 245", smallCapacityHeap.toString()); 
	} // end method testToString
} // end class TestMinHeap