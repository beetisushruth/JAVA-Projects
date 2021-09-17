/*
 * StringIntegerArray.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Many Questions class demonstrating writing elegant code
 * (Ternary Conditional Operator)
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class ManyQuestions {
	
	/**
	 * Sum up from lower to larger integers
	 * PROVIDED BY INSTRUCTOR
	 * @param a
	 * @param b
	 * @return string in the given format
	 */
	public static String sumUpFromLowerToLarger(int a, int b) {
		String rValue = "";
		if(a < b) {
			for(int index = a; index < b; index++) {
				rValue += a + ": " + index + " ";
			}
		} else {
			for(int index = b; index < a; index++) {
				rValue += b + ": " + index + " ";
			}
		}
		return rValue;
	}
	
	/**
	 * Sum up from lower to larger optimized
	 * @param	a	is an integer
	 * @param	b is an integer
	 * @return	String in required format
	 */
	public static String sumUpFromLowerToLargerBetter(int a, int b) {
		int low = a < b ? a : b;
		int high = a >= b ? a : b;
		String rValue = "";
		for(int index=low; index< high; index++) {
			rValue += low + ": " + index + " ";
		}
		return rValue;
	}
	
	/**
	 * Print number in bit representation
	 * PROVIDED BY THE INSTRUCTOR
	 * @param	a is an integer
	 * @return	bit representation of a decimal number
	 */
	public static String printBitRepresentation(int a) {
		String rValue = "";
		for(int index = 31; index >= 0; index -- ) {
			if(((a) & (1 << index)) == (1 << index)) {
				rValue += "1";
			} else {
				rValue += "0";
			}
		}
		return rValue;
	}
	
	/**
	 * Print number in bit representation better version
	 * @param	a is an integer
	 * @return	bit representation of a decimal number
	 */
	public static String printBitRepresentationBetter(int a) {
		String rValue = "";
		for(int index = 31; index >= 0; index -- ) {
			rValue += (a & (1<<index)) == 0 ? "0" : "1";
		}
		return rValue;
	}
	
	/**
	 * Find maximum number out of given numbers 
	 * PROVIDED BY INSTRUCTOR
	 * @param	a
	 * @param 	b
	 * @param 	c
	 * @param 	d
	 * @return	maximum number
	 */
	public static int findMaximum(int a, int b, int c, int d)	{
		int rValue; 
		int maxAndB = 0;
		int maxCndD = 0;
		if ( a > b )
			maxAndB = a;
		else
			maxAndB = b;
		if ( c > d )
			maxCndD = a;
		else
			maxCndD = b;
		if ( maxAndB > maxCndD )
			rValue = maxAndB;
		else
			rValue = maxCndD;
		return rValue;
	}
	
	/**
	 * Find maximum number out of the given numbers better version
	 * @param 	a is an integer
	 * @param 	b is an integer
	 * @param 	c is an integer
	 * @param 	d is an integer
	 * @return	maximum number
	 */
	public static int findMaximumBetter(int a, int b, int c, int d) {
		int maxCD = c > d ? c : d;
		int maxBCD = b > maxCD ? b : maxCD;
		return a > b ? a : maxBCD;
	}
	
	/**
	 * Reverses a string
	 * PROVIDED BY INSTRUCTOR
	 * @param 	original string
	 * @return	reversed string
	 */
	public static String reverseString(String original) {
		String rValue = "";
		for ( int index = original.length(); index > 0; index -- )
			rValue += original.substring(index -1, index);
		return rValue;
	}
	
	/**
	 * Reverses the string using recursion
	 * @param	original string
	 * @return	reversed string
	 */
	public static String reverseStringR(String original) {
		if ( original.length() == 1 ) {
			return original;
		}
		return reverseStringR(original.substring(1)) + 
				original.substring(0, 1);
	}
	
	/**
	 * Reverse a string
	 * @param	original string
	 * @return	reversed string
	 */
	public static String reverseStringBetter(String original) {
		String rValue = "";
		for(int index = original.length() - 1; index >= 0; index --) {
			rValue += original.charAt(index);
		}
		return rValue;
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 5;
		int b = 1;
		int c = 2;
		int d = 1;
		String aString = "abcde";
		System.out.format("sumUpFromLowerToLarger( %d, %d ) = %s\n", 
				a, b, sumUpFromLowerToLarger(a, b));
		System.out.format("sumUpFromLowerToLargerBetter( %d, %d ) = %s\n", 
				a, b, sumUpFromLowerToLargerBetter(a, b));
		System.out.format("printBitRepresentation( %d ): %s \n", 
				a, printBitRepresentation(a));
		System.out.format("printBitRepresentationBetter( %d ): %s\n", 
				a ,printBitRepresentationBetter(a));
		System.out.format("findMaximum( %d, %d, %d, %d ) : %d \n", 
				a, b, c, d, findMaximum(a, b, c, d));
		System.out.format("findMaximumBetter( %d, %d, %d, %d ) : %d \n", 
				a, b, c, d, findMaximumBetter(a, b, c, d));
		System.out.format("reverseStringBetter(\"%s\") = %s\n", 
				aString, reverseString(aString));
		System.out.format("reverseString(\"%s\") = %s\n", 
				aString, reverseStringR(aString));
		System.out.format("reverseStringR(\"%s\") = %s\n", 
				aString, reverseStringBetter(aString));
	}

}
