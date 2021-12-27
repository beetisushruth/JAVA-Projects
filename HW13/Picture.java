/*
 * Picture.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.net.*;
import java.util.Vector;
import java.io.*;

/**
 * Picture class which has a 2 player game integrated with a client server code 
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class Picture {

	private static String HOST_NAME = "127.0.0.1";
	private static int PORT = 42069;
	private static Socket readSocket;
	private static BufferedReader inputReader;
	private static PrintWriter outputWriter;
	private static String[] playerFiles = new String[] { "superman.txt", "batman.txt" };
	private static String[] playerWords = new String[] { "Superman", "Batman" };
	private static Vector<Vector<Character>> pictureOne;
	private static Vector<Vector<Character>> pictureTwo;
	
	/**
	 * Parse the command line arguments and sets variables. -you superman.txt
	 * Superman -me batman.txt Batman -server 127.0.0.1 -port 2233
	 */
	public static void parseArgs(String args[]) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-server")) {
				HOST_NAME = args[++i];
			} else if (args[i].equals("-port")) {
				PORT = Integer.parseInt(args[++i]);
			} else if (args[i].equals("-you")) {
				playerFiles[0] = args[i + 1];
				playerWords[0] = args[i + 2];
			} else if (args[i].equals("-me")) {
				playerFiles[1] = args[i + 1];
				playerWords[1] = args[i + 2];
			}
		}
	}

	/**
	 * Connect to the server
	 */
	public static void getServerConnection() {
		try {
			System.out.println("Connecting to host: " + HOST_NAME + " on port: " + PORT);
			readSocket = new Socket(HOST_NAME, PORT);
			System.out.println("Connection Sucessful");
			outputWriter = new PrintWriter(readSocket.getOutputStream(), true);
			inputReader = new BufferedReader(new InputStreamReader(readSocket.getInputStream()));
		} catch (Exception e) {
			System.out.println("Error occured while connecting to server!");
			System.exit(1);
		}
	}

	/**
	 * Close existing server connection
	 */
	private static void closeServerConnection() {
		try {
			inputReader.close();
			outputWriter.close();
		} catch (Exception e) {
			System.out.println("Exception occured while closing server connection");
		}
	}

	public static void getPicturesFromServer() {
		outputWriter.println("picture:" + playerFiles[0]);
		pictureOne = getPictureVector(0);
		outputWriter.println("picture:" + playerFiles[1]);
		pictureTwo = getPictureVector(1);
		outputWriter.println("END");
		closeServerConnection();
	}

	/**
	 * Get picture vector
	 * @param playerNumber
	 * @return vector of vector characters
	 */
	private static Vector<Vector<Character>> getPictureVector(int playerNumber) {
		Vector<Vector<Character>> playerPicture = new Vector<>();
		String line;
		try {
			while ((line = inputReader.readLine()) != null) {
				if (line.contains("Error:")) {
					throw new Exception(line.substring(6, line.length()));
				}
				if(line.equals("END")) break;
				Vector<Character> vector = new Vector<>();
				for(int i=0; i<line.length(); i++) {
					vector.add(line.charAt(i));
				}
				playerPicture.add(vector);
			}
		} catch (Exception e) {
			System.out.println("Error occurred while client processing response: "+e.getMessage());
			System.exit(1);
		}
		return playerPicture;
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		parseArgs(args);
		getServerConnection();
		getPicturesFromServer();
		Game.startGame(playerWords, pictureOne, pictureTwo);
	}
}