/*
 * X.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * 
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 * 
 * @description There are various scenarios which can be emulated by putting the
 *              sleep statements at various places Main core logic of this
 *              program is this: The key(Object o) on which the part of run
 *              method code is synchronized changes during the execution of the
 *              code A thread must finish executing the synchronized block of
 *              code before the key is further changed, otherwise it will go
 *              into eternal wait state.
 * 
 *              Scenario 1: sleep statement in the constructor before changing
 *              the object Output Prediction: Very likely one thread (Thread 1)
 *              will be in forever wait state. Output: RRRLL
 * 
 *              Scenario 2(No sleep statements): All three right arrows and
 *              three left arrows output. Occurs when the third thread also
 *              changes the Object o and all three threads can pass the
 *              synchronise block one by one under the same key Output: RRRLLL
 * 
 *              Scenario 3: similar output to scenario 1 but sleep statement
 *              else where Output: RRRLL
 * 
 *              Scenario 4: Put sleep statement after the object has been
 *              assigned in the constructor for every object. RRLLR
 * 
 *              Scenario 5: Ends in a eternal wait but this time for the main
 *              thread Output: RRLLR
 */
public class X extends Thread {
	static Object o = new Object();
	static int counter = 0;
	int id;

	public X(int id) {
		this.id = id;
		// sleepNow(); // scenario 1
		o = new Object();
		System.err.println("In " + this.id + "'s constructor " + o.toString());
		// sleepNow(); // scenario 4
	}

	public void sleepNow() {
		try {
			sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void run() {
		if (id == 0) {
			new X(1).start();
			// sleepNow(); // scenario 3
			new X(2).start();
			// sleepNow(); // scenario 5
		}
		System.err.println(this.id + " is at the start of synchronized block " + o.toString());
		synchronized (o) {
			try {
				System.err.println(id + " --->");
				o.notifyAll();
				System.err.println("Before wait " + this.id);
				o.wait();
				o.notifyAll();
				System.err.println(id + " <---");
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String args[]) {
		new X(0).start();
	}
}