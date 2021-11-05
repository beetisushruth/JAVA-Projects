import LivingThing.Node;

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
	 * Add a node to BST
	 * @param node x
	 * @return boolean
	 */
    public boolean add(Node x); 
    
    /**
	 * Find a node in BST
	 * @param node x
	 * @return boolean
	 */
    public boolean find(Node x);
    
    /**
	 * If the BST has nulls
	 * @return boolean
	 */
    public boolean includesNull(); 
    
    /**
	 * Delete a node from BST
	 * @param node x
	 * @return boolean
	 */
    public boolean delete(Node x);  
}
