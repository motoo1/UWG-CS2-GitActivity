package edu.westga.cs1302.task_tracker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Stores basic information for a Task
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Task {

	/**
	 * Possible priority options for a Task
	 * 
	 * @author CS 1302
	 * @version Fall 2025
	 */
	public enum TaskPriority {
		HIGH(1), MEDIUM(2), LOW(3);

		private int value;

		TaskPriority(int value) {
			this.value = value;
		}

		/**
		 * Return the value for the priority HIGH is 1 MEDIUM is 2 LOW is 3
		 * 
		 * @return the value
		 */
		public int getValue() {
			return this.value;
		}
	}

	private String description;
	private final String name;
	private final TaskPriority priority;
	private ObservableList<SubTask> subtasks;

	/**
	 * Create a new Task with the provided information.
	 * 
	 * @preconditon name != null && !name.isEmpty() && description != null &&
	 *              priority != null
	 * 
	 * @param name        the name of the task
	 * @param description the description of the task
	 * @param priority    the priority of the task
	 */
	public Task(String name, String description, TaskPriority priority) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name must not be empty");
		}
		if (description == null) {
			throw new IllegalArgumentException("description must not be null");
		}
		if (priority == null) {
			throw new IllegalArgumentException("priority must not be null");
		}
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.subtasks = FXCollections.observableArrayList();
	}

	/**
	 * Return the name of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the description of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description of the task
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Return the priority of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the priority of the task
	 */
	public TaskPriority getPriority() {
		return this.priority;
	}

	/**
	 * Updates the description to the provided value
	 * 
	 * @precondition description != null
	 * @postcondition none
	 * 
	 * @param description the new description for the task
	 */
	public void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("description must not be null");
		}
		this.description = description;
	}

	/**
	 * Returns the name of the task to represent the task as a String
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Returns the list of subtasks for this task.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the list of subtasks for this task
	 */
	public ObservableList<SubTask> getSubtasks() {
		return this.subtasks;
	}

	/**
	 * Adds a subtask to this task
	 * 
	 * @param subtask the subtask to be added
	 * @precondition subtask is not null
	 * @postcondition the subtask is added to this tasks list of subtasks
	 * @throws IllegalArgumentExeption if subtask is null
	 */
	public void addSubtask(SubTask subtask) {
		if (subtask == null) {
			throw new IllegalArgumentException("subtask must not be null");
		}
		this.subtasks.add(subtask);
	}

	/**
	 * Removes a subtask from this task.
	 * 
	 * @param subtask the subtask to be removed
	 * @precondition subtask is not null
	 * @postcondition the subtask is removed from the list of subtasks
	 */
	public void removeSubtask(SubTask subtask) {
		this.subtasks.remove(subtask);
	}

	/**
	 * Adds a subtask (of type Task) and returns a new ContainerTask.
	 *
	 * @precondition task != null
	 * @postcondition Returns a new ContainerTask containing the provided task
	 * @param task the task to add as a subtask
	 * @return a new ContainerTask containing this and the provided task
	 */
	public ContainerTask addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("task must not be null");
		}

		ContainerTask container = new ContainerTask(this.getName(), this.getDescription(), this.getPriority());
		container.addTask(task);
		return container;
	}

	/**
	 * Returns an empty list of Task objects.
	 *
	 * @return an empty ObservableList of Tasks
	 */
	public ObservableList<Task> getSubTasks() {
		return FXCollections.observableArrayList();
	}
}
