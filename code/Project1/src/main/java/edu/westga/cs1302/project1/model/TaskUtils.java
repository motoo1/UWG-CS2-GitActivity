package edu.westga.cs1302.project1.model;

import java.util.List;

/**
 * Utility class that provides helper methods for working with Task objects.
 *
 * <p>
 * Currently, this class offers functionality to count the number of tasks for a
 * given priority.
 * </p>
 * 
 * @author motoo1
 * @version 1.0
 */
public class TaskUtils {

	/**
	 * Counts the number of tasks in a given list that match the specific priority.
	 *
	 * @param priority The priority to count ("Low", "Medium", or "High").
	 * @param tasks    The list of Task objects to search through.
	 * @return The number of tasks that have the specified priority.
	 */
	public static int countTasksByPriority(String priority, List<Task> tasks) {
		int count = 0;
		for (Task task : tasks) {
			if (task.getPriority().equalsIgnoreCase(priority)) {
				count++;
			}
		}
		return count;
	}
}