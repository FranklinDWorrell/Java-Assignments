/**
 * A generic implementation of a min heap. Because the underlying data structure
 * is an array of the generic type, some pains must be taken to avoid compile-
 * time warnings about unchecked casts and raws types. 
 *
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Assignment: Homework 3
 * Date Due: April 25, 2016
 * Revision Date: April 24, 2016
 * Class: MinHeap
 */ 

import java.util.NoSuchElementException; 
 
/**
 * A basic implementation of a MinHeap for use in UnboundedPriorityQueue.java.
 */ 
public class MinHeap<T extends Comparable<T>> {
	// Declare needed instance variables and constants. 
	private T[] underlyingArray; 
	private static final int INITIAL_SIZE = 12; 
	private int numberOfElements = 0; 
	
	/**
	 * Default constructor initializes an array of size 12. 
	 */
	public MinHeap() {
		this(INITIAL_SIZE); 
	} // end default Constructor
	
	
	/**
	 * An overloaded constructor that allows the user to set the initial
	 * capacity of the MinHeap. Warnings are suppressed, because the 
	 * casting done at the creation of the new array should cover any type
	 * issues caused by the conflict between arrays and generics in Java.
	 *
	 * @param	arraySize	the desired initial capacity of the MinHeap
	 */ 
	@SuppressWarnings("unchecked")
	public MinHeap(int arraySize) {
		this.underlyingArray = (T[]) new Comparable[arraySize]; 
	} // end overload Constructor
	
	
	/**
	 * An overloaded constructor that takes as an argument an array
	 * of a particular type and adds each element of that array to 
	 * the MinHeap.
	 * 
	 * @param	inputArray	the T[] whose elements should constitute the heap
	 */ 
	public MinHeap(T[] inputArray) {
		this(); 
		for (int i = 0; i < inputArray.length; i++) {
			this.insert(inputArray[i]); 
		} 
	} // end overload Constructor
	

	/**
	 * Inserts a new element in its proper place into the MinHeap. 
	 *
	 * @param	newElement	the element to be inserted into the heap 
	 */ 
	public void insert(T newElement) {
		// If the array is full, resize it to make more room. 
		if (this.isFull()) {
			this.growUnderlyingArray(); 
		} 
		
		// Insert the newElement at the end of the array
		int lastChildIndex = this.numberOfElements; 
		this.underlyingArray[lastChildIndex] = newElement; 

		// Move the newElement to its proper place in the array. 
		// Insertion into empty heap does not require reshuffling. 
		if (this.numberOfElements != 0) {
			this.properlyPlaceChild(lastChildIndex); 
		} 
		
		// Increment the count of elements in the underlying array. 
		this.numberOfElements++; 
	} // end method insert
	
	
	/**
	 * Grows the underlying array to accommodate adding new elements when
	 * it begins to get too large. Warnings are suppressed, because the 
	 * casting done at the creation of the new array should cover any type
	 * issues caused by the conflict between arrays and generics in Java. 
	 */ 
	@SuppressWarnings("unchecked") 
	private void growUnderlyingArray() {
		// Create the new array. 
		T[] newArray = (T[]) new Comparable[this.underlyingArray.length * 2]; 
		
		// Copy each element into the new array. 
		for (int i = 0; i < this.numberOfElements; i++) {
			newArray[i] = this.underlyingArray[i]; 
		}
		
		// Set the underlying array equal to the new array. 
		this.underlyingArray = newArray; 
	} // end method growUnderlyingArray
	
	
	/**
	 * Places an inserted element at the appropriate index of the array
	 * by iteratively comparing it with its parent node and swapping as
	 * needed. 
	 *
	 * @param	childIndex	the index of the element that needs placing
	 */ 
	private void properlyPlaceChild(int childIndex) {
		boolean isPlaced = false; 
		int parentIndex = 0; 
		
		while (!isPlaced && (childIndex >= 1)) {
			// Get the index for the parent.
			parentIndex = (childIndex - 1) / 2;

			// Compare the data in the parent and the child. Swap if child's is less. 
			if (this.underlyingArray[childIndex].compareTo(this.underlyingArray[parentIndex]) < 0) {
				this.swapElementsAt(parentIndex, childIndex); 
				childIndex = parentIndex; 
			} 
			
			// Otherwise, the child is in the proper place. 
			else {
				isPlaced = true; 
			} 
		} 
	} // end method properlyPlaceChild


	/**
	 * Removes and returns the root of the MinHeap. Reorders remaining
	 * nodes so that they are in the correct order. 
	 *
	 * @throws 	NoSuchElementException
	 * @returns	the root of the MinHeap
	 */ 
	public T remove() {
		// Throw an exception if performed on an empty heap. 
		if (this.isEmpty()) {
			throw new NoSuchElementException("MinHeap empty; no element to remove."); 
		} 
		
		// Catch a reference to the root before reordering the array. 
		T rootValue = this.underlyingArray[0]; 
		
		// Reorder the array after root is removed. 
		this.reorderAfterRemove(); 
		
		// Decrement the element of count and return removed value. 
		this.numberOfElements--; 
		return rootValue; 
	} // end method remove
	
	
	/**
	 * Reorders the MinHeap after the head of the heap is removed. 
	 */ 
	private void reorderAfterRemove() {
		// Set the root to the last added value. 
		T lastValue = this.underlyingArray[this.numberOfElements - 1]; 
		this.underlyingArray[0] = lastValue; 
		// Reset last node to null. 
		this.underlyingArray[this.numberOfElements - 1] = null; 

		// Compare the former last element to its new children. Shift as needed. 
		for (int i = 0; ((2 * i) + 1) <= this.numberOfElements; i++) {
			
			// The new root has at least one child.  
			if (this.underlyingArray[(2 * i) + 1] != null) {
				
				// The new root only has a left child. 
				if ((((2 * i) + 1) > this.numberOfElements) || (this.underlyingArray[(2 * i) + 2] == null)) {
					// Swap if the left child is less than the new root. 
					if (this.underlyingArray[i].compareTo(this.underlyingArray[(2 * i) + 1]) > 0) {
						this.swapElementsAt(i, (2 * i) + 1); 
					}
				}
				
				// The new root has both a left and a right child. 
				else {
					
					// Left child is smaller than right child. 
					if (this.underlyingArray[(2 * i) + 1].compareTo(this.underlyingArray[(2 * i) + 2]) < 0) {
						// Swap if the left child is less than the new root. 
						if (this.underlyingArray[i].compareTo(this.underlyingArray[(2 * i) + 1]) > 0) {
							this.swapElementsAt(i, (2 * i) + 1); 
						} 
					}
					
					// Right child is smaller than left child. 
					else {
						// Swap if the right child is less than the new root. 
						if (this.underlyingArray[i].compareTo(this.underlyingArray[(2 * i) + 2]) > 0) {
							this.swapElementsAt(i, (2 * i) + 2); 
						}
					} 
				}
			} 
			// Otherwise, the new root has no children, so no shifting required. 
		}
	} // end method reorderAfterRemove
	
	
	/**
	 * Swaps the elements at the indices passed in as arguments. 
	 *
	 * @param	firstIndex	the index of one of the elements to be swapped
	 * @param	secondIndex	the index of the other element to be swapped
	 */ 
	private void swapElementsAt(int firstIndex, int secondIndex) {
		T temp = underlyingArray[firstIndex]; 
		underlyingArray[firstIndex] = underlyingArray[secondIndex]; 
		underlyingArray[secondIndex] = temp; 
	} // end method swapElements


	/**
	 * Returns whether or not the MinHeap is empty, i.e., 
	 * whether it contains any additional elements. 
	 *
	 * @return	whether or not MinHeap contains any elements
	 */ 
	public boolean isEmpty() {
		// If the MinHeap does not have a root, it is empty. 
		if (this.underlyingArray[0] == null) {
			return true; 
		}
		
		// Otherwise, it is not empty. 
		else {
			return false; 
		} 
	} // end method isEmpty


	/**
	 * Returns whether or not each spot in the underlying array is occupied
	 * by an element--i.e., whether or not the underlying array is full. 
	 *
	 * @return	whether the MinHeap is full
	 */ 
	public boolean isFull() {
		// Compare the number of elements that the heap contains to its size. 
		if (this.numberOfElements == (this.underlyingArray.length)) {
			return true; 
		} 
		
		else {
			return false; 
		} 
	} // end method isFull


	/**
	 * Returns a string representation of the contents of the MinHeap.
	 * Given the structure of the underlying array in this implementation,
	 * the string will be a breadth-first traversal of the MinHeap. 
	 *
	 * @returns	a String representation of the MinHeap's contents
	 */ 
	public String toString() {
		// Initialize the string that will be returned. 
		String heapString = ""; 
		
		// Iterate through the MinHeap's elements, adding each to the string. 
		for (int i = 0; i < this.numberOfElements; i++) {
			if (this.underlyingArray[i] != null) {
				heapString += this.underlyingArray[i].toString() + " "; 
			} 
		}
		
		// Trim the last empty space off the string before returning it. 
		return heapString.trim(); 
	} // end method toString
} // end class MinHeap
