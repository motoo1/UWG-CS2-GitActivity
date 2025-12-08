package edu.westga.cs1302.task_tracker.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;

class CollectionViewModelTest {

    private CollectionViewModel viewModel;
    private Comic comic1;
    private Comic comic2;

    @BeforeEach
    void setUp() {
        viewModel = new CollectionViewModel();
        comic1 = new Comic("Spider-Man", 1);
        comic2 = new Comic("Iron Man", 1);
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
        viewModel.removeCollection(); 
        assertEquals(1, viewModel.getCollections().size());
    }

   

    @Test
    void testAddComicToSelectedCollection() {
        viewModel.nameProperty().set("Marvel");
        viewModel.addCollection();
        Collection selected = viewModel.getCollections().get(0);
        viewModel.selectedCollectionProperty().set(selected);

        
        selected.addComic(comic1);
        selected.addComic(comic2);

        assertEquals(2, selected.getComics().size());
        assertTrue(selected.getComics().contains(comic1));
        assertTrue(selected.getComics().contains(comic2));
    }

    @Test
    void testRemoveComicFromSelectedCollection() {
        viewModel.nameProperty().set("Marvel");
        viewModel.addCollection();
        Collection selected = viewModel.getCollections().get(0);
        viewModel.selectedCollectionProperty().set(selected);

        selected.addComic(comic1);
        selected.addComic(comic2);

        
        selected.removeComic(comic1);

        assertEquals(1, selected.getComics().size());
        assertFalse(selected.getComics().contains(comic1));
        assertTrue(selected.getComics().contains(comic2));
    }
}