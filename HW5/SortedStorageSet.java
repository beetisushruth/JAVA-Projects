/*
 * SortedStorage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Sorted Storage Set class which holds a BST Structure
 * without duplicates
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class SortedStorageSet extends SortedStorage {
	
	/**
	 * Add a node to the BST(Sorted Storage set) with no duplicates
	 * 
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(String value) {
		if(value != null && !find(value)) {
			if(rootNode == null) {
				rootNode = new BSTNode(value);
			} else {
				traverseAndAddNode(value, rootNode);
			}
			return true;
		}
		return false;
	}
}
