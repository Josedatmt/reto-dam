package com.frontend.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.frontend.models.Vacante;
import com.frontend.services.VacanteService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class VacanteAdminController {
    @FXML private TableView<Vacante> tableVacantes;

    @FXML
    public void initialize() {
        loadVacantes();
    }

    private void loadVacantes() {
        try {
            List<Vacante> vacantes = VacanteService.getAll();
            tableVacantes.getItems().setAll(vacantes);
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar vacantes: " + e.getMessage());
        }
    }
}