package edu.westga.cs1302.task_tracker.views;

import java.io.IOException;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import edu.westga.cs1302.task_tracker.viewmodel.CollectionViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for MainWindow of the Task Tracker system.
 *
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML
	private ListView<Comic> comicListView;

	@FXML
	private Button addComicButton;

	@FXML
	private Button removeComicButton;
	@FXML
	private TextField collectionName;
	@FXML
	private Button addButton;
	@FXML
	private ListView<Collection> collections;
	@FXML
	private MenuItem removeComicContextMenu;

	private final CollectionViewModel viewModel = new CollectionViewModel();

	@FXML
	void addCollection(ActionEvent event) {
		if (this.viewModel.nameProperty().get().isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a collection name ");
			alert.showAndWait();
			return;
		}
		this.viewModel.addCollection();
	}

	@FXML
	void removeCollection(ActionEvent event) {
		if (this.viewModel.selectedCollectionProperty().get() == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a collection to remove.");
			alert.showAndWait();
			return;
		}
		this.viewModel.removeCollection();
	}

	@FXML
	void addComic(ActionEvent event) {
	    Collection selectedCollection = this.viewModel.selectedCollectionProperty().get();
	    if (selectedCollection == null) {
	        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a collection first.");
	        alert.showAndWait();
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddComicWindow.fxml"));
	        Parent root = loader.load();
	        AddComicWindow controller = loader.getController();
	        controller.setCollection(selectedCollection);

	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Add Comic");
	        stage.showAndWait();
	    } catch (IOException exception) {
	        exception.printStackTrace();
	    }
	}

	@FXML
	void removeComic(ActionEvent event) {
		Comic selected = this.comicListView.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}
		this.viewModel.selectedCollectionProperty().get().getComics().remove(selected);
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
		this.addComicButton.setOnAction(e -> this.addComic(null));
		this.removeComicButton.setOnAction(e -> this.removeComic(null));
		this.viewModel.selectedCollectionProperty().addListener((obs, oldCollection, newCollection) -> {
			if (newCollection != null) {
				this.comicListView.setItems(newCollection.getComics());
			} else {
				this.comicListView.setItems(FXCollections.observableArrayList());
			}
		});
		this.comicListView.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
			this.removeComicButton.setDisable(selected == null);
		});

		this.removeComicButton.setDisable(true);

		this.addButton.disableProperty().bind(this.viewModel.nameProperty().isEmpty());
		this.collectionName.textProperty().bindBidirectional(this.viewModel.nameProperty());
		this.collections.setItems(this.viewModel.getCollections());
		this.viewModel.selectedCollectionProperty().bind(this.collections.getSelectionModel().selectedItemProperty());
		ContextMenu collectionContextMenu = new ContextMenu();
		MenuItem removeItem = new MenuItem("Remove Collection");
		removeItem.setOnAction(e -> this.removeCollection(null));
		collectionContextMenu.getItems().add(removeItem);
		this.collections.setContextMenu(collectionContextMenu);
		ContextMenu comicContextMenu = new ContextMenu();
		MenuItem removeComicItem = new MenuItem("Remove Comic");
		removeComicItem.setOnAction(e -> this.removeComic(null)); 
		comicContextMenu.getItems().add(removeComicItem);
		this.comicListView.setContextMenu(comicContextMenu);

	}
}
