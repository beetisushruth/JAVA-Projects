/*
 * Compare.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Compare class for comparing node objects
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public interface Compare{
	
	/**
	 * Is greater than the current node
	 * @param 	Node n
	 * @return	boolean
	 */
	abstract public boolean isGreaterThan(Node n);
	
	/**
	 * Is equal than the current node
	 * @param	Node n
	 * @return	boolean
	 */
	abstract public boolean isEqual(Node n);
	
	/**
	 * Is less than the current node
	 * @param	Node n
	 * @return	boolean	
	 */
	abstract public boolean isLessThan(Node n);
}
