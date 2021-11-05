/* Board.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Board;

import java.util.List;

/**
 * Chess board interface
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public interface Board {
	
	public static final String BLACK = "b";
	public static final String WHITE = "W";

	/**
	 * Create board
	 * @param n
	 */
	public void createBoard(int n);
	
	/**
	 * Set holes to the board
	 * @param holes
	 */
	public void setHolesToBoard(List<List<Integer>> holes);
	
	/**
	 * Place pieces on the board
	 * @param 	pieceType
	 * @param 	x x co-ordintate
	 * @param 	y y co-ordinate
	 * @param 	z z co-ordinate
	 * @param 	currentPieces
	 */
	public void placePieces(String pieceType, int x, 
			int y, int z, int currentPieces);
	
	/**
	 * Get max pieces possible
	 * @return maxPieces integer
	 */
	public int getMaxPieces();
	
	/**
	 * Print board with max pieces
	 */
	public void printBoardWithMaxPieces();
	
	/**
	 * Clear the board
	 */
	public void clearBoard();
	
	/**
	 * Get board color by cell
	 * @param 	x x co-ordinate
	 * @param 	y y co-ordinate
	 * @param 	z z co-ordintae
	 * @return	string Black or white
	 */
	public String getBoardColor(int x, int y, int z);
}
