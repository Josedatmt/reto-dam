package com.frontend.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    public static void loadScene(Stage stage, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void changeScene(Stage stage, String fxmlPath) {
        try {
            loadScene(stage, fxmlPath);
        } catch (IOException e) {
            AlertUtils.showError("Error al cargar la vista: " + e.getMessage());
        }
    }
}