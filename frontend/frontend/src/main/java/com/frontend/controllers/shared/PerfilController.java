package com.frontend.controllers.shared;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import com.frontend.models.Perfil;
import com.frontend.services.PerfilService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class PerfilController {
    @FXML private ComboBox<Perfil> comboPerfiles;

    @FXML
    public void initialize() {
        loadPerfiles();
    }

    private void loadPerfiles() {
        try {
            List<Perfil> perfiles = PerfilService.getAll();
            comboPerfiles.getItems().setAll(perfiles);
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar perfiles: " + e.getMessage());
        }
    }
}