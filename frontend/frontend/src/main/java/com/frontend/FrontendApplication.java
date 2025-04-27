package com.frontend;

import javafx.application.Application;
import javafx.stage.Stage;
import com.frontend.utils.SceneManager;

public class FrontendApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.loadScene(primaryStage, "/views/auth/login.fxml");
			primaryStage.setTitle("Sistema de Gestión");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}