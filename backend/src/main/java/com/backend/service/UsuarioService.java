package com.backend.service;

import com.backend.model.Usuario;
import com.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findById(username);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteByUsername(String username) {
        usuarioRepository.deleteById(username);

    }
}
