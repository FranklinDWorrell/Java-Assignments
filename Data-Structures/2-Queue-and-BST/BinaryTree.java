/**
 * An implementation of a binary tree. Adds specified methods to the 
 * code provided. 
 *
 * Assignment: Homework 2
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: April 4, 2016
 * Class: BinaryTree
 */ 

import java.util.List;
import java.util.ArrayList;

public class BinaryTree<T>{
	BinaryNode<T> root = null;	
	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}

	private void initFromBfsSequence(T[] seq){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
				
		List<BinaryNode<T>> nodes = new ArrayList<BinaryNode<T>>(seq.length);
		this.root = new BinaryNode<T>(seq[0]);
		nodes.add(root);

		for(int i = 0; i < seq.length; i++){			
			if(nodes.get(i) == null){ 				
				handelNullParentNode(nodes, i, seq.length);				
			}else{				
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}

	// This method will handle the null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handelNullParentNode(List<BinaryNode<T>> nodes, 
						int nullNodeIndex, int seqLength){
		int leftIndex = (nullNodeIndex * 2) + 1; // finding the left child index from formula 
				
		if(leftIndex < seqLength){
			nodes.add(null);

			int rightIndex = (nullNodeIndex * 2) + 2; // finding the right child index from formula
			if(rightIndex < seqLength){
				nodes.add(null);
			}
		}
	}

	// This method will handle the non-null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, 
								int parentIndex, T[] seq){
		int leftIndex = (parentIndex * 2) + 1;			
		if(leftIndex < seq.length){ //need to check if the index falls outdise of the list index
			BinaryNode<T> leftNode = null;
			if(!seq[leftIndex].equals(nullSymbol)){
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			nodes.get(parentIndex).leftNode = leftNode;
			nodes.add(leftNode);

			int rightIndex = (parentIndex * 2) + 2;				
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				if(!seq[rightIndex].equals(nullSymbol)){
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				nodes.get(parentIndex).rightNode = rightNode;
				nodes.add(rightNode);			
			}
		}
	}

	public int height(){
		if (root == null) return 0;	
		return root.height();
	}
	

	/**
	 * Returns the width of the BinaryTree, i.e., the number of
	 * BinaryNodes in the largest level of the tree. 
	 * 
	 * @return	the width of the tree
	 */ 
	public int width(){
		int width = 0; 
		
		// Get the tree's height--i.e., the number of tree levels. 
		int treeHeight = this.height(); 
		
		// Create array such that each index represents a tree level.
		int[] countPerLevel = new int[treeHeight + 1]; 
		
		// Fill in count array.
		countPerLevel = getCountPerLevel(this.root, countPerLevel, 0); 
		
		// Find the maximum of the count array. 
		for (int i = 0; i <= treeHeight; i++) {
			if (countPerLevel[i] > width) {
				width = countPerLevel[i]; 
			} 
		}
		return width; 
	} // end method width
	
	
	/**
	 * Produces an array such that the value contained at an index is the number of 
	 * nodes in the level corresponding to that that index.
	 *
	 * @param	currentNode		the tree Node being counted
	 * @param	countPerLevel	the in-progress array counting nodes per level
	 * @param	levelDepth		the depth of the currentNode
	 * @return	an array of number of Nodes indexed by level depth
	 */ 
	private int[] getCountPerLevel(BinaryNode<T> currentNode, int[] countPerLevel, int levelDepth) {
		// Add the currentNode to the level-wise count of nodes. 
		if (currentNode != null) {
			countPerLevel[levelDepth]++;
			
			// If currentNode has a child, increment the level being considered. 
			if ((currentNode.getLeftNode() != null) || (currentNode.getRightNode() != null)) { 
				levelDepth++; 
			} 
			
			// If there's a left child, call this method on it. 
			if(currentNode.getLeftNode() != null){
				getCountPerLevel(currentNode.getLeftNode(), countPerLevel, levelDepth);				
			}
			
			// If there's a right child, call this method on it. 
			if(currentNode.getRightNode() != null){
				getCountPerLevel(currentNode.getRightNode(), countPerLevel, levelDepth);
			}
		}
		return countPerLevel; 
	} // end method getCountPerLevel
	
	
	/**
	 * Traverses the BinaryTree in breadth-first fashion and returns
	 * a string representing that traversal of the tree. 
	 *
	 * @return 	a String representation of breadth-first traversal of tree
	 */ 
	public String breadthFirstTraverse(){
		// Creates a queue to store references to Nodes by level. 
		Queue<BinaryNode<T>> bftQueue = new Queue<BinaryNode<T>>(); 
		String bftString = ""; 
		
		// If the root isn't null, enqueue it. 
		if (this.root != null) {
			bftQueue.enqueue(this.root); 
		} 
		
		// If the queue isn't empty, continue to process nodes. 
		while (!bftQueue.isEmpty()) {
			// Catch reference to head node and dequeue it. 
			BinaryNode<T> subtreeRoot = bftQueue.dequeue(); 
			
			// Add node's information to the string. 
			bftString += subtreeRoot.getData().toString() + " "; 
			
			// Add BinaryNode's children to the Queue if it has children. 
			if (subtreeRoot.getLeftNode() != null) {
				bftQueue.enqueue(subtreeRoot.getLeftNode()); 
			} 
			if (subtreeRoot.getRightNode() != null) {
				bftQueue.enqueue(subtreeRoot.getRightNode()); 
			} 
		} 
		return bftString.trim(); 
	} // end method  breadthFirstTraverse
	
	
	public String preOrderTraverse(){
		return root.preOrderTraverse().trim();				
	}

	public String postOrderTraverse(){
		return root.postOrderTraverse().trim();
	}

	public String inOrderTraverse(){
		return root.inOrderTraverse().trim();
	}
	
	class BinaryNode<T>{
		private T data = null;
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;

		public BinaryNode(T data){
			this.data = data;			
		}

		public String toString(){
			return "" + data;
		}

		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}

		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}

		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}

		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}

		public T getData(){
			return this.data;
		}

		public void setData(T data){
			this.data = data;
		}

		public int height(){
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;

			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}

			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;

			return maxHeight + 1 ;
		}

		public boolean isLeaf(){
			return (leftNode == null && rightNode == null);
		}


		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			
			stringBuffer.append(" " + data);
			
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}

		
		/**
		 * Produces a post-order traversal of the entire binary tree. 
		 *
		 * @return	a string representing a post-order traversal of the tree. 
		 */ 
		public String postOrderTraverse(){	
			// Initialize a StringBuilder to construct the string. 
			StringBuilder stringBuffer = new StringBuilder();
			
			// Add the left child. 
			if (leftNode != null) {
				stringBuffer.append(leftNode.postOrderTraverse()); 
			} 
			
			// Add the right child. 
			if (rightNode != null) {
				stringBuffer.append(rightNode.postOrderTraverse()); 
			}
			
			// Add the node's data. 
			stringBuffer.append(" " + data); 
			
			return stringBuffer.toString();
		} // end method postOrderTraverse. 

		
		/**
		 * Produces a post-order traversal of the entire binary tree. 
		 *
		 * @return	a string representing a post-order traversal of the tree. 
		 */ 
		public String inOrderTraverse(){ 
			// Initialize a StringBuilder to construct the string. 
			StringBuilder stringBuffer = new StringBuilder(); 
			
			// Add the left child. 
			if (leftNode != null) {
				stringBuffer.append(leftNode.inOrderTraverse()); 
			}
			
			// Add the current node. 
			stringBuffer.append(" " + data);
			
			// Add the right child. 
			if (rightNode != null) {
				stringBuffer.append(rightNode.inOrderTraverse()); 
			} 
			
			return stringBuffer.toString();
		} // end method inOrderTraverse
	}
}