import java.util.Scanner;
import java.util.Vector;

public class Game {
	private static Player playerOne, playerTwo;
	
	/**
	 * Initialize the game with picture data and player data
	 * @param playerWordAndFiles	player word and files	
	 */
	private static void initialisePlayers(String[] playerWords, Vector<Vector<Character>> pictureOne,
			Vector<Vector<Character>> pictureTwo) {
		playerOne = new Player(0, playerWords[0], pictureOne, 
				new boolean[pictureOne.size()][pictureOne.get(0).size()], 
				new boolean[playerWords[0].length()], 0);
		playerTwo = new Player(1, playerWords[1], pictureTwo, 
				new boolean[pictureTwo.size()][pictureTwo.get(0).size()], 
				new boolean[playerWords[1].length()], 0);
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
	public static void startGame(String[] playerWords, Vector<Vector<Character>> pictureOne,
			Vector<Vector<Character>> pictureTwo) {
		//	initially playerOne will start
		initialisePlayers(playerWords, pictureOne, pictureTwo);
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
}
