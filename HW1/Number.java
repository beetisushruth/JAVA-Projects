/*
 * Number.java
 *
 * Version: 1
 *
 * Revisions: 1
 */


/**
 * Program to print all numbers whose sum of squares of all digits
 * eventually results in 1  
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Number {
	
	// an array to keep track of all the numbers seen before
	static boolean[] seenThisNumber = new boolean[1000];			
	// numbers squared and added so far
	static String numbersSoFar = "";
	
	/**
	 * Calculates sum of squares of every digit in a number
	 * @param number	integer to calculate sum of squares
	 */
        
	public static int calculateSumOfSquares( int number ) {
		int sum = 0;
		int remainder;
		String currentNumbers = "";
		while(number != 0) {
			remainder = number%10;
			number = number/10;
			sum += remainder*remainder;
			String prefix = (number == 0 ? "" : " + "); 
			currentNumbers = (prefix+remainder+"^2") + currentNumbers;
		}
		numbersSoFar += (currentNumbers + " = " + sum) 
				+ (sum == 1 ? "" : " | ");
		return sum;
	}
	
	/**
	 * Print the output if number is valid
	 * @param number	integer 
	 */
	
	public static void printOutputIfValidNumber(int number) {
		int originalNumber = number;
		while(!seenThisNumber[number - 1]) {
			seenThisNumber[number - 1] = true;
			number = calculateSumOfSquares(number);
			if(number == 1) {
				System.out.format("%-4.4s : %s\n", originalNumber, numbersSoFar);
				return;
			}
		}
	}
	
	/**
	 * Clear seenThisNumber array
	 */
	
	public static void clearSeenThisNumber() {
		for(int i=0; i<seenThisNumber.length; i++) seenThisNumber[i] = false;
	}
	
	/**
	 * Generate all valid numbers in the provided range
	 * @param start		start range
	 * @param end		end range
	 */
	
	public static void generateAllValidNumbers( int start, int end ) {
		if(start <= 0) {
			System.out.println("Start number has to be greater than 0");
			return;
		}
		for(int i=start; i<=end; i++) {
			printOutputIfValidNumber(i);
			clearSeenThisNumber();
			numbersSoFar = "";
		}
	}
	
	/**
	 * Main method 
	 * @param args  system arguments
	 */
        
	public static void main( String[] args ) {
		generateAllValidNumbers(1, 1000);
	}

}
