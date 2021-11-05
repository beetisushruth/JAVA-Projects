/*
 * MusicLP.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Storage Interface
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class MusicLP implements Comparable<MusicLP>{
	
	private int year;
	private String artist;
	private String title;
	private float length; 
	private int tracks;
    
	/**
	 * MusicLP Constructor
	 * @param year
	 * @param artist
	 * @param title
	 * @param length
	 * @param tracks
	 */
    public MusicLP(int year, String artist, String title, 
    		float length, int tracks) {
    	this.setYear(year);
    	this.setArtist(artist);
    	this.setTitle(title);
    	this.setLength(length);
    	this.tracks = tracks;
    }

    /**
     * Get length of MusicLP
     * @return
     */
	public float getLength() {
		return length;
	}

	/**
	 * Set length of MusicLP
	 * @param length
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * Get title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get artist
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Set artist
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Get year
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set year
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Overrided compareTo method
	 */
	@Override
	public int compareTo(MusicLP musicLP) {
		if(this.tracks < musicLP.tracks) return -1;
		else if(this.tracks > musicLP.tracks) return 1;
		return 0;
	}
	
	/**
	 * Overrided toString method
	 */
	@Override
	public String toString() {
		return "MusicLP [year=" + year + ", artist=" + artist + ", title=" + title + ", length=" + length + ", tracks="
				+ tracks + "]";
	}
}
