package edu.westga.cs1302.task_tracker.views;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    // Call this before showing the window
    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    @FXML
    private void confirm() {
        String title = this.titleTextField.getText().trim();
        String issueText = this.issueNumberTextField.getText().trim();

        if (title.isEmpty() || issueText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in both fields.");
            alert.showAndWait();
            return;
        }

        int issueNumber;
        try {
            issueNumber = Integer.parseInt(issueText);
        } catch (NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Issue number must be an integer.");
            alert.showAndWait();
            return;
        }

        Comic newComic = new Comic(title, issueNumber);
        this.collection.getComics().add(newComic);

        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }
}