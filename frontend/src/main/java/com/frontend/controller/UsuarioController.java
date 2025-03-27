package com.frontend.controller;

import com.frontend.model.Usuario;
import com.frontend.service.ApiService;
import com.frontend.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> cbPerfil;
    @FXML private GridPane formUsuario;

    private final ApiService apiService = new ApiService();
    private Usuario usuarioEdicion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbPerfil.getItems().addAll("ADMIN", "EMPRESA", "USUARIO");
    }

    public void setUsuarioEdicion(Usuario usuario) {
        this.usuarioEdicion = usuario;
        if (usuario != null) {
            txtUsername.setText(usuario.getUsername());
            txtNombre.setText(usuario.getNombre());
            txtApellidos.setText(usuario.getApellidos());
            txtEmail.setText(usuario.getEmail());
            cbPerfil.setValue(usuario.getPerfil().getNombre());
            txtUsername.setDisable(true);
        }
    }

    @FXML
    private void handleGuardar() {
        Usuario usuario = usuarioEdicion != null ? usuarioEdicion : new Usuario();

        usuario.setUsername(txtUsername.getText());
        usuario.setNombre(txtNombre.getText());
        usuario.setApellidos(txtApellidos.getText());
        usuario.setEmail(txtEmail.getText());

        if (usuarioEdicion == null || !txtPassword.getText().isEmpty()) {
            usuario.setPassword(txtPassword.getText());
        }

        try {
            boolean success;
            if (usuarioEdicion == null) {
                success = apiService.crearUsuario(usuario);
            } else {
                success = apiService.actualizarUsuario(usuario);
            }

            if (success) {
                AlertUtil.mostrarInfo("Éxito", "Usuario guardado correctamente");
                ((Stage) formUsuario.getScene().getWindow()).close();
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudo guardar el usuario");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancelar() {
        ((Stage) formUsuario.getScene().getWindow()).close();
    }
}