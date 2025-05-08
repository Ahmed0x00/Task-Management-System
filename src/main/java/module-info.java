module com.example.taskmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens com.example.taskmanagement to javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.taskmanagement.controller to javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.taskmanagement.model to javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.taskmanagement.controller.admin to com.fasterxml.jackson.databind, javafx.fxml;
    opens com.example.taskmanagement.controller.employee to com.fasterxml.jackson.databind, javafx.fxml;

    exports com.example.taskmanagement;

}