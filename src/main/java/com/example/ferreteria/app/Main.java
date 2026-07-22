package com.example.ferreteria.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    java.sql.Connection con = com.example.ferreteria.db.Conexion.getConexion();
    {
        if (con != null) {
            System.out.println("Base de datos y conexión vinculadas correctamente.");
        }
    }

    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ferreteria/Login.fxml"));
            Parent root = loader.load();

            Scene scence = new Scene(root);
            primaryStage.setTitle("Ferreteria 'EL POLI' - Iniciar Sesion");
            primaryStage.setScene(scence);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicacion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public static void main(String[] args) {
        launch(args);
    }
}