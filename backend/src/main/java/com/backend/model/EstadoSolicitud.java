package com.backend.model;

public enum EstadoSolicitud {
    PENDIENTE(0),
    APROBADA(1),
    RECHAZADA(2),
    CANCELADA(3);

    private final int valor;

    EstadoSolicitud(int valor) {
        this.valor = valor;
    }


    public String getDescripcion() {
        switch (this) {
            case PENDIENTE:
                return "La solicitud está pendiente de revisión.";
            case APROBADA:
                return "La solicitud ha sido aprobada.";
            case RECHAZADA:
                return "La solicitud ha sido rechazada.";
            case CANCELADA:
                return "La solicitud ha sido cancelada.";
            default:
                return "Estado desconocido.";
        }
    }
}
