package com.frontend.controllers.admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.frontend.models.Categoria;
import com.frontend.services.CategoriaService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class CategoriaAdminController {
    @FXML private TableView<Categoria> tableCategorias;

    @FXML
    public void initialize() {
        loadCategorias();
    }

    private void loadCategorias() {
        try {
            List<Categoria> categorias = CategoriaService.getAll();
            tableCategorias.setItems(FXCollections.observableArrayList(categorias));
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar categorías: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        loadCategorias();
    }
}