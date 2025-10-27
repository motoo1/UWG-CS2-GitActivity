package edu.westga.cs1302.task_tracker.views;

import javafx.collections.FXCollections;
import edu.westga.cs1302.task_tracker.model.SubTask;
import javafx.scene.control.TextInputDialog;
import java.util.Comparator;
import edu.westga.cs1302.task_tracker.model.AscendingName;
import edu.westga.cs1302.task_tracker.model.DescendingName;
import edu.westga.cs1302.task_tracker.model.AscendingPriority;
import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.DescendingPriority;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.TaskUtility;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML
	private TextArea description;
	@FXML
	private Label highCount;
	@FXML
	private Label lowCount;
	@FXML
	private Label mediumCount;
	@FXML
	private TextField name;
	@FXML
	private ComboBox<TaskPriority> priority;
	@FXML
	private TextArea selectedDescription;
	@FXML
	private TextField selectedPriority;
	@FXML
	private ListView<Task> tasks;
	@FXML
	private ListView<Task> subtasks;
	@FXML
	private ComboBox<Comparator<Task>> order;
	@FXML
	private TextField selectedSubtaskName;
	@FXML
	private TextArea selectedSubtaskDescription;
	@FXML
	private TextField selectedSubtaskPriority;

	/**
	 * Add a new task with the provided information to the listview.
	 * 
	 * @precondition none
	 * @postcondition A task will be added to the listview with 1) a name matching
	 *                the text of the name textfield, 2) a description matching the
	 *                text of the description textarea, 3) a priority matching the
	 *                selected value of the priority combobox,
	 * 
	 * @param event we will not use this parameter, only here due to JavaFX Library
	 *              requirement
	 */

	@FXML
	void addTask(ActionEvent event) {
		try {
			this.tasks.getItems()
					.add(new Task(this.name.getText(), this.description.getText(), this.priority.getValue()));
			this.resortTasks();
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Display the priority and description of the task selected in the listview.
	 * 
	 * @precondition none
	 * @postcondition the description for the selected task will be displayed in the
	 *                selectedDescription text area && the priority for the selected
	 *                task will be displayed in the selectedPriority text field
	 * 
	 * @param event we will not use this parameter, only here due to JavaFX Library
	 *              requirement
	 */

	@FXML
	void selectTask(MouseEvent event) {
		Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
		if (selectedTask != null) {
			this.selectedPriority.setText(selectedTask.getPriority().toString());
			this.selectedDescription.setText(selectedTask.getDescription());
			this.subtasks.setItems(FXCollections.observableArrayList(selectedTask.getSubTasks()));
		}
	}

	/**
	 * Remove the currently selected task.
	 * 
	 * @precondition none
	 * @postcondition task selected in the listview will be removed
	 * 
	 * @param event we will not use this parameter, only here due to JavaFX Library
	 *              requirement
	 */
	@FXML
	void removeTask(ActionEvent event) {
		Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
		if (selectedTask != null) {
			this.tasks.getItems().remove(selectedTask);
		}
	}

	/**
	 * Add a new subtask to the currently selected task.
	 * 
	 * @param event the ActionEvent triggered by the button click
	 */

	@FXML
	void addSubtask(ActionEvent event) {
		Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();

		if (selectedTask == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Please select a task first.");
			alert.showAndWait();
			return;
		}

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add Subtask");
		dialog.setHeaderText("Enter subtask name:");
		dialog.setContentText("Subtask:");

		dialog.showAndWait().ifPresent(subtaskName -> {
			if (!subtaskName.isEmpty()) {

				Task newSubtask = new Task(subtaskName, "Subtask of " + selectedTask.getName(),
						selectedTask.getPriority());

				Task updatedTask = selectedTask.addTask(newSubtask);

				int selectedIndex = this.tasks.getSelectionModel().getSelectedIndex();
				this.tasks.getItems().set(selectedIndex, updatedTask);

				this.subtasks.setItems(FXCollections.observableArrayList(updatedTask.getSubTasks()));
			}
		});
	}

	/** Remove the currently selected subtask. */

	@FXML
	void removeSubtask(ActionEvent event) {
		Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
		Task selectedSubtask = this.subtasks.getSelectionModel().getSelectedItem();

		if (selectedTask == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please select a main task first.");
			alert.showAndWait();
			return;
		}

		if (selectedSubtask == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please select a subtask to remove.");
			alert.showAndWait();
			return;
		}

		if (selectedTask instanceof ContainerTask containerTask) {
			containerTask.getSubTasks().remove(selectedSubtask);

			this.subtasks.getItems().setAll(containerTask.getSubTasks());

			int index = this.tasks.getSelectionModel().getSelectedIndex();
			this.tasks.getItems().set(index, containerTask);
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("This task does not contain any subtasks.");
			alert.showAndWait();
		}
	}

	@FXML
	void selectSubtask(MouseEvent event) {
		Task selectedSub = this.subtasks.getSelectionModel().getSelectedItem();
		if (selectedSub != null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Subtask Details");
			alert.setHeaderText(selectedSub.getName());
			alert.setContentText(
					"Priority: " + selectedSub.getPriority() + "\n\nDescription: " + selectedSub.getDescription());
			alert.showAndWait();
		}
	}

	/**
	 * Update the description of the selected task.
	 * 
	 * @precondition none
	 * @postcondition description for the task selected in the listview will be
	 *                updated to match the text in the selectedDescription text
	 *                area.
	 * 
	 * @param event we will not use this parameter, only here due to JavaFX Library
	 *              requirement
	 */
	@FXML
	void updateDescription(ActionEvent event) {
		Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
		if (selectedTask != null) {
			selectedTask.setDescription(this.selectedDescription.getText());
			this.resortTasks();
		}
	}

	/**
	 * Display the count of tasks for each priority.
	 * 
	 * @precondition none
	 * @postcondition count of tasks for each priority are displayed in the
	 *                appropriate labels.
	 * 
	 * @param event we will not use this parameter, only here due to JavaFX Library
	 *              requirement
	 */
	@FXML
	void countPriorities(ActionEvent event) {
		this.highCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.HIGH, this.tasks.getItems())));
		this.mediumCount
				.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.MEDIUM, this.tasks.getItems())));
		this.lowCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.LOW, this.tasks.getItems())));
	}

	/**
	 * Sort tasks based on the selected ordering.
	 * 
	 * @precondition none
	 * @postcondition tasks in the listview are sorted based on the provided
	 *                ordering.
	 * 
	 * @param event we will not use this parameter, only here due to JavaFX Library
	 *              requirement
	 */
	@FXML
	void sortTasks(ActionEvent event) {
		if (this.order.getValue() != null) {
			this.tasks.getItems().sort(this.order.getValue());
		}
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
		this.priority.getItems().addAll(TaskPriority.HIGH, TaskPriority.MEDIUM, TaskPriority.LOW);
		this.priority.setValue(this.priority.getItems().get(0));

		this.order.getItems().add(new AscendingPriority());
		this.order.getItems().add(new DescendingPriority());
		this.order.getItems().add(new AscendingName());
		this.order.getItems().add(new DescendingName());

		this.subtasks.getSelectionModel().selectedItemProperty().addListener((observable, oldSubtask, newSubtask) -> {
			if (newSubtask != null) {
				this.selectedSubtaskName.setText(newSubtask.getName());
				this.selectedSubtaskDescription.setText(newSubtask.getDescription());
				this.selectedSubtaskPriority.setText(newSubtask.getPriority().toString());
			} else {
				this.selectedSubtaskName.setText("");
				this.selectedSubtaskDescription.setText("");
				this.selectedSubtaskPriority.setText("");
			}
		});
	}

	private void resortTasks() {
		Comparator<Task> comparator = this.order.getValue();
		if (comparator != null) {
			this.tasks.getItems().sort(comparator);

		}

	}
}
