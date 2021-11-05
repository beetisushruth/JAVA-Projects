package LivingThing;

/* Node.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Node abstract class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public abstract class Node{
	
	// left node
	public Node left;
	// right node
	public Node right;

	/**
	 * Abstract method returns true if the weight of node is greater than the node 
	 * referred to by the argument.
	 *
	 * @param    node   	node to compare
	 * @return   true 		if the this weight of node is greater than the 
	 *           			node argument.
	 */
	
	public abstract boolean greaterThan(Node node);
	
	/**
	 * Abstract method returns true if the weight of node is less than the node 
	 * referred to by the argument.
	 *
	 * @param    node   	node to compare
	 * @return   true 		if the this weight of node is less than the 
	 *           			node argument.
	 */

	public abstract boolean lessThan(Node node);
	
	/**
	 * Abstract method returns true if the weight of node is equal to the node 
	 * referred to by the argument.
	 * @param    node   	node to compare
	 * @return   true 		if the this weight of node is equal to the 
	 *           			node argument.
	 */
	public abstract boolean equalTo(Node node);
	
	/**
	 * Abstract method to set the value of a node
	 * @param	n		node to be added
	 */

	public abstract void setValue(Node minimumNode);
}
