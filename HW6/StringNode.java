/*
 * StringNode.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * String Node class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class StringNode extends Node {
	
	// value of the string node
	private String value;
	
	public StringNode(String value) {
		this.value = value;
	}
	
	public StringNode(String value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Is current Node is greater than Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isGreaterThan(Node n) {
		return this.value.compareTo(((StringNode) n).value) > 0;
	}

	/**
	 * Is current Node is equal to Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isEqual(Node n) {
		return this.value.compareTo(((StringNode) n).value) == 0;
	}

	/**
	 * Is current Node is less than Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isLessThan(Node n) {
		return this.value.compareTo(((StringNode) n).value) < 0;
	}
	
	/**
	 * Set value to the node
	 * @param Node n
	 */
	@Override
	public void setValue(Node n) {
		StringNode longNode = (StringNode) n;
		this.value = longNode.value;
	}
	
	/**
	 * Overrided toString() method
	 * @return String
	 */
	public String toString() {
		return this.value;
	}
}
