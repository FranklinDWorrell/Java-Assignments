/**
 * A basic implementation of an unbounded priority queue. 
 *
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Assignment: Homework 3
 * Due Date: April 25, 2016
 * Revision Date: April 24, 2016
 * Class: UnboundedPriorityQueue
 */ 

/**
 * A priority queue that is dynamically resizeable. Has a MinHeap
 * that structures the data and performs the essential functions of 
 * the priority queue. 
 */ 
public class UnboundedPriorityQueue<T extends Comparable<T>> {
	// Declare the heap underlying the UnboundedPriorityQueue. 
	private MinHeap<T> heap; 

	
	/**
	 * Default constructor initializes the underlying MinHeap with
	 * MinHeap's default constructor and size. 
	 */ 
	public UnboundedPriorityQueue() {
		this.heap = new MinHeap<T>(); 
	} // end default Constructor


	/**
	 * Overloaded constructor initializes the underlying MinHeap with
	 * the initial capacity provided as an argument. This will thereby
	 * set the initial capacity of the UnboundedPriorityQueue to the
	 * provided value. 
	 * 
	 * @param	capacity	the initial capacity of the UnboundedPriorityQueue
	 */ 
	public UnboundedPriorityQueue(int capacity) {
		this.heap = new MinHeap<T>(capacity); 
	} // end overload Constructor


	/**
	 * Adds an element to the UnboundedPriorityQueue. Merely calls
	 * MinHeap.insert and lets that class do all the real work. 
	 * 
	 * @param	element		the element to be added to the priority queue
	 */ 
	public void enqueue(T element) {
		this.heap.insert(element);  
	} // end method enqueue


	/** 
	 * Removes the head of the UnboundedPriorityQueue and returns its
	 * value. 
	 *
	 * @throws 	NoSuchElementException
	 * @return	the value of the head of the priority queue
	 */ 
	public T dequeue() {
		return this.heap.remove(); 
	} // end method dequeue


	/**
	 * Returns whether or not the UnboundedPriorityQueue is empty or 
	 * contains additional elements. 
	 * 
	 * @return	whether the UnboundedPriorityQueue contains additional elements
	 */ 
	public boolean isEmpty() {
		return this.heap.isEmpty(); 
	} // end method isEmpty
} // end class UnboundedPriorityQueue
