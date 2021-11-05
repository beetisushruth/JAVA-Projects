/*
 * Line.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Defining a Line
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class Line {
	int length; // length of line

	/**
	 * Creating a new line
	 * 
	 * @param length
	 */
	public Line(int length) {
		this.length = length;
	}

	/**
	 * Getter to get length of line
	 * 
	 * @param length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Getting value of the node
	 * 
	 * @return length of line
	 */
	public int getValue() {
		return this.getLength();
	}

	/**
	 * String representation of the node
	 * 
	 * @return value
	 */
	public String toString() {
		return "Value: " + this.getValue();
	}
}
