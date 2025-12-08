package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollectionTest {

    private Collection collection;
    private Comic comic1;
    private Comic comic2;

    @BeforeEach
    void setUp() {
        collection = new Collection("Work Tasks");
        comic1 = new Comic("Spider-Man", 1);
        comic2 = new Comic("Iron Man", 1);
    }

    
    @Test
    void testValidName() {
        assertEquals("Work Tasks", collection.getName());
    }

    @Test
    void testNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Collection(null));
    }

    @Test
    void testBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Collection("   "));
    }

    @Test
    void testToStringReturnsName() {
        assertEquals("Work Tasks", collection.toString());
    }

    
    @Test
    void testAddComic() {
        assertEquals(0, collection.getComics().size());
        collection.addComic(comic1);
        assertEquals(1, collection.getComics().size());
        assertTrue(collection.getComics().contains(comic1));
    }

    @Test
    void testRemoveComic() {
        collection.addComic(comic1);
        collection.addComic(comic2);
        assertEquals(2, collection.getComics().size());

        collection.removeComic(comic1);
        assertEquals(1, collection.getComics().size());
        assertFalse(collection.getComics().contains(comic1));
        assertTrue(collection.getComics().contains(comic2));
    }
}