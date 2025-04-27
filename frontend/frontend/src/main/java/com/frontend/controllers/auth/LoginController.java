package com.frontend.controllers.auth;

import com.frontend.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.frontend.config.AuthContext;
import com.frontend.services.UsuarioService;
import com.frontend.utils.AlertUtils;
import com.frontend.utils.SceneManager;



public class LoginController {
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;

    @FXML
    private void handleLogin() {
        try {
            Usuario usuario = UsuarioService.login(
                    txtEmail.getText().trim(),
                    txtPassword.getText()
            );


            if (usuario.getPerfiles() == null || usuario.getPerfiles().isEmpty()) {
                AlertUtils.showError("El usuario no tiene perfiles asignados");
                return;
            }

            AuthContext.login(
                    usuario.getEmail(),
                    usuario.getPerfiles().get(0).getNombre(),
                    usuario.getUsername()
            );


            String dashboardPath = AuthContext.isAdmin()
                    ? "/views/admin/dashboard.fxml"
                    : "/views/user/dashboard.fxml";

            SceneManager.changeScene(
                    (Stage) txtEmail.getScene().getWindow(),
                    dashboardPath
            );

        } catch (Exception e) {
            AlertUtils.showError("Error de autenticación: " + e.getMessage());
        }
    }
    @FXML
    private void handleRegisterLink(ActionEvent event) {
        SceneManager.changeScene(
                (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow(),
                "/views/auth/register.fxml"
        );
    }


    @FXML
    private void initialize() {
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                handleLogin();
            }
        });
    }
}