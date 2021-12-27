/*
 * Picture.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.net.*;
import java.util.Vector;

/**
 * Picture class which has a 2 player game integrated with a client server code
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class PictureDG {

	private static String HOST_NAME = "127.0.0.1";
	private static int PORT = 42420;
	private static DatagramSocket dgSocket;
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
			dgSocket = new DatagramSocket();
		} catch (Exception e) {
			System.out.println("Error occured while connecting to server!");
			System.exit(1);
		}
	}

	/**
	 * Get picture data from server
	 * 
	 * @param fileName
	 * @return vector of vector of characters
	 */
	public static Vector<Vector<Character>> getPictureDataFromServer(String fileName) {
		Vector<Vector<Character>> picture = new Vector<>();
		try {
			byte buf[] = ("picture:" + fileName).getBytes();
			InetAddress aInetAddress = InetAddress.getByName(HOST_NAME);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, aInetAddress, PORT);
			dgSocket.send(packet);
			String received;
			byte[] receveingBuffer = new byte[80];
			DatagramPacket receivingPacket = new DatagramPacket(receveingBuffer, receveingBuffer.length);
			while (true) {
				dgSocket.receive(receivingPacket);
				received = new String(receivingPacket.getData());
				if (received.trim().contains("END"))
					break;
				Vector<Character> vector = new Vector<>();
				for (int i = 0; i < received.length(); i++) {
					vector.add(received.charAt(i));
				}
				picture.add(vector);
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting picture data " + e);
		}
		return picture;
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		parseArgs(args);
		getServerConnection();
		pictureOne = getPictureDataFromServer(playerFiles[0]);
		pictureTwo = getPictureDataFromServer(playerFiles[1]);
		Game.startGame(playerWords, pictureOne, pictureTwo);
	}
}