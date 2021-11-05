/*
 * PasswordWrite.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Password write class to serialize the object
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class PasswordWrite {
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Password password = new Password("NoobMaster69");
		try(FileOutputStream fileOutputStream = new FileOutputStream("1234.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			objectOutputStream.writeObject(password);
			objectOutputStream.flush();
		} catch (IOException e) {
			System.out.println("Exception occurred during serialization "+e.getMessage());
		}
	}
}
