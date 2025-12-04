package edu.westga.cs1302.task_tracker.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollectionViewModelTest {

	private CollectionViewModel viewModel;

	@BeforeEach
	void setUp() {
		viewModel = new CollectionViewModel();
	}

	@Test
	void addCollectionValidNameAddsToList() {
		viewModel.nameProperty().set("Work Tasks");
		viewModel.addCollection();
		assertEquals(1, viewModel.getCollections().size());
		assertEquals("Work Tasks", viewModel.getCollections().get(0).getName());
		assertEquals("", viewModel.nameProperty().get());
	}

	@Test
	void removeCollectionRemovesSelectedCollection() {
		viewModel.nameProperty().set("School");
		viewModel.addCollection();
		viewModel.selectedCollectionProperty().set(viewModel.getCollections().get(0));
		viewModel.removeCollection();
		assertEquals(0, viewModel.getCollections().size());
		assertNull(viewModel.selectedCollectionProperty().get());
	}

	@Test
	void removeCollectionWhenNoneSelectedDoesNothing() {
		viewModel.nameProperty().set("Work");
		viewModel.addCollection();
		viewModel.removeCollection(); // selectedCollection is null
		assertEquals(1, viewModel.getCollections().size());
	}
}