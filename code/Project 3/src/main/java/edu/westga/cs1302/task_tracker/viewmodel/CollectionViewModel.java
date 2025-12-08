package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel class for managing collections in the application
 * Provides properties and observable lists for UI binding.
 *
 * @author motoo1
 * @version 1.0
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

	/**
	 * Returns the property used for storing the name of a new collection
	 *
	 * @return the StringProperty that holds the new collection name
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 *  Returns the property representing the currently selected collection
	 *
	 * @return the ObjectProperty that stores the selected collection
	 */
	public ObjectProperty<Collection> selectedCollectionProperty() {
		return this.selectedCollection;
	}

	/**
	 * Returns the observable list containing all collections
	 *
	 * @return the ObservableList of all collections in the ViewModel
	 */
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