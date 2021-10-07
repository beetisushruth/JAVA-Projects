/* Rook.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Piece;

/**
 * Rook piece class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Rook extends Piece{

	public Rook(int x, int y, int z) {
		this.x = x;
		this.y= y;
		this.z = z;
		this.value = "R";
	}
	
	/**
	 * isSafe method to check if a place is safe for this piece
	 * @param 	x	x co-ordinate
	 * @param 	y	y co-ordinate
	 * @param 	z	z co-ordinate
	 */
	@Override
	public boolean isSafe(int x, int y, int z) {
		return isStraightSafe(this.x, this.y, this.z, x, y, z);
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
