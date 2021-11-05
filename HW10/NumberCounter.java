/*
 * NumberCounter.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


/**
 * Number Counter class to read csv data from multiple sources
 * and console output it
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class NumberCounter {
	
	/**
	 * Populate the map with frequency of numbers
	 * @param numbers	numbers
	 * @param map	map to return
	 */
	private static void fillTheMap(String[] numbers, Map<String, Integer> map) {
		for(String number : numbers) {
			if(map.containsKey(number)) {
				map.put(number, map.get(number) + 1);
			} else {
				map.put(number, 1);
			}
		}
	}

	/**
	 * Read from standard In
	 * @return Map
	 */
	private static Map<String, Integer> readFromStdIn() {
		return readInputStream(new InputStreamReader(System.in));
	}
	

	/**
	 * Read from the file input
	 * @param fileName
	 * @return map with frequency count
	 */
	private static Map<String, Integer> readFromFileInput(String fileName) {
		String[] fileNameArray = fileName.split("\\.");
		String extension = fileNameArray[fileNameArray.length - 1];
		InputStreamReader inputStreamReader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			if(extension.equals("gz")) {
				GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
				inputStreamReader = new InputStreamReader(gzipInputStream);
			} else {
				inputStreamReader = new InputStreamReader(fileInputStream);
			}
			return readInputStream(inputStreamReader);
		} catch(IOException exception) {
			System.out.println("Couldn't process the file "+fileName);
		} 
		return null;
	}
	
	/**
	 * Read from the input stream
	 * @param inputStreamReader	input stream reader object
	 * @return	map with frequency count
	 */
	private static Map<String, Integer> readInputStream(InputStreamReader inputStreamReader) {
		Map<String, Integer> map = new HashMap<>();
		try(BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			bufferedReader.readLine();	// swallowing the title
			String line;
			while((line = bufferedReader.readLine()) != null) {
				String ticket = line.split(",")[1];
				String[] numbers = ticket.split(" ");
				fillTheMap(numbers, map);
			}
		} catch (IOException e) {
			System.out.println("Couldn't process the file: "+e.getMessage());
		}
		return map;
	}


	/**
	 * Process file takes in the arguments
	 * @param args arguments
	 * @return map with frequency count
	 */
	private static Map<String, Integer> processArgs(String[] args) {
		Map<String, Integer> frequencyMap = null;
		if(args.length == 0) {
			frequencyMap = readFromStdIn();
		} else {
			boolean match = Pattern.matches("^.+\\.(gz|csv)$", args[0]);
			if(match) {
				frequencyMap = readFromFileInput(args[0]);
			} else {
				System.out.println("File input must be of the form .csv or .gz");
			}
		}
		return frequencyMap;
	}

	/**
	 * Main method
	 * @param args arguments
	 */
	public static void main(String[] args) {
		Map<String, Integer> frequencyMap = processArgs(args);
		if(frequencyMap != null) {
			for(int i=1; i<=frequencyMap.size(); i++) {
				String key = String.format("%02d", i);
				System.out.print(key+": "+frequencyMap.get(key)+" ");
				if(i%4 == 0) System.out.println();
			}
		}
	}
	
}
