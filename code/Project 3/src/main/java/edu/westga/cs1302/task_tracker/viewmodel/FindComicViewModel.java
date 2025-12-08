package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FindComicViewModel {
    
    private final Collection collection;
    private final StringProperty titleProperty;
    private final StringProperty issueNumberProperty;
    
    public FindComicViewModel(Collection collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection cannot be null");
        }
        this.collection = collection;
        this.titleProperty = new SimpleStringProperty();
        this.issueNumberProperty = new SimpleStringProperty();
    }
    
    public StringProperty titleProperty() {
        return this.titleProperty;
    }
    
    public StringProperty issueNumberProperty() {
        return this.issueNumberProperty;
    }
    
    /**
     * Search for a comic using the bound title and issue number properties.
     */
    public Comic searchComic() {
        String title = this.titleProperty.get();
        String issueNumber = this.issueNumberProperty.get();
        if (title == null || title.isBlank() || issueNumber == null || issueNumber.isBlank()) {
            return null;
        }
        return this.collection.findComic(title, issueNumber);
    }
    
    /**
     * Optional: search using parameters (non-bound)
     */
    public Comic searchComic(String title, String issueNumber) {
        if (title == null || title.isBlank() || issueNumber == null || issueNumber.isBlank()) {
            return null;
        }
        return this.collection.findComic(title, issueNumber);
    }
}