/*
 * SortedStorageSet.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
 import LivingThing.Node;

/**
 * Sorted Storage Set class which holds a BST Structure
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class SortedStorageSet extends SortedStorage{
	
	/**
	 * Add a node to the BST(Sorted Storage)
	 * @param	t			node to be added
	 * @return 	boolean		if a new node is added	
	 */
	@Override
	public boolean add(Node t) {
		if(!find(t)) {
			return super.add(t);
		}
		return false;
	}
}
