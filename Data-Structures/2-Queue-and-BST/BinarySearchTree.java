/**
 * A basic implementation of a binary search tree. 
 *
 * Assignment: Assignment 2 
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: April 4, 2016
 * Class: BinarySearchTree
 */ 

/**
 * A binary search tree with methods insert, remove, and contains 
 * implemented.
 */ 
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	/**
	 * Default constructor. Simply calls superclass constructor. 
	 */ 
	public BinarySearchTree() {
		super(); 
	} // end constructor
	
	
	/**
	 * Constructor used for testing. Calls appropriate BinaryTree constructor.
	 *
	 * @param	seq			an array of objects to be added to the tree
	 */ 
	public BinarySearchTree(T[] seq) {
		super(seq); 
	} // end first overload constructor
	
	
	/**
	 * Second constructor used for testing. Calls appropriate BinaryTree
	 * constructor. 
	 * 
	 * @param	seq			an array of objects to be added to the tree
	 * @param	nullSymbol	the symbol used in the array to represent a null node
	 */ 
	public BinarySearchTree(T[] seq, T nullSymbol) {
		super(seq, nullSymbol); 
	} // end second overload constructor
	
	
	/**
	 * Adds the given value to the BinarySearchTree. Calls recursive helper. 
	 *
	 * @param	value		the value to be inserted
	 */ 
	public void insert(T value) {
		// Tree was empty, so root needs to be added. 
		if (this.root == null) {
			this.root = new BinaryNode<T>(value); 
		} 
		
		// Tree already contained Nodes. 
		else {
			this.insert(value, this.root); 
		} 
	} // end method insert
	 
	 
	/**
	 * Recursive helper insert method. Takes a value and a Node and inserts
	 * the value into the appropriate spot in the tree based on the given
	 * Node. 
	 * 
	 * @param	value		the value to be inserted
	 * @param	currentNode	the Node the value is being compared against
	 */ 
	private void insert(T value, BinaryNode<T> currentNode) {
		// New value is less than currentNode's value. 
		if (value.compareTo(currentNode.getData()) < 0) {
			
			// No left child, so create new Node and make it left child. 
			if (currentNode.getLeftNode() == null) {
				currentNode.setLeftNode(new BinaryNode<T>(value)); 
			} 
			
			// Left child exists, so make recursive call on it. 
			else {
				insert(value, currentNode.getLeftNode()); 
			} 
		}
			
		// New value is greater than currentNode's value. 
		else if (value.compareTo(currentNode.getData()) > 0) {
			
			// No right child, so create new Node and make it right child. 
			if (currentNode.getRightNode() == null) {
				currentNode.setRightNode(new BinaryNode<T>(value)); 
			} 
			
			// Right child exists, so make recursive call on it. 
			else {
				insert(value, currentNode.getRightNode()); 
			} 
		} 
		// Duplicates are ignored, so no else statement. 
	} // end overload method insert

	
	/**
	 * Removes the given value from the BinarySearchTree. Merely calls
	 * recursive helper function. 
	 * 
	 * @param	value		the value to be removed from the tree
	 */ 
	public void remove(T value) {
		this.remove(value, this.root, null); 
	} // end method remove
	
	
	/**
	 * Recursive helper method for remove. Compares the value with a given Node
	 * and if the value is contained in the node, removes it. Recurses or calls
	 * helper methods to handle each of the three possible remove cases. 
	 *
	 * @param	value		the value to be removed from the tree 
	 * @param	currentNode	the Node whose data is being compared to value 
	 * @param	parent		parent of the currentNode
	 */ 
	private void remove(T value, BinaryNode<T> currentNode, BinaryNode<T> parent) { 
		// Value not found in tree; return. 
		if (currentNode == null) {
			return;
		} 
		
		// Still searching for or found value. 
		else {
			
			// Value less than currentNode's data; check left child. 
			if (value.compareTo(currentNode.getData()) < 0) { 
				this.remove(value, currentNode.getLeftNode(), currentNode); 
			} 
			
			// Value greater than currentNode's data; check right child. 
			else if (value.compareTo(currentNode.getData()) > 0) {
				this.remove(value, currentNode.getRightNode(), currentNode); 
			} 
			
			// Value matches currentNode's data; handle three remove cases. 
			else {
				handleRemoveCases(currentNode, parent); 
			} 
		}
	} // end overload method remove 
	
	
	/**
	 * Parses the different remove cases and calls the appropriate methods to
	 * manage each of them. When the root is being removed, it is a special 
	 * case, because the reference in this.root may have to be reset. Hence, 
	 * the case where nodeToRemove == this.root is handled as distinct. 
	 *
	 * @param	nodeToRemove	the Node that needs to be removed
	 * @param	parent			the parent of the Node being removed
	 */ 
	private void handleRemoveCases(BinaryNode<T> nodeToRemove, BinaryNode<T> parent) {
		// Node to remove is this.root. 
		if (nodeToRemove == this.root) {
			removeRoot(); 
		}
		
		// Non-root node has two children. 
		else if ((nodeToRemove.getLeftNode() != null) && (nodeToRemove.getRightNode() != null)) {
			removeNodeTwoChildren(nodeToRemove); 
		} 
		
		// Non-root Node to remove has one child or no children. 
		else {
			removeNodeOneOrNoChild(nodeToRemove, parent); 
		}
	} // end method handleRemoveCases
	
	
	/**
	 * Handles the remove case in which a removed node has two children. This
	 * implementation replaces the removed node's data with the data of the 
	 * maximum node of the left subtree. That maximum node is then removed via
	 * a further recursive call to remove. 
	 * 
	 * @param	nodeToRemove	the node being removed
	 * @require	(nodeToRemove.getLeftNode() != null) && (nodeToRemove.getRightNode() != null)
	 */ 
	private void removeNodeTwoChildren(BinaryNode<T> nodeToRemove) {
		// Get a reference to the maximum node of the left subtree. 
		BinaryNode<T> leftSubtreeMax = findMax(nodeToRemove.getLeftNode()); 
		BinaryNode<T> leftMaxParent = getParent(leftSubtreeMax); 
		
		// Replace nodeToRemove's data with leftSubtreeMax's data. 
		nodeToRemove.setData(leftSubtreeMax.getData()); 
		
		// Remove the leftSubtreeMax node. 
		remove(leftSubtreeMax.getData(), leftSubtreeMax, leftMaxParent); 
	} // end method removeNodeTwoChildren
	
	
	/**
	 * Removes the root of the BinarySearchTree.
	 */ 
	private void removeRoot() {
		// Root has two children, just call appropriate method. 
		if ((this.root.getLeftNode() != null) && (this.root.getRightNode() != null)) {
			removeNodeTwoChildren(this.root); 
		}
		
		// Root has a left child to be replaced with. 
		else if (this.root.getLeftNode() != null) {
			this.root = this.root.getLeftNode(); 
		}
		
		// Root has a right child to be replaced with. 
		else if (this.root.getRightNode() != null) {
			this.root = this.root.getRightNode(); 
		}
		
		// Root has no children to be replaced with. 
		else {
			this.root = null; 
		} 
	} // end method removeRoot
	
	
	/**
	 * Handles the remove case in which a removed node has only child. The 
	 * child Node is moved up to replace the removed node. 
	 * 
	 * @param	nodeToRemove	the node being removed 
	 * @param	parent			the parent of the node being removed
	 * @require	!((nodeToRemove.getLeftNode() != null) && (nodeToRemove.getRightNode() != null))
	 */ 
	private void removeNodeOneOrNoChild(BinaryNode<T> nodeToRemove, BinaryNode<T> parent) {
		// Node being removed was parent's left child
		if (nodeToRemove == parent.getLeftNode()) {
			
			// Node being removed had a left child to move. 
			if (nodeToRemove.getLeftNode() != null) {
				parent.setLeftNode(nodeToRemove.getLeftNode()); 
			}
			
			// Node being removed had a right child to move. 
			else {
				parent.setLeftNode(nodeToRemove.getRightNode()); 
			} 
		}
		
		// Node being removed was parent's right child. 
		else if (nodeToRemove == parent.getRightNode()) { 
		
			// Node being removed had a left child to move. 
			if (nodeToRemove.getLeftNode() != null) {					
				parent.setRightNode(nodeToRemove.getLeftNode()); 
			}
			
			// Node being removed had a right child to move. 
			else {
				parent.setRightNode(nodeToRemove.getRightNode()); 
			} 
		}
		
		// Node is a leaf, so simply set parent's appropriate reference to null. 
		else {
			if (nodeToRemove == parent.getLeftNode()) {
				parent.setLeftNode(null); 
			}
			else {
				parent.setRightNode(null); 
			}
		}
	} // end method removeNodeOneOrNoChild
	
	
	/**
	 * Returns a reference to the provided Node's parent node. 
	 * @param	node	the Node whose parent is sought
	 * @return	node's parent Node
	 */ 
	private BinaryNode<T> getParent(BinaryNode<T> node) {
		BinaryNode<T> currentNode = this.root; 
		BinaryNode<T> parentNode = this.root; 
		boolean nodeFound = false; 
		
		// Root node has no parent. Check non-root nodes. 
		if (node != this.root) {
			
			// Iterate through tree to find node, storing references to parents. 
			while (!nodeFound) {

				// Node will be in left subtree. 
				if (node.getData().compareTo(currentNode.getData()) < 0) {
					parentNode = currentNode; 
					currentNode = currentNode.getLeftNode(); 
				}
				// Node will be in right subtree. 
				else if (node.getData().compareTo(currentNode.getData()) > 0) {
					parentNode = currentNode; 
					currentNode = currentNode.getRightNode(); 
				}
				// Node currently under consideration contains the value. 
				else {
					nodeFound = true; 
				}
			} // end while 
		} // end if 
		
		return parentNode; 
	} // end method getParent 
	
	
	/** 
	 * Returns true if the given value is contained in the tree and
	 * false otherwise. Calls recursive helper method. 
	 * 
	 * @param	value		the searched for value
	 * @return	whether the value is contained in the tree
	 */ 
	public boolean contains(T value) {
		return contains(value, this.root); 
	} // end method contains
	
	
	/**
	 * Recursive helper method for contains. Returns true if value is
	 * contained in the given Node. Otherwise, makes a recursive call
	 * to check the value contained in the appropriate child Node. 
	 * 
	 * @param	value		the searched for value
	 * @param	currentNode	the Node whose data is being compared to value
	 * @return	whether the value is contained in currentNode
	 */ 
	private boolean contains(T value, BinaryNode<T> currentNode) {
		// Null node means leaf was searched without finding key. 
		if (currentNode == null) {
			return false; 
		} 
		
		// Node isn't null, so check its data against value. 
		else { 
			// currentNode's data is greater than value, repeat check with left child. 
			if (value.compareTo(currentNode.getData()) < 0) {
				return this.contains(value, currentNode.getLeftNode()); 
			} 
			
			// currentNode's data is less than value, repeat check with right child. 
			else if (value.compareTo(currentNode.getData()) > 0) {
				return this.contains(value, currentNode.getRightNode()); 
			} 
			
			// The value and currentNode's data match. 
			else {
				return true; 
			}
		}
	} // end overload method contains 
	
	
	/**
	 * As implemented here, a helper method for remove. Finds the maximum node of a 
	 * subtree whose root is the given Node. 
	 * 
	 * @param	subTreeRoot	the root of the subtree being inspected
	 * @require	subTreeRoot != null
	 * @return	the subTree if no greater Node can be found
	 */ 
	private BinaryNode<T> findMax(BinaryNode<T> subTreeRoot) {
		// There is a right child, so there is a higher value in the tree. 
		if (subTreeRoot.getRightNode() != null) {
			return this.findMax(subTreeRoot.getRightNode()); 
		} 
		
		// There is no right child, so this is the maximum Node in the tree. 
		else {
			return subTreeRoot; 
		} 
	} // end method findMax
} // end class BinarySearchTree