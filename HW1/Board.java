/*
 * Board.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Find maximum number of kings that can be placed on a chess board. 
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Board {
	
	int MAX_ROWS = 4;
	int MAX_COLUMNS = 4;
	int maxNoOfKings = 0;			// max number of kings possible in a board
	
	char BLACK_SQUARE = 'b'; 
	char WHITE_SQUARE = 'w';
	char ILLEGAL_SQUARE = '.';
	char KING_PIECE = 'K';
	
	char[][] board = new char[MAX_ROWS][MAX_COLUMNS];
	char[][] boardWithMaxKings;		/* board which stores 
                                       the maximum king combination */								    
	
	/**
	 * Method to create the board and set illegal squares
	 */
        
	public void initBoard() {
		for( int row = 0; row < MAX_ROWS; row++ ) {
			for( int column = 0; column < MAX_COLUMNS; column++ ) {
				board[row][column] = getColor( row, column );   // assign color
			}
		}
		//createIllegalSquares();		// creating illegal squares
	}
	
	/**
	 * Returns what color a square of the chess board must be
	 * @param row		row of the chess board
	 * @param column	column of chess board
	 * @return 			color of the square
	 */
        
	public char getColor( int row, int column ) {
		return ( row + column )%2 == 1 ? BLACK_SQUARE : WHITE_SQUARE;
	}
	
	/**
	 * Marks squares illegal, so no piece can occupy the cell.
	 * (CODE PROVIDED BY THE INSTRUCTOR)
	 */
	public void createIllegalSquares() {
		board[2][1] = ILLEGAL_SQUARE;
		board[2][2] = ILLEGAL_SQUARE;
		board[2][3] = ILLEGAL_SQUARE;
		board[4][1] = ILLEGAL_SQUARE;
		board[4][2] = ILLEGAL_SQUARE;
		board[4][3] = ILLEGAL_SQUARE;
		board[4][1] = ILLEGAL_SQUARE;
		board[3][1] = ILLEGAL_SQUARE;
		board[3][3] = ILLEGAL_SQUARE;
		for( int index=0; index<MAX_ROWS; index+=2 ) {
			board[1][index] = ILLEGAL_SQUARE;
			board[MAX_ROWS-2][index] = ILLEGAL_SQUARE;
		}
	}
	
	/**
	 * Displays the board
	 * @param board		the board to be displayed
	 */
        
	public void visualizeBoard( char[][] board ) {
		for( int row = 0; row < MAX_ROWS; row++ ) {
			for( int column = 0; column < MAX_COLUMNS; column++ ) {
				System.out.print(board[row][column]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Generates all valid board combinations and 
	 * stores maximum kings board combination
	 * @param rowStart		initial row
	 * @param columnStart   initial column
	 * @param kingsCount    kings count for a board
	 */
        
	public void generateAllBoardCombinations( int rowStart, int columnStart, 
			int kingsCount ) {
		int row = rowStart;
		int column = columnStart;
		// loop across all the squares in a board
		while( row < MAX_ROWS ) {
			while( column < MAX_COLUMNS ) {
				// conditional statement to check if a square is legal and safe
				if( board[row][column] != ILLEGAL_SQUARE && 
						isPositionSafe( row, column )) {
					char square = board[row][column];
					board[row][column] = KING_PIECE;
					kingsCount += 1;
					int nextRow = column + 1 < MAX_COLUMNS ? row : row + 1;
					int nextColumn = column + 1 < MAX_COLUMNS ? column + 1 : 0;
					System.out.println();
					// recursion call to generate all possibilities
					generateAllBoardCombinations( nextRow, nextColumn, 
							kingsCount );	
					// storing maximum number of kings observed and it's board 
					if( maxNoOfKings < kingsCount ) {		      
						maxNoOfKings = kingsCount;
						boardWithMaxKings = cloneThisBoard();
					}
					// rolling back to previous board state
					board[row][column] = square;		
					kingsCount -= 1;
				}
				column += 1;
			}
			column = 0;
			row += 1;
		}
	}
	
	/**
	 * Checks if it's safe to place the King in a square
	 * @param row		row number of the square
	 * @param column	column number of the square
	 * @return			true for safe and false for unsafe
	 */
        
	public boolean isPositionSafe( int row, int column ) {
		int[] points = {1, -1, 0};		// array to get neighboring positions
										// of a square 
                
		/* Nested loop generates all 8 neighboring positions and checks if it's
		   safe to put king piece */
		for( int i = 0; i<points.length; i++ ) {
			for( int j=0; j<points.length; j++ ) {
				int neighborRow = row + points[i];
				int neighborColumn = column + points[j];
				// conditional statement to check for a KING PIECE
				if( neighborRow >= 0 && neighborRow < MAX_ROWS && 
						neighborColumn >= 0 && 
						neighborColumn < MAX_COLUMNS && 
						board[neighborRow][neighborColumn] == KING_PIECE ) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Creates a new board from the current board
	 * @return newBoard		newly created board copy
	 */
        
	public char[][] cloneThisBoard() {
		char[][] newBoard = new char[MAX_ROWS][MAX_COLUMNS];	// new board
		for( int row=0; row<MAX_ROWS; row++ ) {
			for( int column=0; column<MAX_COLUMNS; column++ ) {
				newBoard[row][column] = board[row][column]; 
			}
		}
		return newBoard;
	}
	
	/**
	 * Main method
	 * @param args  system arguments
	 */
        
	public static void main( String[] args ) {
		Board myBoard = new Board();
		myBoard.initBoard();		// initialize the board
		// generate board combinations
		myBoard.generateAllBoardCombinations( 0, 0, 0 );			
		System.out.format("Maximum number of kings = %d\n\n",
				myBoard.maxNoOfKings);	
		myBoard.visualizeBoard( myBoard.boardWithMaxKings );
	}

}
