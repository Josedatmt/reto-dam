package com.frontend.controllers.user;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.frontend.config.AuthContext;
import com.frontend.models.Solicitud;
import com.frontend.services.SolicitudService;
import com.frontend.utils.AlertUtils;
import java.util.List;

public class SolicitudController {
    @FXML private TableView<Solicitud> tableSolicitudes;

    @FXML
    public void initialize() {
        loadSolicitudes();
    }

    private void loadSolicitudes() {
        try {
            List<Solicitud> solicitudes = SolicitudService.getByUser(AuthContext.getUserId());
            tableSolicitudes.getItems().setAll(solicitudes);
        } catch (Exception e) {
            AlertUtils.showError("Error al cargar solicitudes: " + e.getMessage());
        }
    }
}