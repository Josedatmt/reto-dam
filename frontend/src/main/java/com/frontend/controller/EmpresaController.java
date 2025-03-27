package com.frontend.controller;

import com.frontend.model.Empresa;
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

public class EmpresaController implements Initializable {

    @FXML private TableView<Empresa> tableEmpresas;
    @FXML private TableColumn<Empresa, Integer> colId;
    @FXML private TableColumn<Empresa, String> colNombre;
    @FXML private TableColumn<Empresa, String> colDireccion;
    @FXML private TableColumn<Empresa, String> colPais;

    private final ApiService apiService = new ApiService();
    private final ObservableList<Empresa> empresasList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadEmpresas();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id_empresa"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_social"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion_fiscal"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

        tableEmpresas.setItems(empresasList);
    }

    private void loadEmpresas() {
        try {
            List<Empresa> empresas = apiService.getEmpresas();
            empresasList.setAll(empresas);
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudieron cargar las empresas");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddEmpresa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/empresa/form_empresa.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nueva Empresa");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadEmpresas(); // Recargar la lista después de cerrar el formulario
        } catch (IOException e) {
            AlertUtil.mostrarError("Error", "No se pudo abrir el formulario");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditEmpresa() {
        Empresa selected = tableEmpresas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/empresa/form_empresa.fxml"));
                Parent root = loader.load();

                FormEmpresaController controller = loader.getController();
                controller.setEmpresa(selected);

                Stage stage = new Stage();
                stage.setTitle("Editar Empresa");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                loadEmpresas(); // Recargar la lista después de cerrar el formulario
            } catch (IOException e) {
                AlertUtil.mostrarError("Error", "No se pudo abrir el formulario");
                e.printStackTrace();
            }
        } else {
            AlertUtil.mostrarError("Error", "Seleccione una empresa para editar");
        }
    }

    @FXML
    private void handleDeleteEmpresa() {
        Empresa selected = tableEmpresas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirm = AlertUtil.mostrarConfirmacion("Confirmar",
                    "¿Está seguro de eliminar la empresa " + selected.getNombre_social() + "?");

            if (confirm) {
                try {
                    if (apiService.deleteEmpresa(selected.getId_empresa())) {
                        empresasList.remove(selected);
                        AlertUtil.mostrarInfo("Éxito", "Empresa eliminada correctamente");
                    }
                } catch (Exception e) {
                    AlertUtil.mostrarError("Error", "No se pudo eliminar la empresa");
                    e.printStackTrace();
                }
            }
        } else {
            AlertUtil.mostrarError("Error", "Seleccione una empresa para eliminar");
        }
    }

    @FXML
    private void handleViewDetails() {
        Empresa selected = tableEmpresas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/empresa/detalles_empresa.fxml"));
                Parent root = loader.load();

                DetallesEmpresaController controller = loader.getController();
                controller.setEmpresa(selected);

                Stage stage = new Stage();
                stage.setTitle("Detalles de Empresa");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                AlertUtil.mostrarError("Error", "No se pudo mostrar los detalles");
                e.printStackTrace();
            }
        } else {
            AlertUtil.mostrarError("Error", "Seleccione una empresa para ver detalles");
        }
    }
}