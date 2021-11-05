/*
 * SortedStorageSet.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * SortedStorageSet Interface
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class SortedStorageSet<E extends Comparable<E>> extends SortedStorage<E>{
	
	/**
	 * Add a node to the BST(Sorted Storage)
	 * @param	value		value of new node to be added
	 */
	@Override
	public void add(E value) {
		if(!find(value)) {
			super.add(value);
		}
	}
}
