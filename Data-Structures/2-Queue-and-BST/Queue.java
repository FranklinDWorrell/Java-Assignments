/**
 * A basic implementation of a queue for use in breadth-first 
 * traversal of a binary search tree. 
 * 
 * Assignment 2
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: April 4, 2016
 * Class: Queue
 */ 

/**
 * An implementation of a basic queue that is simply a SinglyLinkedList under the
 * hood. Methods peek, dequeue, enqueue, isEmpty, and toString are implemented. 
 * Since the SinglyLinkedList working in the background is a private instance 
 * variable, SinglyLinkedList's methods will not be accessible to the user of Queue. 
 */ 
public class Queue<T> {
	// Declare a SinglyLinkedList that will be the Queue under the hood. 
	private SinglyLinkedList<T> queueList; 
	
	
	/**
	 * Constructor initializes the under-the-hood SinglyLinkedList. 
	 */ 
	public Queue() {
		this.queueList = new SinglyLinkedList<T>(); 
	} // end constructor
	
	
	/**
	 * Returns the value in the head of the queue. 
	 * 
	 * @throws	IndexOutOfBoundsException
	 * @return	the element contained in the head node
	 */ 
	public T peek() {
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException("Queue is empty; no element to view."); 
		} 
		else {
			return this.queueList.getNthFromFirst(0); 
		} 
	} // end method peek 
	
	
	/**
	 * Removes the queue's head node and returns the value it contained. 
	 * 
	 * @throws	IndexOutOfBoundsException
	 * @return	the element contained in the head node
	 */ 
	public T dequeue() {
		return queueList.removeFromFront(); 
	} // end method dequeue
	
	
	
	/**
	 * Adds a new node containing the inputted element at the end of the queue. 
	 * 
	 * @param	value		the element the new node should contain
	 */ 
	public void enqueue(T value) {
		this.queueList.add(value); 
	} // end method enqueue
	
	
	/**
	 * Returns whether or not the queue is empty.
	 * 
	 * @return	whether queue contains additional nodes
	 */ 
	public boolean isEmpty() {
		return this.queueList.isEmpty(); 
	} // end method isEmpty
	
	
	/**
	 * Returns a String representation of the aueue. 
	 *
	 * @return	a String representing the queue's contents
	 */ 
	public String toString() {
		return queueList.toString(); 
	}// end method toString
} // end class Queue 