package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ComicTest {

	@Test
	void testValidComic() {
		Comic comic = new Comic("Spider-Man", 1);
		assertEquals("Spider-Man", comic.getTitle());
		assertEquals(1, comic.getIssueNumber());
		assertEquals("Spider-Man #1", comic.toString());
	}

	@Test
	void testBlankTitleThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Comic("", 1);
		});
	}

	@Test
	void testNullTitleThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Comic(null, 1);
		});
	}

	@Test
	void testNegativeIssueNumberThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Comic("Spider-Man", -5);
		});
	}
}