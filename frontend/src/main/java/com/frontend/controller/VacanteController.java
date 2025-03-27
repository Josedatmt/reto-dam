package com.frontend.controller;

import com.frontend.model.Vacante;
import com.frontend.service.ApiService;
import com.frontend.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VacanteController implements Initializable {

    @FXML private TableView<Vacante> tableVacantes;
    @FXML private TableColumn<Vacante, Integer> colId;
    @FXML private TableColumn<Vacante, String> colNombre;
    @FXML private TableColumn<Vacante, String> colDescripcion;
    @FXML private TableColumn<Vacante, Double> colSalario;
    @FXML private TableColumn<Vacante, String> colEstado;

    private final ApiService apiService = new ApiService();
    private final ObservableList<Vacante> vacantesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadVacantes();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id_vacante"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tableVacantes.setItems(vacantesList);
    }

    private void loadVacantes() {
        try {
            List<Vacante> vacantes = apiService.getVacantes();
            vacantesList.setAll(vacantes);
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudieron cargar las vacantes");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddVacante() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/vacante/form_vacante.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nueva Vacante");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadVacantes();
        } catch (IOException e) {
            AlertUtil.mostrarError("Error", "No se pudo abrir el formulario");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditVacante() {
        Vacante selected = tableVacantes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/vacante/form_vacante.fxml"));
                Parent root = loader.load();

                FormVacanteController controller = loader.getController();
                controller.setVacante(selected);

                Stage stage = new Stage();
                stage.setTitle("Editar Vacante");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                loadVacantes();
            } catch (IOException e) {
                AlertUtil.mostrarError("Error", "No se pudo abrir el formulario");
                e.printStackTrace();
            }
        } else {
            AlertUtil.mostrarError("Error", "Seleccione una vacante para editar");
        }
    }

    @FXML
    private void handleDeleteVacante() {
        Vacante selected = tableVacantes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirm = AlertUtil.mostrarConfirmacion("Confirmar",
                    "¿Está seguro de cancelar la vacante " + selected.getNombre() + "?");

            if (confirm) {
                try {
                    if (apiService.cancelarVacante(selected.getId_vacante())) {
                        loadVacantes();
                        AlertUtil.mostrarInfo("Éxito", "Vacante cancelada correctamente");
                    }
                } catch (Exception e) {
                    AlertUtil.mostrarError("Error", "No se pudo cancelar la vacante");
                    e.printStackTrace();
                }
            }
        } else {
            AlertUtil.mostrarError("Error", "Seleccione una vacante para cancelar");
        }
    }

    @FXML
    private void handleViewDetails() {
        Vacante selected = tableVacantes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/vacante/detalles_vacante.fxml"));
                Parent root = loader.load();

                DetallesVacanteController controller = loader.getController();
                controller.setVacante(selected);

                Stage stage = new Stage();
                stage.setTitle("Detalles de Vacante");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                AlertUtil.mostrarError("Error", "No se pudo mostrar los detalles");
                e.printStackTrace();
            }
        } else {
            AlertUtil.mostrarError("Error", "Seleccione una vacante para ver detalles");
        }
    }
}