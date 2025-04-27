package com.backend.service;

import com.backend.model.EstadoSolicitud;
import com.backend.model.Solicitud;
import com.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.repository.SolicitudRepository;
import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private final SolicitudRepository solicitudRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository, UsuarioRepository usuarioRepository){
        this.solicitudRepository = solicitudRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Solicitud saveSolicitud(Solicitud solicitud){
        solicitud.setId_solicitud(solicitud.getId_solicitud());
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void deleteSolicitud(Long id_solicitud){

        solicitudRepository.deleteById(id_solicitud);
    }

    @Override
    public List<Solicitud> findAllSolicitud(){

        return solicitudRepository.findAll();
    }

    @Override
    public Solicitud findById(Long id_Solicitud) {
        return solicitudRepository.findById(id_Solicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + id_Solicitud));
    }

    @Override
    @Transactional
    public void actualizarEstadoSolicitud(Long idSolicitud, int estado) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("No se encontró la solicitud con ID: " + idSolicitud));

        if (estado < 0 || estado > 1) {
            throw new IllegalArgumentException("Estado inválido. Debe ser 0 (Pendiente) o 1 (APROBADA)");
        }

        solicitud.setEstado(EstadoSolicitud.APROBADA);
        solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> findByUsuario(Long username) {

        if (username == null) {
            throw new IllegalArgumentException("El ID de usuario no puede ser nulo");
        }


        if (!usuarioRepository.existsById(username)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + username);
        }


        List<Solicitud> solicitudes = solicitudRepository.findByUsuarioUsernameOrderByFechaDesc(username);



        return solicitudes;
    }

    @Override
    @Transactional
    public void cancelarSolicitud(Long id, Long username) {
        Solicitud solicitud = solicitudRepository.findById(id).orElseThrow();
        solicitud.setEstado(EstadoSolicitud.CANCELADA);
        solicitudRepository.save(solicitud);
    }
}
