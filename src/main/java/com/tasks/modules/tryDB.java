package com.tasks.modules;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class tryDB {
    public static void main(String[] args) {
        try {
            DataBase.makeConnection();
            DataBase.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        LocalDate today = LocalDate.now();
        DateTimeFormatter todaDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayAsString = today.format(todaDate);

        Task newTask = new Task("Task1", "SampleTask1", todayAsString, "High Priority");

        DataBase.addTask(newTask);
    }
}
