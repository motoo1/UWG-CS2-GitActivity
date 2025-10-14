package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compares Task objects by ascending priority: HIGH < MEDIUM < LOW.
 * 
 * @author motoo1
 * @version Fall 2025
 */
public class AscendingComparison implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        return task1.getPriority().compareTo(task2.getPriority());
    }
    
   @Override
   public String toString() {
	   return "Ascending";
   }
}
