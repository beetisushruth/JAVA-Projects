/*
 * Test.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.Arrays;
import java.util.List;

/**
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class Test {
	
	/**
	 * Test method to test the sorted storage
	 * @param sortedStorage
	 * @param testCases
	 */
	public static <E  extends Comparable<E>> void test(Storage<E> sortedStorage, List<E> testCases) {
		for(int i=0; i<testCases.size(); i++) {
			sortedStorage.add(testCases.get(i));
		}
		System.out.println(sortedStorage);
		for(int i=0; i<testCases.size(); i++) {
			sortedStorage.delete(testCases.get(i));
		}
		System.out.println(sortedStorage);
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Storage<String> sortedStorageStrings = new SortedStorageSetWithNulls<>();
		String[] stringInput = new String[] { "1", "2", "3", "0", "5"};
		List<String> stringList = Arrays.asList(stringInput);
		test(sortedStorageStrings, stringList);
		
		Storage<Integer> sortedStorageIntegers = new SortedStorageSetWithNulls<>();
		Integer[] integerInput = new Integer[] {1, 2, 3, 0, 5};
		List<Integer> integerList = Arrays.asList(integerInput);
		test(sortedStorageIntegers, integerList);
		
		Storage<Integer> sortedStorageLong = new SortedStorageSetWithNulls<>();
		Integer[] longInput = new Integer[] {1, 2, 3, 0, 5};
		List<Integer> longList = Arrays.asList(longInput);
		test(sortedStorageLong, longList);
		
		Storage<Human> sortedStorageHuman = new SortedStorageSetWithNulls<>();
		Human[] humanInput = new Human[] {new Human("01-03-1969", "Schumacher", "1"), 
										  new Human("02-22-1949", "Lauda", "2"),
										  new Human("03-21-1960", "Senna", "3"),
										  new Human("01-07-1985", "Hamilton", "4"),
										  new Human("09-30-1997", "Verstappen", "5")};
		List<Human> humanList = Arrays.asList(humanInput);
		test(sortedStorageHuman, humanList);
		
		Storage<Address> sortedStorageAddress = new SortedStorageSetWithNulls<>();
		Address[] addressInput = new Address[] {
				new Address(7, "Garden Street Cumming", "Old Town", "GA", 300401), 
				new Address(42, "Shady Lane Randolph", "New Town", "MA", 23680),
				new Address(4, "Gartner Street Wasilla", "West Street", "AK", 99654),
				new Address(37, "Orange Court Woodside", "North Face", "NY", 11377),
				new Address(9800, "East Wild Horse Ave.", "Falls Church", "VA", 22041)
				};
		List<Address> addressList = Arrays.asList(addressInput);
		test(sortedStorageAddress, addressList);
		
		Storage<MusicLP> sortedStorageMusicLP = new SortedStorageSetWithNulls<>();
		MusicLP[] musicLPInput = new MusicLP[] {
				new MusicLP(1967, "The Velvet Underground", "The Velvet Underground & Nico", 
						Float.valueOf("20.50"), 9), 
				new MusicLP(1975, "Pink Floyd", "Wish You Were Here", Float.valueOf("18.36"), 5),
				new MusicLP(1975, "The Beatles", "Revolver", Float.valueOf("25.29"), 8),
				};
		List<MusicLP> musicLPList = Arrays.asList(musicLPInput);
		test(sortedStorageMusicLP, musicLPList);
	}
}
