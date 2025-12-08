package edu.westga.cs1302.task_tracker.views;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import edu.westga.cs1302.task_tracker.viewmodel.FindComicViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindComicWindow {

	@FXML
	private TextField titleTextField;

	@FXML
	private TextField issueNumberTextField;

	@FXML
	private Button searchButton;

	@FXML
	private Button cancelButton;

	private FindComicViewModel viewModel;

	public void setViewModel(FindComicViewModel viewModel) {
		this.viewModel = viewModel;
	}

	@FXML
	private void search() {
		String title = titleTextField.getText().trim();
		String issueNumber = issueNumberTextField.getText().trim();

		if (title.isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please enter a title.");
			alert.showAndWait();
			return;
		}

		if (issueNumber.isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please enter an issue number.");
			alert.showAndWait();
			return;
		}

		try {
			int issue = Integer.parseInt(issueNumber);
			if (issue <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Issue number must be a positive integer.");
			alert.showAndWait();
			return;
		}

		Comic found = viewModel.searchComic(title, issueNumber);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		if (found != null) {
			alert.setContentText("Comic found: " + found);
		} else {
			alert.setContentText("Comic not found.");
		}
		alert.showAndWait();
	}

	@FXML
	private void cancel() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}