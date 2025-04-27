package com.frontend.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.frontend.models.Usuario;
import com.frontend.services.UsuarioService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class UsuarioAdminController {
    @FXML private TableView<Usuario> tableUsuarios;

    @FXML
    public void initialize() {
        loadUsuarios();
    }

    private void loadUsuarios() {
        try {
            List<Usuario> usuarios = UsuarioService.getAll();
            tableUsuarios.getItems().setAll(usuarios);
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar usuarios: " + e.getMessage());
        }
    }
}