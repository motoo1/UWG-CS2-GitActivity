package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator that sorts tasks by descending priority: HIGH > MEDIUM > LOW.
 * 
 * @author motoo1
 * @version Fall 2025
 */
public class DescendingComparison implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        return task2.getPriority().compareTo(task1.getPriority());
    }
    
    @Override
    public String toString() {
 	   return "Descending";
}
}