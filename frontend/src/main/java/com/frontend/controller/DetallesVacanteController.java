package com.frontend.controller;

import com.frontend.model.Vacante;
import com.frontend.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetallesVacanteController {

    @FXML private Label lblNombre;
    @FXML private Label lblDescripcion;
    @FXML private Label lblSalario;
    @FXML private Label lblEstado;
    @FXML private Label lblFecha;
    @FXML private Label lblDetalles;

    private Vacante vacante;

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
        cargarDatos();
    }

    private void cargarDatos() {
        if (vacante != null) {
            lblNombre.setText(vacante.getNombre());
            lblDescripcion.setText(vacante.getDescripcion());
            lblSalario.setText(String.format("%,.2f", vacante.getSalario()));
            lblEstado.setText(vacante.getEstado());
            lblFecha.setText(DateUtil.formatDate(vacante.getFecha()));
            lblDetalles.setText(vacante.getDetalles() != null ? vacante.getDetalles() : "N/A");
        }
    }

    @FXML
    private void handleCerrar() {
        lblNombre.getScene().getWindow().hide();
    }
}