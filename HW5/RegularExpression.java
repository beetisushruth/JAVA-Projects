/*
 * RegularExpression.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.io.File;
/**
 * Create patterns which will match a particular set of words
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Regular Expression class to test various cases of patterns
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */
public class RegularExpression {
	/**
	 * Creating an array containing regular expressions and verbal descriptions
	 */
	
	// input strings
	private static String[][]patterns = new String[][] {
			{"a word which is two characters long", "^..$"},
			{"a word which is two or more characters long","..+"},
			{"a word with the vowels ’aeiou’ in order and each vowel "
					+ "can appear only once",
					"[^aeiou]*[a][^aeiou]*[e][^aeiou]*[i][^aeiou]*[o]"
					+ "[^aeiou]*[u][^aeiou]*"},
			{"includes ac/dc or AC/DC",".*(ac\\/dc)|(AC\\/DC).*"},
			{"includes only lower case characters, "
					+ "but not the character ’h’, ’p’, "
					+ "and ’b’","[a-z&&[^hpb]]+"},
			{"starts with ’(’, followed by ’one’ or ’uno’, or "
					+ "’eins’, followed by ’)’"
						,"^\\((uno|eins|one)\\)"},
			{"starts with ’M’ or ’m’ followed ’oma’","^[Mm](oma)"},
			{"starts with ’[’, followed by ’a-m’ or ’O-Z’, followed by ’]’",
				"^\\[((a\\-m)|(O\\-Z))\\]"},
			{"starts with ’a’ followed by 2 digits in the"
					+ " range between 1 to 3 only",
					"^a[1-3]{2}"},
			{"starts with ’a’ followed by one digit or more digits",
						"^a[0-9]+"},
			{"starts with 2 lower case characters’ followed by 3 digits",
				"^[a-z]{2}[0-9]{3}"},
			{"starts with ’a’ followed by one digit or more digits",
					"^a[0-9]+"}
	};
	
	/**
	 * Parsing arguments and adding to the arguments array
	 * @param arguments
	 */
	
	private static String[] parseArgs(String[] args) {
		String[] arguments = new String[3];	// array containing input elements
		try {
			for(int i=0; i<args.length; i++) {
				//java RegularExample -d ’:’ -input -
				if(args[i].equalsIgnoreCase("-d")) {
					arguments[0] = args[i + 1];
				} 
				if(args[i].equalsIgnoreCase("-input")) {
					arguments[1] = args[i + 1];
				} 
				if(args[i].equalsIgnoreCase("-")) {
					arguments[2] = args[i + 1];
				}
			}
		}
		catch(Exception e) {
			System.out.println("Few arguments might be missing.");
		}
		return arguments;
	}
	
	/**
	 * Process file to extract the words excluding delimiter
	 * @param filename
	 * @param delimiter
	 */
	private static void processInputFile(String fileName, String delimiter) {
		String word;
        try {
    		File file = new File(fileName);	// creates a new file object
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				word = scanner.nextLine();
				processSentence(word, delimiter);
			}
			// closing scanner resource
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.format("Loading the file %s failed!", fileName);
		}
	}
	
	/**
	 * Process file to extract the words excluding delimiter
	 * @param sentence
	 * @param delimiter
	 */
	
	private static void processSentence(String sentence, String delimiter) {
		String word;
        try {
			Scanner scanner = new Scanner(sentence);
			scanner.useDelimiter(delimiter);
			while(scanner.hasNext()) {
				word = scanner.next();
				matchPatterns(word,delimiter);
			}
			// closing scanner resource
			scanner.close();
		} catch (Exception e) {
			System.out.println("Error occurred while processing sentence");
		}
	}
	
	/**
	 * Match word with patterns and print
	 * @param word
	 * @param delimiter
	 */
	
	private static void matchPatterns(String word, String delimiter) {
		System.out.println("---------------------------------------------------------------");
		System.out.format("Input: -%s=\n",word);
		for(int i=0; i<patterns.length - 1; i++) {
			String pattern = patterns[i][1];
			String description = patterns[i][0];
			if(Pattern.matches(pattern, word)){
				System.out.format("This regular expression \"%s\" "
						+ "matches the following input: -%s=\n"
						+ "\tverbal explanation: %s\n",pattern,
						word, description);
			}
		}
	}
	
	/**
	 * Main method
	 * @param args
	 */
	
	public static void main(String[] args) {
		//parse the arguments
		args = parseArgs(args);
		String delimiter = args[0]; //capturing delimiter
		String fileName = args[1];
		String inputSentence = args[2];
		if(delimiter != null && inputSentence != null) {
			processSentence(inputSentence, delimiter);
		}
		else if(delimiter != null && fileName != null) {
			processInputFile(fileName, delimiter);
		} else {
			System.out.println("Invalid arguments!");
		}
	}
}
