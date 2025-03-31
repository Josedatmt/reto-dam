package com.backend.repository;

import com.backend.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findByVacanteId(Integer idVacante); // Listado de solicitudes por vacante
    List<Solicitud> findByUsuarioUsername(String username); // Listado de solicitudes por usuario
}
