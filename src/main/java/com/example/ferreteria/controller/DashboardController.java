package com.example.ferreteria.controller;

import com.example.ferreteria.model.Sesion;
import com.example.ferreteria.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Label lblBienvenida;
    @FXML private Button btnProductos;
    @FXML private Button btnVentas;
    @FXML private Button btnReportes;

    @FXML
    public void initialize() {
        Usuario usuario = Sesion.getUsuarioActual();
        if (usuario == null) return;

        lblBienvenida.setText("Bienvenido, " + usuario.getNombre() + " (" + usuario.getRol() + ")");

        // Restriccion de opciones segun el rol
        switch (usuario.getRol()) {
            case "CAJERO" -> btnReportes.setDisable(true);
            case "REPORTES" -> {
                btnVentas.setDisable(true);
                btnProductos.setDisable(true);
            }
            default -> { } // ADMIN ve todo
        }
    }

    private void cambiarEscena(String fxml, String titulo, Node origen) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) origen.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ferreteria - " + titulo);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar la pantalla: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void irAProductos() {
        cambiarEscena("/com/example/ferreteria/productos.fxml", "Inventario", lblBienvenida);
    }

    @FXML
    private void irAVentas() {
        cambiarEscena("/com/example/ferreteria/ventas.fxml", "Ventas", lblBienvenida);
    }

    @FXML
    private void irAReportes() {
        cambiarEscena("/com/example/ferreteria/reportes.fxml", "Reportes", lblBienvenida);
    }

    @FXML
    private void cerrarSesion() {
        Sesion.setUsuarioActual(null);
        cambiarEscena("/com/example/ferreteria/Login.fxml", "Login", lblBienvenida);
    }
}
