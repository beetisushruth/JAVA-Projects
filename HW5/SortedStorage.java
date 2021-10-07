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
public class SortedStorage {
	
	public BSTNode rootNode;	// root node of BST
	
	/**
	 * Add a node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(String value) {
		if(value != null) {
			if(rootNode == null) {
				rootNode = new BSTNode(value);
			} else {
				traverseAndAddNode(value, rootNode);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Traverse and add a new node (utility function)
	 * @param 	value			value of a node to be added
	 * @param 	currentNode		current node in traverse
	 * @return					root node
	 */
	protected BSTNode traverseAndAddNode(String value, BSTNode currentNode) {
		if(currentNode == null) {
			return new BSTNode(value);
		}
		// if current node value is greater than the value, traverse left
		
		if(isGreaterThanEqual(currentNode.value, value)) {
			currentNode.leftNode = traverseAndAddNode(value, 
					currentNode.leftNode);
		// if current node value is less than the value, traverse right
		} else {
			currentNode.rightNode = traverseAndAddNode(value, 
					currentNode.rightNode);
		} 
		return currentNode;
	}
	
	/**
	 * Is a valueA string greater than or equals to valueB string
	 * @param 	valueA
	 * @param 	valueB
	 * @return	boolean
	 */
	protected boolean isGreaterThanEqual(String valueA, String valueB) {
		int i = 0, j = 0;
		while(i < valueA.length() && j < valueB.length()) {
			char charA = valueA.charAt(i);
			char charB = valueB.charAt(j);
			if(charA > charB) return true;
			else if(charA < charB) return false;
			i++;
			j++;
		}
		return valueA.length() >= valueB.length();
	}

	/**
	 * Finds if a value exists in Sorted Storage
	 * @param 	value		value to be found
	 * @return				boolean
	 */
	public boolean find(String value) {
		if(value !=null) {
			return traverseAndFindNode(value, rootNode);
		} 
		return false;
	}
	
	/**
	 * Utility method to traverse and find a node
	 * @param 	value			value to be found
	 * @param 	currentNode		current node in traverse
	 * @return					boolean
	 */
	protected boolean traverseAndFindNode(String value, BSTNode currentNode) {
		if(currentNode == null) return false;
		if(currentNode.value.equals(value)) {
			return true;
		}
		// if current node value is greater than the value, traverse left
		if(isGreaterThanEqual(currentNode.value, value)) {
			return traverseAndFindNode(value, currentNode.leftNode);
		}
		// if current node value is greater than the value, traverse right
		return traverseAndFindNode(value, currentNode.rightNode);
	}

	/**
	 * Delete the node from the BST
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	public boolean delete(String value) {
		// if string is numeric parse it and delete the node
		if(value != null) {
			return traverseAndDeleteNode(rootNode, value);
		} 
		return false;
	}
	
	/**
	 * Utility function to traverse and delete a node
	 * @param 	root		root node of BST
	 * @param 	value		value of the node to be deleted
	 * @return	boolean 	if the node has been deleted successfully	
	 */
	protected boolean traverseAndDeleteNode(BSTNode root, String value) {
		BSTNode parentNode = null;
		BSTNode currentNode = root;
		// searching for the node
		while(currentNode != null && currentNode.value != value)
        {
			parentNode = currentNode;
            if (isGreaterThanEqual(currentNode.value, value)) {
            	currentNode = currentNode.leftNode;
            } else {
            	currentNode = currentNode.rightNode;
            }
        }
		// if the node is not found return false
		if(currentNode == null) return false;
		// if the node is found and it doesn't have any children
		if(currentNode.leftNode == null && currentNode.rightNode == null) {
			if(parentNode != null) {
                if(parentNode.leftNode == currentNode) {
                	parentNode.leftNode = null;
                } else {
                	parentNode.rightNode = null;
                }
            } else {
            	rootNode = null;
            }
		// if the node has one child to the right or left
        } else if(currentNode.leftNode == null || 
        		currentNode.rightNode == null) {
        	BSTNode nodeToSet = (currentNode.leftNode == null) ? 
        			currentNode.rightNode : 
        		currentNode.leftNode;
        	if(currentNode == rootNode) {
        		rootNode = nodeToSet;
        	} else if(parentNode.leftNode == currentNode) {
        		parentNode.leftNode = nodeToSet;
        	} else {
        		parentNode.rightNode = nodeToSet;
        	}
        // if the node has both the children
        } else {
        	// find the minimum node in the right subtree
        	BSTNode minimumNode = findMinimumInRightSubTree(
        			currentNode.rightNode);
        	String minimumNodeValue = minimumNode.value;
        	// recursively delete the minimum node
        	traverseAndDeleteNode(rootNode, minimumNodeValue);
            currentNode.value = minimumNodeValue;
        }
		return true;
	}
	
	/**
	 * Find minimum in the right sub tree
	 * @param currentNode	current node in traverse
	 * @return				minimum value node
	 */
	protected BSTNode findMinimumInRightSubTree(BSTNode currentNode) {
		// find the minimum node in the left subtree
		while (currentNode.leftNode != null) {
			currentNode = currentNode.leftNode;
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
	protected String traverseAndPrintBST(BSTNode node, 
			String sortedStorageString) {
		if(node == null) {
			return "null";
		}
		sortedStorageString += "(l: "+traverseAndPrintBST(node.leftNode, 
				sortedStorageString) + 
				" "+node.value+" r: "+traverseAndPrintBST(node.rightNode, 
						sortedStorageString)+" )";
		return sortedStorageString;
	}
	
	/**
	 * Print BST in tree format
	 * @param root		root node
	 * @param space		spaces to be left for each node
	 */
	public void printAsATree(BSTNode root, int space) {
	    if (root == null) return;
	    space += 5;
	    printAsATree(root.rightNode, space);
	    System.out.print("\n");
	    for (int i = 5; i < space; i++)
	        System.out.print(" ");
	    System.out.print(root.value + "\n");
	    printAsATree(root.leftNode, space);
	}
}
