/* IntegerNode.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Integer node which holds integer values
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class IntegerNode extends Node {
	
	// value of the node
	private Integer value = 0;
	
	public IntegerNode(Integer value) {
		this.value = value;
	}
	
	public IntegerNode(Integer value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/**
	 * Is current Node greater than Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isGreaterThan(Node n) {
		return this.value.compareTo(((IntegerNode) n).value) > 0;
	}

	/**
	 * Is current Node is equal to Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isEqual(Node n) {
		return this.value.compareTo(((IntegerNode) n).value) == 0;
	}

	/**
	 * Is current Node is less than Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isLessThan(Node n) {
		return this.value.compareTo(((IntegerNode) n).value) > 0;
	}
	
	/**
	 * Set value to the node
	 * @param Node n
	 */
	public void setValue(Node n) {
		IntegerNode integerNode = (IntegerNode) n;
		this.value = integerNode.value;
	}
	
	/**
	 * Override toString() method
	 * @return String
	 */
	public String toString() {
		return Integer.toString(this.value);
	}
}
