/*
 * Address.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Address Class
 * 
 * @author   Sushruth Beeti (sb3112@rit.edu)
 * @author   Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class Address implements Comparable<Address>{
	
	private int houseNumber;	// house number
    private String streetName;  // street name
    private String nameOfTown;  // name of the town
    private String state;       // state
    private int zipCode;        // zip code
    
    
    /**
     * Address constructor
     * @param houseNumber
     * @param streetName
     * @param nameOfTown
     * @param state
     * @param zipCode
     */
	public Address(int houseNumber, String streetName, String nameOfTown, String state, int zipCode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.nameOfTown = nameOfTown;
		this.state = state;
		this.zipCode = zipCode;
	}

	/**
	 * Get house number
	 * @return house number
	 */
	public int getHouseNumber() {
		return houseNumber;
	}
	
	/**
	 * Set house number
	 * @param houseNumber
	 */
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	/**
	 * Get street name
	 * @return street name
	 */
	public String getStreetName() {
		return streetName;
	}
	
	/**
	 * Set street name
	 * @param streetName
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	/**
	 * Get name of the town
	 * @return name of town
	 */
	public String getNameOfTown() {
		return nameOfTown;
	}
	
	/**
	 * Set name of town
	 * @param nameOfTown
	 */
	public void setNameOfTown(String nameOfTown) {
		this.nameOfTown = nameOfTown;
	}
	
	/**
	 * Get state
	 * @return state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Set state
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Get zip code
	 * @return zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}
	
	/**
	 * Set zip code
	 * @param zipCode
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * Overrided compareTo method
	 */
	@Override
	public int compareTo(Address address) {
		if(this.zipCode < address.zipCode) return -1;
		else if(this.zipCode > address.zipCode) return 1;
		return 0;
	}
	
	/**
	 * Overrided toString method
	 */
	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", streetName=" + streetName + ", nameOfTown=" + nameOfTown
				+ ", state=" + state + ", zipCode=" + zipCode + "]";
	}
}
