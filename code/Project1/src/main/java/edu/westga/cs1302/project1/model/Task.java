package edu.westga.cs1302.project1.model;

/**
 * Represents a task with a name, description and priority. A task can be
 * created, updated and displayed in a task list
 *
 * @author motoo1
 * @version 1.0
 */
public class Task {
	private final String name;
	private String description;
	private final String priority;

	/**
	 * Creates a new Task with the given details.
	 *
	 * @param name        the name of the task
	 * @param description the description
	 * @param priority    the priority of the task
	 */

	public Task(String name, String description, String priority) {
		this.name = name;
		this.description = description;
		this.priority = priority;
	}

	/**
	 * Gets the name of the task
	 *
	 * @return the task name
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * Gets the description of the task
	 *
	 * @return the task description
	 */

	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the task
	 *
	 * @param description the new task description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the priority of the task
	 *
	 * @return the task priority
	 */
	public String getPriority() {
		return this.priority;
	}

	@Override
	public String toString() {
		return this.name + " (" + this.priority + ") -" + this.description;
	}
}