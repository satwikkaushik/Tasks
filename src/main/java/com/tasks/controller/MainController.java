package com.tasks.controller;

import com.tasks.Task;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedList;

public class MainController {
    @FXML
    private Button exitAppButton;

    @FXML
    private Label greetingsLabel, usernameLabel;

    @FXML
    private Button newTaskButton;

    @FXML
    private Button inboxButton, todayButton, allTasksButton, completedButton;

    @FXML
    private Button highPriorityButton, mediumPriorityButton, lowPriorityButton, noPriorityButton;

    @FXML
    private AnchorPane emptyPane;

    @FXML
    private VBox tasksVBox;


    @FXML
    public void exitAppLogic(){
        System.exit(1);
    }

    @FXML
    private void refresh(ActionEvent event) {
        try {
            // Clear the existing tasks in VBox
            tasksVBox.getChildren().clear();

            // Simulating the linked list obtained from a database
            LinkedList<Task> taskList = getTasksFromDatabase(); // Replace with your actual database function

            // Load TaskView.fxml for each task
            for (Task task : taskList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/tasks/taskCells.fxml"));
                AnchorPane taskView = loader.load();

                TaskCellController controller = loader.getController();

                // Set data for the task using the Task object
                String title = task.getTitle();
                String deadline = task.getDeadline();
                String priority = task.getPriority();

                controller.setTaskData(title, deadline, priority);

                // Add the taskView to the tasksVBox
                tasksVBox.getChildren().add(taskView);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Add proper error handling
        }
    }


    // TODO : delete this sample testing code
    private LinkedList<Task> getTasksFromDatabase() {
        // Simulating tasks from a database
        LinkedList<Task> tasks = new LinkedList<>();
        tasks.add(new Task("Task 1", "2024-01-31", "High"));
        tasks.add(new Task("Task 2", "2024-02-15", "Medium"));
        tasks.add(new Task("Task 3", "2024-03-10", "Low"));
        // Add more tasks as needed
        return tasks;
    }
}