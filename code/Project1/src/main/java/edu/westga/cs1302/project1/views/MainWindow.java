package edu.westga.cs1302.project1.views;

import edu.westga.cs1302.project1.model.Task;
import edu.westga.cs1302.project1.model.TaskUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 *
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private TextField nameField;

	@FXML
	private TextArea descriptionArea;

	@FXML
	private ComboBox<String> priorityCombo;

	@FXML
	private ListView<Task> taskListView;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private TextField selectedPriorityField;

	@FXML
	private Button updateDescriptionButton;

	@FXML
	private Button removeTaskButton;

	@FXML
	private Label lowCountLabel;

	@FXML
	private Label mediumCountLabel;

	@FXML
	private Label highCountLabel;

	@FXML
	private Button countTasksButton;

	private ObservableList<Task> taskList;

	/**
	 * Initializes the controller after the FXML file has been loaded Sets up the
	 * task list, priority options and selection
	 */

	@FXML
	public void initialize() {
		// Initialize priority options
		this.priorityCombo.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));
		this.taskList = FXCollections.observableArrayList();
		this.taskListView.setItems(this.taskList);
		this.taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldtask, newTask) -> {
			if (newTask != null) {
				this.descriptionTextArea.setText(newTask.getDescription());
				this.selectedPriorityField.setText(newTask.getPriority());
			} else {
				this.descriptionTextArea.clear();
				this.selectedPriorityField.clear();

			}
		});
	}

	@FXML
	private void addTask(ActionEvent event) {
		String name = this.nameField.getText();
		String description = this.descriptionArea.getText();
		String priority = this.priorityCombo.getValue();

		if (name.isEmpty() || priority == null || name == null) {
			this.showAlert("Please enter both name and priority.");
			return;
		}

		Task newTask = new Task(name, description, priority);
		this.taskList.add(newTask);

		this.nameField.clear();
		this.descriptionArea.clear();
		this.priorityCombo.getSelectionModel().clearSelection();
	}

	@FXML
	private void handleUpdateDescription(ActionEvent event) {
		Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
		if (selectedTask != null) {
			selectedTask.setDescription(this.descriptionTextArea.getText());
			this.taskListView.refresh();
		}
	}

	@FXML
	private void onRemoveTask() {
		Task selected = this.taskListView.getSelectionModel().getSelectedItem();
		if (selected == null) {
			this.showAlert("No task selected to remove.");
			return;
		}
		this.taskList.remove(selected);

	}

	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("WINDOW_TITLE");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	private void handleCountTasks() {
		ObservableList<Task> tasks = this.taskListView.getItems();

		int lowCount = TaskUtils.countTasksByPriority("Low", tasks);
		int mediumCount = TaskUtils.countTasksByPriority("Medium", tasks);
		int highCount = TaskUtils.countTasksByPriority("High", tasks);

		this.lowCountLabel.setText("Low Priority: " + lowCount);
		this.mediumCountLabel.setText("Medium Priority: " + mediumCount);
		this.highCountLabel.setText("High Priority: " + highCount);
	}
}