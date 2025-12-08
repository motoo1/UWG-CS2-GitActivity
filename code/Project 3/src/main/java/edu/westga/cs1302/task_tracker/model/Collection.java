package edu.westga.cs1302.task_tracker.model;

import java.util.Map;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a named collection.
 *
 * @author CS1302
 * @version Fall 2025
 */

public class Collection {

	private String name;
	private ObservableList<Comic> comics;
	private Map<String, Comic> comicMap = new HashMap<>();

	/**
	 * Creates a new Collection with the provided name.
	 *
	 * @param name the name of the collection
	 *
	 * @precondition name != null && !name.isBlank()
	 * @postcondition getName().equals(name)
	 */
	public Collection(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Collection name cannot be empty.");
		}
		this.name = name;
		this.comics = FXCollections.observableArrayList();
	}

	/**
	 * Gets the name of this collection.
	 *
	 * @return the collection name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the comics in this collection.
	 *
	 * @return an observable list of comics
	 */
	public ObservableList<Comic> getComics() {
		return this.comics;
	}

	public void addComic(Comic comic) {
		this.comics.add(comic);
		this.comicMap.put(makeKey(comic.getTitle(), comic.getIssueNumber()), comic);
	}

	public void removeComic(Comic comic) {
		this.comics.remove(comic);
		this.comicMap.remove(makeKey(comic.getTitle(), comic.getIssueNumber()));
	}

	public Comic findComic(String title, String issueNumber) {
	    try {
	        int issue = Integer.parseInt(issueNumber.trim());
	        return this.comicMap.get(makeKey(title, issue));
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}
	
	private String makeKey(String title, int issueNumber) {
		return title.toLowerCase() + "-" + issueNumber;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
