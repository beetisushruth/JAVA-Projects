/*
 * Chess.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Board.Board2D;
import Board.Board3D;

/**
 * Chess class to get maximum pieces possible
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Chess {
	
	// dimensions of the chess board eg: 2D, 3D
	public static int dimensions;
	// chess board board size
	public static int boardSize;
	// holes of the chess board
	public static List<List<Integer>> holes = new ArrayList<>();
	
	/**
	 * Parsing arguments required for the program
	 * @param arguments
	 */
	
	private static void parseArgs(String[] args) {
		for(int i=0; i<args.length; i++) {
			if(args[i].equalsIgnoreCase("-d")) {
				dimensions = Integer.parseInt(args[i + 1]);
			} 
			if(args[i].equalsIgnoreCase("-n")) {
				boardSize = Integer.parseInt(args[i + 1]);
			} 
			if(args[i].equalsIgnoreCase("-h")) {
				String[] points = args[i + 1].split("\\.");
				List<Integer> list = new ArrayList<Integer>();
				list.add(Integer.parseInt(points[0]) - 1);
				list.add(Integer.parseInt(points[1]) - 1);
				if(points.length == 3) {
					list.add(Integer.parseInt(points[2]) - 1);
				} else {
					list.add(0);
				}
				holes.add(list);
			}
		}
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			parseArgs(args);
			Board board;
			if(dimensions == 2) {
				board = new Board2D(boardSize, holes);
			} else {
				board = new Board3D(boardSize, holes);
			}
			String[] pieceNames = new String[] {"Queen", 
					"Bishop", "Rook", "Knight"};
			String[] pieces = new String[] {"Q", "B", "R", "K"};
			for(int i=0; i<pieces.length; i++) {
				board.placePieces(pieces[i], 0, 0, 0, 0);
				System.out.println("Maximum"+" "+pieceNames[i]+ 
						" pieces:"+ board.getMaxPieces());
				board.printBoardWithMaxPieces();
				board.clearBoard();
			}
		} catch(Exception e) {
			System.out.println("Invalid arguments");
		}
	}
}
