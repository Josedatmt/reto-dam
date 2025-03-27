package com.frontend.controller;

import com.frontend.model.Categoria;
import com.frontend.service.ApiService;
import com.frontend.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoriaController implements Initializable {

    @FXML private TableView<Categoria> tableCategorias;
    @FXML private TableColumn<Categoria, Integer> colId;
    @FXML private TableColumn<Categoria, String> colNombre;
    @FXML private TableColumn<Categoria, String> colDescripcion;

    private final ApiService apiService = new ApiService();
    private final ObservableList<Categoria> categoriasList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadCategorias();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tableCategorias.setItems(categoriasList);
    }

    private void loadCategorias() {
        try {
            List<Categoria> categorias = apiService.getCategorias();
            categoriasList.setAll(categorias);
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudieron cargar las categorías");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCategoria() {
        //  añadir categoría
    }

    @FXML
    private void handleEditCategoria() {
        Categoria selected = tableCategorias.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // editar categoría
        }
    }

    @FXML
    private void handleDeleteCategoria() {
        Categoria selected = tableCategorias.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                if (apiService.deleteCategoria(selected.getId_categoria())) {
                    categoriasList.remove(selected);
                }
            } catch (Exception e) {
                AlertUtil.mostrarError("Error", "No se pudo eliminar la categoría");
                e.printStackTrace();
            }
        }
    }
}