/**
 * A basic stack implementation; will be used for infix to postfix conversion
 * application. 
 * 
 * Assignment 1
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: February 25, 2016
 * Class: MyStack
 */ 

/**
 * An implementation of a basic stack that is simply a SinglyLinkedList under the
 * hood. Methods top, pop, push, and isEmpty implemented. Since the SinglyLinkedList 
 * working in the background is a private instance variable, SinglyLinkedList's 
 * methods will not be accessible to the user of MyStack. 
 */ 
public class MyStack<T extends Comparable<T>> {
	// Declare a SinglyLinkedList that will be MyStack under the hood. 
	private SinglyLinkedList<T> stackList; 
	
	
	/**
	 * Constructor initializes the under-the-hood SinglyLinkedList. 
	 */ 
	public MyStack() {
		this.stackList = new SinglyLinkedList<T>(); 
	} // end constructor
	
	
	/**
	 * Returns the value the stack's top contains. 
	 * 
	 * @throws	IndexOutOfBoundsException
	 * @return	the element contained in the head node
	 */ 
	public T top() {
		return this.stackList.getNthFromFirst(0); 
	} // end method top 
	
	
	/**
	 * Removes the stack's top and returns the value it contained. 
	 * 
	 * @throws	IndexOutOfBoundsException
	 * @return	the element contained in the head node
	 */ 
	public T pop() {
		return stackList.removeFromFront(); 
	} // end method pop
	
	
	
	/**
	 * Adds a new node containing the inputted element at the head of the stack. 
	 * 
	 * @param	value		the element the new head should contain
	 */ 
	public void push(T value) {
		this.stackList.insertAt(value, 0); 
	} // end method push
	
	
	/**
	 * Returns whether or not the stack is empty
	 * 
	 * @return	whether stack contains additional nodes
	 */ 
	public boolean isEmpty() {
		return this.stackList.isEmpty(); 
	} // end method isEmpty
} // end class MyStack 