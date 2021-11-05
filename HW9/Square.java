/*
 * Square.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Defining a Square
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class Square extends Line {
	int length; // length of side of square

	/**
	 * Creating a new square
	 * 
	 * @param length
	 */

	public Square(int length) {
		super(length);
		this.length = length;
	}

	/**
	 * Calculating the area of the square
	 * 
	 * @return area
	 */
	public int getArea() {
		return getLength() * getLength();
	}

	/**
	 * Getting value of the node
	 * 
	 * @return area of square
	 */
	public int getValue() {
		return this.getArea();
	}
}
