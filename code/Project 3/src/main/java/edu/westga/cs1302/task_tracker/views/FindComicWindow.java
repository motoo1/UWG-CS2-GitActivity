package edu.westga.cs1302.task_tracker.views;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
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

    private Collection collection;
    private Map<String, Comic> comicMap;

    public void setCollection(Collection collection) {
        this.collection = collection;
        this.comicMap = new HashMap<>();
        for (Comic comic : collection.getComics()) {
            String key = comic.getTitle().toLowerCase() + "#" + comic.getIssueNumber();
            comicMap.put(key, comic);
        }
    }

    @FXML
    private void search() {
        String title = titleTextField.getText().trim().toLowerCase();
        int issue;

        try {
            issue = Integer.parseInt(issueNumberTextField.getText().trim());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Issue number must be an integer.");
            alert.showAndWait();
            return;
        }

        String key = title + "#" + issue;
        Comic found = this.comicMap.get(key);

        Alert alert;
        if (found != null) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Comic found: " + found);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION, "Comic not found.");
        }
        alert.showAndWait();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}