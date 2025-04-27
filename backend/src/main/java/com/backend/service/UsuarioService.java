package com.backend.service;

import com.backend.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);

    void deleteUsuario(Long id_usuario);

    List<Usuario> findAllUsuarios();

    Usuario findById(Long id);

    Usuario updateUsuario(Long username, Usuario usuarioDetails);

    boolean existsByEmail(String email);
}
