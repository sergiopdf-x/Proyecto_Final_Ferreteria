module com.example.ferreteria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ferreteria to javafx.fxml;
    exports com.example.ferreteria;
}