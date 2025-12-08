package edu.westga.cs1302.task_tracker.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import org.junit.jupiter.api.BeforeEach;

class FindComicViewModelTest {

	private Collection collection;
	private FindComicViewModel viewModel;
	private Comic spiderMan;
	private Comic ironMan;

	@BeforeEach
	void setUp() {
		collection = new Collection("Marvel");
		spiderMan = new Comic("Spider-Man", 1);
		ironMan = new Comic("Iron Man", 2);
		collection.addComic(spiderMan);
		collection.addComic(ironMan);

		viewModel = new FindComicViewModel(collection);
	}

	@Test
	void searchExistingComicReturnsComic() {
		Comic found = viewModel.searchComic("Spider-Man", "1");
		assertEquals(spiderMan, found);
	}

	@Test
	void searchNonExistingComicReturnsNull() {
		Comic found = viewModel.searchComic("Hulk", "1");
		assertNull(found);
	}

	@Test
	void searchInvalidIssueNumberReturnsNull() {
		Comic found = viewModel.searchComic("Spider-Man", "abc");
		assertNull(found);
	}

	@Test
	void searchCorrectTitleWrongIssueReturnsNull() {
		Comic found = viewModel.searchComic("Spider-Man", "99");
		assertNull(found);
	}
}
