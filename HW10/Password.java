/*
 * Password.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * Password class which implements serializable
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */
public class Password implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2161034782932497276L;
	private String password;
	private final static String KEY = "MYKEY74125";	// 10 digit key to encrypt user password
	
	/**
	 * Constructor
	 */
	public Password() {
		this.password = "default";
	}
	
	/**
	 * Parameterized constructor
	 * @param password
	 */
	public Password(String password) {
		this.password = password;
	}
	
	/**
	 * Overrided readObject method
	 * @param objectInputStream
	 */
	private void readObject(ObjectInputStream objectInputStream) {
		String passwordString = null;
		String encodedPasswordString = null;
		try {
			passwordString = (String) objectInputStream.readObject();
			encodedPasswordString = (String) objectInputStream.readObject();
		} catch (Exception e) {
			System.out.println("Object couldn't be read");
		} 
		try {
			byte [] byteArray = Base64.getDecoder().decode(encodedPasswordString); 
			String passwordKeyString = new String(byteArray);
			if(passwordKeyString != null && 
				passwordKeyString.substring(0, 10).equals(KEY) && 
				passwordString.equals(passwordKeyString.substring(10, 
						passwordKeyString.length()))) {
				password = passwordString;
			
			} else {
				throw new Exception("Password has been tampered");
			}
		} catch(Exception e) {
			System.out.println("Password has been tampered!");
			password = "";
		}
	}
	
	/**
	 * Overrided writeObject method
	 * @param objectOutputStream
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream objectOutputStream) throws ClassNotFoundException, IOException {
		String encodedPassword = Base64.getEncoder().encodeToString((KEY+password).getBytes());
		objectOutputStream.defaultWriteObject();
		objectOutputStream.writeObject(encodedPassword);
	}

	/**
	 * Overrided toString method
	 */
	public String toString() {
		return "Password: "+password;
	}
}
