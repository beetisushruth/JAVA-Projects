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
	 * Add a string node value to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(String value) {
		if(value == null) {
			numberOfNulls++;
			return true;
		}
		return super.add(value);
	}
	
	/**
	 * Add a long node value to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(long value) {
		return super.add(value);
	}
	
	/**
	 * Add a integer node value to the BST(Sorted Storage)
	 * @param	value		value to be added
	 * @return 	boolean		if a new node is added	
	 */
	public boolean add(int value) {
		return super.add(value);
	}
	
	/**
	 * Finds if a string node value exists in 
	 * Sorted Storage set with nulls
	 * @param 	value		value to be found
	 * @return	boolean
	 */
	public boolean find(String value) {
		if(value == null) return numberOfNulls > 0;
		return super.find(value);
	}
	
	/**
	 * Finds if a long node value exists in Sorted Storage set with nulls
	 * @param 	value		value to be found
	 * @return	boolean
	 */
	public boolean find(long value) {
		return super.find(value);
	}
	
	/**
	 * Finds if a integer node 
	 * value exists in Sorted Storage set with nulls
	 * @param 	value		value to be found
	 * @return	boolean
	 */
	public boolean find(int value) {
		return super.find(value);
	}
	
	/**
	 * Delete the string node from the BST, in sorted storage set with nulls
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
		return super.delete(value);
	}
	
	/**
	 * Delete the long node from the BST, in sorted storage set with nulls
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	public boolean delete(long value) {
		return super.delete(value);
	}
	
	/**
	 * Delete the integer node from the BST, in sorted storage set with nulls
	 * @param	value		value of the node to be deleted
	 * @return	boolean		if the value has been deleted
	 */
	public boolean delete(int value) {
		return super.delete(value);
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
	 * Add method to facilitate test class
	 * @param comparable c
	 * @return boolean
	 */
	public boolean add(Comparable c) {
		if(c instanceof Integer) {
			return this.add((int) c);
		} else if(c instanceof Long) {
			return this.add((long) c);
		}
		return this.add((String) c);
	}
	
	/**
	 * Find method to facilitate test class
	 * @param comparable c
	 * @return boolean
	 */
	public boolean find(Comparable c) {
		if(c instanceof Integer) {
			return find((int) c);
		} else if(c instanceof Long) {
			return find((long) c);
		}
		return find((String) c);
	}
	
	/**
	 * Delete method to facilitate test class
	 * @param comparable c
	 * @return boolean
	 */
	public boolean delete(Comparable c) {
		if(c instanceof Integer) {
			return delete((int) c);
		} else if(c instanceof Long) {
			return delete((long) c);
		}
		return delete((String) c);
	}
}
