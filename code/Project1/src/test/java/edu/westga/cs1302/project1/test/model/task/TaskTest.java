package edu.westga.cs1302.project1.test.model.task;

import edu.westga.cs1302.project1.model.Task;

/**
 * TESTING NOTE: JUnit configuration failed due to module system conflicts
 * Attempted adding org.junit.jupiter.Assertions.*;
 * But my main code does not run when if it is added
 * Attempted:JUnit 5 module configuration fixes
 * Issues:The type org.junit.jupiter.api.Test is not accessible"
 */

class SimpleTaskTest{

	public static void main(String[] args) {
		System.out.println("Simple task tests");
		
		Task task1 = new Task("Study","Study for exam","High");
		System.out.println("Task 1: " + task1.getName());
		
		Task task2 = new Task("Homework","Math assignment","Medium");
		System.out.println("Task 2: " + task2.getName());
		System.out.println("All tests passed");
	
	}

}
