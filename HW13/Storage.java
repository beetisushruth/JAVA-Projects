/*
 * Storage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Storage class
 * 
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
class Storage {

	public final static int MATCHES_LIMIT = 90;
	public final static int MATCH_BOX_LIMIT = 9;
	public final static int TOTAL_MATCHES_LIMIT = 1000;
	private int matchesCount = 0;
	private int matchBoxCount = 0;
	private int totalMatchesCount = 0;

	/**
	 * Produce method
	 * 
	 * @param type
	 * @throws InterruptedException
	 */
	public synchronized int produce(String type) throws InterruptedException {
		notify();
		if (type.equals("matches")) {
			if (matchesCount >= MATCHES_LIMIT) {
				System.err.println("Waiting thread matches");
				wait();
			} else {
				matchesCount++;
				totalMatchesCount++;
				System.err.println("Creating "+type+" | matches count: "+matchesCount+" matchbox count: "+matchBoxCount);
			}
		}
		if (type.equals("matchbox")) {
			if (matchBoxCount >= MATCH_BOX_LIMIT) {
				System.err.println("Waiting thread match box");
				wait();
			} else {
				matchBoxCount++;
				System.err.println("Creating "+type+" | matches count: "+matchesCount+" matchbox count: "+matchBoxCount);
			}
		}
		return totalMatchesCount;
	}

	public boolean isProducerDead() {
		if (totalMatchesCount == TOTAL_MATCHES_LIMIT)
			return true;
		return false;
	}

	/**
	 * Consume method
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void consume() throws InterruptedException {
		if (matchesCount >= 50 && matchBoxCount >= 1) {
			System.err.println("Consuming 50 matches and a matchbox and made final product");
			matchesCount -= 50;
			matchBoxCount -= 1;
			System.err.println("matches count: "+matchesCount+" matchbox count: "+matchBoxCount);
			notifyAll();
		} else {
			System.err.println("Waiting consumer");
			wait();
		}
	}

	public int getProductionLimit(String type) {
		return TOTAL_MATCHES_LIMIT;
	}
}
