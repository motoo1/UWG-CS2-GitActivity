package edu.westga.cs1302.project1.model;

public class Task {
	private String description;
	private String priority;
	
	// Constructor
	public Task( String description, String priority) {
		this.description = description;
		this.priority = priority;
	}
    
	// Getters
	public String getDescription() {
		return description;	
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String toString() {
		return description + " (" + priority + ")";
	}
	
}
