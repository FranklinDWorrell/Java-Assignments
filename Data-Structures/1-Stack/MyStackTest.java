/**
 * A JUnit test class for the MyStack class for Assignment 1 in 2125
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

/**
 * Since the SLLTest class tested the rigor of the SinglyLinkedList methods across
 * multiple types, this test class will only use one instance of MyStack using
 * ints as elements. This is acceptable, because all of the MyStack methods are
 * parasitic upon the SinglyLinkedList methods. 
 */ 
public class MyStackTest {
	private MyStack<Integer> testStack = new MyStack<Integer>(); 
	
	/** 
	 * Fill the test stack with values.
	 */ 
	@Before
	public void setUp() {
		testStack.push(-32); 
		testStack.push(595); 
		testStack.push(0); 
		testStack.push(-1); 
		testStack.push(77); 
	} // end method setUp
	
	
	/** 
	 * Tests the pop method of class MyStack. 
	 */ 
	@Test
	public void testPop() {
		assertEquals(77, (int) testStack.pop()); 
		assertEquals(-1, (int) testStack.pop()); 
		testStack.push(-917776); 
		assertEquals(-917776, (int) testStack.pop()); 
		assertEquals(0, (int) testStack.pop()); 
		assertEquals(595, (int) testStack.pop()); 
		assertEquals(-32, (int) testStack.pop()); 
		try {
			testStack.pop(); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is ("List is empty, cannot remove item.")); 
		} 
	} // end method testPop
	
	
	/** 
	 * Tests the top method of class MyStack.
	 */ 
	@Test
	public void testTop() {
		assertEquals(77, (int) testStack.top()); 
		testStack.pop(); 
		assertEquals(-1, (int) testStack.top()); 
		testStack.pop(); 
		testStack.pop(); 
		assertEquals(595, (int) testStack.top()); 
		testStack.push(-198); 
		assertEquals(-198, (int) testStack.top()); 
		testStack.push(-2); 
		assertEquals(-2, (int) testStack.top()); 
		testStack.pop(); 
		assertEquals(-198, (int) testStack.top()); 
		testStack.pop(); 
		testStack.pop(); 
		assertEquals(-32, (int) testStack.top()); 
		assertEquals(-32, (int) testStack.pop()); 
		assertEquals(true, testStack.isEmpty()); 
		testStack.push(0); 
		assertEquals(false, testStack.isEmpty()); 
		assertEquals(0, (int) testStack.top()); 
		testStack.pop();
		try {
			testStack.top(); 
		} 
		catch (IndexOutOfBoundsException exception) {
			assertThat(exception.getMessage(), is ("Invalid index.")); 
		}
	} // end method testTop
 
	
	/** 
	 * Tests the isEmpty method of class MyStack.
	 */ 
	@Test
	public void testIsEmpty() {
		assertEquals(false, testStack.isEmpty()); 
		testStack.pop(); 
		assertEquals(false, testStack.isEmpty()); 
		assertEquals(-1, (int) testStack.pop()); 
		testStack.pop(); 
		testStack.pop(); 
		assertEquals(false, testStack.isEmpty()); 
		testStack.pop(); 
		assertEquals(true, testStack.isEmpty()); 
		testStack.push(99); 
		assertEquals(false, testStack.isEmpty()); 
		assertEquals(99, (int) testStack.pop()); 
		assertEquals(true, testStack.isEmpty()); 
	} // end method testIsEmpty
} // end class MyStackTest