/*
 * Storage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Storage Interface
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public interface Storage<E> {
	
	/**
	 * Add the value in the storage
	 * @param value
	 */
	public void add(E value);
	
	/**
	 * Find the value in the storage
	 * @param value
	 * @return boolean
	 */
	boolean find(E x);
	
	/**
	 * Checks if storage has nulls
	 * @return boolean
	 */
	boolean includesNull();
	
	/**
	 * Delete the value from the storage
	 * @param value
	 * @return boolean
	 */
	boolean delete(E x); 
}
