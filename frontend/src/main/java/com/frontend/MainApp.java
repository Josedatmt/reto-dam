package com.frontend;

import com.frontend.controller.AuthController;
import com.frontend.service.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/auth/login.fxml"));
        Parent root = loader.load();


        AuthController authController = loader.getController();


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/views/css/styles.css").toExternalForm());


        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/views/images/logo.png")));

        primaryStage.setTitle("Sistema de Gestión de Vacantes");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {

        SessionManager.logout();


        launch(args);
    }


    public static void changeScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(MainApp.class.getResource("/views/css/styles.css").toExternalForm());

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            System.err.println("Error loading scene: " + fxmlPath);
            e.printStackTrace();
        }
    }


    public static void logout() {
        SessionManager.logout();
        changeScene("/views/auth/login.fxml", "Inicio de Sesión");
    }
}