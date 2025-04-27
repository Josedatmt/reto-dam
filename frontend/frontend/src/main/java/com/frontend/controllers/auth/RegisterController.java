package com.frontend.controllers.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.frontend.models.Usuario;
import com.frontend.services.UsuarioService;
import com.frontend.utils.AlertUtils;
import com.frontend.utils.FormValidator;
import com.frontend.utils.SceneManager;
import javafx.stage.Stage;

public class RegisterController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;

    @FXML
    private void handleRegister() {
        if (!FormValidator.isValidEmail(txtEmail.getText())) {
            AlertUtils.showError("Email no válido");
            return;
        }

        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            AlertUtils.showError("Las contraseñas no coinciden");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(txtNombre.getText());
        usuario.setApellidos(txtApellidos.getText());
        usuario.setEmail(txtEmail.getText());
        usuario.setPassword(txtPassword.getText());

        try {
            UsuarioService.create(usuario);
            AlertUtils.showInfo("Registro exitoso");
            SceneManager.changeScene(
                    (Stage) txtNombre.getScene().getWindow(),
                    "/views/auth/login.fxml"
            );
        } catch (Exception e) {
            AlertUtils.showError("Error en el registro: " + e.getMessage());
        }
    }

    @FXML
    private void handleLoginLink(ActionEvent actionEvent) {
        try {
            SceneManager.changeScene(
                    (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow(),
                    "/views/auth/login.fxml"
            );
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar el login: " + e.getMessage());
        }
    }
}