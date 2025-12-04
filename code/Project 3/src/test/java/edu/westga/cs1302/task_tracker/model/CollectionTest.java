package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CollectionTest {

	@Test
	void testValidName() {
		Collection collection= new Collection("Work Tasks");
		assertEquals("Work Tasks", collection.getName());
	}

	@Test
	void testNullNameThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Collection(null);
		});
	}

	@Test
	void testBlankNameThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Collection("   ");
		});
	}

	@Test
	void testToStringReturnsName() {
		Collection collection = new Collection("School");
		assertEquals("School", collection.toString());
	}
}