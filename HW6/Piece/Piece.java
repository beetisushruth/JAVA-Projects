/* Piece.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Piece;

/**
 * Chess piece abstract class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

abstract public class Piece {
	// x co-ordinate
	public int x;
	// y co-ordinate
	public int y;
	// z co-ordinate
	public int z;
	// value of the string
	public String value;
	
	/**
	 * Get value of the piece
	 * @return	string
	 */
	public abstract String getValue();
	
	/**
	 * isSafe method to check if a place is safe for this piece
	 * @param 	x	x co-ordinate
	 * @param 	y	y co-ordinate
	 * @param 	z	z co-ordinate
	 */
	public abstract boolean isSafe(int x, int y, int z);
	
	/**
	 * Is moving cross safe
	 * @param 	x1
	 * @param 	y1
	 * @param 	z1
	 * @param 	x2
	 * @param 	y2
	 * @param 	z2
	 * @return	boolean
	 */
	public boolean isCrossSafe(int x1, int y1, int z1, 
			int x2, int y2, int z2) {
		boolean value = true;
		int xDiff = Math.abs(x1 - x2);
		int yDiff = Math.abs(y1 - y2);
		int zDiff = Math.abs(z1 - z2);
		if((xDiff == yDiff && yDiff == zDiff) ||
				(xDiff == 0 && yDiff == zDiff) ||
				(yDiff == 0 && xDiff == zDiff) ||
				(zDiff == 0 && yDiff == xDiff)) {
			value = false;
		}
		return value;
	}
	
	/**
	 * Is moving straight safe
	 * @param 	x1
	 * @param 	y1
	 * @param 	z1
	 * @param 	x2
	 * @param 	y2
	 * @param 	z2
	 * @return	boolean
	 */
	public boolean isStraightSafe(int x1, int y1, int z1, 
			int x2, int y2, int z2) {
		int xDiff = Math.abs(x1 - x2);
		int yDiff = Math.abs(y1 - y2);
		int zDiff = Math.abs(z1 - z2);
		if((xDiff == 0 && yDiff == 0) || (xDiff == 0 && zDiff == 0)
				|| (yDiff == 0 && zDiff == 0)) return false;
		return true;
	}
}
