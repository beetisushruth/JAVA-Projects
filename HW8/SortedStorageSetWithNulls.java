/*
 * SortedStorageSetWithNulls.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * SortedStorageSetWithNulls Interface
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class SortedStorageSetWithNulls<E extends Comparable<E>> extends SortedStorageSet<E> {
	
	public int numberOfNulls;
	
	/**
	 * Add a node value to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public void add(E value) {
		if(value == null) {
			numberOfNulls++;
		}
		else {
			super.add(value);
		}
	}
	
	/**
	 * Finds if a node exists in 
	 * Sorted Storage set with nulls
	 * @param 	value		value to be found
	 * @return	boolean
	 */
	public boolean find(E value) {
		if(value == null) return numberOfNulls > 0;
		return super.find(value);
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
	 * Delete the node from the BST, in sorted storage set with nulls
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	@Override
	public boolean delete(E value) {
		if(value == null) {
			if(numberOfNulls > 0) {
				numberOfNulls--;
				return true;
			}
			return false;
		}
		return super.delete(value);
	}
}
