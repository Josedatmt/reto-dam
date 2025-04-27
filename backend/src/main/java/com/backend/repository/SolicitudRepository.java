package com.backend.repository;

import com.backend.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository <Solicitud, Long> {
    List<Solicitud> findByUsuarioUsernameOrderByFechaDesc(Long username);
}
