package com.frontend.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AdminDashboardController {
    @FXML private Label lblWelcome;

    @FXML
    public void initialize() {
        lblWelcome.setText("Bienvenido, Administrador");
    }
}