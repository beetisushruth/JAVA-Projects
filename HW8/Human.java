/*
 * Human.java
 *
 * Version: 1
 *
 * Revisions: 1
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Human Class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class Human implements Comparable<Human>{
	
	private Date dateOfBirth;	// date of birth
    private String firstName;	// first name
    private String userId;		// user Id
    
    /**
     * Human constructor
     * @param date
     * @param firstName
     * @param userId
     */
    public Human(String date, String firstName, String userId) {
    	DateFormat formatter = new SimpleDateFormat("MM-DD-yyyy"); 
    	Date dateConverted;
		try {
			dateConverted = (Date)formatter.parse(date);
		} catch (ParseException e) {
			dateConverted = new Date();
		}
    	this.dateOfBirth = dateConverted;
    	this.setFirstName(firstName);
    	this.setUserId(userId);
    }

    /**
     * Get first name
     * @return first name
     */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Set first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get user Id
	 * @return user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Set user Id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Overrided compareTo method
	 */
	@Override
	public int compareTo(Human human) {
		return this.dateOfBirth.compareTo(human.dateOfBirth);
	}

	/**
	 * Overrided toString method
	 */
	@Override
	public String toString() {
		return "Human [dateOfBirth=" + dateOfBirth + ", firstName=" + firstName + ", userId=" + userId + "]";
	}

    
}
