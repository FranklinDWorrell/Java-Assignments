/** 
 * An implementation of a singly linked list. 
 *
 * Assignment 1
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: February 25, 2016
 * Class: SinglyLinkedList
 */ 

import java.util.Iterator;
import java.util.NoSuchElementException; 

/**
 * A class for building singly linked lists. Inner classes defining the list nodes
 * and the iterator for the class. 
 */ 
public class SinglyLinkedList<AnyType extends Comparable<AnyType>> implements Iterable<AnyType> {
	private int size; 
	private Node<AnyType> head; 
	private Node<AnyType> tail; 
	
	
	/**
	 * Constructor creates a new SinglyLinkedList object and 
	 * initializes the size of the list to zero and the head
	 * and tail references to null. 
	 */ 
	public SinglyLinkedList() {
		this.size = 0; 
		this.head = new Node<AnyType>(null); 
		this.tail = new Node<AnyType>(null); 
		this.head.setNext(this.tail); 
	} // end SinglyLinkedList constructor
	

	/**
	 * Adds a new Node to the end of the SinglyLinkedList. 
	 * @param	element	the data the new Node should contain
	 */ 
	public void add(AnyType element) {
		// Create a new Node object to add. 
		Node<AnyType> newNode = new Node<AnyType>(element);

		// List is empty, so new Node set as head and tail.  
		if (this.head.getNext() == this.tail) {
			this.head.setNext(newNode); 
			newNode.setNext(this.tail); 
		} 

		// List is not empty, so iterate to last Node and set 
		// its next link to the new Node. 
		else {
			Node<AnyType> endOfList = iterateToIndex(this.size - 1); 
			endOfList.setNext(newNode); 
			newNode.setNext(this.tail); 
		} 

		// Increment the SinglyLinkedList's size. 
		this.size++; 
	} // end method add


	/** 
	 * Inserts a new Node at the given index of the list. Indexing
	 * begins at zero. 
	 * @param	element	the data the new Node should contain
	 * @param	index	where the new Node should be inserted 
	 * @throws	IndexOutOfBoundsException
	 */ 
	public void insertAt(AnyType element, int index) {
		// Validate index argument. 
		if ( (index < 0) || (index  > (this.size))) {
			throw new IndexOutOfBoundsException("Invalid index."); 
		} 
		
		// Provided index was valid. 
		else {
			// Create the new Node. 
			Node<AnyType> newNode = new Node<AnyType>(element); 
		
			// New Node should be inserted as the head of the list
			if (index == 0) {
				newNode.setNext(this.head.getNext()); 
				this.head.setNext(newNode); 
			} 
		
			// Otherwise, iterate to appropriate index and set appropriate references. 
			else {
				Node<AnyType> currentNode = this.iterateToIndex(index - 1); 
				Node<AnyType> oldNext = currentNode.getNext(); 
				currentNode.setNext(newNode); 
				newNode.setNext(oldNext); 
			} 
		
			this.size++; 
		} 
	} // end method insertAt
	
	
	/** 
	 * Removes the first occurence of the provided data from the list. 
	 * @param	element	the data to remove from the list 
	 * @throws	NoSuchElementException
	 */ 
	public void remove(AnyType element) {
		// Initialize temporary variables to hold necessary references. 
		Node<AnyType> currentNode = this.head.getNext(); 
		Node<AnyType> previousNode = this.head; 

		// Initialize boolean to track whether element found in list. 
		boolean elementFound = false; 

		// Iterate through list to find first occurence of element. 
		while (!elementFound && (currentNode != this.tail)) {
			// Element found in currentNode. 
			if (currentNode.getData().compareTo(element) == 0) {
				// Make the previous Node's next link skip the current Node.
				previousNode.setNext(currentNode.getNext()); 				
				this.size--; 
				elementFound = true; 
			} 
			
			// Element not found in currentNode. 
			else {
				previousNode = currentNode; 
				currentNode = currentNode.getNext(); 
			} 
		}  
		
		// Element did not occur in list; throw exception. 
		if (elementFound == false) {
			throw new NoSuchElementException("Element not contained in list."); 
		} 
	} // end method remove
	
	
	/**
	 * Clears all elements from the SinglyLinkedList. 
	 */ 
	public void clear() {
		this.head.setNext(this.tail); 
		this.size = 0;  
	} // end method clear
	
	
	/**
	 * Returns true if the SinglyLinkedList is empty, false otherwise. 
	 * @return 	whether the list is empty or not
	 */ 
	public boolean isEmpty() {
		if (this.size == 0) {
			return true; 
		} 
		else {
			return false; 
		} 
	} // end method isEmpty
	
	
	/**
	 * Returns the number of elements currently in the list. 
	 * @return	the number of elements the list contains
	 */ 
	public int size() {
		return this.size; 
	} // end method size
	
	
	/**
	 * Returns the nth value from the first Node. Count starts at zero.
	 * @param	n		the index of the Node whose value is to be returned
	 * @throws	IndexOutOfBoundsException
	 * @return	the value contained in the nth Node
	 */ 
	public AnyType getNthFromFirst(int n) {
		// Validate index given.  
		if ( (n < 0) || (n  > (this.size - 1))) {
			throw new IndexOutOfBoundsException("Invalid index."); 
		} 
		
		Node<AnyType> currentNode = this.iterateToIndex(n); 
		
		return currentNode.getData(); 
	} // end method getNthFromFirst
	
	
	/**
	 * Returns the nth value from the last Node. Count starts at zero. 
	 * @param	n		the index (from the tail) of the Node whose value is desired
	 * @throws	IndexOutOfBoundsException
	 * @return	the contained in the (this.size - n)th Node
	 */ 
	public AnyType getNthFromLast(int n) {
		// Call to getNthFromFirst will validate n, so no check is done here. 
		int reversedIndex = (this.size - 1) - n; 	
		
		// Call getNthFromFirst with index from front as argument. 
		return getNthFromFirst(reversedIndex); 
	} // end method getNthFromLast 
	
	
	/**
	 * Returns a SinglyLinkedListIterator for traversing the list. 
	 * @return	a SinglyLinkedListIterator object
	 */ 
	public SinglyLinkedListIterator iterator() {
		SinglyLinkedListIterator newIterator = new SinglyLinkedListIterator(this.head, this.tail); 
		return newIterator; 
	} // end method iterator 
	
	
	/**
	 * Returns a String representing the content of the SinglyLinkedList.
	 * @return	a String representing the list's contents
	 */ 
	public String toString() { 
		// Create variable to hold string and a reference to the current Node.
		String listString = ""; 
		Node<AnyType> currentNode = this.head.getNext(); 
		
		// Iterate through the Nodes, pulling a String representation of each element.
		while (currentNode != this.tail) {
			if (currentNode.getNext() == this.tail) {
				listString += currentNode.getData().toString(); 
			} 
			
			else {
				listString += currentNode.getData().toString() + ", "; 
			}
			
			currentNode = currentNode.getNext(); 
		} 
		
		return listString; 
	} // end method toString
	
	
	/**
	 * Iterates through the SinglyLinkedList to capture a reference to a Node at 
	 * particular index. Helper method for insertAt and getNthFromFirst. Since it
	 * is merely a helper method, it is private access. Since calling methods 
	 * handle potential indexing issues, this method throws no exceptions. 
	 * @param	index		the index for which a reference is needed
	 * @require	0 <= index <= (this.size - 1)
	 * @return	a reference to the Node at the index given
	 */ 
	private Node<AnyType> iterateToIndex(int index) {
		// Create variables to hold count and current Node. 
		Node<AnyType> currentNode = this.head.getNext(); 
		int counter = 0; 
		
		// Iterate through list until nth Node reached. 
		while (counter < index) {
			currentNode = currentNode.getNext(); 
			counter++;
		} 
		
		return currentNode; 
	} // end method iterateToIndex
	
	
	/**
	 * Removes the first Node of the SinglyLinkedList
	 * 
	 * @throws	IndexOutOfBoundsException
	 */ 
	public AnyType removeFromFront() {
		/* Stubbed */ 
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException("List is empty, cannot remove item."); 
		} 
		
		else { 
			Node<AnyType> firstNode = this.head.getNext(); 
			AnyType element = firstNode.getData(); 
			this.head.setNext(firstNode.getNext()); 
			this.size--; 
			return element; 
		} 
	} // end method removeFromFront
		

	/**
	 * An Iterator class for the SinglyLinkedList. The
	 * remove() method is the only one that is not 
	 * fully functional.
	 */ 
	public class SinglyLinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> currentNode; 
		private Node<AnyType> listHead; 
		private Node<AnyType> listTail; 
		
		/**
		 * Constructor initializes Node pointer to the SinglyLinkedList's head.
		 */ 
		public SinglyLinkedListIterator(Node<AnyType> listHead, Node<AnyType> listTail) {
			this.currentNode = listHead.getNext(); 
			this.listHead = listHead; 
			this.listTail = listTail; 
		} // end SinglyLinkedListIterator constructor
		
		
		/**
		 * Returns true is there is at least one more element in the iteration, 
		 * false otherwise.
		 * @return	whether there is a next element in the iteration
		 */ 
		public boolean hasNext() {
			if (currentNode != this.listTail) { 
				return true; 
			} 
			
			else {
				return false; 
			} 
		} // end method hasNext
		
		
		/**
		 * Returns the next element in the iteration. 
		 * @throws	NoSuchElementException
		 * @returns	the element contained by the next Node in the list
		 */ 
		public AnyType next() { 
			Node<AnyType> oldCurrentNode = this.currentNode; 
			
			// Throw exception if there are no further Nodes. 
			if (currentNode == this.listTail) {
				throw new NoSuchElementException("List is empty");  
			} 
			
			// Otherwise, return this Node's data and move to next Node. 
			else { 
				currentNode = currentNode.getNext(); 
				return oldCurrentNode.getData(); 
			}
		} // end method next


		/**
		 * This method is not functional in this implemenation of
		 * of the SinglyLinkedListIterator. If called, it simply
		 * throws an exception. 
		 * @throws	UnsupportedOperationException
		 */ 
		public void remove() {
			throw new UnsupportedOperationException(
				"remove operation is not supported by this iterator"); 
		} // end method remove
	} // end class SinglyLinkedListIterator

	
	/**
	 * Class for Nodes that SinglyLinkedList is built of. Each Node 
	 * contains some piece of data and a link to its successor Node in the
	 * SinglyLinkedList
	 */ 
	private class Node<AnyType> {
		private AnyType data; 
		private Node<AnyType> nextNode; 
		
		
		/**
		 * Constructor initializes a new Node containing the provided
		 * data and initializes the nextNode link to null. 
		 * @param	data	the data the new Node will contain
		 */ 
		public Node(AnyType element) {
			this.data = element; 
			this.nextNode = null; 
		} // end Node constructor 

		
		/**
		 * Returns the data contained by the Node.
		 * @return	the data contained by the Node
		 */ 
		public AnyType getData() {
			return this.data; 
		} // end method getData


		/**
		 * Returns this Node's successor node.
		 * @return	the Node pointed to by the nextNode field
		 */ 
		public Node<AnyType> getNext() {
			return this.nextNode; 
		} 


		/** 
		 * Sets this Node's successor node. 
		 * @param	newNext	the Node to be set as successor
		 */ 
		public void setNext(Node<AnyType> newNext) {
			this.nextNode = newNext; 
		} // end method setNext
	} // end class Node
} // end class SinglyLinkedList
