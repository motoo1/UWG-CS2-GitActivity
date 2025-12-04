package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator for sorting task objects in descending order by their names
 * 
 * <p>
 * This comparator compares Task names alphabetically, ignoring case If either
 * task is null, an IllegalArgumentException is thrown.
 * </p>
 * 
 * @author motoo1
 * @version Oct 2025
 */
public class DescendingName implements Comparator<Task> {
	/**
	 * Compares two Task objects based on their names in descending order.
	 * 
	 * @param t1 the first Task to compare
	 * @param t2 the second Task to compare
	 * @return a negative integer if t1 name comes after t2 name,zero if they are
	 *         equal or a positive integer if t1 comes before t2
	 * @throws IllegalArgumentException if either task is null
	 */

	@Override
	public int compare(Task t1, Task t2) {
		if (t1 == null || t2 == null) {
			throw new IllegalArgumentException("Tasks cannot be null");
		}
		return t2.getName().compareToIgnoreCase(t1.getName());
	}

	/**
	 * Returns a string representation of this comparator
	 * 
	 * @return the text "Descending (by Name)"
	 */
	@Override
	public String toString() {
		return "Descending (by Name)";

	}

}
