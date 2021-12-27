/*
 * Test.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Test class to test the Producer Consumer problem
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class Test {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]) {
		Storage theStorage = new Storage();
		Producer producerMatches = new Producer("matches", theStorage);
		Producer producerMatchBox = new Producer("matchbox", theStorage);
		Consumer consumer = new Consumer(theStorage);
		producerMatches.start();
		producerMatchBox.start();
		consumer.start();
	}
}