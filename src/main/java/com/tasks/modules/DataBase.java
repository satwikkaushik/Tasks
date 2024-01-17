package com.tasks.modules;

import java.sql.*;

import java.util.LinkedList;

public class DataBase {

    private static final String URL = "jdbc:sqlite:tasksDB.db";
    private static Connection connection = null;

    public static boolean makeConnection() throws SQLException{
        try {
            connection = DriverManager.getConnection(URL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createTable() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "deadline TEXT, " +
                "priority TEXT NOT NULL, " +
                "isCompleted INTEGER NOT NULL CHECK (isCompleted IN (0, 1)))";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addTask(Task newTask){
        String query = "INSERT INTO tasks (title, description, deadline, priority, isCompleted) VALUES (?, ?, ?, ?, ?)";

        int isCompleted = (newTask.isComplete() == true) ? 1 : 0;

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, newTask.getTitle());
            statement.setString(2, newTask.getDescription());
            statement.setString(3, newTask.getDeadline());
            statement.setString(4, newTask.getPriority());
            statement.setInt(5, isCompleted);

            // this is to just check whether database was updated or not
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static LinkedList<Task> getInboxTasks(String date){
        String query = "SELECT * FROM tasks WHERE (deadline = ? OR deadline IS NULL) AND isCompleted = 0";

        LinkedList<Task> taskList = new LinkedList<>();

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, date);

            try(ResultSet result = statement.executeQuery()){
                while (result.next()){
                    String title = result.getString("title");
                    String description = result.getString("description");
                    String deadline = result.getString("deadline");
                    String priority = result.getString("priority");

                    Task newTask = new Task(title, description, deadline, priority);
                    taskList.add(newTask);

                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return taskList;
    }

    public static LinkedList<Task> getAllTasks(){
        String query = "SELECT * FROM tasks WHERE isCompleted = 0";

        LinkedList<Task> taskList = new LinkedList<>();

        try(PreparedStatement statement = connection.prepareStatement(query)){
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    String title = result.getString("title");
                    String description = result.getString("description");
                    String deadline = result.getString("deadline");
                    String priority = result.getString("priority");

                    Task newTask = new Task(title, description, deadline, priority);
                    taskList.add(newTask);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return taskList;
    }

    public static LinkedList<Task> getCompleted(){
        String query = "SELECT * FROM tasks WHERE isCompleted = 1";

        LinkedList<Task> taskList = new LinkedList<>();

        try(PreparedStatement statement = connection.prepareStatement(query)){
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    String title = result.getString("title");
                    String description = result.getString("description");
                    String deadline = result.getString("deadline");
                    String priority = result.getString("priority");

                    Task newTask = new Task(title, description, deadline, priority);
                    taskList.add(newTask);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return taskList;
    }

    public static boolean deleteTask(String title, String deadline, String priority) {
        String query = "DELETE FROM tasks WHERE title = ? AND deadline = ? AND priority = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, deadline);
            statement.setString(3, priority);

            // this is to just check whether database was updated or not
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
