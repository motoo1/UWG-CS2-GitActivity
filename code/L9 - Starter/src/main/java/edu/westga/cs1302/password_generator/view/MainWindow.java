package edu.westga.cs1302.password_generator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML
	private MenuItem saveMenuItem;
	@FXML
	private MenuItem aboutMenuItem;
	@FXML
	private MenuItem closeMenuItem;
	@FXML
	private CheckBox mustIncludeDigits;
	@FXML
	private CheckBox mustIncludeLowerCaseLetters;
	@FXML
	private CheckBox mustIncludeUpperCaseLetters;
	@FXML
	private TextField minimumLength;
	@FXML
	private Label errorTextLabel;
	@FXML
	private Label minLengthErrorText;
	@FXML
	private Button generatePasswordButton;
	@FXML
	private ListView<String> passwordHistory;

	private ViewModel vm;

	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
		this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
		this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
		this.minimumLength.setText(this.vm.getMinimumLength().getValue());
		this.vm.getMinimumLength().bind(this.minimumLength.textProperty());

		this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
		this.passwordHistory.setItems(this.vm.getPasswordHistory());

		this.minimumLength.textProperty().addListener((observable, newValue, oldValue) -> {
			this.minLengthErrorText.setVisible(!newValue.matches("\\d+") || Integer.parseInt(newValue) == 0);
		});

		this.generatePasswordButton.setOnAction((event) -> {
			this.vm.generatePassword();
		});
	}

	@FXML
	private void handleSave(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Password History");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File file = fileChooser.showSaveDialog(((Node) this.errorTextLabel).getScene().getWindow());

		if (file != null) {
			try (FileWriter writer = new FileWriter(file)) {
				for (String password : this.vm.getPasswordHistory()) {
					writer.write(password + System.lineSeparator());
				}
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Save Successful");
				alert.setHeaderText(null);
				alert.setContentText("Passwords saved successfully to:\n" + file.getAbsolutePath());
				alert.showAndWait();
			} catch (IOException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle(null);
				alert.setHeaderText("Error saving file");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}
	}

	@FXML
	private void handleAbout(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About Password Generator");
		alert.setHeaderText("About This Project");
		alert.setContentText("This application generates random passwords with customizable settings.\n"
				+ "Created by Maame Ama Otoo for CS 1302, Fall 2025.");
		alert.showAndWait();
	}

	@FXML
	private void handleClose(ActionEvent event) {
		((Node) (this.errorTextLabel)).getScene().getWindow().hide();
	}

	private void showAlert(Alert.AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}


