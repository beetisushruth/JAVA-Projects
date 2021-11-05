import LivingThing.Node;

/*
 * SortedStorage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Sorted Storage class which holds a BST Structure
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class SortedStorage implements Storage {
	
	private Node rootNode;	// root node of BST
	
	/**
	 * Add a node to the BST(Sorted Storage)
	 * @param	nodeToAdd		node to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(Node nodeToAdd) {
		if(rootNode == null) {
			rootNode = nodeToAdd;
		} else {
			traverseAndAddNode(nodeToAdd, rootNode);
		}
		return true;
	}
	
	/**
	 * Traverse and add a new node (utility function)
	 * @param 	nodeToAdd		node to be added
	 * @param 	currentNode		current node in traverse
	 * @return					currentNode
	 */
	private Node traverseAndAddNode(Node nodeToAdd, Node currentNode) {
		if(currentNode == null) {
			return nodeToAdd;
		}
		
		// if current node is greater than or equal to the node to add, traverse left
		if(currentNode.greaterThan(nodeToAdd) || currentNode.equalTo(nodeToAdd)) {
			currentNode.left = traverseAndAddNode(nodeToAdd, 
					currentNode.left);
		// if current node is less than the node, traverse right
		} else {
			currentNode.right = traverseAndAddNode(nodeToAdd, 
					currentNode.right);
		} 
		return currentNode;
	}
	
	/**
	 * Finds if a string node value exists in Sorted Storage
	 * @param 	nodeToFind	node to be found
	 * @return				boolean
	 */
	@Override
	public boolean find(Node nodeToFind) {
		return traverseAndFindNode(nodeToFind, rootNode);
	}
	
	/**
	 * Utility method to traverse and find a node
	 * @param 	nodeToFind		node to be found
	 * @param 	currentNode		current node in traverse
	 * @return					boolean
	 */
	private boolean traverseAndFindNode(Node nodeToFind, Node currentNode) {
		if(currentNode == null) return false;
		if(nodeToFind.equalTo(currentNode)) {
			return true;
		}
		// if current node is greater than the node to find, traverse left
		if(currentNode.greaterThan(nodeToFind)) {
			return traverseAndFindNode(nodeToFind, currentNode.left);
		}
		// if current node value is greater than the node to find, traverse right
		return traverseAndFindNode(nodeToFind, currentNode.right);
	}

	/**
	 * Delete the node from the BST
	 * @param	nodeToDelete		node to be deleted
	 * @return	boolean			if the node has been deleted
	 */
	@Override
	public boolean delete(Node nodeToDelete) {
		// if string is numeric parse it and delete the node
		return traverseAndDeleteNode(rootNode, nodeToDelete);
	}
	
	/**
	 * Utility function to traverse and delete a node
	 * @param 	root		root node of BST
	 * @param 	nodeToDelete	the node to be deleted
	 * @return	boolean 	if the node has been deleted successfully	
	 */
	protected boolean traverseAndDeleteNode(Node root, Node nodeToDelete) {
		Node parentNode = null;
		Node currentNode = root;
		// searching for the node
		while(currentNode != null && !currentNode.equalTo(nodeToDelete))
        {
			parentNode = currentNode;
            if (currentNode.greaterThan(nodeToDelete) || 
            		currentNode.equalTo(nodeToDelete)) {
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
        	Node nodeToSet = (currentNode.left == null) ? 
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
        	Node minimumNode = findMinimumInRightSubTree(
        			currentNode.right);
        	// recursively delete the minimum node
        	traverseAndDeleteNode(rootNode, minimumNode);
        	currentNode.setValue(minimumNode);
        }
		return true;
	}
	
	/**
	 * If the BST includes null value
	 * @return boolean
	 */
	@Override
	public boolean includesNull() {
		return false;
	}
	
	/**
	 * Find minimum in the right sub tree
	 * @param currentNode	current node in traverse
	 * @return				minimum value node
	 */
	private Node findMinimumInRightSubTree(Node currentNode) {
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
	protected String traverseAndPrintBST(Node node, 
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
	
	/**
	 * Print BST in tree format
	 * @param 	root	root node
	 * @param 	space	spaces to be left for each node
	 */
	protected void printAsATree(Node root, int space) {
	    if (root == null) return;
	    space += 5;
	    printAsATree(root.right, space);
	    System.out.print("\n");
	    for (int i = 5; i < space; i++)
	        System.out.print(" ");
	    System.out.print(root + "\n");
	    printAsATree(root.left, space);
	}
}
