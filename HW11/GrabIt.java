/*
 * GrabIt.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Grab it class which demonstrates how synchronization can be used across
 * multiple thread objects
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class GrabIt extends Thread {
	
	private static int rounds = 100;
	public static String key = "marble";
	private int playerId;
	private static Map<Integer, Integer> playerWinCount = new HashMap<>();

	public GrabIt(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * Grab method which gives the access monitor
	 * to a single thread only
	 */
	public void grab() {
		synchronized (key) {
			if (rounds > 0) {
				if (playerWinCount.containsKey(this.playerId)) {
					playerWinCount.put(this.playerId, playerWinCount.get(this.playerId) + 1);
				} else {
					playerWinCount.put(this.playerId, 1);
				}
				rounds--;
			}
		}
		try {
			sleep((int) (10 * Math.random()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Overrided run method
	 */
	@Override
	public void run() {
		while (rounds > 0) {
			grab();
		}
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		int players = 10;
		List<Thread> listOfThreads = new ArrayList<>();
		if (args.length > 0) {
			try {
				players = Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("Illegal argument. Number of players set to " + players);
			}
		}
		for (int i = 0; i < players; i++) {
			Thread t = new GrabIt(i);
			listOfThreads.add(t);
			t.start();
		}
		for (int i = 0; i < players; i++) {
			try {
				listOfThreads.get(i).join();
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted");
			}
		}
		for (Integer player : playerWinCount.keySet()) {
			System.out.format("player %d grabbed so many: %d marbles\n", player, playerWinCount.get(player));
		}
	}
}
