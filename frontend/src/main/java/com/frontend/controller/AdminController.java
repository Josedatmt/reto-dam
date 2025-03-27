package com.frontend.controller;

import com.frontend.model.*;
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

public class AdminController implements Initializable {

    @FXML private TableView<Usuario> tableUsuarios;
    @FXML private TableColumn<Usuario, String> colUsername;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colEmail;
    @FXML private TableColumn<Usuario, String> colPerfil;
    @FXML private TableColumn<Usuario, Boolean> colEnabled;

    private final ApiService apiService = new ApiService();
    private final ObservableList<Usuario> usuariosList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadUsuarios();
    }

    private void setupTable() {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfilNombre"));
        colEnabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));

        tableUsuarios.setItems(usuariosList);
    }

    private void loadUsuarios() {
        try {
            List<Usuario> usuarios = apiService.getUsuarios();
            usuariosList.setAll(usuarios);
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudieron cargar los usuarios");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToggleUsuario() {
        Usuario selected = tableUsuarios.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean nuevoEstado = !selected.isEnabled();
            try {
                if (apiService.toggleUsuario(selected.getUsername(), nuevoEstado ? 1 : 0)) {
                    selected.setEnabled(nuevoEstado);
                    tableUsuarios.refresh();
                }
            } catch (Exception e) {
                AlertUtil.mostrarError("Error", "No se pudo cambiar el estado");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleShowEmpresas() {
        //  mostrar empresas
    }

    @FXML
    private void handleShowCategorias() {
        //  mostrar categorías
    }

    @FXML
    private void handleLogout() {
        //cerrar sesión
    }
}