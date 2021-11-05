/* LivingThing.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

package LivingThing;

/**
 * LivingThing class
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */

public class LivingThing extends Node {
	
	String species;
	double height; // inches
	double weight;	// kilograms
	
    /**
     * Create a new LivingThing.
     *
     * @param   species   the species of the LivingThing
     * @param	height    the height of the LivingThing
     * @param	weight    the weight of the LivingThing
     */
	
	public LivingThing(String species, double height, 
			double weight) {
		this.species = species;
		this.height = height;
		this.weight = weight;
	}
	
    /**
     * Return a string representation of the data contained in this
     * LivingThing.
     * @return    a string representation of this LivingThing.
     */
	
	public String toString() {
		return this.species+": Height: "+this.height+
				" Weight: "+this.weight;
	}
	
	/**
     * Create a new LivingThing
     * @param   species   the species of the LivingThing
     */
	
	public LivingThing(String species) {
		this.species = species;
	}
	
	/**
     * Returns true if this weight of LivingThing is greater than the LivingThing 
     * referred to by the argument.
     * @param    LivingThing
     * @return   true if weights of LivingThings are equal
	*/
	
	@Override
	public boolean greaterThan(Node livingThing) {
		return this.weight > ((LivingThing)livingThing).weight;
	}
	
	/**
	 * Returns true if this weight of LivingThing is lesser than the LivingThing
	 * @param    LivingThing 
	 * @return   true	if the this weight of LivingThing is lesser than the LivingThing
	 */

	@Override
	public boolean lessThan(Node livingThing) {
		return this.weight < ((LivingThing)livingThing).weight;
	}
	
	/**
	 * Returns true if this weights of LivingThings are equal
	 * @param    LivingThing 
	 * @return   true  	if the this weight of LivingThings are equal.
	 */
	
	@Override
	public boolean equalTo(Node livingThing) {
		return this.weight == ((LivingThing)livingThing).weight;
	}
	
	/**
	 * Setting the value of the node as the weight of the LivingThing node
	 * @param	LivingThing
	 */
	
	@Override
	public void setValue(Node node) {
		this.weight = ((LivingThing) node).weight;
	}
}
