package com.frontend.controllers.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserDashboardController {
    @FXML private Label lblWelcome;

    @FXML
    public void initialize() {
        lblWelcome.setText("Bienvenido, Usuario");
    }
}