/*
 * PasswordRead.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Password read class to deserialize the object file
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class PasswordRead {
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = (args.length == 0) ? "1234.ser" : args[0];
		try(FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream)) {
			Password password = (Password) objectOutputStream.readObject();
			System.out.println("The password is "+password.toString());
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Exception occurred during deserialization "+e.getMessage());
		}
	}
}
