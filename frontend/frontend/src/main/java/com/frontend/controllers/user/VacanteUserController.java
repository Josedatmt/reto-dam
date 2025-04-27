package com.frontend.controllers.user;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.frontend.models.Vacante;
import com.frontend.services.VacanteService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class VacanteUserController {
    @FXML private TableView<Vacante> tableVacantes;

    @FXML
    public void initialize() {
        loadVacantes();
    }

    private void loadVacantes() {
        try {
            List<Vacante> vacantes = VacanteService.getActive();
            tableVacantes.getItems().setAll(vacantes);
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar vacantes: " + e.getMessage());
        }
    }
}