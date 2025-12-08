package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;

public class FindComicViewModel {

	private final Collection collection;

	public FindComicViewModel(Collection collection) {
		if (collection == null) {
			throw new IllegalArgumentException("Collection cannot be null");
		}
		this.collection = collection;
	}

	/**
	 * Search for a comic by title and issue number. Returns the Comic if found, or
	 * null if not found/invalid input.
	 */
	public Comic searchComic(String title, String issueNumber) {
		if (title == null || title.isBlank() || issueNumber == null || issueNumber.isBlank()) {
			return null;
		}
		return this.collection.findComic(title, issueNumber);
	}
}