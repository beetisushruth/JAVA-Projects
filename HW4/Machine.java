/*
 * Machine.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Machine class for a Finite State Machine
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Machine {
	State currentState;		// current state of the machine
	State[] states;			// total states
	
	/**
	 * Parameterized constructor for the Machine class
	 * @param states
	 * @param startState
	 */
	public Machine(State[] states, State startState) {
		this.states = states;
		this.currentState = startState;
	}
	
	/**
	 * Process the current character 
	 * @param character	
	 */
	public void processCharacter(char character) {
		// get next possible states
		State[] nextStates = currentState.getNextStates();
		char[] alphabetInput = currentState.getAlphabetInput();
		int index = -1;		// index of the character
		int indexOfX = -1;  // index of X (proxy for any other character)
		for(int i=0; i<alphabetInput.length; i++) {
			if(("" + alphabetInput[i]).equalsIgnoreCase(character+"")) {
				index = i;
				break;
			}
			if(("" + alphabetInput[i]).equalsIgnoreCase("x")) {
				indexOfX = i;
			}
		}
		index = (index == -1) ? indexOfX : index;
		this.currentState = nextStates[index];	// change state
	}
	
	/**
	 * Process the string
	 * @param 	inputString
	 * @return	boolean if the current state is accepting	
	 */
	public boolean processString(String inputString) {
		for(int i=0; i<inputString.length(); i++) {
			processCharacter(inputString.charAt(i));
		}
		return currentState.isAcceptingState();
	}
}
