import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Number.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Program to print all numbers whose sum of squares of all digits eventually
 * results in 1 using concurrent programming
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */

public class Number extends Thread {

	// global map
	private static Map<Integer, String> globalMap = new HashMap<>();
	// local map
	private Map<Integer, String> localMap = null;
	// default constants
	private static String executionType = "global";
	private static int numOfThreads = 10;
	private static int maxBound = 100;
	private int start;
	private int end;

	public Number(int start, int end, boolean isLocal) {
		this.start = start;
		this.end = end;
		if (isLocal)
			localMap = new HashMap<>();
	}

	/**
	 * Parse arguments Accepts any arguments of the required format no of threads |
	 * max bound | (global | local) Arguments not provided would be defaulted
	 * 
	 * @param args
	 */
	private static void parseArgs(String[] args) {
		try {
			if (args.length > 0)
				numOfThreads = Integer.parseInt(args[0]);
			if (args.length > 1)
				maxBound = Integer.parseInt(args[1]);
			if (args.length > 2)
				executionType = args[2];
			if (!(executionType.equals("global") || executionType.equals("local")) || maxBound <= 0
					|| numOfThreads <= 0)
				throw new Exception("Illegal arguments");
		} catch (Exception e) {
			System.out.println("Illegal system arguments provided! Usage: java Number [positive "
					+ "integer: no of threads] [positive integer: " + "max bound] [global | local]");
			System.exit(0);
		}
	}

	/**
	 * Overrided run method
	 */
	@Override
	public void run() {
		synchronized (globalMap) {
			Map<Integer, String> map = generateAlmostPerfectNumbers(start, end);
			if (executionType.equals("local")) {
				this.localMap = map;
			} else {
				globalMap.putAll(map);
			}
		}
	}

	/**
	 * Generate almost perfect numbers and put them in map
	 * 
	 * @param start
	 * @param end
	 * @return map
	 */
	public static Map<Integer, String> generateAlmostPerfectNumbers(int start, int end) {
		Map<Integer, String> map = new HashMap<>();
		for (int i = start; i < end; i++) {
			String sumString = getIfAlmostPerfectNumber(i);
			if (sumString.length() > 0) {
				map.put(i, sumString);
			}
		}
		return map;
	}

	/**
	 * Get if almost perfect number, returns the string leading to sum 1 else return
	 * empty string
	 * 
	 * @param number
	 * @return string
	 */
	private static String getIfAlmostPerfectNumber(int number) {
		Set<Integer> numberSeen = new HashSet<>();
		String totalSumString = "";
		while (!numberSeen.contains(number)) {
			numberSeen.add(number);
			int sum = 0;
			String sumString = "";
			while (number != 0) {
				sumString = ((number % 10) + "^2 +") + sumString;
				sum += (number % 10) * (number % 10);
				number = number / 10;
			}
			sumString = sumString.substring(0, sumString.length() - 1) + "= " + sum;
			totalSumString += sumString + " | ";
			number = sum;
		}
		return number == 1 ? totalSumString.substring(0, totalSumString.length() - 2) : "";
	}

	/**
	 * Get local map
	 * 
	 * @return map
	 */
	public Map<Integer, String> getLocalMap() {
		return this.localMap;
	}

	/**
	 * To string method to print the localmap
	 */
	@Override
	public String toString() {
		return "Number [localMap=" + localMap + ", start=" + start + ", end=" + end + "]";
	}

	/**
	 * Main method
	 * 
	 * @param args system arguments
	 */

	public static void main(String[] args) {
		parseArgs(args);
		int start = 1;
		int end = 1;
		Number[] listOfNumber = new Number[numOfThreads];
		for (int i = 0; i < numOfThreads; i++) {
			start = end;
			end = start + maxBound / numOfThreads;
			end = i < maxBound % numOfThreads ? end + 1 : end;
			Number number = new Number(start, end, executionType.equals("local"));
			listOfNumber[i] = number;
		}
		for (int i = 0; i < listOfNumber.length; i++) {
			listOfNumber[i].start();
		}
		for (int i = 0; i < listOfNumber.length; i++) {
			try {
				listOfNumber[i].join();
				if (listOfNumber[i].getLocalMap() != null) {
					globalMap.putAll(listOfNumber[i].getLocalMap());
				}
			} catch (InterruptedException e) {
				System.out.println("Interruption occured");
			}
		}
		for (int i = 1; i <= maxBound; i++) {
			if (globalMap.containsKey(i)) {
				System.out.println(i + ": " + globalMap.get(i));
			}
		}
	}
}
