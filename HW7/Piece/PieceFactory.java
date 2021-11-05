/* PieceFactory.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Piece;

/**
 * Piece Factory class (Factory Pattern)
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class PieceFactory {
	
	/**
	 * Get piece object for a type
	 * @param pieceType
	 * @param x
	 * @param y
	 * @param z
	 * @return	piece object
	 */
	public static Piece getPieceObject(String pieceType, int x, int y, int z){
		Piece piece = null;
		if(pieceType.equalsIgnoreCase("K")) {
			piece = new Knight(x, y, z);
	    } else if(pieceType.equalsIgnoreCase("R")){
	    	piece = new Rook(x, y, z); 
	    } else if(pieceType.equalsIgnoreCase("B")){
	        piece = new Bishop(x, y, z);
	    } else if(pieceType.equalsIgnoreCase("Q")) {
	    	piece = new Queen(x, y, z);
	    }
		return piece;
	}
}
