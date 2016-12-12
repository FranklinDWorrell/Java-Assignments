/**
 * A generic implementation of a binary search tree (BST). The BST has iterators that can
 * traverse it according to three different traversal strategies: inorder, preorder, and 
 * postorder. 
 * 
 * Assignment: Homework 4 - Binary Search Tree
 * Class: BinarySearchTree
 * Author: Franklin D. Worrell
 * Date: 11/13/2015
 */ 

import java.util.Iterator;
import java.util.LinkedList; 
import java.util.NoSuchElementException; 

/**
 * A generic binary search tree (BST) class. Each BST instance contains a piece of data, a 
 * reference to its left subtree, and a reference to its right subtree. The BST can have new 
 * elements inserted, can be searched, and can be iterated through according to different traversal 
 * schemes.
 */ 
public class BinarySearchTree<E extends Comparable<E>> {
	
	private E data;
	private BinarySearchTree<E> leftSubtree, rightSubtree;
	
	/**
	 * Initialize the BST with the given data. Both the left and right subtrees should initially
	 * be null.
	 *
	 * @param 	datum 	the BST's data
	 */
	public BinarySearchTree(E datum) {
		this.data = datum; 
		this.leftSubtree = null; 
		this.rightSubtree = null; 
	} // end constructor
	
		
	/**
	 * Inserts a new data item to the tree. Implementation is recursive. If there is a child in the 
	 * direction that the new BST should be placed, this method is called on that child. If the 
	 * data item is already contained by the BST, the method does nothing since BSTs cannot contain 
	 * duplicates. 
	 *
	 * @param 	datum 	the value to insert
	 */
	public void insert(E datum) { 
		// The datum to be inserted is less that the datum contained by the current BST. 
		if (this.data.compareTo(datum) > 0) {
			
			// There is no left subtree, so a new BST is added as the left child.
			if (this.leftSubtree == null) {
				this.leftSubtree = new BinarySearchTree<E>(datum); 
			}
			
			// There is a left child, so the datum is checked against that child's contents. 
			else {
				this.leftSubtree.insert(datum); 
			}
		}
		
		// The datum to be inserted is greater than the datum contained by the current BST. 
		else if (this.data.compareTo(datum) < 0) {
			
			// There is no right subtree so a new BST is added as the right child. 
			if (this.rightSubtree == null) {
				this.rightSubtree = new BinarySearchTree<E>(datum); 
			}
			
			// There is a right child, so the datum is checked against that child's contents. 
			else {
				this.rightSubtree.insert(datum); 
			}
		}
		// If the values are equal, then the insert call is ignored--no duplicates in a BST!
	}
	
	
	/**
	 * Returns true if the searchValue is in the BST or false otherwise. This method implements the 
	 * recursive binary search algorithm.
	 *
	 * @param 	searchValue 	the value to search for
	 * @return 	whether the searchValue is in the BST
	 */
	public boolean contains(E searchValue) {
		// The current tree contains the same datum. 
		if (searchValue.compareTo(this.data) == 0) {
			return true; 
		}
		
		// The datum being searched for is less than the current tree's datum. 
		// There are still other subtrees to search. 
		else if ((searchValue.compareTo(this.data) < 0) && (this.leftSubtree != null)) {
			return this.leftSubtree.contains(searchValue); 
		}
		
		// The datum being searched for is greater than the current tree's datum. 
		// There are still other subtrees to search. 
		else if ((searchValue.compareTo(this.data) > 0) && (this.rightSubtree != null)) {
			return this.rightSubtree.contains(searchValue); 
		}
		
		// The data in the trees does not match and there are no remaining trees to search. 
		else {
			return false; 
		} 
	} // end method binarySearch
	
	
	/*
	 * The following methods and class are all used to construct the Iterators required for this 
	 * implementation of the BST. Since the stubbed code did not have each BST containing a 
	 * reference to its parent BST (and I'm hard-headed and like a challenge), work-arounds for the
	 * traversals had to be found. Namely, a LinkedList is created and treated like a queue. When 
	 * each type of Iterator is built, it is passed the appropriate LinkedList. Hence, only one 
	 * Iterator class needs to be implemented--its constructor is simply passed a LinkedList that
	 * reflects the appropriate traversal scheme. In a sense, it's a minimal version of a Strategy
	 * design pattern. 
	 */ 
	
	
	/**
	 * Returns an iterator that inorder-traverses the BST. 
	 *
	 * @return	an Iterator that performs an inorder traversal
	 */
	public Iterator getInOrderIterator(){
		// Initializes a LinkedList to be sent to the Iterator's constructor method.
		LinkedList<BinarySearchTree<E>> inOrderList = new LinkedList<BinarySearchTree<E>>(); 
		
		// Fills that list with the BSTs added in the inorder order. 
		inOrderAdd(inOrderList); 
		
		// Calls constructor of Iterator
		Iterator inOrderIterator = new Iterator(inOrderList); 
		
		return inOrderIterator;
	} // end method getInOrderIterator
	
	
	/**
	 * Returns an iterator that preorder-traverses the BST.
	 *
	 * @return	an Iterator that performs a preorder traversal
	 */
	public Iterator getPreOrderIterator(){
		// Initializes a LinkedList to be sent to the Iterator's constructor method.
		LinkedList<BinarySearchTree<E>> preOrderList = new LinkedList<BinarySearchTree<E>>(); 
		
		// Fills that list with the BSTs added in the preorder order. 
		preOrderAdd(preOrderList); 
		
		// Calls constructor of Iterator
		Iterator preOrderIterator = new Iterator(preOrderList); 
		
		return preOrderIterator;
	} // end method getPreOrderIterator
	
	/**
	 * Returns an iterator that postorder-traverses the BST. 
	 *
	 * @return	an Iterator that performs a postorder traversal
	 */
	public Iterator getPostOrderIterator(){
		// Initializes a LinkedList to be sent to the Iterator's constructor method.
		LinkedList<BinarySearchTree<E>> postOrderList = new LinkedList<BinarySearchTree<E>>(); 
		
		// Fills that list with the BSTs added in the postorder order. 
		postOrderAdd(postOrderList); 
		
		// Calls constructor of Iterator
		Iterator postOrderIterator = new Iterator(postOrderList); 

		return postOrderIterator;
	} // end method getPostOrderIterator
	
	
	/**
	 * Returns a LinkedList that captures an inorder traversal of the BST. Method is recursive. For
	 * any given BST, it will add--if the children exist--its left child before adding the BST and 
	 * add its right child after adding the BST. 
	 * 
	 * @param	list	the LinkedList to which BSTs are being adding in inorder order
	 * @return	a LinkedList that contains references to each BST in inorder order
	 */ 
	private LinkedList<BinarySearchTree<E>> inOrderAdd(LinkedList<BinarySearchTree<E>> list) { 
		// If there is left child, see if it has a left child. 
		if (this.leftSubtree != null){
			this.leftSubtree.inOrderAdd(list); 
		} 
		
		// Add this BST to the LinkedList. 
		list.add(this); 
		
		// If there is a right child, see if it has a left child. 
		if (this.rightSubtree != null){
			this.rightSubtree.inOrderAdd(list); 
		} 
		
		return list;
	} // end method inOrderAdd
	
	/**
	 * Returns a LinkedList that captures an preorder traversal of the BST. Method is recursive. 
	 * For any given BST, it will add that BST then--if the children exist--its left child  and 
	 * then its right child. 
	 * 
	 * @param	list	the LinkedList to which BSTs are being adding in preorder order
	 * @return	a LinkedList that contains references to each BST in preorder order
	 */ 
	private LinkedList<BinarySearchTree<E>> preOrderAdd(LinkedList<BinarySearchTree<E>> list) {
		// Add the current tree to the Linked List
		list.add(this); 
		
		// Move to the left child, add it, and see if it has children. 
		if (this.leftSubtree != null){
			this.leftSubtree.preOrderAdd(list);
		} 
		
		// Move to the right child, add it, and see if it has children. 
		if (this.rightSubtree != null){
			this.rightSubtree.preOrderAdd(list);
		}
		
		return list;
	} // end method preOrderAdd
	
	/** 
	 * Returns a LinkedList that captures an postorder traversal of the BST. Method is recursive. 
	 * For any given BST, it will add--if the children exist--its left child and then its right 
	 * child before adding the BST. 
	 * 
	 * @param	list		the LinkedList to which BSTs are being adding in postorder order
	 * @return	a LinkedList that contains references to each BST in postorder order
	 */ 
	private LinkedList<BinarySearchTree<E>> postOrderAdd(LinkedList<BinarySearchTree<E>> list) {
		// If there is a left child, check to see if it has children and add appropriately. 
		if (this.leftSubtree != null){
			this.leftSubtree.postOrderAdd(list);
		}
		
		// If there is a right child, check to see if it has children and add appropriately. 
		if (this.rightSubtree != null){
			this.rightSubtree.postOrderAdd(list);
		} 
		
		// Adds the current BST to the list.
		list.add(this); 
		
		return list;
	} // end method postOrderAdd
	
	
	/**
	 * An Iterator class for the BST built here. Instead of creating three different types of 
	 * Iterators, only one type of iterator is built. The different traversal strategies are created
	 * by first building a LinkedList object and passing that LinkedList to the Iterator's 
	 * constructor. The elements of the LinkedList correspond to the traversal of the BST that was 
	 * called by the user. Irrespective of the travseral scheme, this Iterator is fail-fast--it 
	 * will fail if the BST is altered any time after the Iterator is constructed (because the 
	 * LinkedLists on which it is built will no longer accurately reflect the structure and 
	 * contents of the BST). 
	 */ 
	public class Iterator {
		
		private LinkedList<BinarySearchTree<E>> traversalList; 
		
		/**
		 * Constructor for Iterator class initializes the LinkedList used for iteration to the 
		 * LinkedList passed to it by the calling method. Depending on the calling method, the 
		 * LinkedList will reflect either an inorder, preorder, or postorder traversal of the BST.
		 * 
		 * @param	traversalList	the LinkedList that the iterator will get BST data from
		 */ 
		public Iterator(LinkedList<BinarySearchTree<E>> traversalList) {
			this.traversalList = traversalList; 
		} // end constructor 
		
		/**
		 * Returns true if there is a next BST in the LinkedList, returns false otherwise. 
		 * 
		 * @return	whether there is a next BST in the LinkedList
		 */ 
		public boolean hasNext() { 
			// Checks to make sure the traversal list is not empty. 
			if (traversalList.peekFirst() != null) {
				return true;  
			} 
			// Returns null if the traversal list is empty. 
			else {
				return false; 
			}
		} // end method hasNext
		
		/**
		 * Returns the datum contained in the next BST stored in the LinkedList if there is a next 
		 * BST. Removes that BST from the head of the LinkedList. If LinkedList is empty, throws 
		 * a NoSuchElementException. 
		 * 
		 * @return	the data of the next BST in the LinkedList
		 * @throws	NoSuchElementException
		 */ 
		public E next() { 
			// Checks to make sure the traversal list is not empty. 
			// Necessary to avoid NullPointerException.
			if (this.hasNext()) {
				return traversalList.pollFirst().data; 
			} 
			else {
				throw new NoSuchElementException("Traversal of BST complete.");
			}
		} // end method next
	} // end class Iterator	
} // end class BinarySearchTree
