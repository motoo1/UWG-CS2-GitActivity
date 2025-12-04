package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

public class DescendingNameTest {

	@Test
	public void testShortLowercaseNames() {
		List<Task> tasks = Arrays.asList(new Task("bob", "desc", TaskPriority.HIGH),
				new Task("amy", "desc", TaskPriority.HIGH), new Task("dan", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("dan", tasks.get(0).getName());
		assertEquals("bob", tasks.get(1).getName());
		assertEquals("amy", tasks.get(2).getName());
	}

	@Test
	public void testShortMixedCaseNames() {
		List<Task> tasks = Arrays.asList(new Task("Bob", "desc", TaskPriority.HIGH),
				new Task("amy", "desc", TaskPriority.HIGH), new Task("Dan", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("Dan", tasks.get(0).getName());
		assertEquals("Bob", tasks.get(1).getName());
		assertEquals("amy", tasks.get(2).getName());
	}

	@Test
	public void testShortNamesWithSpecialCharacters() {
		List<Task> tasks = Arrays.asList(new Task("@task", "desc", TaskPriority.HIGH),
				new Task("#urgent", "desc", TaskPriority.HIGH), new Task("alpha", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("alpha", tasks.get(0).getName());
		assertEquals("@task", tasks.get(1).getName());
		assertEquals("#urgent", tasks.get(2).getName());
	}

	@Test
	public void testLongLowercaseNames() {
		List<Task> tasks = Arrays.asList(new Task("zebra crossing", "desc", TaskPriority.HIGH),
				new Task("apple orchard", "desc", TaskPriority.HIGH),
				new Task("mountain peak", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("zebra crossing", tasks.get(0).getName());
		assertEquals("mountain peak", tasks.get(1).getName());
		assertEquals("apple orchard", tasks.get(2).getName());
	}

	@Test
	public void testLongMixedCaseNames() {
		List<Task> tasks = Arrays.asList(new Task("Zebra Crossing", "desc", TaskPriority.HIGH),
				new Task("Apple Orchard", "desc", TaskPriority.HIGH),
				new Task("Mountain Peak", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("Zebra Crossing", tasks.get(0).getName());
		assertEquals("Mountain Peak", tasks.get(1).getName());
		assertEquals("Apple Orchard", tasks.get(2).getName());
	}

	@Test
	public void testLongNamesWithSpecialCharacters() {
		List<Task> tasks = Arrays.asList(new Task("!important task", "desc", TaskPriority.HIGH),
				new Task("~cleanup routine", "desc", TaskPriority.HIGH),
				new Task("alpha zone", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("~cleanup routine", tasks.get(0).getName());
		assertEquals("alpha zone", tasks.get(1).getName());
		assertEquals("!important task", tasks.get(2).getName());
	}

	@Test
	public void testIdenticalLowercaseNames() {
		List<Task> tasks = Arrays.asList(new Task("alpha", "desc", TaskPriority.HIGH),
				new Task("alpha", "desc", TaskPriority.HIGH), new Task("alpha", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("alpha", tasks.get(0).getName());
		assertEquals("alpha", tasks.get(1).getName());
		assertEquals("alpha", tasks.get(2).getName());
	}

	@Test
	public void testIdenticalMixedCaseNames() {
		List<Task> tasks = Arrays.asList(new Task("Alpha", "desc", TaskPriority.HIGH),
				new Task("alpha", "desc", TaskPriority.HIGH), new Task("ALPHA", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("Alpha", tasks.get(0).getName());
		assertEquals("alpha", tasks.get(1).getName());
		assertEquals("ALPHA", tasks.get(2).getName());
	}

	@Test
	public void testIdenticalSpecialCharacterNames() {
		List<Task> tasks = Arrays.asList(new Task("!task", "desc", TaskPriority.HIGH),
				new Task("!task", "desc", TaskPriority.HIGH), new Task("!task", "desc", TaskPriority.HIGH));
		tasks.sort(new DescendingName());
		assertEquals("!task", tasks.get(0).getName());
		assertEquals("!task", tasks.get(1).getName());
		assertEquals("!task", tasks.get(2).getName());
	}
}
