package com.frontend.config;

import javafx.scene.control.Alert;

public class RestExceptionHandler {
    public static void handle(Throwable e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en la operación");

        if (e instanceof java.net.ConnectException) {
            alert.setHeaderText("Error de conexión");
            alert.setContentText("No se pudo conectar al servidor. Verifique su conexión.");
        } else if (e instanceof RuntimeException) {
            alert.setHeaderText("Error en la solicitud");
            alert.setContentText(e.getMessage());
        } else {
            alert.setHeaderText("Error inesperado");
            alert.setContentText(e.toString());
        }

        alert.showAndWait();
    }
}