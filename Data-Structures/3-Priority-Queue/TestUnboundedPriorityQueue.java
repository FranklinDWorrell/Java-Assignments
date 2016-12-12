/**
 * A JUnit test class for UnboundedPriorityQueue.java.
 *
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Assignment: Homework 3
 * Date Due: April 25, 2016
 * Revision Date: April 24, 2016
 * Class: TestUnboundedPriorityQueue
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
 * This test class only performs tests on UnboundedPriorityQueues of 
 * Integers. Since UnboundedPriorityQueue is generic and the ints 
 * inserted into the UnboundedPriorityQueue get wrapped as Integers, 
 * if the tests work on them, they will work on any class that 
 * implements Comparable.
 *
 * Since the methods of UnboundedPriorityQueue are completely 
 * parasitic on the methods of MinHeap and have no content of
 * their own apart from the calls to MinHeap's methods, testing here
 * is minimal as it is largely redundant given the thorough testing 
 * of MinHeap. 
 */ 
public class TestUnboundedPriorityQueue {
	// Declare queues to perform tests on. 
	private UnboundedPriorityQueue<Integer> startEmptyQueue; 
	private UnboundedPriorityQueue<Integer> smallCapacityQueue; 
	private UnboundedPriorityQueue<Integer> integerQueue; 
	
	// Declare array to use for quick initialization. 
	private int[] arrayOfInts = {1, 4, 5, 0, 9, 11, 2, 3, 1, 7, 7, 11}; 
	
	
	/**
	 * Initializes the queues used for testing before each test is 
	 * performed. 
	 */ 
	@Before
	public void setUpTestUnboundedPriorityQueues() {
		startEmptyQueue = new UnboundedPriorityQueue<Integer>(); 
		smallCapacityQueue = new UnboundedPriorityQueue<Integer>(2); 
		integerQueue = new UnboundedPriorityQueue<Integer>(); 
		for (int i = 0; i < arrayOfInts.length; i++) {
			integerQueue.enqueue(arrayOfInts[i]); 
		} 
	} // end method setUpTestUnboundedPriorityQueues
	
	
	/**
	 * Tests the enqueue method of class UnboundedPriorityQueue. As
	 * with dequeue, casting operation is needed for JUnit's assertEquals
	 * method to function properly. Corner cases are enqueuing of 
	 * duplicate items, enqueuing into an empty queue, and enqueuing into
	 * a queue that will need to be resized. Since integerQueue is 
	 * initialized with duplicate items and passes the dequeue tests, 
	 * enqueuing duplicate items is not tested here. 
	 */ 
	@Test
	public void testEnqueue() {
		// Enqueue into an empty queue.
		startEmptyQueue.enqueue(-4); 
		assertFalse(startEmptyQueue.isEmpty()); 
		// Normal cases of enqueuing. 
		startEmptyQueue.enqueue(-12); 
		startEmptyQueue.enqueue(52); 
		assertEquals(-12, (int) startEmptyQueue.dequeue()); 
		assertEquals(-4, (int) startEmptyQueue.dequeue()); 
		assertEquals(52, (int) startEmptyQueue.dequeue()); 
		assertTrue(startEmptyQueue.isEmpty()); 
		// Enqueue into an empty queue.
		assertTrue(smallCapacityQueue.isEmpty()); 
		smallCapacityQueue.enqueue(15); 
		assertFalse(smallCapacityQueue.isEmpty()); 
		// Normal case of enqueuing. 
		smallCapacityQueue.enqueue(26); 
		// Queue now needs resizing. 
		smallCapacityQueue.enqueue(34); 
		smallCapacityQueue.enqueue(-12); 
		// Queue must now be resized a second time. 
		smallCapacityQueue.enqueue(0); 
		// Check to make sure everything dequeues in proper order. 
		assertEquals(-12, (int) smallCapacityQueue.dequeue()); 
		assertEquals(0, (int) smallCapacityQueue.dequeue()); 
		assertEquals(15, (int) smallCapacityQueue.dequeue()); 
		assertEquals(26, (int) smallCapacityQueue.dequeue()); 
		assertEquals(34, (int) smallCapacityQueue.dequeue()); 
		assertTrue(smallCapacityQueue.isEmpty()); 
		try {
			smallCapacityQueue.dequeue(); 
		} 
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is ("MinHeap empty; no element to remove.")); 
		} 
	} // end method testEnqueue
	
	
	/**
	 * Tests the dequeue method of class UnboundedPriorityQueue. As 
	 * with MinHeap, the casting operation is needed for JUnit's 
	 * assertEquals method to function properly. The only corner case
	 * is making sure the exception handling is done correctly when a 
	 * call to remove is made on an empty queue. 
	 */ 
	@Test
	public void testDequeue() {
		/* Normal cases */ 
		assertEquals(0, (int) integerQueue.dequeue()); 
		assertEquals(1, (int) integerQueue.dequeue()); 
		assertEquals(1, (int) integerQueue.dequeue()); 
		assertEquals(2, (int) integerQueue.dequeue()); 
		assertEquals(3, (int) integerQueue.dequeue()); 
		assertEquals(4, (int) integerQueue.dequeue()); 
		assertEquals(5, (int) integerQueue.dequeue()); 
		assertEquals(7, (int) integerQueue.dequeue()); 
		assertEquals(7, (int) integerQueue.dequeue()); 
		assertEquals(9, (int) integerQueue.dequeue()); 
		assertEquals(11, (int) integerQueue.dequeue()); 
		assertEquals(11, (int) integerQueue.dequeue()); 		
		
		/* Corner cases */ 
		// Attempt dequeue calls on empty queues. 
		try {
			startEmptyQueue.dequeue(); 
		} 
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is ("MinHeap empty; no element to remove.")); 
		} 
		try {
			smallCapacityQueue.dequeue(); 
		} 
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is ("MinHeap empty; no element to remove.")); 
		}
		try {
			integerQueue.dequeue(); 
		}
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is ("MinHeap empty; no element to remove.")); 
		} 
	} // end method testDequeue
	
	
	/**
	 * Tests the isEmpty method of class UnboundedPriorityQueue. As with
	 * class MinHeap, the only real corner case is when the queue is 
	 * actually empty. 
	 */ 
	@Test
	public void testIsEmpty() {
		// Queues initialized empty.
		assertTrue(startEmptyQueue.isEmpty()); 
		assertTrue(smallCapacityQueue.isEmpty()); 
		// Queue not initialized empty. 
		assertFalse(integerQueue.isEmpty()); 
		// Add an element to an empty queue, test, remove it, test again. 
		startEmptyQueue.enqueue(111); 
		assertFalse(startEmptyQueue.isEmpty()); 
		assertEquals(111, (int) startEmptyQueue.dequeue()); 
		assertTrue(startEmptyQueue.isEmpty()); 
	} // end method testIsEmpty
} // end class TestUnboundedPriorityQueue