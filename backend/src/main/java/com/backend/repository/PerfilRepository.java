package com.backend.repository;

import com.backend.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository <Perfil, Long> {
    Optional<Perfil> findByNombre(String nombre);
}
