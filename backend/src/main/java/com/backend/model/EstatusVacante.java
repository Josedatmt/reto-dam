package com.backend.model;

public enum EstatusVacante {
    ABIERTA,
    EN_REVISION,
    CERRADA,
    CANCELADA;


    public String getDescripcion() {
        switch (this) {
            case ABIERTA:
                return "La vacante está abierta y disponible.";
            case EN_REVISION:
                return "La vacante está en proceso de revisión.";
            case CERRADA:
                return "La vacante ha sido cerrada.";
            case CANCELADA:
                return "La vacante ha sido cancelada.";
            default:
                return "Estatus desconocido.";
        }
    }
}
