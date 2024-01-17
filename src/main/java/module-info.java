module com.tasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.tasks to javafx.fxml;
    exports com.tasks;
    exports com.tasks.controller;
    opens com.tasks.controller to javafx.fxml;
    exports com.tasks.modules;
    opens com.tasks.modules to javafx.fxml;
}