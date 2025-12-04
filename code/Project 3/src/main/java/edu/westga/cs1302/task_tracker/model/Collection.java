package edu.westga.cs1302.task_tracker.model;

/**
 * Represents a named collection.
 * 
 * @author CS1302
 * @version Fall 2025
 */
public class Collection {

	private String name;

	/**
	 * Creates a new Collection with the provided name.
	 * 
	 * @param name the name of the collection
	 * 
	 * @precondition name != null && !name.isBlank()
	 * @postcondition getName().equals(name)
	 */
	public Collection(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Collection name cannot be empty.");
		}
		this.name = name;
	}

	/**
	 * Gets the name of this collection.
	 * 
	 * @return the collection name
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
