/*
 * SortedStorageSet.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Sorted Storage Set class which holds a BST Structure
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class SortedStorageSet extends SortedStorage{
	
	/**
	 * Add a String node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	@Override
	public boolean add(String value) {
		if(!find(value)) {
			return super.add(value);
		}
		return false;
	}
	
	/**
	 * Add a Long node to the BST(Sorted Storage)
	 * @param	value	value to be added
	 * @return 	boolean	if a new node is added	
	 */
	@Override
	public boolean add(long value) {
		if(!find(value)) {
			return super.add(value);
		}
		return false;
	}
	
	/**
	 * Add a integer node to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	@Override
	public boolean add(int value) {
		if(!find(value)) {
			return super.add(value);
		}
		return false;
	}
}
