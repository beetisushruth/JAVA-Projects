/*
 * MyList.java
 *
 * Version: 1
 *
 * Revisions: 1
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Creating a list that stores objects of Line, Square, Cube, 3dCube
 *
 * @author Sushruth Beeti (sb3112@rit.edu)
 * @author Mallika Vengarai (mv4207@rit.edu)
 */

public class MyList<E extends Line> {

	List<E> l = new ArrayList<>();

	/**
	 * Adding an element to the list
	 * 
	 * @param element
	 */
	public void add(E element) {
		l.add(element);
	}

	/**
	 * Getting the element at a particular position in the list
	 * 
	 * @param i
	 * @return element
	 */
	public E get(int i) {
		if (i >= 0 && i < l.size()) {
			return l.get(i);
		}
		return null;
	}

	/**
	 * Printing out the list
	 */
	public void print() {
		for (E element : l) {
			System.out.println(element);
		}
	}

	/**
	 * Returning the size of list
	 */
	public int size() {
		return l.size();
	}
}
