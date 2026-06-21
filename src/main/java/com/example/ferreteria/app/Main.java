package com.example.ferreteria.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

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
}
