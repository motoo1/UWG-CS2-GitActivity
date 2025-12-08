package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CollectionComicTest {

	private Collection collection;

	@BeforeEach
	void setUp() {
		collection = new Collection("Superheroes");
	}

	@Test
	void testAddComicIncreasesListSize() {
		Comic comic = new Comic("Spider-Man", 1);
		collection.addComic(comic);
		assertEquals(1, collection.getComics().size());
		assertTrue(collection.getComics().contains(comic));
	}

	@Test
	void testRemoveComicDecreasesListSize() {
		Comic comic = new Comic("Spider-Man", 1);
		collection.addComic(comic);
		collection.removeComic(comic);
		assertEquals(0, collection.getComics().size());
		assertTrue(!collection.getComics().contains(comic));
	}

	@Test
	void testGetComicsReturnsObservableList() {
		assertEquals(0, collection.getComics().size());
		Comic comic1 = new Comic("Batman", 1);
		Comic comic2 = new Comic("Superman", 1);
		collection.addComic(comic1);
		collection.addComic(comic2);
		assertEquals(2, collection.getComics().size());
		assertTrue(collection.getComics().contains(comic1));
		assertTrue(collection.getComics().contains(comic2));
	}
}