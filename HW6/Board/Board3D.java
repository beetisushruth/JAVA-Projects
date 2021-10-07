/* Board3D.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package Board;

import java.util.List;

import Piece.Hole;
import Piece.Piece;
import Piece.PieceFactory;

/**
 * Chess board 3D class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Board3D implements Board{

	// chess board 3d
	private Piece[][][] board;
	// board with maximum pieces
	private Piece[][][] boardWithMaxPieces;
	// max pieces on the board
	private int maxPieces;
	
	public Board3D(int n, List<List<Integer>> holes) {
		createBoard(n);
		setHolesToBoard(holes);
		maxPieces = 0;	
	}
	
	/**
	 * create the board
	 * @param	n size of the board
	 */
	@Override
	public void createBoard(int n) {
		board = new Piece[n][n][n];
		boardWithMaxPieces = new Piece[n][n][n];
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
			board[x][y][z] = new Hole(x, y, z);
			boardWithMaxPieces[x][y][z] = board[x][y][z];
		}
	}

	/**
	 * Place pieces on the board
	 * @param 	pieceType
	 * @param 	x x-coordinate
	 * @param	y y-coordinate
	 * @param	z z-coordinate
	 * @param	currentPieces
	 */
	@Override
	public void placePieces(String pieceType, int startX, int startY,
			int startZ, int currentPieces) {
		for(int z = startZ; z < board.length; z++) {
			for(int x = startX; x < board.length; x++ )	{
				for(int y = startY; y < board.length; y++) {
					if(isSafePosition(x, y, z)) {
						currentPieces++;
						board[x][y][z] = PieceFactory.getPieceObject(pieceType, x, y, z);
						if(currentPieces > maxPieces) {
							maxPieces = currentPieces;
							copyTheBoard();
						}
						placePieces(pieceType, startX, startY, startZ, currentPieces);
						board[x][y][z] = null;
						currentPieces--;
					}
				}
			}
		}
		
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
		return (x + y + z)%2 == 0 ? "b" : "w";
	}

	/**
	 * Print board with max pieces
	 */
	@Override
	public void printBoardWithMaxPieces() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				for(int k=0; k<board.length; k++) {
					System.out.print((boardWithMaxPieces[i][j][k] == null ? 
							getBoardColor(i, j, k) : 
								boardWithMaxPieces[i][j][k].getValue())+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	/**
	 * Visualise board
	 */
	public void visualiseBoard() {
		for(int k=0; k<board.length; k++) {
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board.length; j++) {
					System.out.print((board[i][j][k] == null ? 
							getBoardColor(i, j, k) : 
								board[i][j][k].getValue())+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	/** 
	 * clear the board
	 */
	@Override
	public void clearBoard() {
		maxPieces = 0;
	}
	
	/**
	 * copy the board
	 */
	private void copyTheBoard() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				for(int k=0; k<board.length; k++) {
					boardWithMaxPieces[i][j][k] = board[i][j][k];
				}
			}
		}
	}
	
	/**
	 * Is safe position for a piece
	 * @param 	x	x co-ordinate
	 * @param 	y	y co-ordinate
	 * @param 	z	z co-ordinate
	 * @return	boolean
	 */
	private boolean isSafePosition(int x, int y, int z) {
		if(board[x][y][z] != null) return false;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				for(int k=0; k<board.length; k++) {
					if(board[i][j][k] != null && !board[i][j][k].isSafe(x, y, z)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
}
