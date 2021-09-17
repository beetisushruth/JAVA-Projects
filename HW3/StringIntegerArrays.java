/*
 * StringIntegerArray.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.Arrays;

/**
 * Class to demonstrate properties of String, Integer, Arrays
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */
public class StringIntegerArrays {

	/**
	 * Main method to execute all the statements
	 * @param args
	 */
	public static void main(String[] args) {
		// this part is mentioned in the question
		@SuppressWarnings("unused")
		String aString, bString, cString, dString, eString, 
		fString, gString, hString, iString;
		
		if ( args.length == 1 ) {
            aString = "123425" + "25";
            bString = "12342525";
            cString = "5";
            dString = "25" + "2" + cString;
            eString = "a, b, c, d, e, f, g, h, i, j, k, l, m, n, "
            		+ "o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "A, B, C, D, E, F, G, H, I, J, K, *, M, N, "
            		+ "O, P, Q, R, S, T, U,	V, W, X, Y, Z";
            gString = aString + ( bString + cString ) + dString;
            hString = "2525" +  "+" + "2525";
            iString = "2525+2525";
		} else {
            aString = "12342" + new String("" + 5) +  "25";
            bString = "12342525";
            cString = "5";
            dString = "" + "25" + "" + "2" + cString;
            eString = "b, c, d, e, f, g, h, i, j, k, l, m, n, "
            		+ "o, p, q, r, s, t, u, v, x, y, z";
            fString = "B, C, D, E, F, G, H, I, J, K, *, M, N, "
            		+ "O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = ( ( aString + bString )  + "" + 5 ) + dString;
            hString = "2525" +  "+" + "2525";
            iString = "25" + ( 2 + 5 ) + "+2525";
		}
		// .equals() compares only the values of the strings
		System.out.println(aString.equals(bString));
		// compares strings as objects, checks the reference also
		System.out.println(aString == bString);
		System.out.println(aString.equals(dString));
		System.out.println(aString == dString);
		System.out.println(hString.equals(iString));
		System.out.println(hString == iString);
		char first = aString.charAt(0);
		char second = aString.charAt(1);
		// substring is used to extract out a part of the string
		System.out.println(dString.substring(first - '0' - 1, second - '0'));
		int starPosition = fString.indexOf('*');
		// charAt() returns the character at any index mentioned
		System.out.println(eString.charAt(starPosition));
		String uniqueBString = removeDuplicates(bString);
		String sortedBString = sortString(uniqueBString);
		char thirdCharacter = sortedBString.charAt(2);
		char fourCharacter = sortedBString.charAt(3);
		System.out.println(sortedBString.substring(thirdCharacter - '0', 
				fourCharacter - '0'));
	}

	/**
	 * Function to sort the string
	 * @param s is a string
	 * @return	sorted string
	 */
	public static String sortString(String s) {
		// break string down to char array
		char[] charArray = s.toCharArray();
		// sort the char array using built-in method
		Arrays.sort(charArray);
		// rebuild the sorted string
		String sortedString = new String(charArray);
		return sortedString;
	}
	
	public static String removeDuplicates(String str) {
		str = str.replaceAll("(.)(?=.*\\1)", "");
		return str;
	}
}
