/* Fish.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package LivingThing;

/**
 * Fish class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Fish extends LivingThing {
	
    /**
     * Create a new Fish.
     *
     * @param   species   the species of the Fish
     * @param	height    the height of the Fish
     * @param	weight    the weight of the Fish
     */
	
	public Fish(String species, double height, double weight) {
		super(species, height, weight);
	}
	
	/**
     * Create a new Fish.
     *
     * @param	species	the species of the Fish
     */
	
	public Fish(String species) {
		super(species);
	}
	
}
