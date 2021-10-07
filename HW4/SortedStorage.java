/*
 * SortedStorage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.regex.Pattern;


/**
 * Sorted Storage class which holds a BST Structure
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class SortedStorage {
	
	private BSTNode rootNode;	// root node of BST
	private int numberOfNulls;	// number of nulls 
	
	/**
	 * Is a string numeric
	 * @param 	value		value to checked
	 * @return	boolean		if a string is numeric
	 */
	public boolean isNumeric(String value) {
		Pattern pattern = Pattern.compile("\\d+");
		return pattern.matcher(value).matches();
	}
	
	/**
	 * Add a node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean addNode(String value) {
		if(value == null) {
			numberOfNulls++;
			return true;
		}
		if(isNumeric(value)) {
			int parsedValue = Integer.parseInt(value);
			// if the root node is null, add root node
			if(rootNode == null) {
				rootNode = new BSTNode(parsedValue);
				return true;
			}
			// if the node is not present, add node.
			if(!findNode(value)) {
				traverseAndAddNode(parsedValue, rootNode);
				return true;
			}
		} else {
			System.out.println("Invalid input for a BST Node");
		}
		return false;
	}
	
	/**
	 * Traverse and add a new node (utility function)
	 * @param 	value			value of a node to be added
	 * @param 	currentNode		current node in traverse
	 * @return					root node
	 */
	private BSTNode traverseAndAddNode(int value, BSTNode currentNode) {
		if(currentNode == null) {
			return new BSTNode(value);
		}
		// if current node value is greater than the value, traverse left
		if(currentNode.value > value) {
			currentNode.leftNode = traverseAndAddNode(value, 
					currentNode.leftNode);
		// if current node value is less than the value, traverse right
		} else if(currentNode.value < value) {
			currentNode.rightNode = traverseAndAddNode(value, 
					currentNode.rightNode);
		} 
		return currentNode;
	}
	
	/**
	 * Finds if a value exists in Sorted Storage
	 * @param 	value		value to be found
	 * @return				boolean
	 */
	public boolean findNode(String value) {
		if(value == null) return (numberOfNulls > 0);
		// if string is numeric parse it and find the node
		if(isNumeric(value)) {
			int parsedValue = Integer.parseInt(value);
			return traverseAndFindNode(parsedValue, rootNode);
		} else {
			System.out.println("Invalid input for a BST Node!");
		}
		return false;
	}
	
	/**
	 * Utility method to traverse and find a node
	 * @param 	value			value to be found
	 * @param 	currentNode		current node in traverse
	 * @return					boolean
	 */
	private boolean traverseAndFindNode(int value, BSTNode currentNode) {
		if(currentNode == null) return false;
		if(currentNode.value == value) {
			return true;
		}
		// if current node value is greater than the value, traverse left
		if(currentNode.value > value) {
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
	public boolean deleteNode(String value) {
		if(value == null ) {
			if(numberOfNulls > 0) {
				numberOfNulls--;
				return true;
			}
			return false;
		}
		// if string is numeric parse it and delete the node
		if(isNumeric(value)) {
			if(!findNode(value)) return false;
			int parsedValue = Integer.parseInt(value);
			return traverseAndDeleteNode(rootNode, parsedValue);
		} else {
			System.out.println("Invalid input for the BST");
		}
		return false;
	}
	
	/**
	 * Utility function to traverse and delete a node
	 * @param 	root		root node of BST
	 * @param 	value		value of the node to be deleted
	 * @return	boolean 	if the node has been deleted successfully	
	 */
	private boolean traverseAndDeleteNode(BSTNode root, int value) {
		BSTNode parentNode = null;
		BSTNode currentNode = root;
		// searching for the node
		while(currentNode != null && currentNode.value != value)
        {
			parentNode = currentNode;
            if (value < currentNode.value) {
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
        	int minimumNodeValue = minimumNode.value;
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
	public BSTNode findMinimumInRightSubTree(BSTNode currentNode) {
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
	private String traverseAndPrintBST(BSTNode node, 
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
