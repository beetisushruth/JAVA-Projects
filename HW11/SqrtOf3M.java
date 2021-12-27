/*
 * SqrtOf3M.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * SqrtOf3M which calculates sqrt(2) using multiple threads 
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class SqrtOf3M{
	
	private static int numberOfThreads = 10;
	private static int maxBound = 100;

	/**
	 * Parse Arguments
	 * @param args arguments
	 */
	public static void parseArgs(String[] args) {
		try {
			if (args.length >= 1) {
				numberOfThreads = Integer.parseInt(args[0]);
			}
			if (args.length > 1) {
				maxBound = Integer.parseInt(args[1]);
			}
			if (numberOfThreads <= 0 || maxBound <= 0) {
				throw new Exception("Illegal arguments!");
			}
		} catch (Exception e) {
			System.out.println(
					"Illegal system arguments provided! Usage: java SqrtOF3M [positive integer: no of threads] [positive integer: max bound]");
			System.exit(0);
		}
	}


	/**
	 * Main method
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args){
		parseArgs(args);
		BigDecimal product = BigDecimal.ONE;
		MathContext mc = new MathContext(30);
		int start = 0;
		int end = 0;
		Multiplication[] listOfMultiplication = new Multiplication[numberOfThreads];
		for(int i=0; i<numberOfThreads; i++) {
			start = end;
			end = start + maxBound/numberOfThreads;
			end = i < maxBound%numberOfThreads ? end + 1 : end;
			Multiplication multiplication = new Multiplication(start, end);
			listOfMultiplication[i] = multiplication;
		}
		for(int i=0; i<listOfMultiplication.length; i++) {
			listOfMultiplication[i].start();
		}
		for(int i=0; i<listOfMultiplication.length; i++) {
			try {
				listOfMultiplication[i].join();
			} catch (InterruptedException e) {
				System.out.println("Interruption occured");
			}
			product = product.multiply(listOfMultiplication[i].getProduct(), mc);
		}
		System.out.println(BigDecimal.ONE.divide(product, mc));
	}
}

/**
 * Multiplication class which extends Thread
 * @author sushruth
 * @author mallika
 */
class Multiplication extends Thread {

	private int start;
	private int  end;
	private BigDecimal product = BigDecimal.ONE;
	
	public Multiplication(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Overrided run method
	 */
	@Override
	public void run() {
		MathContext mc = new MathContext(30);
		for(int i=start; i<end; i++) {
			BigDecimal denominator = BigDecimal.valueOf(4*i + 2);
			denominator = denominator.multiply(denominator, mc);
			BigDecimal value = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(denominator, mc));
			product = product.multiply(value, mc);
		}
	}
	
	/**
	 * Get product
	 * @return product
	 */
	public BigDecimal getProduct() {
		return product;
	}
	
	/**
	 * Get start
	 * @return start
	 */
	public int getStart() {
		return this.start;
	}
	
	/**
	 * Set start
	 * @param start
	 */
	public void setStart(int start) {
		this.start = start;
	}
	
	/**
	 * Get end
	 * @return
	 */
	public int getEnd() {
		return this.end;
	}
	
	/**
	 * Set end
	 * @param end
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Overrided to string method
	 */
	@Override
	public String toString() {
		return "Multiplication [start=" + start + ", end=" + end + ", product=" + product + "]";
	}
}

