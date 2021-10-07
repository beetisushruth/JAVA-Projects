/* LongNode.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Long node which holds long values
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class LongNode extends Node{
	
	// value of the long node
	private Long value;
	
	public LongNode(Long value) {
		this.value = value;
	}
	
	public LongNode(Long value, Node left, Node right) {
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
		return this.value.compareTo(((LongNode) n).value) > 0;
	}

	/**
	 * Is current Node equal to Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isEqual(Node n) {
		return this.value.compareTo(((LongNode) n).value) == 0;
	}

	/**
	 * Is current Node less than Node n
	 * @param Node n
	 * @return boolean
	 */
	@Override
	public boolean isLessThan(Node n) {
		return this.value.compareTo(((LongNode) n).value) < 0;
	}
	
	/**
	 * Set value to this node
	 * @param Node n
	 */
	@Override
	public void setValue(Node n) {
		LongNode longNode = (LongNode) n;
		this.value = longNode.value;
	}
	
	/**
	 * Overrided toString method
	 * @return String
	 */
	public String toString() {
		return Long.toString(this.value);
	}
}
