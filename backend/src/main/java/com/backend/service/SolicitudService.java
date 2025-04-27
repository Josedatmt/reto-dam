package com.backend.service;

import com.backend.model.Solicitud;

import java.util.List;

public interface SolicitudService {
    Solicitud saveSolicitud(Solicitud solicitud);

    void deleteSolicitud(Long id_solicitud);

    List<Solicitud> findAllSolicitud();

    Solicitud findById(Long idSolicitud);

    void actualizarEstadoSolicitud(Long idSolicitud, int i);

    List<Solicitud> findByUsuario(Long username);

    void cancelarSolicitud(Long id, Long username);
}
