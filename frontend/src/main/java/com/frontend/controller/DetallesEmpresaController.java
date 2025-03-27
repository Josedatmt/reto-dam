package com.frontend.controller;

import com.frontend.model.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetallesEmpresaController {

    @FXML private Label lblNombre;
    @FXML private Label lblDireccion;
    @FXML private Label lblPais;
    @FXML private Label lblTelefono;
    @FXML private Label lblEmail;

    private Empresa empresa;

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        cargarDatos();
    }

    private void cargarDatos() {
        if (empresa != null) {
            lblNombre.setText(empresa.getNombre_social());
            lblDireccion.setText(empresa.getDireccion_fiscal());
            lblPais.setText(empresa.getPais());

        }
    }

    @FXML
    private void handleCerrar() {
        lblNombre.getScene().getWindow().hide();
    }
}