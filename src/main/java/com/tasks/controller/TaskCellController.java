package com.tasks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TaskCellController {
    @FXML
    private AnchorPane taskBox;

    @FXML
    private Button titleButton;

    @FXML
    private Label deadlineLabel;

    @FXML
    private Label priorityLabel;

    // Method to set data for the task
    public void setTaskData(String title, String deadline, String priority) {
        titleButton.setText(title);
        deadlineLabel.setText(deadline);
        priorityLabel.setText(priority);
    }
}
