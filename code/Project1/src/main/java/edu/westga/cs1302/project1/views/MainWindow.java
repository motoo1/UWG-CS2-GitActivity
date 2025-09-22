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



/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	
   
	@FXML
    private TextArea descriptionArea;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> priorityCombo;

    @FXML
    private ListView<Task> taskListView;
    
    @FXML
    private TextArea selectedDescriptionArea;
    
    @FXML
    private TextField selectedPriorityField;
    
    private ObservableList<Task> tasks;
	
    
    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
    @FXML
    public void initialize() {
    	tasks = FXCollections.observableArrayList();
    	taskListView.setItems(tasks);
    	
    	priorityCombo.setItems(FXCollections.observableArrayList("High","Medium","Low"));
    	taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue,newTask) -> {
    		
    	if (newTask != null) {
    		selectedDescriptionArea.setText(newTask.getDescription());
    		selectedPriorityField.setText(newTask.getPriority());
    	} else {
    		selectedDescriptionArea.clear();
    		selectedPriorityField.clear();
    	}
    	}
    	);
   
}
    
    @FXML
    public void onAddTask(ActionEvent event) {
    String name = nameField.getText().trim();
	String description = descriptionArea.getText().trim();
	String priority = priorityCombo.getValue();
	
	if(name.isEmpty() || priority == null) {
		System.out.println("Task name and priority are required.");
		return;
	}
	
	Task newTask = new Task(name, description, priority);
	tasks.add(newTask);
	
	nameField.clear();
	descriptionArea.clear();
	priorityCombo.setValue(null);
}
}

    
    
  
