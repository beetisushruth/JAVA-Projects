/*
 * Test.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Consumer class
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
class Consumer extends Thread {
	private Storage thisStorage;
	
	/**
	 * Consumer constructor
	 * @param thisStorage
	 */
	public Consumer(Storage thisStorage) {
		this.thisStorage = thisStorage;
	}

	/**
	 * Run method
	 */
	public void run() {
		while (true) {
			try {
				this.thisStorage.consume();
				if(thisStorage.isProducerDead()) break;
			} catch (InterruptedException e) {
				System.out.println("Error occured while consuming");
			}
		}
	}
}