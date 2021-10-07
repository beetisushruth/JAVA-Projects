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

public class Game {
	
	public static Player playerOne;		// player one instance
	public static Player playerTwo;		// player two instance
	
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
	 * @return	player names, word files and picture files array
	 */
	private static String[] getPlayerWordsAndFiles(String[] args) {
		String[] playerWordAndFiles = new String[6];
		try {
			for(int i=0; i<args.length; i++) {
				if(args[i].equalsIgnoreCase("-me")) {
					playerWordAndFiles[0] = args[i + 1];
				} else if (args[i].equalsIgnoreCase("-meWords")) {
					playerWordAndFiles[1] = args[i + 1];
				} else if (args[i].equalsIgnoreCase("-mePicture")) {
					playerWordAndFiles[2] = args[i + 1];
				} else if (args[i].equalsIgnoreCase("-you")) {
					playerWordAndFiles[3] = args[i + 1];
				} else if (args[i].equalsIgnoreCase("-youWords")) {
					playerWordAndFiles[4] = args[i + 1];
				} else if (args[i].equalsIgnoreCase("-youPicture")) {
					playerWordAndFiles[5] = args[i + 1];
				}
			}
		} catch(Exception e) {
			System.out.println("Wrong arguments provided!");
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
		Puzzle puzzle = player.getPuzzle();
		String word = puzzle.getWord();
		boolean[] wordRevelead = puzzle.getWordRevelead();
		for(int i=0; i<word.length(); i++) {
			char character = word.charAt(i);
			// guessed character check
			if(!wordRevelead[i] && 
					(guessedCharacter.equalsIgnoreCase(""+character))) {
				wordRevelead[i] = true;
				puzzle.setNumOfCharactersGuessed(
						puzzle.getNumOfCharactersGuessed() + 1);
				player.setPuzzle(puzzle);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Start the game for players
	 * @param	currentPlayer
	 */
	private static void startGame() {
		//	initially playerOne will start
		Player currentPlayer = playerOne;	
		Scanner sc = new Scanner(System.in);
		Puzzle currentPlayerPuzzle = currentPlayer.getPuzzle();
		while(currentPlayerPuzzle.getWord().length() != 
				currentPlayerPuzzle.getNumOfCharactersGuessed()) {
			System.out.format("Player %d turn: make a guess! (%s)", 
					currentPlayer.getId() + 1, 
					getWordToShow(currentPlayerPuzzle.getWord(),
							currentPlayerPuzzle.getWordRevelead()));
			String characterGuess = sc.nextLine();
			// if a valid guess happens show word and the picture
			boolean isValidGuess = validateGuess(characterGuess, 
					currentPlayer);
			if(isValidGuess) {
				System.out.format("Your guess was correct: %s\n", 
						getWordToShow(currentPlayerPuzzle.getWord(),
								currentPlayerPuzzle.getWordRevelead()));
				showPicture(currentPlayerPuzzle.getPicture(), 
						currentPlayerPuzzle.getPictureRevealed(),
						currentPlayerPuzzle.getWord(), 
						currentPlayerPuzzle.getNumOfCharactersGuessed());
			}
			// break out immediately if any player wins
			if(currentPlayerPuzzle.getWord().length() == 
					currentPlayerPuzzle.getNumOfCharactersGuessed()) break;
			// change the current player
			currentPlayer = (currentPlayer == playerOne) ? playerTwo : 
				playerOne;
			currentPlayerPuzzle = currentPlayer.getPuzzle();
		}
		System.out.format("%s has won!!\n", currentPlayer.getName());
		System.out.format("%s has lost!!\n", currentPlayer == playerOne ? 
				playerTwo.getName() : 
			playerOne.getName());
		sc.close();
	}
	
	/**
	 * Shows the revealed word to the player
	 * @param	wordToGuess		word the player has to guess	
	 * @param	wordRevealed	word revealed so far
	 */
	private static String getWordToShow(String wordToGuess, 
			boolean[] wordRevealed) {
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
	 * @param	playerWordAndFiles	player word and files	
	 */
	private static void initialiseGame(String[] playerWordAndFiles) {
		String playerOneWord = loadWord(playerWordAndFiles[1]);
		String playerTwoWord = loadWord(playerWordAndFiles[4]);
		Vector<Vector<Character>> playerOnePicture = 
				loadPicture(playerWordAndFiles[2]);
		Vector<Vector<Character>> playerTwoPicture = 
				loadPicture(playerWordAndFiles[5]);
		playerOne = new Player(0, playerWordAndFiles[0], playerOneWord, 
				playerOnePicture,
				new boolean[playerOnePicture.size()]
						[playerOnePicture.get(0).size()], 
				new boolean[playerOneWord.length()], 0);
		playerTwo = new Player(1, playerWordAndFiles[3], playerTwoWord, 
				playerTwoPicture,
				new boolean[playerTwoPicture.size()]
						[playerTwoPicture.get(0).size()], 
				new boolean[playerTwoWord.length()], 0);
	}

	/**
	 * Load word from the file
	 * @param	fileSource
	 * @return	word		string from the file
	 */
	private static String loadWord(String fileSource) {
		String word = null;
		File file = new File(fileSource);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				word = scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.format("Loading the file %s failed!", fileSource);
		}
		return word;
	}


	/**
	 * Main method 
	 * @param	args  system arguments
	 */
        
	public static void main( String[] args ) {
		// get player words and files
		String[] playerWordAndFiles = getPlayerWordsAndFiles(args);
		// initialize the game with player word and files
		initialiseGame(playerWordAndFiles);
		startGame();
	}

}
