package LivingThing;

/**
 * Mouse class
 * 
 * @author  Sushruth Beeti (sb3112@rit.edu)
 * @author  Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class Mouse extends LivingThing {
	
    /**
     * Create a new Mouse.
     *
     * @param   species   the species of the Mouse
     * @param	height    the height of the Mouse
     * @param	weight    the weight of the Mouse
     */
	
	public Mouse(String species, double height, double weight) {
		super(species, height, weight);
	}
	
	/**
     * Create a new Mouse.
     * @param   species   the species of the Mouse
     */
	
	public Mouse(String species) {
		super(species);
	}
}
