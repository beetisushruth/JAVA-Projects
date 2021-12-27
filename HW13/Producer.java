/*
 * Producer.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Producer class
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
class Producer extends Thread {
	private Storage thisStorage;
	private int productionLimit;
	String type;

	/**
	 * Contructor for Producer
	 * 
	 * @param type
	 * @param thisStorage
	 */
	Producer(String type, Storage thisStorage) {
		this.type = type;
		this.thisStorage = thisStorage;
		this.productionLimit = thisStorage.getProductionLimit(type);
	}

	/**
	 * Run method
	 */
	public void run() {
		while (true) {
			try {
				int currentNumber = thisStorage.produce(type);
				if(currentNumber == productionLimit) break;
			} catch (InterruptedException e) {
				System.out.println("Exception occured while producing");
			}
		}
	}
}