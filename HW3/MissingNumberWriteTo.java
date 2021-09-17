/*
 * MissingNumberWriteTo.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Write numbers into a file by number of bits except a random number  
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */
public class MissingNumberWriteTo {
	
	static int soManyBits;	// Number of bits to generate the numbers
	static String fileName; // File name to save the numbers
	static Scanner input;   // Scanner object
	
	/**
	 * Parse the arguments
	 * @param args
	 */
	public static void parseArgs(String[] args) {
		// base case to check for no arguments case
		if(args.length == 0) {
			System.out.println("No arguments provided!");
			return;
		}
		// parsing arguments
		for(int index = 0; index < args.length; index += 2)  {
			if(args[index].equals("-n")) {
				soManyBits = Integer.parseInt(args[index + 1 ]);
			} else if(args[index].equals(">")) {
				fileName = args[index + 1];
			} else {
				System.out.println("Unrecognized argument -"  + args[index]);
				System.exit(1);
			}
		}
	}
	
	/**
	 * Save the generated numbers into a file
	 */
	public static void saveNumbersToFile() {
		long maximumNumber = (long)Math.pow(2, soManyBits );
		// generate a random missing number
		long thisNumberIsMissing = (long)new Random().nextInt(
				(int) maximumNumber);
		thisNumberIsMissing = ( thisNumberIsMissing == 0 ) ? 1 : 
			thisNumberIsMissing;
		System.out.println(thisNumberIsMissing);
		try {
			// Open a fileOutputStream and write all the numbers
			File file = new File(fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(fileOutputStream));
			for(int index = 1; index < maximumNumber; index++) {
				if(index != thisNumberIsMissing) {
					bufferedWriter.write(index+"");
					bufferedWriter.newLine();
				}
			}
			// close the resource
			bufferedWriter.close();
			System.out.println("Numbers generated and saved successfully!");
		} catch(IOException e) {
			System.out.println("Failed to save the file "+ e.getMessage());
		}
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		parseArgs(args);
		if(soManyBits > 0 && fileName != null) {
			saveNumbersToFile();
		}
	}

}
