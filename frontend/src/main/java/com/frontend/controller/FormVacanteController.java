package com.frontend.controller;

import com.frontend.model.Vacante;
import com.frontend.service.ApiService;
import com.frontend.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Date;

public class FormVacanteController {

    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtSalario;
    @FXML private ComboBox<String> cbEstado;
    @FXML private DatePicker dpFecha;

    private Vacante vacante;
    private final ApiService apiService = new ApiService();

    public void initialize() {
        cbEstado.getItems().addAll("Activa", "Inactiva", "Cancelada");
        cbEstado.getSelectionModel().selectFirst();
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
        if (vacante != null) {
            txtNombre.setText(vacante.getNombre());
            txtDescripcion.setText(vacante.getDescripcion());
            txtSalario.setText(String.valueOf(vacante.getSalario()));
            cbEstado.setValue(vacante.getEstado());

            if (vacante.getFecha() != null) {
                dpFecha.setValue(LocalDate.of(
                        vacante.getFecha().getYear() + 1900,
                        vacante.getFecha().getMonth() + 1,
                        vacante.getFecha().getDate()
                ));
            }
        }
    }

    @FXML
    private void handleGuardar() {
        if (vacante == null) {
            vacante = new Vacante();
        }

        vacante.setNombre(txtNombre.getText());
        vacante.setDescripcion(txtDescripcion.getText());
        vacante.setSalario(Double.parseDouble(txtSalario.getText()));
        vacante.setEstado(cbEstado.getValue());


        if (dpFecha.getValue() != null) {
            vacante.setFecha(new Date(
                    dpFecha.getValue().getYear() - 1900,
                    dpFecha.getValue().getMonthValue() - 1,
                    dpFecha.getValue().getDayOfMonth()
            ));
        }

        try {
            boolean success = apiService.saveVacante(vacante);
            if (success) {
                AlertUtil.mostrarInfo("Éxito", "Vacante guardada correctamente");
                closeWindow();
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudo guardar la vacante");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancelar() {
        closeWindow();
    }

    private void closeWindow() {
        txtNombre.getScene().getWindow().hide();
    }
}