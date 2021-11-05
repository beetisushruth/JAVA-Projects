// original from: http://rosettacode.org/wiki/Pi_set#Java
// modified for color

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import java.util.Random;
import java.util.regex.Pattern;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.zip.GZIPInputStream;

public class Visual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int LENGTH_OF_SQUARE = 3;
	private final int LENGTH = 330;
	private final int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
	private BufferedImage theImage;
	private String fileName = null;
	Reader input;

	public Visual() {
		super("Visual");
		setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Visual(String fileName) {
		this();
		this.fileName = fileName;
	}

	private char nextDigit(BufferedReader input) {
		char buf[] = new char[1];
		try {
			input.read(buf);
			while (!('0' <=  buf[0])  && (buf[0] <= '9')) {
				input.read(buf);
	        }
		} catch (IOException e) {
			System.out.println("Couldn't read the file");
		}
		return buf[0];
	}

	private void saveImage(BufferedImage theImage) {
		String suffix = "png";
		String outputFileName = fileName == null ? "output" : fileName + "_output" + "." + suffix;
		try {
			File outputfile = new File(outputFileName);
			ImageIO.write(theImage, suffix, outputfile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void fillSquare(int xOrig, int yOrig, int color) {
		for (int x = 0; x < LENGTH_OF_SQUARE; x++)
			for (int y = 0; y < LENGTH_OF_SQUARE; y++)
				theImage.setRGB(xOrig + x, yOrig + y, color);
	}

	/**
	 * Get Input Stream Reader
	 * 
	 * @return input stream reader
	 * @throws Exception
	 */
	public InputStreamReader getInputStreamReader() throws Exception {
		InputStreamReader inputStreamReader = null;
		if (fileName == null) {
			inputStreamReader = new InputStreamReader(System.in);
		} else {
			boolean match = Pattern.matches("^.+\\.(gz|txt)$", fileName);
			if (!match)
				throw new Exception("Invalid file input");
			String[] fileNameArray = fileName.split("\\.");
			String extension = fileNameArray[fileNameArray.length - 1];
			InputStream inputStream = new FileInputStream(fileName);
			if (extension.equals("gz")) {
				inputStream = new GZIPInputStream(inputStream);
			}
			inputStreamReader = new InputStreamReader(inputStream);
		}
		return inputStreamReader;
	}

	public void createImage() {
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Random ran = new Random(256*256*256);
		int black = Color.BLACK.getRGB(); // you might like to change the colors if you like
		try (BufferedReader input = new BufferedReader(getInputStreamReader())) {
			for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
				for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
			        int next = ran.nextInt();
					char digit = nextDigit(input);
					fillSquare(x, y, digit % 2 == 0 ? black : next);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		repaint();
		saveImage(theImage);
		System.exit(0);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) {
		Visual aVisual = new Visual(args.length == 1 ? args[0] : null);
		aVisual.setVisible(true);
		aVisual.createImage();
	}
}