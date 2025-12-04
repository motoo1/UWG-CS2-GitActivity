package edu.westga.cs1302.task_tracker.model;

/**
 * Represents a single SubTask that belongs to a Task.
 * 
 * @author You
 * @version Fall 2025
 */
public class SubTask {
	private String name;

	/**
	 * Creates a new SubTask with the provided name.
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @postcondition none
	 * 
	 * @param name the name of the subtask
	 */
	public SubTask(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Subtask name must not be null or empty");
		}
		this.name = name;
	}

	/**
	 * Returns the name of the subtask.
	 * 
	 * @return the subtask name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the name of the subtask as its string representation.
	 * 
	 * @return the subtask name
	 */
	@Override
	public String toString() {
		return this.name;
	}
}