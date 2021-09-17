/*
 * Picture.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Guess a word game played by two players with ASCII picture clues 
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Picture {
	
	public static Player playerOne = null;		// player one instance
	public static Player playerTwo = null;		// player two instance
	
	/**
	 * Load the picture text file into vector
	 * @param	fileSource	text file of the image
	 * @return	2D vector of the image text data
	 */
	private static Vector<Vector<Character>> loadPicture(String fileSource) {
		// picture vector holds the text file data in 2D vector
		Vector<Vector<Character>> picture = new Vector<>();	
		File file = new File(fileSource);	// creates a new file object
		String pictureRow;
        try {
			Scanner scanner = new Scanner(file);
			// iterating over lines in the file and adding them to vector
			while(scanner.hasNextLine()) {
				Vector<Character> rowVector = new Vector<>();
				pictureRow = scanner.nextLine();
				for(int i=0; i<pictureRow.length(); i++) {
					rowVector.add(pictureRow.charAt(i));
				}
				picture.add(rowVector);
			}
			// closing scanner resource
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.format("Loading the file %s failed!", fileSource);
			return null;
		}
        return picture;
	}
	

	/**
	 * Get player words and files from the arguments
	 * @param	args	command line arguments
	 * @return	player word and files array
	 */
	private static String[] getPlayerWordsAndFiles(String[] args) {
		String[] playerWordAndFiles = new String[4];
		// return null if arguments are not passed properly
		if(args.length != 6) {
			System.out.println("Wrong number of arguments provided!");
			return null;
		}
		// variable to make sure valid arguments are passed
		int validArgumentPassed = 0;
		for(int i=0; i<args.length; i++) {
			if(args[i].toLowerCase().equals("-me") && i + 2 < args.length) {
				playerWordAndFiles[0] = args[i + 1];
				playerWordAndFiles[1] = args[i + 2];
				validArgumentPassed++;
			} else if (args[i].toLowerCase().equals("-you") && 
					i + 2 < args.length) {
				playerWordAndFiles[2] = args[i + 1];
				playerWordAndFiles[3] = args[i + 2];
				validArgumentPassed++;
			} 
		}
		// extra check to make sure valid arguments are passed
		if(validArgumentPassed != 2) {
			System.out.println("Invalid Arguments provided!");
			return null;	
		}
		return playerWordAndFiles;
	}
	
	/**
	 * Validates if a player has guessed right character
	 * @param	guessedCharacter	character guessed by the player
	 * @param	player	current player
	 * @return	boolean if player guessed right or not	
	 */
	private static boolean validateGuess(String guessedCharacter, 
			Player player) {
		String word = player.getWordToGuess();
		boolean[] wordRevelead = player.getWordRevealed();
		for(int i=0; i<word.length(); i++) {
			char character = word.charAt(i);
			// guessed character check
			if(!wordRevelead[i] && 
					(guessedCharacter.equalsIgnoreCase(""+character))) {
				wordRevelead[i] = true;
				player.setNumOfCharactersGuessed(
						player.getNumOfCharactersGuessed() + 1);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Start the game for players
	 * @param currentPlayer
	 */
	private static void startGame() {
		//	initially playerOne will start
		Player currentPlayer = playerOne;	
		Scanner sc = new Scanner(System.in);
		while(currentPlayer.getWordToGuess().length() != 
				currentPlayer.getNumOfCharactersGuessed()) {
			System.out.format("Player %d turn: make a guess! (%s)", 
					currentPlayer.getId() + 1, 
					getWordToShow(currentPlayer.getWordToGuess(),
							currentPlayer.getWordRevealed()));
			String characterGuess = sc.nextLine();
			// if a valid guess happens show word and the picture
			boolean isValidGuess = validateGuess(characterGuess, 
					currentPlayer);
			if(isValidGuess) {
				System.out.format("Your guess was correct: %s\n", 
						getWordToShow(currentPlayer.getWordToGuess(),
								currentPlayer.getWordRevealed()));
				showPicture(currentPlayer.getPicture(), 
						currentPlayer.getPictureRevealed(),
						currentPlayer.getWordToGuess(), 
						currentPlayer.getNumOfCharactersGuessed());
			}
			// break out immediately if any player wins
			if(currentPlayer.getWordToGuess().length() == 
					currentPlayer.getNumOfCharactersGuessed()) break;
			// change the current player
			currentPlayer = (currentPlayer == playerOne) ? playerTwo : 
				playerOne;
		}
		System.out.format("Player %d wins!!\n", currentPlayer.getId() + 1);
		sc.close();
	}
	
	/**
	 * Shows the revealed word to the player
	 * @param	wordToGuess		word the player has to guess	
	 * @param	wordRevealed	word revealed so far
	 */
	private static String getWordToShow(String wordToGuess, boolean[] wordRevealed) {
		String word = "";
		for(int i=0; i<wordToGuess.length(); i++) {
			if(wordRevealed[i]) word+= wordToGuess.charAt(i);
			else word += ".";
		}
		return word;
	}

	/**
	 * Show picture to the player
	 * @param	picture				2D vector of the player
	 * @param	pictureRevealed		2D vector of picture revealed		
	 * @param	wordToGuess			word to guess for the player
	 * @param	numOfCharactersGuessed  number of characters guessed so far
	 */
	private static void showPicture(Vector<Vector<Character>> picture, 
			boolean[][] pictureRevealed,
			String wordToGuess, int numOfCharactersGuessed) {
		int rows = picture.size();			// number of rows
		int cols = picture.get(0).size();	// number of columns
		int totalPixels = rows*cols;		// total pixels(characters)
		// percentage of image that can be shown per a character guessed
		double percentPerCharacter = (double) 1/wordToGuess.length();
		// number of characters to reveal
		int numOfCharactersToReveal = (int) Math.ceil((percentPerCharacter)
				*totalPixels);
		// number of characters to reveal when it's the final character
		numOfCharactersToReveal = (numOfCharactersGuessed == 
				wordToGuess.length()) ? 
				totalPixels - numOfCharactersToReveal*
				(numOfCharactersGuessed - 1) : 
					numOfCharactersToReveal;
		int row = 0;
		int col = 0;
		int index = 0;
		while(index < numOfCharactersToReveal) {
			row = (int)(Math.random() * (rows));
			col = (int)(Math.random() * (cols));
			if(!pictureRevealed[row][col]) {
				pictureRevealed[row][col] = true;
				index++;
			}
		}
		// print the picture
		for(int j=0; j<rows; j++) {
			for(int k=0; k<cols; k++) {
				char character = pictureRevealed[j][k] ? 
						picture.get(j).get(k) : '.'; 
				System.out.print(character);
			}
			System.out.println();
		}
	}

	/**
	 * Initialize the game with picture data and player data
	 * @param playerWordAndFiles	player word and files	
	 */
	private static void initialiseGame(String[] playerWordAndFiles) {
		String playerOneWord = playerWordAndFiles[1];
		String playerTwoWord = playerWordAndFiles[3];
		Vector<Vector<Character>> pictureOne =  
				loadPicture(playerWordAndFiles[0]);
		Vector<Vector<Character>> pictureTwo =  
				loadPicture(playerWordAndFiles[2]);
		playerOne = new Player(0, playerOneWord, pictureOne, 
				new boolean[pictureOne.size()][pictureOne.get(0).size()], 
				new boolean[playerOneWord.length()], 0);
		playerTwo = new Player(1, playerTwoWord, pictureTwo, 
				new boolean[pictureTwo.size()][pictureTwo.get(0).size()], 
				new boolean[playerTwoWord.length()], 0);
	}


	/**
	 * Main method 
	 * @param args  system arguments
	 */
        
	public static void main( String[] args ) {
		// get player words and files
		String[] playerWordAndFiles = getPlayerWordsAndFiles(args);
		// return if the files are not loaded properly
		if(playerWordAndFiles == null) return;
		// initialize the game with player word and files
		initialiseGame(playerWordAndFiles);
		startGame();
	}

}
