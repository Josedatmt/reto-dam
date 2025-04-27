package com.backend.repository;

import com.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.perfiles WHERE u.email = :email")
    Optional<Usuario> findByEmailWithPerfiles(@Param("email") String email);


    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}