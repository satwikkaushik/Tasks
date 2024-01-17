package com.tasks.controller;

import com.tasks.modules.Task;
import com.tasks.modules.DataBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        DataBase.closeConnection();
        System.exit(1);
    }

    @FXML
    private void refresh(ActionEvent event) {
        try {
            LinkedList<Task> taskList = getTasks();

            if (taskList == null || taskList.isEmpty()) {
                emptyPane.setVisible(true);
                tasksVBox.setVisible(false);
            } else {
                // clear existing tasks
                tasksVBox.getChildren().clear();

                // load TaskView for each task
                for (Task task : taskList) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/tasks/taskCells.fxml"));
                    AnchorPane taskView = null;
                    try {
                        taskView = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    TaskCellController controller = loader.getController();

                    // Set data for the task using the Task object
                    String title = task.getTitle();
                    String deadline = task.getDeadline();
                    String priority = task.getPriority();

                    controller.setTaskData(title, deadline, priority);

                    // add the taskView to the tasksVBox
                    tasksVBox.getChildren().add(taskView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private LinkedList<Task> getTasks() {
        // get and format today's date for getting today's tasks
        LocalDate today = LocalDate.now();
        DateTimeFormatter todaDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayAsString = today.format(todaDate);

        return DataBase.getInboxTasks(todayAsString);
    }

    @FXML
    private void addNewTask(ActionEvent event) {
        try {
            // Load the FXML file for the new task view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tasks/addTaskView.fxml"));
            Parent parent = loader.load();

            // Create a new stage
            Stage newTaskStage = new Stage();
            newTaskStage.setTitle("New Task");
            newTaskStage.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
            newTaskStage.initStyle(StageStyle.UNDECORATED);
            newTaskStage.setScene(new Scene(parent, 720, 218));
            newTaskStage.setResizable(false);

            newTaskStage.showAndWait();

            // Code here will resume after the new stage is closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}