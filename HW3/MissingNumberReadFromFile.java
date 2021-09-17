/*
 * MissingNumberReadFromFile.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Find missing number reading from the file  
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class MissingNumberReadFromFile {
	
	private static int soManyBits = 0;		// no of bits
	private static String fileName;			// file name of the file to read
	private static long[] bitArray = null;	// bit array
	
	/**
	 * Find the missing number using bit array
	 * @return	missing number
	 */
	
	private static long findMissingNumberUsingArray() {
		bitArray = new long[soManyBits];
		File file = new File(fileName);
	    Scanner scanner;
	    long missingNumber = 0;
		try {
			scanner = new Scanner(file);
			// Add decimal number to bit array as bits
			while (scanner.hasNextLine()) {
				// assuming it wont't reach illegal parse
		    	int number = Integer.parseInt(scanner.nextLine());
		    	addToBitArray(number);
		    }
			// Adding zero as bits
			for(int i=0; i<bitArray.length; i++) {
				bitArray[i]--;
			}
			missingNumber = getNumberFromBitArray();
		    scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't read the file " + e.getMessage());
		}
		return missingNumber;
	}
	
	/**
	 * Get decimal from the bit array
	 * @return	missing number
	 */
	private static long getNumberFromBitArray() {
		long number = 0;
		int base = 1;
		for(int index = soManyBits - 1; index >= 0; index--) {
			int bit = bitArray[index] == 1 ? 0 : 1;
			number += base*bit;
			base *= 2;
		}
		return number;
	}
	
	/**
	 * Add decimal number to bit array as bits
	 * @param number
	 */
	private static void addToBitArray(int number) {
		int bit;
		for(int index = soManyBits - 1; index >= 0; index -- ) {
			bit = (number & (1<<index)) == 0 ? -1 : 1;
			bitArray[soManyBits - 1 - index] += bit;
		}
	}

	/**
	 * Parse the arguments for file name and number of bits
	 * @param arguments
	 */
	private static void parseArgs(String[] args) {
		// Condition to check for no arguments case
		if(args.length == 0) {
			System.out.println("No arguments provided!");
			return;
		}
		// parsing for number of bits and filename
		for(int index = 0; index < args.length; index += 2)  {
			if(args[index].equalsIgnoreCase("-n")) {
				soManyBits = Integer.parseInt(args[index + 1 ]);
			} else if(args[index].equalsIgnoreCase("-f")) {
				fileName = args[index + 1];
			} else {
				System.out.println("Unrecognized argument -"  + args[index]);
				System.exit(1);
			}
		}
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		// parse arguments
		parseArgs(args);
		if(fileName != null && soManyBits > 0) {
			System.out.format("Found the missing number: %d", 
					findMissingNumberUsingArray());
		}
	}


}
