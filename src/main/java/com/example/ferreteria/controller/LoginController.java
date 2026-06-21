package com.example.ferreteria.controller;

import javafx.fxml.FXML;
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

    private void alerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void manejarLogin(){
        String usuario = txtUsuario.getText().trim();
        String contra = txtContra.getText().trim();

        if(usuario.isEmpty() || contra.isEmpty()){
            alerta("Campos vacios","Usuario o contraseña no pueden estar vacios",Alert.AlertType.WARNING);
            return;
        }

        if(contra.length() < 6){
            alerta("Contraseña invalida","La contraseña debe tener mínimo 6 caracteres",Alert.AlertType.WARNING);
            return;
        }
        alerta("Exito","Validaciones aprobadas.",Alert.AlertType.INFORMATION);
    }
}
