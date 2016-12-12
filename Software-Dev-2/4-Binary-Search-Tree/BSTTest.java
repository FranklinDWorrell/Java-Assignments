/**
 * A JUnit test class for the BinarySearchTree class. Populates a series of test BSTs with items, 
 * then tests the contains method of class BinarySearchTree and the hasNext and next methods of the 
 * Iterator inner class, which constitutes a test of the methods that construct the underlying
 * data structure (a LinkedList) of the Iterator. 
 *
 * Assignment: Homework 4 - Binary Search Tree
 * Class: BSTTest
 * Author: Franklin D. Worrell
 * Date: 11/13/2015
 */ 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import org.junit.Before; 
import java.util.NoSuchElementException; 
import java.lang.NullPointerException; 

/**
 * Test class for the BinarySearchTree class. Tests for the contains method and the methods of the 
 * various Iterators (which in turn tests the implemenation of each traversal strategy). 
 */ 
public class BSTTest {
	// testBST1 is a relatively balanced tree filled with ints.
	private BinarySearchTree testBST1 = new BinarySearchTree(50);
	
	// testBST2 is a left-heavy unbalanced tree filled with Strings.
	private BinarySearchTree testBST2 = new BinarySearchTree("jump jump"); 
	
	// testBST3 is a right-heavy unbalanced tree of chars.
	private BinarySearchTree testBST3 = new BinarySearchTree('p'); 
	
	// testBST4 is a  tree of booleans--this will make sure the no dupes rule is working. 
	private BinarySearchTree testBST4 = new BinarySearchTree(false); 
	
	// testBST5 is a tree of doubles with only one element.
	private BinarySearchTree testBST5 = new BinarySearchTree(151.72); 
	
	// testBST6 is a tree containing only a null reference--an empty BST. 
	private BinarySearchTree testBST6 = new BinarySearchTree(null); 
	
	/**
	 * Initializes the test Binary Search Trees and adds elements to them. If this functions 
	 * properly, then the insert method of BinarySearchTree will be considered as functioning 
	 * properly. Additionally, at the stage when the Iterator methods are tested, it will be shown
	 * that insert has not added duplicates. Since testBST5 and testBST6 do not need other elements 
	 * added to fulfill their testing purposes, they are ignored here. 
	 */ 
	@Before
	public void setUp() {
		// Inserts elements into testBST1 (the balanced int tree). 
		testBST1.insert(25); 
		testBST1.insert(15); 
		testBST1.insert(20); 
		testBST1.insert(21); 
		testBST1.insert(35); 
		testBST1.insert(30); 
		testBST1.insert(65); 
		testBST1.insert(101); 
		testBST1.insert(72); 
		testBST1.insert(59); 
		testBST1.insert(99); 
		testBST1.insert(12); 
		testBST1.insert(111); 
		
		// Inserts elements into testBST2 (the left-heavy String tree). 
		testBST2.insert("aaa"); 
		testBST2.insert("Modus Ponens"); 
		testBST2.insert("Make Believe"); 
		testBST2.insert("Last Tango in Paris"); 
		testBST2.insert("Kappa"); 
		testBST2.insert("Hold me closer, Tony Danza"); 
		testBST2.insert("Don't fear the reaper"); 
		testBST2.insert("DELIVERANCE"); 
		testBST2.insert("Categorical Imperative"); 
		testBST2.insert("BBBBB"); 
		
		// Inserts elements into testBST3 (the right-heavy char tree). 
		testBST3.insert('r'); 
		testBST3.insert('s'); 
		// This will make sure dupes are, in fact being ignored.
		testBST3.insert('r'); 
		testBST3.insert('s'); 
		testBST3.insert('t'); 
		testBST3.insert('v'); 
		testBST3.insert('w'); 
		testBST3.insert('x'); 
		testBST3.insert('y'); 
		testBST3.insert('y'); 
		
		// Inserts elements into testBST4 (the boolean tree).
		// After first insertion, it should only contain two BSTs--no duplicates.
		testBST4.insert(true); 
		testBST4.insert(false); 
		testBST4.insert(false); 
		testBST4.insert(false); 
		testBST4.insert(true); 
		testBST4.insert(false); 
		testBST4.insert(true); 
		testBST4.insert(true); 
	} // end method setUp
	
	
	/**
	 * Tests the empty test BST. Easier to have its own tests given JUnit's exception handling 
	 * apparatus. Everything method of the BinarySearchTree and Iterator classes should throw
	 * NullPointerExceptions when invoked on an empty BST. The Iterators can be created, but the 
	 * underlying LinkedList upon which the Iterator is build will contain only a reference to a
	 * BST that itself only contains a null reference. Hence, though the Iterator can be 
	 * constructed, its methods will throw the exception.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullBST() {
		// Tests the contains method on the empty BST.
		testBST6.contains('b'); 
		testBST6.contains("No One Is Home."); 
		testBST6.contains(736.93); 
		
		// Tests inorder iterator on empty BST.
		BinarySearchTree.Iterator inOrderTest6 = testBST6.getInOrderIterator(); 
		inOrderTest6.hasNext(); 
		inOrderTest6.next(); 

		// Tests preorder iterator on empty BST.
		BinarySearchTree.Iterator preOrderTest6 = testBST6.getPreOrderIterator(); 
		preOrderTest6.hasNext(); 
		preOrderTest6.next(); 
		
		// Tests postorder iterator on empty BST. 
		BinarySearchTree.Iterator postOrderTest6 = testBST6.getPostOrderIterator(); 
		postOrderTest6.hasNext(); 
		postOrderTest6.next(); 
 	} // end method testNullBST
	
	
	/** 
	 * Tests the contains method of class BinarySearchTree. 
	 */ 
	@Test
	public void testContains() {
		// Tests for balanced integer tree
		assertEquals(true, testBST1.contains(21)); 
		assertEquals(false, testBST1.contains(13)); 
		assertEquals(true, testBST1.contains(101)); 
		assertEquals(false, testBST1.contains(112)); 
		
		// Tests for left-heavy String tree 
		assertEquals(true, testBST2.contains("Last Tango in Paris")); 
		assertEquals(true, testBST2.contains("aaa")); 
		assertEquals(false, testBST2.contains("AA")); 
		assertEquals(false, testBST2.contains("BBbBB"));
		assertEquals(false, testBST2.contains("Z")); 
		assertEquals(false, testBST2.contains("modus ponens")); 

		// Tests for right-heavy char tree
		assertEquals(true, testBST3.contains('w')); 
		assertEquals(false, testBST3.contains('u')); 
		assertEquals(false, testBST3.contains('z')); 
		assertEquals(true, testBST3.contains('r')); 
		assertEquals(false, testBST3.contains('C')); 
		assertEquals(false, testBST3.contains('R')); 
		
		// Tests for the boolean tree (2 elements)
		assertEquals(true, testBST4.contains(true)); 
		assertEquals(true, testBST4.contains(false)); 
		
		// Tests for double tree with only one member
		assertEquals(false, testBST5.contains(151.73)); 
		assertEquals(true, testBST5.contains(151.72)); 
		assertEquals(false, testBST5.contains(0.321)); 
	} // end method testContains
	
	
	/** 
	 * Tests inorder traversing Iterator of class BinarySearch. Tests both the hasNext and next 
	 * methods. next is called in each Iterator until it hits the end of the BST. These tests also
	 * effectively test the methods that construct the LinkedList upon which the Iterator is built,
	 * since those methods are private. 
	 */ 
	@Test
	public void testInOrderIterator() {
		BinarySearchTree.Iterator inOrderTest1 = testBST1.getInOrderIterator(); 
		// Inorder traversal of testBST1: 12, 15, 20, 21, 25, 30, 35, 50, 59, 65, 72, 99, 101, 111. 
		assertEquals(true, inOrderTest1.hasNext()); 
		assertEquals(12, inOrderTest1.next()); 
		assertEquals(15, inOrderTest1.next()); 
		assertEquals(20, inOrderTest1.next()); 
		assertEquals(21, inOrderTest1.next()); 
		assertEquals(true, inOrderTest1.hasNext()); 
		assertEquals(true, inOrderTest1.hasNext()); 
		assertEquals(25, inOrderTest1.next()); 
		assertEquals(true, inOrderTest1.hasNext()); 
		assertEquals(30, inOrderTest1.next()); 
		assertEquals(35, inOrderTest1.next()); 
		assertEquals(true, inOrderTest1.hasNext()); 
		assertEquals(50, inOrderTest1.next()); 
		assertEquals(59, inOrderTest1.next()); 
		assertEquals(65, inOrderTest1.next()); 
		assertEquals(72, inOrderTest1.next()); 
		assertEquals(99, inOrderTest1.next()); 
		assertEquals(101, inOrderTest1.next()); 
		assertEquals(111, inOrderTest1.next()); 
		assertEquals(false, inOrderTest1.hasNext()); 
		try {
			inOrderTest1.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, inOrderTest1.hasNext()); 
		
		// Tests inorder traversing iterator of left-heavy String tree.
		// Inorder traversal of testBST2: "BBBBB", "Categorical Imperative", "DELIVERANCE", 
			// "Don't fear the reaper", "Hold me closer, Tony Danza", "Kappa", 
			// "Last Tango in Paris", "Make Believe", "Modus Ponens", "aaa", "jump jump". 
		BinarySearchTree.Iterator inOrderTest2 = testBST2.getInOrderIterator(); 
		assertEquals(true, inOrderTest2.hasNext()); 
		assertEquals("BBBBB", inOrderTest2.next()); 
		assertEquals("Categorical Imperative", inOrderTest2.next()); 
		assertEquals("DELIVERANCE", inOrderTest2.next()); 
		assertEquals(true, inOrderTest2.hasNext()); 
		assertEquals("Don't fear the reaper", inOrderTest2.next()); 
		assertEquals("Hold me closer, Tony Danza", inOrderTest2.next()); 
		assertEquals(true, inOrderTest2.hasNext()); 
		assertEquals("Kappa", inOrderTest2.next()); 
		assertEquals("Last Tango in Paris", inOrderTest2.next()); 
		assertEquals("Make Believe", inOrderTest2.next()); 
		assertEquals("Modus Ponens", inOrderTest2.next()); 
		assertEquals("aaa", inOrderTest2.next()); 
		assertEquals(true, inOrderTest2.hasNext()); 
		assertEquals("jump jump", inOrderTest2.next()); 
		assertEquals(false, inOrderTest2.hasNext()); 
		assertEquals(false, inOrderTest1.hasNext()); 
		try {
			inOrderTest2.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, inOrderTest2.hasNext()); 
		
		// Tests inorder traversing iterator of right-heavy char tree.
		// Inorder traversal of testBST3: 'p', 'r', 's', 't', 'v', 'w', 'x', 'y'. 
		BinarySearchTree.Iterator inOrderTest3 = testBST3.getInOrderIterator(); 
		assertEquals(true, inOrderTest3.hasNext()); 
		assertEquals('p', inOrderTest3.next()); 
		assertEquals('r', inOrderTest3.next()); 
		assertEquals('s', inOrderTest3.next()); 
		assertEquals('t', inOrderTest3.next()); 
		assertEquals('v', inOrderTest3.next()); 
		assertEquals(true, inOrderTest3.hasNext()); 
		assertEquals(true, inOrderTest3.hasNext()); 
		assertEquals('w', inOrderTest3.next()); 
		assertEquals('x', inOrderTest3.next()); 
		assertEquals('y', inOrderTest3.next()); 
		assertEquals(false, inOrderTest3.hasNext()); 
		try {
			inOrderTest3.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, inOrderTest3.hasNext()); 
		
		// Test inorder traversing iterator of boolean tree. 
		// Inorder traversal of testBST4: false, true. 
		BinarySearchTree.Iterator inOrderTest4 = testBST4.getInOrderIterator(); 
		assertEquals(true, inOrderTest4.hasNext()); 
		assertEquals(false, inOrderTest4.next()); 
		assertEquals(true, inOrderTest4.hasNext()); 
		assertEquals(true, inOrderTest4.next()); 
		assertEquals(false, inOrderTest4.hasNext()); 
		try {
			inOrderTest4.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, inOrderTest4.hasNext()); 

		// Test inorder traversing iterator of tree containing a single double. 
		// Inorder traversal of testBST5: 151.72. 
		BinarySearchTree.Iterator inOrderTest5 = testBST5.getInOrderIterator(); 
		assertEquals(true, inOrderTest5.hasNext()); 
		assertEquals(151.72, inOrderTest5.next()); 
		assertEquals(false, inOrderTest5.hasNext()); 
		try {
			inOrderTest5.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, inOrderTest5.hasNext()); 
		} // end method testInOrderIterator 
	
	
	/**
	 * Tests preorder traversing Iterator of class BinarySearch. Tests both the hasNext and next 
	 * methods. next is called in each Iterator until it hits the end of the BST. These tests also
	 * effectively test the methods that construct the LinkedList upon which the Iterator is built,
	 * since those methods are private. 
	 */ 
	@Test
	public void testPreOrderIterator() {
		// Tests preorder traversing iterator on the balanced int tree. 
		// Preorder traversal of testBST1: 50, 25, 15, 12, 20, 21, 35, 30, 65, 59, 101, 72, 99, 111.		
		BinarySearchTree.Iterator preOrderTest1 = testBST1.getPreOrderIterator(); 
		assertEquals(50, preOrderTest1.next()); 
		assertEquals(true, preOrderTest1.hasNext()); 
		assertEquals(25, preOrderTest1.next()); 
		assertEquals(15, preOrderTest1.next()); 
		assertEquals(12, preOrderTest1.next()); 
		assertEquals(true, preOrderTest1.hasNext()); 
		assertEquals(20, preOrderTest1.next()); 
		assertEquals(21, preOrderTest1.next()); 
		assertEquals(35, preOrderTest1.next()); 
		assertEquals(30, preOrderTest1.next()); 
		assertEquals(65, preOrderTest1.next()); 
		assertEquals(59, preOrderTest1.next()); 
		assertEquals(101, preOrderTest1.next()); 
		assertEquals(72, preOrderTest1.next()); 
		assertEquals(true, preOrderTest1.hasNext()); 
		assertEquals(99, preOrderTest1.next()); 
		assertEquals(111, preOrderTest1.next()); 
		assertEquals(false, preOrderTest1.hasNext()); 
		try {
			preOrderTest1.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, preOrderTest1.hasNext()); 
		
		// Tests preorder traversing iterator of left-heavy String tree.
		// Preorder traversal of testBST2: "jump jump", "aaa", "Modus Ponens", "Make Believe", 
			// "Last Tango in Paris", "Kappa", "Hold me closer, Tony Danza", 
			// "Don't fear the reaper", "DELIVERANCE", "Categorical Imperative", "BBBBB".
		BinarySearchTree.Iterator preOrderTest2 = testBST2.getPreOrderIterator(); 
		assertEquals(true, preOrderTest2.hasNext()); 
		assertEquals("jump jump", preOrderTest2.next());
		assertEquals("aaa", preOrderTest2.next()); 
		assertEquals("Modus Ponens", preOrderTest2.next()); 
		assertEquals(true, preOrderTest2.hasNext()); 
		assertEquals(true, preOrderTest2.hasNext()); 
		assertEquals("Make Believe", preOrderTest2.next()); 
		assertEquals("Last Tango in Paris", preOrderTest2.next()); 
		assertEquals("Kappa", preOrderTest2.next()); 
		assertEquals(true, preOrderTest2.hasNext()); 
		assertEquals("Hold me closer, Tony Danza", preOrderTest2.next()); 
		assertEquals("Don't fear the reaper", preOrderTest2.next()); 
		assertEquals("DELIVERANCE", preOrderTest2.next()); 
		assertEquals("Categorical Imperative", preOrderTest2.next()); 
		assertEquals("BBBBB", preOrderTest2.next()); 
		assertEquals(false, preOrderTest2.hasNext()); 
		try {
			preOrderTest2.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		
		// Tests preorder traversing iterator of right-heavy char tree. 
		// In a right-subtree-only BST, preorder and inorder traversals will be identical. 
		// Preorder traversal of testBST3: 'p', 'r', 's', 't', 'v', 'w', 'x', 'y'. 
		BinarySearchTree.Iterator preOrderTest3 = testBST3.getPreOrderIterator(); 
		assertEquals(true, preOrderTest3.hasNext()); 
		assertEquals('p', preOrderTest3.next()); 
		assertEquals('r', preOrderTest3.next()); 
		assertEquals('s', preOrderTest3.next()); 
		assertEquals('t', preOrderTest3.next()); 
		assertEquals('v', preOrderTest3.next()); 
		assertEquals(true, preOrderTest3.hasNext()); 
		assertEquals(true, preOrderTest3.hasNext()); 
		assertEquals('w', preOrderTest3.next()); 
		assertEquals('x', preOrderTest3.next()); 
		assertEquals('y', preOrderTest3.next()); 
		assertEquals(false, preOrderTest3.hasNext()); 
		try {
			preOrderTest3.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, preOrderTest3.hasNext()); 

		// Test preorder traversing iterator of boolean tree. 
		// Preorder traversal of testBST4: false, true. 
		BinarySearchTree.Iterator preOrderTest4 = testBST4.getPreOrderIterator(); 
		assertEquals(true, preOrderTest4.hasNext()); 
		assertEquals(false, preOrderTest4.next()); 
		assertEquals(true, preOrderTest4.hasNext()); 
		assertEquals(true, preOrderTest4.next()); 
		assertEquals(false, preOrderTest4.hasNext()); 
		try {
			preOrderTest4.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, preOrderTest4.hasNext()); 

		// Test preorder traversing iterator of tree containing a single double. 
		// Preorder traversal of testBST5: 151.72. 
		BinarySearchTree.Iterator preOrderTest5 = testBST5.getPreOrderIterator(); 
		assertEquals(true, preOrderTest5.hasNext()); 
		assertEquals(151.72, preOrderTest5.next()); 
		assertEquals(false, preOrderTest5.hasNext()); 
		try {
			preOrderTest5.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, preOrderTest5.hasNext()); 
		} // end method testPreOrderIterator
	
	
	/**
	 * Tests postorder traversing Iterator of class BinarySearch. Tests both the hasNext and next 
	 * methods. next is called in each Iterator until it hits the end of the BST. These tests also
	 * effectively test the methods that construct the LinkedList upon which the Iterator is built,
	 * since those methods are private. 
	 */ 
	@Test
	public void testPostOrderIterator() {
		// Tests postorder traversing iterator of balanced int tree. 
		// Postorder traversal of testBST1: 12, 21, 20, 15, 30, 35, 25, 59, 99, 72, 111, 101, 65, 50.
		BinarySearchTree.Iterator postOrderTest1 = testBST1.getPostOrderIterator(); 
		assertEquals(12, postOrderTest1.next()); 
		assertEquals(21, postOrderTest1.next()); 
		assertEquals(true, postOrderTest1.hasNext()); 
		assertEquals(true, postOrderTest1.hasNext()); 
		assertEquals(20, postOrderTest1.next()); 
		assertEquals(15, postOrderTest1.next()); 
		assertEquals(30, postOrderTest1.next()); 
		assertEquals(true, postOrderTest1.hasNext()); 
		assertEquals(35, postOrderTest1.next()); 
		assertEquals(25, postOrderTest1.next()); 
		assertEquals(59, postOrderTest1.next()); 
		assertEquals(99, postOrderTest1.next()); 
		assertEquals(72, postOrderTest1.next()); 
		assertEquals(111, postOrderTest1.next()); 
		assertEquals(101, postOrderTest1.next()); 
		assertEquals(65, postOrderTest1.next()); 
		assertEquals(50, postOrderTest1.next()); 
		assertEquals(false, postOrderTest1.hasNext()); 
		try {
			postOrderTest1.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		} 
		assertEquals(false, postOrderTest1.hasNext()); 
		
		// Tests postorder traversing iterator of left-heavy String tree.
		// In a left-subtrees-only tree, this traversal will be identical to the inorder traversal.
		// Postorder traversal of testBST2: "BBBBB", "Categorical Imperative", "DELIVERANCE", 
			// "Don't fear the reaper", "Hold me closer, Tony Danza", "Kappa", 
			// "Last Tango in Paris", "Make Believe", "Modus Ponens", "aaa", "jump jump". 
		BinarySearchTree.Iterator postOrderTest2 = testBST2.getPostOrderIterator(); 
		assertEquals(true, postOrderTest2.hasNext()); 
		assertEquals("BBBBB", postOrderTest2.next()); 
		assertEquals("Categorical Imperative", postOrderTest2.next()); 
		assertEquals("DELIVERANCE", postOrderTest2.next()); 
		assertEquals(true, postOrderTest2.hasNext()); 
		assertEquals("Don't fear the reaper", postOrderTest2.next()); 
		assertEquals("Hold me closer, Tony Danza", postOrderTest2.next()); 
		assertEquals(true, postOrderTest2.hasNext()); 
		assertEquals("Kappa", postOrderTest2.next()); 
		assertEquals("Last Tango in Paris", postOrderTest2.next()); 
		assertEquals("Make Believe", postOrderTest2.next()); 
		assertEquals("Modus Ponens", postOrderTest2.next()); 
		assertEquals("aaa", postOrderTest2.next()); 
		assertEquals(true, postOrderTest2.hasNext()); 
		assertEquals("jump jump", postOrderTest2.next()); 
		assertEquals(false, postOrderTest2.hasNext()); 
		assertEquals(false, postOrderTest1.hasNext()); 
		try {
			postOrderTest2.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, postOrderTest2.hasNext()); 

		// Tests postorder traversing iterator of right-heavy char tree. 
		// Postorder traversal of testBST3: 'y', 'x', 'w', 'v', 't', 's', 'r', 'p'. 
		BinarySearchTree.Iterator postOrderTest3 = testBST3.getPostOrderIterator(); 
		assertEquals(true, postOrderTest3.hasNext()); 
		assertEquals('y', postOrderTest3.next()); 
		assertEquals(true, postOrderTest3.hasNext()); 
		assertEquals('x', postOrderTest3.next()); 
		assertEquals('w', postOrderTest3.next()); 
		assertEquals('v', postOrderTest3.next()); 
		assertEquals('t', postOrderTest3.next()); 
		assertEquals('s', postOrderTest3.next()); 
		assertEquals('r', postOrderTest3.next()); 
		assertEquals(true, postOrderTest3.hasNext()); 
		assertEquals('p', postOrderTest3.next()); 
		assertEquals(false, postOrderTest3.hasNext()); 
		try {
			postOrderTest3.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, postOrderTest3.hasNext()); 

		// Test postorder traversing iterator of boolean tree. 
		// Postorder traversal of testBST4: true, false. 
		BinarySearchTree.Iterator postOrderTest4 = testBST4.getPostOrderIterator(); 
		assertEquals(true, postOrderTest4.hasNext()); 
		assertEquals(true, postOrderTest4.next()); 
		assertEquals(true, postOrderTest4.hasNext()); 
		assertEquals(false, postOrderTest4.next()); 
		assertEquals(false, postOrderTest4.hasNext()); 
		try {
			postOrderTest4.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, postOrderTest4.hasNext()); 

		// Test postorder traversing iterator of tree containing a single double. 
		// Postorder traversal of testBST5: 151.72. 
		BinarySearchTree.Iterator postOrderTest5 = testBST5.getPostOrderIterator(); 
		assertEquals(true, postOrderTest5.hasNext()); 
		assertEquals(151.72, postOrderTest5.next()); 
		assertEquals(false, postOrderTest5.hasNext()); 
		try {
			postOrderTest5.next();
		} 
		catch (NoSuchElementException exception) {
			assertThat(exception.getMessage(), is("Traversal of BST complete."));
		}
		assertEquals(false, postOrderTest5.hasNext()); 
		} // end method testPostOrderIterator1
} // end class BSTTest