/*
 * SortedStorage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Storage Interface
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class SortedStorage<E extends Comparable<E>> implements Storage<E>{
	
	private Node<E> rootNode;	// root node of BST
	
	/**
	 * Add the value to the storage
	 * @param value
	 */
	@Override
	public void add(E value) {
		Node<E> node = new Node<E>(value);
		if(rootNode == null) {
			rootNode = node;
		} else {
			traverseAndAddNode(node, rootNode);
		}
	}

	/**
	 * Traverse and add the node at right position
	 * @param node
	 * @param currentNode
	 * @return Node
	 */
	private Node<E> traverseAndAddNode(Node<E> node, Node<E> currentNode) {
		if(currentNode == null) {
			return node;
		}
		
		// if current node is greater than or equal to the node to add, traverse left
		if(currentNode.isGreaterThan(node) || currentNode.isEqual(node)) {
			currentNode.left = traverseAndAddNode(node, currentNode.left);
		// if current node is less than the node, traverse right
		} else {
			currentNode.right = traverseAndAddNode(node, 
					currentNode.right);
		} 
		return currentNode;
	}

	/**
	 * Find the value in the storage
	 * @return boolean
	 */
	@Override
	public boolean find(E value) {
		// TODO Auto-generated method stub
		Node<E> node = new Node<>(value);
		return traverseAndFindNode(node, rootNode);
	}
	
	/**
	 * Traverse and find node in the storage
	 * @param nodeToFind
	 * @param currentNode
	 * @return boolean
	 */
	private boolean traverseAndFindNode(Node<E> nodeToFind, Node<E> currentNode) {
		if(currentNode == null) return false;
		if(nodeToFind.isEqual(currentNode)) {
			return true;
		}
		// if current node is greater than the node to find, traverse left
		if(currentNode.isGreaterThan(nodeToFind)) {
			return traverseAndFindNode(nodeToFind, currentNode.left);
		}
		// if current node value is greater than the node to find, traverse right
		return traverseAndFindNode(nodeToFind, currentNode.right);
	}

	/**
	 * If storage includes null
	 * @return boolean
	 */
	@Override
	public boolean includesNull() {
		return false;
	}

	/**
	 * Delete the node of this value from storage
	 * @return boolean
	 */
	@Override
	public boolean delete(E value) {
		return traverseAndDeleteNode(rootNode, new Node<E>(value));
	}

	/**
	 * Utility function to traverse and delete a node
	 * @param 	root		 root node of BST
	 * @param 	nodeToDelete the node to be deleted
	 * @return	boolean 	 if the node has been deleted successfully	
	 */
	protected boolean traverseAndDeleteNode(Node<E> root, Node<E> nodeToDelete) {
		Node<E> parentNode = null;
		Node<E> currentNode = root;
		// searching for the node
		while(currentNode != null && !currentNode.isEqual(nodeToDelete))
        {
			parentNode = currentNode;
            if (currentNode.isGreaterThan(nodeToDelete) || 
            		currentNode.isEqual(nodeToDelete)) {
            	currentNode = currentNode.left;
            } else {
            	currentNode = currentNode.right;
            }
        }
		// if the node is not found return false
		if(currentNode == null) return false;
		// if the node is found and it doesn't have any children
		if(currentNode.left == null && currentNode.right == null) {
			if(parentNode != null) {
                if(parentNode.left == currentNode) {
                	parentNode.left = null;
                } else {
                	parentNode.right = null;
                }
            } else {
            	rootNode = null;
            }
		// if the node has one child to the right or left
        } else if(currentNode.left == null || 
        		currentNode.right == null) {
        	Node<E> nodeToSet = (currentNode.left == null) ? 
        			currentNode.right : 
        		currentNode.left;
        	if(currentNode == rootNode) {
        		rootNode = nodeToSet;
        	} else if(parentNode.left == currentNode) {
        		parentNode.left = nodeToSet;
        	} else {
        		parentNode.right = nodeToSet;
        	}
        // if the node has both the children
        } else {
        	// find the minimum node in the right subtree
        	Node<E> minimumNode = findMinimumInRightSubTree(
        			currentNode.right);
        	// recursively delete the minimum node
        	traverseAndDeleteNode(rootNode, minimumNode);
        	currentNode.setValue(minimumNode.getValue());
        }
		return true;
	}
	
	/**
	 * Find minimum in the right sub tree
	 * @param currentNode	current node in traverse
	 * @return				minimum value node
	 */
	private Node<E> findMinimumInRightSubTree(Node<E> currentNode) {
		// find the minimum node in the left subtree
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode;
	}
	
	
	/**
	 * Overridden toString method
	 */
	public String toString() {
		return traverseAndPrintBST(rootNode, "");
	}
	
	/**
	 * Traverse and print BST
	 * @param node					current node in traverse		
	 * @param sortedStorageString	string to output
	 * @return						String tree output
	 */
	protected String traverseAndPrintBST(Node<E> node, 
			String sortedStorageString) {
		if(node == null) {
			return "null";
		}
		sortedStorageString += "(l: "+traverseAndPrintBST(node.left, 
				sortedStorageString) + 
				" "+node+" r: "+traverseAndPrintBST(node.right, 
						sortedStorageString)+" )";
		return sortedStorageString;
	}
}
