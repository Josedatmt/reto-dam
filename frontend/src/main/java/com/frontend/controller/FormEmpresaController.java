package com.frontend.controller;

import com.frontend.model.Empresa;
import com.frontend.service.ApiService;
import com.frontend.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FormEmpresaController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtPais;

    private Empresa empresa;
    private final ApiService apiService = new ApiService();

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        if (empresa != null) {
            txtNombre.setText(empresa.getNombre_social());
            txtDireccion.setText(empresa.getDireccion_fiscal());
            txtPais.setText(empresa.getPais());
        }
    }

    @FXML
    private void handleGuardar() {
        if (empresa == null) {
            empresa = new Empresa();
        }

        empresa.setNombre_social(txtNombre.getText());
        empresa.setDireccion_fiscal(txtDireccion.getText());
        empresa.setPais(txtPais.getText());

        try {
            boolean success = apiService.saveEmpresa(empresa);
            if (success) {
                AlertUtil.mostrarInfo("Éxito", "Empresa guardada correctamente");
                closeWindow();
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "No se pudo guardar la empresa");
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