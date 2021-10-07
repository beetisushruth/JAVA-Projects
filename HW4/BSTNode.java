/*
 * BSTNode.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * BSTNode class to hold a node
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */
public class BSTNode {
	
	public int value;			// value of the BSTNode
	public BSTNode leftNode;	// leftNode of the BSTNode
	public BSTNode rightNode;	// rightNode of the BSTNode
	
	/**
	 * Constructor of the BST
	 * @param value
	 */
	public BSTNode(int value) {
		this.value = value;
	}
	
	/**
	 * Parameterized constructor of the BST
	 * @param value
	 * @param leftNode
	 * @param rightNode
	 */
	public BSTNode(int value, BSTNode leftNode, BSTNode rightNode) {
		this.value = value;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
}
