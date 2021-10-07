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
	 * Add a string node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	@Override
	public boolean add(String value) {
		Node nodeToAdd = new StringNode(value);
		if(rootNode == null) {
			rootNode = nodeToAdd;
		} else {
			traverseAndAddNode(nodeToAdd, rootNode);
		}
		return true;
	}
	
	/**
	 * Add a long node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	@Override
	public boolean add(long value) {
		Node nodeToAdd = new LongNode(value);
		if(rootNode == null) {
			rootNode = nodeToAdd;
		} else {
			traverseAndAddNode(nodeToAdd, rootNode);
		}
		return true;
	}
	
	/**
	 * Add a integer node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	@Override
	public boolean add(int value) {
		Node nodeToAdd = new IntegerNode(value);
		if(rootNode == null) {
			rootNode = nodeToAdd;
		} else {
			traverseAndAddNode(nodeToAdd, rootNode);
		}
		return true;
	}
	
	/**
	 * Traverse and add a new node (utility function)
	 * @param 	value			value of a node to be added
	 * @param 	currentNode		current node in traverse
	 * @return					root node
	 */
	private Node traverseAndAddNode(Node nodeToAdd, Node currentNode) {
		if(currentNode == null) {
			return nodeToAdd;
		}
		// if current node value is greater than the value, traverse left
		if(currentNode.isGreaterThan(nodeToAdd) || currentNode.isEqual(nodeToAdd)) {
			currentNode.left = traverseAndAddNode(nodeToAdd, 
					currentNode.left);
		// if current node value is less than the value, traverse right
		} else {
			currentNode.right = traverseAndAddNode(nodeToAdd, 
					currentNode.right);
		} 
		return currentNode;
	}
	
	/**
	 * Finds if a string node value exists in Sorted Storage
	 * @param 	value		value to be found
	 * @return				boolean
	 */
	@Override
	public boolean find(String value) {
		Node nodeToFind = new StringNode(value);
		return traverseAndFindNode(nodeToFind, rootNode);
	}
	
	/**
	 * Finds if a string value exists in Sorted Storage
	 * @param 	value		value to be found
	 * @return				boolean
	 */
	@Override
	public boolean find(long value) {
		Node nodeToFind = new LongNode(value);
		return traverseAndFindNode(nodeToFind, rootNode);
	}
	
	/**
	 * Finds if a integer node value exists in Sorted Storage
	 * @param 	value		value to be found
	 * @return				boolean
	 */
	@Override
	public boolean find(int value) {
		Node nodeToFind = new IntegerNode(value);
		return traverseAndFindNode(nodeToFind, rootNode);
	}
	
	/**
	 * Utility method to traverse and find a node
	 * @param 	value			value to be found
	 * @param 	currentNode		current node in traverse
	 * @return					boolean
	 */
	private boolean traverseAndFindNode(Node nodeToFind, Node currentNode) {
		if(currentNode == null) return false;
		if(nodeToFind.isEqual(currentNode)) {
			return true;
		}
		// if current node value is greater than the value, traverse left
		if(currentNode.isGreaterThan(nodeToFind)) {
			return traverseAndFindNode(nodeToFind, currentNode.left);
		}
		// if current node value is greater than the value, traverse right
		return traverseAndFindNode(nodeToFind, currentNode.right);
	}

	/**
	 * Delete the String node from the BST
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	@Override
	public boolean delete(String value) {
		// if string is numeric parse it and delete the node
		return traverseAndDeleteNode(rootNode, new StringNode(value));
	}
	
	/**
	 * Delete the long node from the BST
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	@Override
	public boolean delete(long value) {
		// if string is numeric parse it and delete the node
		return traverseAndDeleteNode(rootNode, new LongNode(value));
	}
	
	/**
	 * Delete the integer node from the BST
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	@Override
	public boolean delete(int value) {
		// if string is numeric parse it and delete the node
		return traverseAndDeleteNode(rootNode, new IntegerNode(value));
	}
	
	/**
	 * Utility function to traverse and delete a node
	 * @param 	root		root node of BST
	 * @param 	value		value of the node to be deleted
	 * @return	boolean 	if the node has been deleted successfully	
	 */
	protected boolean traverseAndDeleteNode(Node root, Node nodeToDelete) {
		Node parentNode = null;
		Node currentNode = root;
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
	 * Overrided toString method
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
