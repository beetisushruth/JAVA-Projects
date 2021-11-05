/*
 * Cube.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Defining a cube
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
class Cube extends Square {
	int length; // length of side

	/**
	 * Creating a new cube
	 * 
	 * @param length
	 */
	public Cube(int length) {
		super(length);
		this.length = length;
	}

	/**
	 * Calculating the volume of the cube
	 * 
	 * @return volume
	 */
	public int getVolume() {
		return getArea() * getLength();
	}

	/**
	 * Getting value of the node
	 * 
	 * @return volume of cube
	 */
	public int getValue() {
		return this.getVolume();
	}
}
