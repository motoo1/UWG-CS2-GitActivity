package edu.westga.cs1302.task_tracker.views;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.viewmodel.CollectionViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML
	private TextField collectionName;
	@FXML
	private Button addButton;
	@FXML
	private ListView<Collection> collections;
	private final CollectionViewModel viewModel = new CollectionViewModel();
		
	@FXML
	void addCollection(ActionEvent event) {
	    if (this.viewModel.nameProperty().get().isBlank()) {
	        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a collection name ");
	        alert.showAndWait();
	        return;
	    }

	    this.viewModel.addCollection();	}

	@FXML
	void removeCollection(ActionEvent event) {
	    if (this.viewModel.selectedCollectionProperty().get() == null) {
	        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a collection to remove.");
	        alert.showAndWait();
	        return;
	    }
	    this.viewModel.removeCollection();

	    	}

	/**
	 * Perform any needed initialization of UI components and underlying objects.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	@FXML
	public void initialize() {
		this.addButton.disableProperty().bind(
			    this.viewModel.nameProperty().isEmpty()
			);
		
	this.collectionName.textProperty().bindBidirectional(this.viewModel.nameProperty());
	this.collections.setItems(this.viewModel.getCollections());
	this.viewModel.selectedCollectionProperty().bind(this.collections.getSelectionModel().selectedItemProperty());
	ContextMenu collectionContextMenu = new ContextMenu();
	MenuItem removeItem = new MenuItem("Remove Collection");
	removeItem.setOnAction(e -> this.removeCollection(null));
	collectionContextMenu.getItems().add(removeItem);
	this.collections.setContextMenu(collectionContextMenu);
	}
	}
