/*
 * WordSearch.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Finds input words in a puzzle (String array)  
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class WordSearch {
	
	// 6*6 puzzle
	static String[] puzzle = { 
			"tomato", 
			"omelet",
			"carrot",
			"onions",
			"garlic",
			"bagels"
			};
	//puzzle rotated 
	static String[] puzzleRotated = new String[puzzle.length];	
	// input words to search
	static String wordsToSearchFor[] = { "to", "me", "ma", "on" };
	
	/**
	 * Reverses a given word
	 * @param word
	 * @return wordReversed
	 */
	
	public static String getReverseWord(String word) {
		String reverseWord = "";
		// Add characters in the reverse order
		for(int i=word.length() - 1; i>=0; i--) {
			reverseWord += word.charAt(i);
		}
		return reverseWord;
	}
	
	/**
	 * Create a new puzzle by rotating the original puzzle
	 */
	
	public static void createPuzzleRotated() {
		// Create puzzle rotated by forming words across columns
		for(int column=0; column<puzzle[0].length(); column++) {
			String word = "";
			for(int row=0; row<puzzle.length; row++) {
				word += puzzle[row].charAt(column);
			}
			puzzleRotated[column] = word;
		}
	}
	
	/**
	 * Finds a string inside an other
	 * @param wordToSearchIn		word to search in
	 * @param wordToFind            word to find
	 * @param matchesFoundArray     boolean array to track matches found
	 * @param index					index of the wordToSearchIn in puzzle
	 */
	
	public static void findMatchInWord(String wordToSearchIn, 
			String wordToFind, boolean[] matchesFoundArray, int index) {
		// Use indexOf property to find the match inside a string
		int matchIndex = wordToSearchIn.indexOf(wordToFind);
		if(!matchesFoundArray[index] && matchIndex != -1) {
			matchesFoundArray[index] = true;
		}
	}
	
	/**
	 * Search a word in the puzzle
	 * @param puzzle				puzzle string array
	 * @param matchesFound		    matches found array
	 * @param wordToFind            word to find string
	 * @param wordToFindReversed    reversed word to find string
	 * @return boolean array of the matches found set to true
	 */
	
	public static boolean[] searchWord(String[] puzzle, boolean[] matchesFound, 
			String wordToFind, String wordToFindReversed) {
		for(int i=0; i< puzzle.length; i++) {
			findMatchInWord(puzzle[i], wordToFind, matchesFound, i);
			findMatchInWord(puzzle[i], wordToFindReversed, matchesFound, i);
		}
		return matchesFound;
	}
	
	/**
	 * Search rows of the puzzle to find a match
	 * @param wordToFind	word to find in the puzzle
	 * @return boolean array of row size with valid match set to true
	 */
	
	public static boolean[] searchRows(String wordToFind) {
		// reverse the word
		String wordToFindReversed = getReverseWord(wordToFind);		
		boolean[] matchesFoundInRows = new boolean[puzzle.length];
		return searchWord(puzzle, matchesFoundInRows, wordToFind, wordToFindReversed);
	}
	
	/**
	 * Search columns of the puzzle to find a match
	 * @param wordToFind	word to find in the puzzle
	 * @return boolean array of column size with valid match set to true
	 */
	
	public static boolean[] searchColumns(String wordToFind) {
		String wordToFindReversed = getReverseWord(wordToFind);
		boolean[] matchesFoundInColumns = new boolean[puzzle[0].length()];
		// create puzzle rotated
		createPuzzleRotated();
		return searchWord(puzzleRotated, matchesFoundInColumns, wordToFind, wordToFindReversed);
	}	
	
		
	/**
	 * Finds target word in the puzzle
	 * @param wordToFind 	word to find in the puzzle
	 */
        
	public static void findWordInRowsAndColumns( String wordToFind ) {
		boolean[] matchesFoundInRows = searchRows(wordToFind);
		boolean[] matchesFoundInColumns = searchColumns(wordToFind);
		boolean isMatchFound = false;
		int index = 0;
		while(!isMatchFound && index < matchesFoundInRows.length) {
			if(matchesFoundInRows[index] || matchesFoundInColumns[index]) { 
				isMatchFound = true;
			}
			index++;
		}
		// if match found then print the matches
		if(isMatchFound) {
			printWordMatches(matchesFoundInRows, matchesFoundInColumns, wordToFind);
		}
	}
	
	/**
	 * Print the matches found in the puzzle
	 * @param matchesFoundInRows	 boolean array of matches found in rows
	 * @param matchesFoundInColumns  boolean array of matches found in columns
	 * @param word					 word to find string
	 */
	
	public static void printWordMatches(boolean[] matchesFoundInRows, 
			boolean[] matchesFoundInColumns, String wordToFind) {
		System.out.format("Searching for: %s \n", wordToFind);
		for(int i=0; i<matchesFoundInRows.length; i++) {
			if(matchesFoundInRows[i]) {
				System.out.format("Found \"%s\": in row: %d %s\n", 
						wordToFind, i, puzzle[i]);
			}
		}
		for(int i=0; i<matchesFoundInColumns.length; i++) {
			if(matchesFoundInColumns[i]) {
				System.out.format("Found \"%s\": in column: %d %s\n", 
						wordToFind, i, puzzleRotated[i]);
			}
		}
	}
	
	/**
	 * Print the puzzle
	 */
	
	public static void printPuzzle() {
		for(int i=0; i<puzzle.length; i++) {
			System.out.format("%d: \t %s \n", i, puzzle[i]);
		}
		System.out.println();
	}
	
	/**
	 * Main method 
	 * @param args  system arguments
	 */
        
	public static void main( String[] args ) {
		printPuzzle();		// printing initial puzzle
		for(int i=0; i<wordsToSearchFor.length; i++) {
			findWordInRowsAndColumns(wordsToSearchFor[i]);
		}
	}

}
