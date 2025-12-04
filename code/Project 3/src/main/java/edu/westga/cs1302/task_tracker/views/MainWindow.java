package edu.westga.cs1302.task_tracker.views;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;


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
	private ListView<String> collections;
		
	@FXML
	void addCollection(ActionEvent event) {
	    String name = this.collectionName.getText().trim();

	    if (name.isEmpty()) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setContentText("Please enter a collection name.");
	        alert.showAndWait();
	        return;
	    }

	    this.collections.getItems().add(name);
	    this.collectionName.clear();
	}

	@FXML
	void removeCollection(ActionEvent event) {
	    String selected = this.collections.getSelectionModel().getSelectedItem();

	    if (selected == null) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setContentText("Please select a collection to remove.");
	        alert.showAndWait();
	        return;
	    }

	    this.collections.getItems().remove(selected);
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
				this.collections.setItems(FXCollections.observableArrayList());
	
	ContextMenu collectionContextMenu = new ContextMenu();
	MenuItem removeItem = new MenuItem("Remove Collection");
	removeItem.setOnAction(e -> removeCollection(null));
	collectionContextMenu.getItems().add(removeItem);
	this.collections.setContextMenu(collectionContextMenu);
	}


	}
