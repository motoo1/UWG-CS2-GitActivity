package edu.westga.cs1302.task_tracker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a task that contains subtasks.
 * 
 * @author You
 * @version Fall 2025
 */
public class ContainerTask extends Task {

	private ObservableList<Task> subtasks;

	/**
	 * Creates a new ContainerTask with the provided information.
	 * 
	 * @param name        the name of the task
	 * @param description the description of the task
	 * @param priority    the priority of the task
	 */
	public ContainerTask(String name, String description, TaskPriority priority) {
		super(name, description, priority);
		this.subtasks = FXCollections.observableArrayList();
	}

	/**
	 * Adds the provided task to this container’s list of subtasks.
	 * 
	 * @param task the task to add
	 * @return this ContainerTask (for chaining)
	 */
	@Override
	public ContainerTask addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("task must not be null");
		}
		this.subtasks.add(task);
		return this;
	}

	/**
	 * Returns the list of subtasks contained within this task.
	 * 
	 * @return the list of subtasks
	 */
	@Override
	public ObservableList<Task> getSubTasks() {
		return this.subtasks;
	}

	/**
	 * Returns the task’s name and indicates it contains subtasks.
	 * 
	 * @return a string like "Clean Room (+)"
	 */
	@Override
	public String toString() {
		return this.getName() + " (+)";
	}
}