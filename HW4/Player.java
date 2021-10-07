/*
 * Player.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.Vector;

/**
 * Player class used for the guess game (bean)
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Player {
	
	private int id;			// player id
	private String name;	// player name
	private Puzzle puzzle;   // player puzzle
	
	// parameterized constructor to initialize constructor
	public Player(int id, String name, String wordToGuess, 
			Vector<Vector<Character>> picture, 
			boolean[][] pictureRevealed, boolean[] wordRevealed, 
			int numOfCharactersGuessed) {
		this.id = id;
		this.name = name;
		this.puzzle =  new Puzzle(wordToGuess, picture, 
				pictureRevealed, wordRevealed, 
				numOfCharactersGuessed);
	}

	/**
	 * Getter for Id
	 * @return	id of the player
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter of player id
	 * @param id of the player
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get puzzle for a player
	 * @return	puzzle	
	 */
	public Puzzle getPuzzle() {
		return puzzle;
	}

	/**
	 * Set puzzle for the player
	 * @param	puzzle
	 */
	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	/**
	 * Get name of the player
	 * @return	name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of the player
	 * @param	name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
