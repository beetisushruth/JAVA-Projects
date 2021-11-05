/* Ant.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package LivingThing;

/**
 * Ant class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Ant extends LivingThing {
	
    /**
     * Create a new Ant.
     *
     * @param   species   the species of the Ant
     * @param	height    the height of the Ant
     * @param	weight    the weight of the Ant
     */

	public Ant(String species, double height, double weight) {
		super(species, height, weight);
	}
	
	/**
     * Create a new Ant.
     *
     * @param    species    the species of the Ant
     */
	
	public Ant(String species) {
		super(species);
	}
	
}
