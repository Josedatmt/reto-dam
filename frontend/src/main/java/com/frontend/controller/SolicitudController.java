package com.frontend.controller;

import com.frontend.model.Solicitud;
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

public class SolicitudController implements Initializable {

    @FXML private TableView<Solicitud> tableSolicitudes;
    @FXML private TableColumn<Solicitud, Integer> colId;
    @FXML private TableColumn<Solicitud, String> colVacante;
    @FXML private TableColumn<Solicitud, String> colUsuario;
    @FXML private TableColumn<Solicitud, String> colEstado;
    @FXML private TableColumn<Solicitud, String> colFecha;

    private final ApiService apiService = new ApiService();
    private final ObservableList<Solicitud> solicitudesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadSolicitudes();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id_solicitud"));
        colVacante.setCellValueFactory(new PropertyValueFactory<>("vacanteNombre"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuarioNombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaSolicitud"));

        tableSolicitudes.setItems(solicitudesList);
    }

    private void loadSolicitudes() {
        try {
            List<Solicitud> solicitudes = apiService.getSolicitudes();
            solicitudesList.setAll(solicitudes);
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudieron cargar las solicitudes");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAsignarSolicitud() {
        Solicitud selected = tableSolicitudes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                if (apiService.asignarSolicitud(selected.getId_solicitud())) {
                    loadSolicitudes();
                }
            } catch (Exception e) {
                AlertUtil.mostrarError("Error", "No se pudo asignar la solicitud");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleCancelarSolicitud() {
        Solicitud selected = tableSolicitudes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                if (apiService.cancelarSolicitud(selected.getId_solicitud())) {
                    loadSolicitudes();
                }
            } catch (Exception e) {
                AlertUtil.mostrarError("Error", "No se pudo cancelar la solicitud");
                e.printStackTrace();
            }
        }
    }
}