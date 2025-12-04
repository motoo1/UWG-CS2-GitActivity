package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel for storing and managing collections. Handles properties and
 * methods for UI binding.
 * 
 * Task 1c
 * 
 */
public class CollectionViewModel {

	private final StringProperty nameProperty;
	private final ObservableList<Collection> collections;
	private final ObjectProperty<Collection> selectedCollection;

	/**
	 * Creates a new CollectionViewModel with empty properties.
	 */
	public CollectionViewModel() {
		this.nameProperty = new SimpleStringProperty("");
		this.collections = FXCollections.observableArrayList();
		this.selectedCollection = new SimpleObjectProperty<>(null);
	}

	/** Property for the name of a new collection */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/** Property for the currently selected collection */
	public ObjectProperty<Collection> selectedCollectionProperty() {
		return this.selectedCollection;
	}

	/** Observable list of all collections */
	public ObservableList<Collection> getCollections() {
		return this.collections;
	}

	/** Adds a new collection using the current nameProperty value */
	public void addCollection() {
		String name = this.nameProperty.get();
		Collection newCollection = new Collection(name);
		this.collections.add(newCollection);
		this.nameProperty.set("");
	}

	/** Removes the currently selected collection from the list */
	public void removeCollection() {
		Collection selected = this.selectedCollection.get();
		if (selected != null) {
			this.collections.remove(selected);
			this.selectedCollection.set(null);
		}
	}
}