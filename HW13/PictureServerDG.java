/*
 * PictureServer.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.net.*;
import java.io.*;

/**
 * Picture Server class which serves picture files
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class PictureServerDG extends Thread {

	private static DatagramSocket dgSocket;
	private static int PORT = 42420;

	/**
	 * Parse the command line arguments and sets variables.
	 */
	private static void parseArgs(String args[]) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-port")) {
				PORT = Integer.parseInt(args[++i]);
			} else {
				System.out.println("Using default port: " + PORT + " to start the server");
			}
		}
	}

	/**
	 * Start picture server
	 */
	public static void startServer() {
		try {
			dgSocket = new DatagramSocket(PORT);
			System.out.println("Listening on port: " + dgSocket.getLocalPort());
			for (;;) {
				byte[] buf = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				dgSocket.receive(packet);
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				String request = new String(packet.getData());
				if (request.contains("picture:")) {
					String fileName = request.substring(8, request.length());
					BufferedReader fileReader = new BufferedReader(
							new InputStreamReader(new FileInputStream(fileName.trim())));
					String pictureLine;
					while ((pictureLine = fileReader.readLine()) != null) {
						byte[] bytes = pictureLine.getBytes();
						DatagramPacket newPacket = new DatagramPacket(bytes, bytes.length, address, port);
						dgSocket.send(newPacket);
					}
					String end = "END";
					dgSocket.send(new DatagramPacket(end.getBytes(), end.getBytes().length, address, port));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		parseArgs(args);
		startServer();
	}
}