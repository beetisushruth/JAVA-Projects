
public class F {

	private int noSystemExit() {
		try {
			throw new Exception("1");
		} catch (Exception e) {
			System.out.println("2");
			return 0;
		} finally {
			System.out.println("3 finally");
		}
		// return 3; // does not compile
	}

	private int noExeption() {
		try {
			int x = 1 - 1;
			System.out.println("inside try: 1");
		} catch (Exception e) {
			System.out.println("inside catch: 2");
		} finally {
			System.out.println("inside finally: 3 ");
		}
		return 3; // does not compile
	}

	private int anExeption1() {
		int[] anArray = new int[1];
		try {
			anArray[2] = 1 / 0;
			System.out.println("inside try: 1");
			return 0;
		} catch (ArithmeticException e) {
			anArray[2] = 0;
			System.out.println(anArray[2] + "inside catch: 2");
			return 1;
		} finally {
			System.out.println("inside finally: 3 ");
			return 2;
		}
	}

	private int anExeption2() {
		int[] anArray = new int[1];
		try {
			anArray[2] = 0;
			anArray[2] = 1 / 0;
			System.out.println("inside try: 1");
			return 0;
		} catch (ArithmeticException e) {
			System.out.println("inside catch: 2");
			return 1;
		} catch (Exception e) {
			System.out.println(e);
		} finally {

			System.out.println("inside finally: 3 ");
			return 2;
		}
		// return 3; // does not compile
	}

	private int withSystemExit(){
		int k;
		try {
			k= 2;
			throw new Exception("4");
		} catch (Exception e) {
			k = 4;
			System.out.println("5");
			return k;
		} finally {
			System.out.println("6 finally");
			k = 3;
		}
	}

	public static void main(String[] args) {
		new F().noSystemExit();
		new F().noExeption();
		new F().anExeption1();
		new F().anExeption2();
		System.out.println( new F().withSystemExit());
	}
}