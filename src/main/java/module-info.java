module com.tasks {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tasks to javafx.fxml;
    exports com.tasks;
    exports com.tasks.controller;
    opens com.tasks.controller to javafx.fxml;
}