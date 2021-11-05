import LivingThing.Node;

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
	 * Add a node value to the BST(Sorted Storage)
	 * @param	t		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(Node t) {
		if(t == null) {
			numberOfNulls++;
			return true;
		}
		return super.add(t);
	}
	
	/**
	 * Finds if a node exists in 
	 * Sorted Storage set with nulls
	 * @param 	node		node to be found
	 * @return	boolean
	 */
	public boolean find(Node t) {
		if(t == null) return numberOfNulls > 0;
		return super.find(t);
	}
	
	/**
	 * If the BST includes null values
	 * @return boolean
	 */
	@Override
	public boolean includesNull() {
		return numberOfNulls > 0;
	}
	
	/**
	 * Delete the string node from the BST, in sorted storage set with nulls
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	public boolean delete(Node node) {
		if(node == null) {
			if(numberOfNulls > 0) {
				numberOfNulls--;
				return true;
			}
			return false;
		}
		return super.delete(node);
	}
}
