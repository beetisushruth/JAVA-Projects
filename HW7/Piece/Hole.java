/* Hole.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Piece;

/**
 * Hole Piece class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Hole extends Piece{
	
	public Hole(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = "H";
	}

	/**
	 * Get value of the piece
	 * @return	string
	 */
	@Override
	public String getValue() {
		return this.value;
	}

	/**
	 * isSafe method to check if a place is safe for this piece
	 * @param 	x	x co-ordinate
	 * @param 	y	y co-ordinate
	 * @param 	z	z co-ordinate
	 */
	@Override
	public boolean isSafe(int x, int y, int z) {
		if(this.x == x && this.y == y && this.z == z) {
			return false;
		}
		return true;
	}
}
