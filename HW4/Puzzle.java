/*
 * SortedStorage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.Vector;

/**
 * Puzzle class that needs to be solved by Player
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class Puzzle {
	// word to guess 
	private String word;	
	// picture 2D vector
	private Vector<Vector<Character>> picture;
	// amount of picture revealed
	private boolean[][] pictureRevealed;
	// part of the word revealed
	private boolean[] wordRevealed;
	// number of characters guessed 
	private int numOfCharactersGuessed;
	
	/**
	 * Parameterized puzzle constructor
	 * @param word
	 * @param picture
	 * @param pictureRevealed
	 * @param wordRevealed
	 * @param numOfCharactersGuessed
	 */
	public Puzzle(String word, Vector<Vector<Character>> picture,
			boolean[][] pictureRevealed, boolean[] wordRevealed,
			int numOfCharactersGuessed) {
		this.word = word;
		this.picture = picture;
		this.pictureRevealed = pictureRevealed;
		this.wordRevealed = wordRevealed;
		this.numOfCharactersGuessed = numOfCharactersGuessed;
	}
	
	/**
	 * Get word of the puzzle
	 * @return	word	string
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Set word of the puzzle
	 * @param word		string
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Get picture of the puzzle
	 * @return	picture		2D vector of characters
	 */
	public Vector<Vector<Character>> getPicture() {
		return picture;
	}
	
	/**
	 * Set picture of the puzzle
	 * @param 	picture		2D vector of characters
	 */
	public void setPicture(Vector<Vector<Character>> picture) {
		this.picture = picture;
	}
	
	/**
	 * Get picture revealed array
	 * @return	picture revealed
	 */
	public boolean[][] getPictureRevealed() {
		return pictureRevealed;
	}
	
	/**
	 * Set picture revealed array
	 * @param	pictureRevealed
	 */
	public void setPictureRevealed(boolean[][] pictureRevealed) {
		this.pictureRevealed = pictureRevealed;
	}
	
	/**
	 * Get word revealed array
	 * @return	wordRevealed
	 */
	public boolean[] getWordRevelead() {
		return wordRevealed;
	}
	
	/**
	 * Set word revealed
	 * @param	wordRevealed
	 */
	public void setWordRevelead(boolean[] wordRevealed) {
		this.wordRevealed = wordRevealed;
	}
	
	/**
	 * Get number of character guessed
	 * @return	number of characters guessed so far
	 */
	public int getNumOfCharactersGuessed() {
		return numOfCharactersGuessed;
	}
	
	/**
	 * Set number of character guessed
	 * @param	numOfCharactersGuessed	number of characters guessed
	 */
	public void setNumOfCharactersGuessed(int numOfCharactersGuessed) {
		this.numOfCharactersGuessed = numOfCharactersGuessed;
	}
}
