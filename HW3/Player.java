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
	
	private int id;		// player id
	// word to guess 
	private String wordToGuess = null;	
	// picture 2D vector
	private Vector<Vector<Character>> picture = new Vector<>();
	// amount of picture revealed
	private boolean[][] pictureRevealed = null;
	// part of the word revealed
	private boolean[] wordRevealed = null;
	// number of characters guessed 
	private int numOfCharactersGuessed = 0;
	
	// default constructor
	public Player( ) {
		
	}
	
	// parameterized constructor to initialize constructor
	public Player(int id, String wordToGuess, 
			Vector<Vector<Character>> picture, 
			boolean[][] pictureRevealed, boolean[] wordRevealed, 
			int numOfCharactersGuessed) {
		this.id = id;
		this.wordToGuess = wordToGuess;
		this.picture = picture;
		this.pictureRevealed = pictureRevealed;
		this.wordRevealed = wordRevealed;
		this.numOfCharactersGuessed = numOfCharactersGuessed;
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
	 * Get word to guess
	 * @return word to guess
	 */
	public String getWordToGuess() {
		return wordToGuess;
	}
	
	/**
	 * Set word to guess
	 * @param wordToGuess
	 */
	public void setWordToGuess(String wordToGuess) {
		this.wordToGuess = wordToGuess;
	}
	
	/**
	 * Get 2D vector picture 
	 * @return 2d vector picture
	 */
	public Vector<Vector<Character>> getPicture() {
		return picture;
	}
	
	/**
	 * Set 2D vector picture
	 * @param picture
	 */
	public void setPicture(Vector<Vector<Character>> picture) {
		this.picture = picture;
	}
	
	/**
	 * Get picture revealed 
	 * @return picture revealed
	 */
	public boolean[][] getPictureRevealed() {
		return pictureRevealed;
	}
	
	/**
	 * Set picture revealed
	 * @param pictureRevealed
	 */
	public void setPictureRevealed(boolean[][] pictureRevealed) {
		this.pictureRevealed = pictureRevealed;
	}
	
	/**
	 * Get number of characters guessed
	 * @return	number of characters
	 */
	public int getNumOfCharactersGuessed() {
		return numOfCharactersGuessed;
	}
	
	/**
	 * Set number of characters guessed
	 * @param numOfCharactersGuessed
	 */
	public void setNumOfCharactersGuessed(int numOfCharactersGuessed) {
		this.numOfCharactersGuessed = numOfCharactersGuessed;
	}
	
	/**
	 * Get word revealed
	 * @return word revealed so far
	 */
	public boolean[] getWordRevealed() {
		return wordRevealed;
	}
	
	/**
	 * Set word revealed
	 * @param wordRevealed
	 */
	public void setWordRevealed(boolean[] wordRevealed) {
		this.wordRevealed = wordRevealed;
	}
	
}
