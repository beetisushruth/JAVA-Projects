/*
 * Patterns.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

/**
 * Patterns class which holds FSM
 *
 * @author      Sushruth Beeti (sb3112@rit.edu)
 * @author      Mallika Vengarai (mv4207@rit.edu)
 */

public class Patterns {
	
	/**
	 * Match the strings starting with A and ends with B (^ab$)
	 * @param inputString  	
	 */
	public static void matchStartWithAEndWithB(String inputString) {
		// fsm construction
		State[] states = new State[] {
				new State(0, false), 
				new State(1, false), 
				new State(2, true), 
				new State(3, false)};
		char[] alphabetInput = {'a', 'b', 'x'};
		for(int i=0; i<states.length; i++) {
			states[i].setAlphabetInput(alphabetInput);
		}
		states[0].setNextStates(new State[] {states[1], states[3], states[3]});
		states[1].setNextStates(new State[] {states[3], states[2], states[3]});
		states[2].setNextStates(new State[] {states[3], states[3], states[3]});
		states[3].setNextStates(new State[] {states[3], states[3], states[3]});
		Machine machine = new Machine(states, states[0]);
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: ^ab$ %s\n", inputString);
		}
	}
	
	/**
	 * Match the strings starting with any number of A's and ends with B (^a+b$)
	 * @param inputString 
	 */
	public static void matchStartWithAsEndWithB(String inputString) {
		// fsm construction
		State q0 = new State(0, false);
		State q1 = new State(1, true);
		State q2 = new State(2, false);
		State[] states = new State[3];
		states[0] = q0;
		states[1] = q1;
		states[2] = q2;
		char[] alphabetInput = {'a', 'b', 'x'};
		q0.setAlphabetInput(alphabetInput);
		q1.setAlphabetInput(alphabetInput);
		q2.setAlphabetInput(alphabetInput);
		State[] q0NextStates = {q0, q1, q2};
		State[] q1NextStates = {q2, q2, q2};
		State[] q2NextStates = {q2, q2, q2};
		q0.setNextStates(q0NextStates);
		q1.setNextStates(q1NextStates);
		q2.setNextStates(q2NextStates);
		Machine machine = new Machine(states, q0);
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: ^a+b$ %s\n", inputString);
		}
	}
	
	/**
	 * Match the strings starting with any character then
	 *  A's, B then any character (.a+b.)
	 * @param	inputString	input string to be matched		
	 * @param 	regex		pattern
	 */
	public static void matchPatternDotAPlusBDot(String inputString, 
			String regex) {
		// fsm construction
		State[] states = new State[] {
				new State(0, false), 
				new State(1, false), 
				new State(2, false), 
				new State(3, false), 
				new State(4, true)};
		char[] alphabetInput = {'a', 'b', 'x'};
		for(int i=0; i<states.length; i++) {
			states[i].setAlphabetInput(alphabetInput);
		}
		states[0].setNextStates(new State[] {states[1], states[1], states[1]});
		states[1].setNextStates(new State[] {states[2], states[1], states[1]});
		states[2].setNextStates(new State[] {states[2], states[3], states[1]});
		states[3].setNextStates(new State[] {states[4], states[4], states[4]});
		states[4].setNextStates(new State[] {states[4], states[4], states[4]});
		Machine machine = new Machine(states, states[0]);
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: %s %s\n", regex, inputString);
		}
	}
	
	/**
	 * Match the strings with AC OR BC string (ˆ[ab]c$)
	 * @param inputString
	 */
	public static void matchPatternACorBC(String inputString) {
		// fsm construction
		State[] states = new State[] {
				new State(0, false), 
				new State(1, false), 
				new State(2, true), 
				new State(3, false)};
		char[] alphabetInput = {'a', 'b', 'c', 'x'};
		for(int i=0; i<states.length; i++) {
			states[i].setAlphabetInput(alphabetInput);
		}
		states[0].setNextStates(new State[] {states[1], states[1], states[3], 
				states[3]});
		states[1].setNextStates(new State[] {states[3], states[3], states[2], 
				states[3]});
		states[2].setNextStates(new State[] {states[3], states[3], states[3], 
				states[3]});
		states[3].setNextStates(new State[] {states[3], states[3], states[3], 
				states[3]});
		Machine machine = new Machine(states, states[0]);
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: ˆ[ab]c$ %s\n", inputString);
		}
	}
	
	/**
	 * Match the strings with ABA OR BAB ˆ(bab|aba)$
	 * @param inputString
	 */
	public static void matchPatternABAorBAB(String inputString) {
		// fsm construction
		State[] states = new State[] {
				new State(0, false), 
				new State(1, false), 
				new State(2, false),
				new State(3, true),
				new State(4, false), 
				new State(5, false),
				new State(6, false)};
		char[] alphabetInput = {'a', 'b', 'x'};
		for(int i=0; i<states.length; i++) {
			states[i].setAlphabetInput(alphabetInput);
		}
		states[0].setNextStates(new State[] {
				states[4], states[1], states[5]});
		states[1].setNextStates(new State[] {
				states[2], states[5], states[5]});
		states[2].setNextStates(new State[] {
				states[5], states[3], states[5]});
		states[3].setNextStates(new State[] {
				states[5], states[5], states[5]});
		states[4].setNextStates(new State[] {
				states[5], states[6], states[5]});
		states[5].setNextStates(new State[] {
				states[5], states[5], states[5]});
		states[6].setNextStates(new State[] {
				states[3], states[5], states[5]});
		Machine machine = new Machine(states, states[0]);
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: ˆ(bab)|(aba)$ %s\n", inputString);
		}
	}
	
	/**
	 * Match the strings with pattern ABAB (ˆ..\1\2$)
	 * @param inputString
	 */
	public static void matchPatternABAB(String inputString) {
		// fsm construction
		State[] states = new State[] {
				new State(0, false), 
				new State(1, false), 
				new State(2, false),
				new State(3, false),
				new State(4, true), 
				new State(5, false)};
		char[] alphabetInput = {'a', 'b', 'x'};
		for(int i=0; i<states.length; i++) {
			states[i].setAlphabetInput(alphabetInput);
		}
		states[0].setNextStates(new State[] {
				states[1], states[5], states[5]});
		states[1].setNextStates(new State[] {
				states[5], states[2], states[5]});
		states[2].setNextStates(new State[] {
				states[3], states[5], states[5]});
		states[3].setNextStates(new State[] {
				states[5], states[4], states[5]});
		states[4].setNextStates(new State[] {
				states[5], states[5], states[5]});
		states[5].setNextStates(new State[] {
				states[5], states[5], states[5]});
		Machine machine = new Machine(states, states[0]);
		
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: ˆ..\\1\\2$ %s\n", inputString);
		}
	}
	
	/**
	 * Match the strings with pattern ABBA (ˆ..\2\1$)
	 * @param inputString
	 */
	public static void matchPatternABBA(String inputString) {
		// fsm construction
		State[] states = new State[] {
				new State(0, false), 
				new State(1, false), 
				new State(2, false),
				new State(3, false),
				new State(4, true), 
				new State(5, false)};
		char[] alphabetInput = {'a', 'b', 'x'};
		for(int i=0; i<states.length; i++) {
			states[i].setAlphabetInput(alphabetInput);
		}
		states[0].setNextStates(new State[] {
				states[1], states[5], states[5]});
		states[1].setNextStates(new State[] {
				states[5], states[2], states[5]});
		states[2].setNextStates(new State[] {
				states[5], states[3], states[5]});
		states[3].setNextStates(new State[] {
				states[4], states[5], states[5]});
		states[4].setNextStates(new State[] {
				states[5], states[5], states[5]});
		states[5].setNextStates(new State[] {
				states[5], states[5], states[5]});
		Machine machine = new Machine(states, states[0]);
		
		boolean isMatch = machine.processString(inputString);
		if(isMatch) {
			System.out.format("Line: ˆ..\\2\\1$ %s\n", inputString);
		}
	}
	
	/**
	 * Match the string with the all the patterns
	 * @param inputString
	 */
	public static void matchAllPatterns(String inputString) {
		matchStartWithAEndWithB(inputString);
		matchStartWithAsEndWithB(inputString);
		matchPatternDotAPlusBDot(inputString, ".a+b.");
		matchPatternDotAPlusBDot(inputString, ".ab.");
		matchPatternACorBC(inputString);
		matchPatternABAorBAB(inputString);
		matchPatternABAB(inputString);
		matchPatternABBA(inputString);
	}
}
