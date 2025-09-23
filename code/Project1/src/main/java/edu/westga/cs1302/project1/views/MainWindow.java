package edu.westga.cs1302.project1.views;

import edu.westga.cs1302.project1.model.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;



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
    private ComboBox<String> priorityCombo;

    @FXML
    private ListView<Task> taskListView;
    
    private ObservableList<Task> tasks;
    
     @FXML
    private TextArea descriptionArea;
     
     @FXML
     private TextField selectedPriorityField;
     
     @FXML
     private Button removeTaskButton;
     
     
     @FXML
     void onRemoveTask(ActionEvent event) {
    	 Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
    	 if (selectedTask != null) {
    		 taskListView.getItems().remove(selectedTask);
    	 }
     }
     

	
    
    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
    @FXML
    public void initialize() {
    	tasks = FXCollections.observableArrayList();
    	taskListView.setItems(tasks);
    	
    	priorityCombo.setItems(FXCollections.observableArrayList("High","Medium","Low"));
    	taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldTask,newTask) -> {
    		if (newTask != null) {
    		descriptionArea.setText(newTask.getDescription());
    		selectedPriorityField.setText(newTask.getPriority());
    	} else {
    		descriptionArea.clear();
    		selectedPriorityField.clear();
    	}
    	}
    	);
   
}
    
    @FXML
    public void onAddTask(ActionEvent event) {
    String name = nameField.getText();
	String priority = priorityCombo.getValue();
	
	if(name  != null && !name.isBlank() && priority != null) {	
	  Task newTask = new Task(name, priority);
	  tasks.add(newTask);
	
	nameField.clear();
	priorityCombo.setValue(null);
}
}
    
    @FXML
    private void onUpdateDescription(ActionEvent event) {
    	Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		String newDescription = descriptionArea.getText();
    		selectedTask.setDescription(newDescription);
    		taskListView.refresh();
    	}
    }
    	}
  
    

    
    
  
