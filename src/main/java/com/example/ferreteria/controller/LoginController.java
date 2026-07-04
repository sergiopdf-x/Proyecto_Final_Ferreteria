package com.example.ferreteria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContra;
    @FXML
    private Button btnIngresar;

    private void alerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void navegarEntreFormularios(String rutaFxml, String titulo) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource(rutaFxml));
            javafx.scene.Parent root = loader.load();

            //Obtener la pagina actual utilizando el boton ingresar y cambio a la nueva pagina
            javafx.stage.Stage stage = (javafx.stage.Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Ferreteria - " + titulo);
            stage.show();

        } catch (Exception e) {
            alerta("Error", "No fue posible cambiar la pantalla: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void manejarLogin() {
        String usuario = txtUsuario.getText().trim();
        String contra = txtContra.getText().trim();

        if (usuario.isEmpty() || contra.isEmpty()) {
            alerta("Campos vacios", "Usuario o contraseña no pueden estar vacios", Alert.AlertType.WARNING);
            return;
        }

        if (contra.length() < 6) {
            alerta("Contraseña invalida", "La contraseña debe tener mínimo 6 caracteres", Alert.AlertType.WARNING);
            return;
        }

        if (usuario.equals("admin@poli.com") && contra.equals("admin123")) {
            alerta("Exito", "Validaciones aprobadas. Bienvenido ADMIN", Alert.AlertType.INFORMATION);
            navegarEntreFormularios("/com/example/ferreteria/dashboard.fxml", "Dashboard Principal");
        } else if (usuario.equals("bodega@poli.com") && contra.equals("bodega123")) {
            alerta("Exito", "Bienvenido CAJERO", Alert.AlertType.INFORMATION);
            navegarEntreFormularios("/com/example/ferreteria/productos.fxml", "Gestion de Inventario");
        } else {
            alerta("Error", "Credenciales Incorrectas", Alert.AlertType.ERROR);
        }
    }
}