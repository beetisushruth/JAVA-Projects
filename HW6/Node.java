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

public abstract class Node implements Compare {
	// left node
	public Node left;
	// right node
	public Node right;
	
	/**
	 * Set value to the node
	 * @param n
	 */
	abstract public void setValue(Node n);
}
