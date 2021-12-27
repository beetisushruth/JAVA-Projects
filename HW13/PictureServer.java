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
public class PictureServer extends Thread {

	private static ServerSocket serverSocket;
	private static int PORT = 42069;
	private static BufferedReader inputReader;
	private static PrintWriter outputWriter;

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
	 * Process Client Request
	 * 
	 * @param inputReader
	 * @param outputWriter
	 * @throws IOException
	 */
	private static void processClientRequest(BufferedReader inputReader, PrintWriter outputWriter) {
		String line;
		BufferedReader fileReader = null;
		try {
			while ((line = inputReader.readLine()) != null) {
				if (line.contains("picture:")) {
					String fileName = line.substring(8, line.length());
					fileReader = new BufferedReader(
							new InputStreamReader(new FileInputStream(fileName)));
					String pictureLine;
					while ((pictureLine = fileReader.readLine()) != null) {
						outputWriter.println(pictureLine);
					}
					outputWriter.println("END");
				} else if(line.contains("END")) {
					if(fileReader != null) fileReader.close();
					break;
				} else {
					outputWriter.println("Error:Unknown request to the server");
				}
			}
		} catch (FileNotFoundException e) {
			if (outputWriter != null)
				outputWriter.println("Error:Server cannot process requested file");
		} catch (IOException e) {
			if (outputWriter != null)
				outputWriter.println("Error:Error occured while reading client input");
		} catch (Exception e) {
			System.out.println("Unknown exception occured");
		} 
	}

	/**
	 * Start picture server
	 */
	public static void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Listening on port: " + serverSocket.getLocalPort());
			for (;;) {
				Socket connectionToClientSocket = serverSocket.accept();
				inputReader = new BufferedReader(new InputStreamReader(connectionToClientSocket.getInputStream()));
				outputWriter = new PrintWriter(connectionToClientSocket.getOutputStream(), true);
				processClientRequest(inputReader, outputWriter);
				connectionToClientSocket.close();
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