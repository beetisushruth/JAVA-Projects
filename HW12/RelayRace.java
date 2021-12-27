import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

public class RelayRace extends Thread {

	int playerId;
	public static int numOfRunners = 4;
	public static int numOfLaps = 3;
	public static String baton = "BATON";
	public static List<Integer> orderOfRunners = new ArrayList<>();
	public static int count = 0;
	public static int nextExpectedPlayer = 0;

	public RelayRace(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * Parse the args
	 * 
	 * @param args
	 */
	private static void parseArgs(String[] args) {
		try {
			if (args.length < 0)
				throw new Exception("Invalid arguments");
			if (args.length >= 1) {
				numOfRunners = Integer.parseInt(args[0]);
			}
			if (args.length >= 2) {
				numOfLaps = Integer.parseInt(args[1]);
			}
			if (numOfRunners <= 0 && numOfLaps <= 0)
				throw new Exception("Invalid arguments");
		} catch (Exception e) {
			System.out.println(
					"Invalid arguments: " + "Expected [Number of players : integer] " + "[Number of laps: integer]");
			System.exit(0);
		}
	}

	public void waitPlayer() {
		try {
			baton.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Overridden run method
	 */
	@Override
	public void run() {
		while (count < numOfLaps * numOfRunners) {
			runLap();
		}
	}

	/**
	 * Run lap simulates the relay race among different threads
	 */
	private void runLap() {
		synchronized (baton) {
			int currentRunner = count % numOfRunners;
			String newLap = String.format("New lap (lap# = %d) is handed to runner %d", count / numOfRunners + 1,
					this.playerId + 1);
			String lap = String.format("baton is handed to player %d", this.playerId + 1);
			System.err.println(currentRunner == 0 ? newLap : lap);
			//runners running the first lap
			if (orderOfRunners.size() < numOfRunners) {
				orderOfRunners.add(this.playerId);
				count++;
				if (orderOfRunners.size() == numOfRunners) {
					baton.notify();
				}
				waitPlayer();
			// from the second lap
			} else {
				if (orderOfRunners.get(currentRunner) == this.playerId) {
					count++;
				}
				if (count == numOfLaps * numOfRunners) {
					baton.notifyAll();
				} else {
					baton.notify();
					waitPlayer();
				}
			}
		}
	}

	/**
	 * Main method
	 * 
	 * @param args system arguments
	 */

	public static void main(String[] args) {
		parseArgs(args);
		RelayRace[] relayRaces = new RelayRace[numOfRunners];
		for (int i = 0; i < numOfRunners; i++) {
			relayRaces[i] = new RelayRace(i);
			relayRaces[i].start();
		}
	}
}
