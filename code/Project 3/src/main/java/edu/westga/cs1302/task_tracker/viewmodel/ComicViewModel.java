package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComicViewModel {

	private final StringProperty title = new SimpleStringProperty();
	private final IntegerProperty issueNumber = new SimpleIntegerProperty();
	private final ObjectProperty<Comic> selectedComic = new SimpleObjectProperty<>();
	private final ObservableList<Comic> comics = FXCollections.observableArrayList();
	private final ObjectProperty<Collection> selectedCollection = new SimpleObjectProperty<>();

	public StringProperty titleProperty() {
		return this.title;
	}

	public IntegerProperty issueNumberProperty() {
		return this.issueNumber;
	}

	public ObjectProperty<Comic> selectedComicProperty() {
		return this.selectedComic;
	}

	public ObservableList<Comic> getComics() {
		return this.comics;
	}

	public ObjectProperty<Collection> selectedCollectionProperty() {
		return this.selectedCollection;
	}

	public void addComic() {
		Collection collection = this.selectedCollection.get();
		if (collection != null) {
			Comic newComic = new Comic(this.title.get(), this.issueNumber.get());
			collection.addComic(newComic);
			this.comics.setAll(collection.getComics());

			this.title.set("");
			this.issueNumber.set(0);
		}
	}

	public void removeComic() {
		Comic comic = this.selectedComic.get();
		Collection collection = this.selectedCollection.get();
		if (collection != null && comic != null) {
			collection.removeComic(comic);
			this.comics.setAll(collection.getComics());
		}
	}
}