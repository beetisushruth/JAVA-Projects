/*
 * Storage.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Storage Interface to hold BST Structure
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public interface Storage {
	
	/**
	 * Add a long value to BST
	 * @param long value x
	 * @return boolean
	 */
	public boolean add(long x);  
	
	/**
	 * Add a integer value to BST
	 * @param int value x
	 * @return boolean
	 */
    public boolean add(int x); 
    
    /**
	 * Add a String value to BST
	 * @param String value x
	 * @return boolean
	 */
    public boolean add(String x); 
    
    /**
	 * Find a long value to BST
	 * @param long value x
	 * @return boolean
	 */
    public boolean find(long x);   
    
    /**
	 * Find an integer value to BST
	 * @param integer value x
	 * @return boolean
	 */
    public boolean find(int x);
    
    /**
	 * Find a String value to BST
	 * @param String value x
	 * @return boolean
	 */
    public boolean find(String x);
    
    /**
	 * If the BST has nulls
	 * @return boolean
	 */
    public boolean includesNull(); 
    
    /**
	 * Add a long value to BST
	 * @param long value x
	 * @return boolean
	 */
    public boolean delete(long x);  
    
    /**
	 * Add a integer value to BST
	 * @param integer value x
	 * @return boolean
	 */
    public boolean delete(int x);
    
    /**
	 * Add a String value to BST
	 * @param string value x
	 * @return boolean
	 */
    public boolean delete(String x);
}
