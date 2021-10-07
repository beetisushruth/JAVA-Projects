/*
 * SortedStorageSetWithNulls.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Sorted Storage Set class which holds a BST Structure with nulls
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class SortedStorageSetWithNulls extends SortedStorageSet{
	
	// number of nulls in bst
	public int numberOfNulls;
	
	/**
	 * Add a node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(String value) {
		if(value == null) {
			numberOfNulls++;
			return true;
		}
		if(!find(value)) {
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
	 * Finds if a value exists in Sorted Storage set with nulls
	 * @param 	value		value to be found
	 * @return				boolean
	 */
	public boolean find(String value) {
		if(value == null) return numberOfNulls > 0;
		return traverseAndFindNode(value, rootNode);
	}
	
	/**
	 * Delete the node from the BST, in sorted storage set with nulls
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	public boolean delete(String value) {
		if(value == null) {
			if(numberOfNulls > 0) {
				numberOfNulls--;
				return true;
			}
			return false;
		}
		return traverseAndDeleteNode(rootNode, value);
	}
}
