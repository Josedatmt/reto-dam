package com.frontend.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.frontend.models.Empresa;
import com.frontend.services.EmpresaService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class EmpresaAdminController {
    @FXML private TableView<Empresa> tableEmpresas;

    @FXML
    public void initialize() {
        loadEmpresas();
    }

    private void loadEmpresas() {
        try {
            List<Empresa> empresas = EmpresaService.getAll();
            tableEmpresas.getItems().setAll(empresas);
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar empresas: " + e.getMessage());
        }
    }
}