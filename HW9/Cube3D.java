/*
 * Cube3D.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Defining a 3D cube
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class Cube3D extends Cube {

	int length; // length of side

	/**
	 * Creating a new 3D cube
	 * 
	 * @param length
	 */
	public Cube3D(int length) {
		super(length);
		this.length = length;
	}

	/**
	 * Calculating the volume of the 3D cube
	 * 
	 * @return volume
	 */
	public int getVolume() {
		return super.getVolume() * super.getVolume();
	}

	/**
	 * Getting value of the node
	 * 
	 * @return volume of 3D cube
	 */

	public int getValue() {
		return this.getVolume();
	}
}
