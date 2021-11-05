/* Board2D.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Board;

import Piece.Hole;
import Piece.Piece;
import Piece.PieceFactory;

import java.util.List;

/**
 * Chess board 2D class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Board2D implements Board {
	
	// chess board
	private Piece[][] board;
	// board with maximum pieces
	private Piece[][] boardWithMaxPieces;
	// max pieces
	private int maxPieces;
	
	public Board2D(int n, List<List<Integer>> holes) {
		createBoard(n);
		setHolesToBoard(holes);
		maxPieces = 0;
	}
	
	
	/**
	 * Set holes to the board
	 * @param	holes
	 */
	@Override
	public void setHolesToBoard(List<List<Integer>> holes) {
		for(int i=0; i<holes.size(); i++) {
			int x = holes.get(i).get(0);
			int y = holes.get(i).get(1);
			int z = holes.get(i).get(2);
			board[x][y] = new Hole(x, y, z);
			boardWithMaxPieces[x][y] = board[x][y];
		}
	}

	/**
	 * create the board
	 * @param	n size of the board
	 */
	@Override
	public void createBoard(int n) {
		board = new Piece[n][n];
		boardWithMaxPieces = new Piece[n][n];
	}
	
	/**
	 * Place pieces on the board
	 * @param 	pieceType
	 * @param 	x x-coordinate
	 * @param	y y-coordinate
	 * @param	z z-coordinate
	 * @param	currentPieces
	 */
	public void placePieces(String pieceType, int x, int y, int z, 
			int currentPieces) {
		for(int row = x; row < board.length; row ++ )	{
			for(int column = y; column < board[0].length; column++) {
				if(isSafePosition(row, column)) {
					currentPieces++;
					board[row][column] = PieceFactory.getPieceObject(pieceType, row, column, 0);
					if(currentPieces > maxPieces) {
						maxPieces = currentPieces;
						copyTheBoard();
					}
					placePieces(pieceType, x, y, z, currentPieces);
					board[row][column] = null;
					currentPieces--;
				}
			}
		}
	}
	
	/**
	 * Copy the board
	 */
	private void copyTheBoard() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				boardWithMaxPieces[i][j] = board[i][j];
			}
		}
	}
	
	/**
	 * Clear the board
	 */
	@Override
	public void clearBoard() {
		maxPieces = 0;
	}

	/**
	 * Is this position safe
	 * @param 	row
	 * @param 	col
	 * @return	boolean
	 */
	public boolean isSafePosition(int row, int col) {
		if(board[row][col] != null) return false;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(board[i][j] != null && !board[i][j].isSafe(row, col, 0)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * Get max pieces
	 */
	@Override
	public int getMaxPieces() {
		return this.maxPieces;
	}
	
	/**
	 * Get a cell color for board
	 * @param	x
	 * @param 	y
	 * @param	z
	 * @return	black or white string
	 */
	@Override
	public String getBoardColor(int x, int y, int z) {
		return (x + y + z)%2 == 0 ? BLACK : WHITE;
	}
	
	/**
	 * Print board with max pieces
	 */
	@Override
	public void printBoardWithMaxPieces() {
		for(int i=0; i<boardWithMaxPieces.length; i++) {
			for(int j=0; j<boardWithMaxPieces[0].length; j++) {
				System.out.print((boardWithMaxPieces[i][j] == null ? 
						getBoardColor(i, j, 0) : boardWithMaxPieces[i][j].getValue())+" ");
			}
			System.out.println();
		}
	}
}
