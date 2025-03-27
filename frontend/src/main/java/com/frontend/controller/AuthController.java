package com.frontend.controller;

import com.frontend.MainApp;
import com.frontend.model.Usuario;
import com.frontend.service.AuthService;
import com.frontend.service.SessionManager;
import com.frontend.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AuthController {

    @FXML private TabPane authTabPane;
    @FXML private Tab loginTab;
    @FXML private Tab registerTab;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtRegUsername;
    @FXML private PasswordField txtRegPassword;
    @FXML private TextField txtRegEmail;
    @FXML private ComboBox<String> cbRegPerfil;

    private final AuthService authService = new AuthService();


    @FXML
    public void initialize() {

        if (cbRegPerfil != null) {
            cbRegPerfil.getItems().addAll("USUARIO", "EMPRESA");
            cbRegPerfil.getSelectionModel().selectFirst();
        } else {
            System.err.println("Error: cbRegPerfil no fue inyectado correctamente");
        }
    }

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            Usuario usuario = authService.login(username, password);
            if (usuario != null) {
                redirectToDashboard(usuario);
            } else {
                AlertUtil.mostrarError("Error de autenticación", "Credenciales incorrectas");
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudo conectar al servidor");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegister() {
        String username = txtRegUsername.getText();
        String password = txtRegPassword.getText();
        String email = txtRegEmail.getText();
        String perfil = cbRegPerfil.getValue();

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);


        try {
            if (authService.register(usuario)) {
                AlertUtil.mostrarInfo("Registro exitoso", "Usuario registrado correctamente");
                authTabPane.getSelectionModel().select(loginTab);
            } else {
                AlertUtil.mostrarError("Error", "No se pudo registrar el usuario");
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "Error al registrar usuario");
            e.printStackTrace();
        }
    }

    private void redirectToDashboard(Usuario usuario) throws IOException {
        String fxmlFile;
        String title;

        switch (usuario.getPerfil().getNombre()) {
            case "ADMIN":
                fxmlFile = "/views/admin/dashboard.fxml";
                title = "Panel de Administración";
                break;
            case "EMPRESA":
                fxmlFile = "/views/empresa/list_empresas.fxml";
                title = "Gestión de Empresas";
                break;
            case "USUARIO":
                fxmlFile = "/views/vacante/list_vacantes.fxml";
                title = "Buscar Vacantes";
                break;
            default:
                throw new IllegalStateException("Perfil no reconocido");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    private void handleLoginLink(MouseEvent event) {
        authTabPane.getSelectionModel().select(loginTab);
    }

    @FXML
    private void handleRegisterLink(MouseEvent event) {
        authTabPane.getSelectionModel().select(registerTab);
    }

    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleMinimize(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}