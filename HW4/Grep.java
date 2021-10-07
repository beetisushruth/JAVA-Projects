/*
 * Grep.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Grep class to process the words against patterns
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Grep {
	
	static String inputWord;	// input word
	static String fileName;		// file to process
	
	/**
	 * Process the file of words against various patterns
	 */
	public static void processFile() {
		File file = new File(fileName);	// creates a new file object
        try {
			Scanner scanner = new Scanner(file);
			String line;
			while(scanner.hasNextLine()) {
				line = scanner.nextLine();
				processString(line);
			}
			// closing scanner resource
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.format("Loading the file %s failed!", fileName);
		}
	}
	
	/**
	 * Process string to match against all patterns
	 * @param inputString
	 */
	public static void processString(String inputString) {
		Patterns.matchAllPatterns(inputString);
	}
	
	/**
	 * Parse arguments
	 * @param args
	 */
	public static void parseArgs(String[] args) {
		if(args.length == 0) {
			System.out.println("Invalid arguments provided");
			return;
		}
		if(args[0].equals("-")) {
			inputWord = args[1];
		} else {
			fileName = args[0];
		}
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		parseArgs(args);
		if(fileName != null) processFile();
		if(inputWord != null) processString(inputWord);
	}

}
