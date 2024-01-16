package com.tasks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Button exitAppButton;


    @FXML
    public void exitAppLogic(){
        System.exit(1);
    }
}