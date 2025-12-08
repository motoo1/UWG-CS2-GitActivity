package edu.westga.cs1302.task_tracker.model;

/**
 * Represents a comic with a title and an issue number
 *
 * Each comic object has a title and an integer issue number and can be displayed as a string in the format title issuenumber
 *
 * @author motoo1
 * @version Fall 2025
 */
public class Comic {
	    private String title;
	    private int issueNumber;

	    public Comic(String title, int issueNumber) {
	        this.title = title;
	        this.issueNumber = issueNumber;
	    }

	    public String getTitle() {
	    	return this.title;
	    	}

	    public int getIssueNumber() {
	    	return this.issueNumber;
	    	}

	    @Override
	    public String toString() {
	        return this.title + " #" + this.issueNumber;
	    }
	}

