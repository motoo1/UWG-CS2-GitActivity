package edu.westga.cs1302.task_tracker.model.descending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.DescendingComparison;
import edu.westga.cs1302.task_tracker.model.Task;

/**
 * 
 * @author motoo1
 * @version Fall 2025
 */
public class TestCompare {

    private final DescendingComparison comparator = new DescendingComparison();

    @Test
    void testHighComparedToHighReturnsZero() {
        Task high1 = new Task("High1", "", Task.TaskPriority.HIGH);
        Task high2 = new Task("High2", "", Task.TaskPriority.HIGH);
        assertEquals(0, comparator.compare(high1, high2)); 
    }

    @Test
    void testHighComparedToMediumReturnsPositive() {
        Task high = new Task("High", "", Task.TaskPriority.HIGH);
        Task medium = new Task("Medium", "", Task.TaskPriority.MEDIUM);
        assertTrue(comparator.compare(high, medium) > 0); 
    }

    @Test
    void testHighComparedToLowReturnsPositive() {
        Task high = new Task("High", "", Task.TaskPriority.HIGH);
        Task low = new Task("Low", "", Task.TaskPriority.LOW);
        assertTrue(comparator.compare(high, low) > 0); 
    }

    @Test
    void testMediumComparedToHighReturnsNegative() {
        Task medium = new Task("Medium", "", Task.TaskPriority.MEDIUM);
        Task high = new Task("High", "", Task.TaskPriority.HIGH);
        assertTrue(comparator.compare(medium, high) < 0); 
    }

    @Test
    void testMediumComparedToMediumReturnsZero() {
        Task medium1 = new Task("Medium1", "", Task.TaskPriority.MEDIUM);
        Task medium2 = new Task("Medium2", "", Task.TaskPriority.MEDIUM);
        assertEquals(0, comparator.compare(medium1, medium2)); 
    }

    @Test
    void testMediumComparedToLowReturnsPositive() {
        Task medium = new Task("Medium", "", Task.TaskPriority.MEDIUM);
        Task low = new Task("Low", "", Task.TaskPriority.LOW);
        assertTrue(comparator.compare(medium, low) > 0); 
    }

    @Test
    void testLowComparedToHighReturnsNegative() {
        Task low = new Task("Low", "", Task.TaskPriority.LOW);
        Task high = new Task("High", "", Task.TaskPriority.HIGH);
        assertTrue(comparator.compare(low, high) < 0); 
    }

    @Test
    void testLowComparedToMediumReturnsNegative() {
        Task low = new Task("Low", "", Task.TaskPriority.LOW);
        Task medium = new Task("Medium", "", Task.TaskPriority.MEDIUM);
        assertTrue(comparator.compare(low, medium) < 0); 
    }

    @Test
    void testLowComparedToLowReturnsZero() {
        Task low1 = new Task("Low1", "", Task.TaskPriority.LOW);
        Task low2 = new Task("Low2", "", Task.TaskPriority.LOW);
        assertEquals(0, comparator.compare(low1, low2)); 
    }
}
