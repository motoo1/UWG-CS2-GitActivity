package edu.westga.cs1302.task_tracker.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;

class ComicViewModelTest {

	private ComicViewModel viewModel;
	private Collection testCollection;

	@BeforeEach
	void setUp() {
		viewModel = new ComicViewModel();
		testCollection = new Collection("Test Collection");
		viewModel.selectedCollectionProperty().set(testCollection);
	}

	@Test
	void testAddComicAddsToCollectionAndResetsFields() {
		viewModel.titleProperty().set("Spider-Man");
		viewModel.issueNumberProperty().set(1);
		viewModel.addComic();

		assertEquals(1, testCollection.getComics().size());
		Comic addedComic = testCollection.getComics().get(0);
		assertEquals("Spider-Man", addedComic.getTitle());
		assertEquals(1, addedComic.getIssueNumber());
		assertEquals(1, viewModel.getComics().size());
		assertSame(addedComic, viewModel.getComics().get(0));
		assertEquals("", viewModel.titleProperty().get());
		assertEquals(0, viewModel.issueNumberProperty().get());
	}

	@Test
	void testRemoveComicRemovesFromCollectionAndList() {
		Comic comic = new Comic("Batman", 50);
		testCollection.addComic(comic);
		viewModel.selectedComicProperty().set(comic);
		viewModel.getComics().setAll(testCollection.getComics());
		viewModel.removeComic();
		assertTrue(testCollection.getComics().isEmpty());
		assertTrue(viewModel.getComics().isEmpty());
	}

	@Test
	void testObservableListUpdatesWhenCollectionChanges() {
		Comic comic1 = new Comic("X-Men", 10);
		Comic comic2 = new Comic("Avengers", 5);
		testCollection.addComic(comic1);
		testCollection.addComic(comic2);
		viewModel.getComics().setAll(testCollection.getComics());
		assertEquals(2, viewModel.getComics().size());
		assertTrue(viewModel.getComics().contains(comic1));
		assertTrue(viewModel.getComics().contains(comic2));
	}
}