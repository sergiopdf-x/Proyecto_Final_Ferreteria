module com.example.ferreteria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.ferreteria to javafx.fxml;
    opens com.example.ferreteria.controller to javafx.fxml;
    opens com.example.ferreteria.app to javafx.graphics;

    exports com.example.ferreteria.app;
    exports com.example.ferreteria.controller;
    exports com.example.ferreteria.model;
}