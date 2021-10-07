/*
 * State.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * State class for Finite State Machine
 * 
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 *
 */
public class State {
	
	// id of the state
	private int id;
	// alphabet the state takes
	// example: ['a', 'b', 'x'] x: every alphabet except a, b
	private char[] alphabetInput; 
	// next states the current state could go
	// example: [q0, q1, q2]
	private State[] nextStates;  
	// if the state is accepting
	private boolean isAcceptingState;
	
	/**
	 * Parameterized constructor for state class
	 * @param id
	 * @param isAcceptingState
	 */
	public State(int id, boolean isAcceptingState) {
		this.id = id;
		this.isAcceptingState = isAcceptingState;
	}
	
	/**
	 * Gets the id of the state
	 * @return	id 	state-id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the state
	 * @param	id	state-id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the alphabets for the FSM
	 * @return	alphabetInput
	 */
	public char[] getAlphabetInput() {
		return alphabetInput;
	}

	/**
	 * Sets the alphabets for the state
	 * @param alphabetInput
	 */
	public void setAlphabetInput(char[] alphabetInput) {
		this.alphabetInput = alphabetInput;
	}

	/**
	 * Get the possible next states for a state
	 * @return	nextStates	array of next states
	 */
	public State[] getNextStates() {
		return nextStates;
	}
	
	/**
	 * Set next states for a state
	 * @param nextStates	next state array
	 */
	public void setNextStates(State[] nextStates) {
		this.nextStates = nextStates;
	}

	/**
	 * Is the current state accepting state
	 * @return	boolean
	 */
	public boolean isAcceptingState() {
		return isAcceptingState;
	}

	/**
	 * Set is accepting state for a state
	 * @param isAcceptingState	boolean
	 */
	public void setAcceptingState(boolean isAcceptingState) {
		this.isAcceptingState = isAcceptingState;
	}
}
