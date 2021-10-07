/*
 * Test.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Test class for Binary Search Tree (Sorted Storage)
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class Test {
	
	/**
	 * Test sorted storage class 
	 * @param sortedStorage sorted storage class
	 */
	public static void testIt(SortedStorage sortedStorage) {
        String toInsert[] = { "3", "3", "2", "1", "4", "5", "10", "6", ""};
		for (int index = 0; index < toInsert.length; index ++ ) {
            System.out.println("add(" + toInsert[index] + "): "  + 
            		sortedStorage.addNode(toInsert[index]));
        }
		System.out.println("add(null): "  + sortedStorage.addNode(null));
		System.out.println("add(null): "  + sortedStorage.addNode(null));
		System.out.println("find(2): "  + sortedStorage.findNode("2"));
		System.out.println(sortedStorage);
		System.out.println("find(5): "  + sortedStorage.findNode("5"));
		System.out.println("delete(3)" + sortedStorage.deleteNode("3"));
		System.out.println("delete(4)" + sortedStorage.deleteNode("4"));
		System.out.println("delete(5)" + sortedStorage.deleteNode("5"));
		System.out.println("delete(8)" + sortedStorage.deleteNode("8"));
		System.out.println("delete(10)" + sortedStorage.deleteNode("10"));
		System.out.println(sortedStorage);
		System.out.println("delete(6)" + sortedStorage.deleteNode("6"));
		System.out.println("delete(2)" + sortedStorage.deleteNode("2"));
		System.out.println("delete(1)" + sortedStorage.deleteNode("1"));
		System.out.println("delete(null)" + sortedStorage.deleteNode(null));
		System.out.println("delete(null)" + sortedStorage.deleteNode(null));
		System.out.println("delete(null)" + sortedStorage.deleteNode(null));
		System.out.println("delete(6)" + sortedStorage.deleteNode("6"));
		System.out.println(sortedStorage);
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SortedStorage sortedStorage = new SortedStorage();
		testIt(sortedStorage);
	}
}
