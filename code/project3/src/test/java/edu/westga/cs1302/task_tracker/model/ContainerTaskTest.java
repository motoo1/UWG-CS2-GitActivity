package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

public class ContainerTaskTest {

	@Test
	public void testConstructorShouldStoreValues() {
		ContainerTask task = new ContainerTask("Clean Room", "Deep clean the room", TaskPriority.HIGH);
		assertEquals("Clean Room", task.getName());
		assertEquals("Deep clean the room", task.getDescription());
		assertEquals(TaskPriority.HIGH, task.getPriority());
	}

	@Test
	public void testGetSubTasksInitiallyEmpty() {
		ContainerTask task = new ContainerTask("Clean Room", "Deep clean", TaskPriority.LOW);
		assertTrue(task.getSubTasks().isEmpty());
	}

	@Test
	public void testAddTaskAddsSubtask() {
		ContainerTask parent = new ContainerTask("Clean Room", "Main task", TaskPriority.MEDIUM);
		Task sub = new Task("Vacuum", "Vacuum the carpet", TaskPriority.LOW);

		parent.addTask(sub);

		assertEquals(1, parent.getSubTasks().size());
		assertEquals(sub, parent.getSubTasks().get(0));
	}

	@Test
	public void testAddTaskReturnsItself() {
		ContainerTask parent = new ContainerTask("Clean Room", "Main task", TaskPriority.HIGH);
		Task sub = new Task("Dust", "Dust shelves", TaskPriority.LOW);

		ContainerTask result = parent.addTask(sub);

		assertSame(parent, result);
	}

	@Test
	public void testToStringIncludesPlusSymbol() {
		ContainerTask task = new ContainerTask("Clean Room", "Deep clean", TaskPriority.HIGH);
		assertTrue(task.toString().contains("(+)"));
	}
}