package edu.westga.cs1302.project1.model;

public class Task {
	private String name;
	private String description;
	private String priority;
	
	// Constructor
	public Task(String name, String description, String priority) {
		this.name = name;
		this.description = description;
		this.priority = priority;
	}
    
	// Getters
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;	
	}
	
	public String getPriority() {
		return this.priority;
	}
	
	public String toString() {
		return this.name + " (" + this.priority + ")";
	}
	
}
