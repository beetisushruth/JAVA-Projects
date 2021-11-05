/* Knight.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Piece;

/**
 * Knight piece class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Knight extends Piece{
	
	public Knight(int x, int y, int z) {	
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = "K";
	}
	
	/**
	 * isSafe method to check if a place is safe for this piece
	 * @param 	x	x co-ordinate
	 * @param 	y	y co-ordinate
	 * @param 	z	z co-ordinate
	 */
	@Override
	public boolean isSafe(int x, int y, int z) {
		int xValue = Math.abs(this.x - x);
		int yValue = Math.abs(this.y - y);
		int zValue = Math.abs(this.z - z);
		if(xValue == 2 && yValue == 1 && zValue == 0) return false;
		if(xValue == 1 && yValue == 2 && zValue == 0) return false;
		if(yValue == 2 && zValue == 1 && xValue == 0) return false;
		if(yValue == 1 && zValue == 2 && xValue == 0) return false;
		if(xValue == 1 && zValue == 2 && yValue == 0) return false;
		if(xValue == 2 && zValue == 1 && yValue == 0) return false;
		return true;
	}

	/**
	 * Get value of the piece
	 * @return	string
	 */
	@Override
	public String getValue() {
		return this.value;
	}
}
