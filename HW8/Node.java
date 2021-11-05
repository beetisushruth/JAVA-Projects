/* Node.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Node class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class Node<E extends Comparable<E>> {
	
	// left node
	public Node<E> left;
	// right node
	public Node<E> right;
	
	private E value;
	
	public Node(E value) {
		this.value = value;
	}

	/**
	 * If this node is greater than the other node
	 * @param    node   node to compare
	 * @return   boolean
	 */
	public boolean isGreaterThan(Node<E> node) {
		return this.value.compareTo(node.value) > 0;
	}
	
	/**
	 * If this node is less than the other node
	 * @param    node   node to compare
	 * @return   boolean
	 */
	public boolean isLessThan(Node<E> node) {
		return this.value.compareTo(node.value) < 0;
	}
	
	/**
	 * If this node is equal to the other node
	 * @param    node   node to compare
	 * @return   boolean
	 */
	public boolean isEqual(Node<E> node) {
		return this.value.compareTo(node.value) == 0;
	}
	
	/**
	 * Get value of the node
	 * @return
	 */
	public E getValue() {
		return value;
	}
	
	/**
	 * Set value of the node
	 * @param value
	 */
	public void setValue(E value) {
		this.value = value;
	}
	
	/**
	 * Overrided toString method
	 */
	public String toString() {
		return ""+this.value;
	}
}
