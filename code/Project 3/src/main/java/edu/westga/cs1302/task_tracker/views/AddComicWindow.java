package edu.westga.cs1302.task_tracker.views;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.viewmodel.ComicViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class AddComicWindow {

	@FXML
	private TextField titleTextField;

	@FXML
	private TextField issueNumberTextField;

	@FXML
	private Button confirmButton;

	@FXML
	private Button cancelButton;

	private Collection collection;
	private ComicViewModel viewModel;

	public void setViewModel(ComicViewModel viewModel, Collection selectedCollection) {
		this.viewModel = viewModel;
		this.viewModel.selectedCollectionProperty().set(selectedCollection);
		this.titleTextField.textProperty().bindBidirectional(viewModel.titleProperty());
		this.issueNumberTextField.textProperty().bindBidirectional(viewModel.issueNumberProperty(),
				new NumberStringConverter());
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	@FXML
	private void confirm() {
		try {
			this.viewModel.addComic(); 
			Stage stage = (Stage) this.confirmButton.getScene().getWindow();
			stage.close();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	private void cancel() {
		Stage stage = (Stage) this.cancelButton.getScene().getWindow();
		stage.close();
	}
}