package com.backend.service;

import com.backend.model.Perfil;
import com.backend.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    @Override
    public Usuario saveUsuario(Usuario usuario){
        usuario.setUsername(usuario.getUsername());

       return usuarioRepository.save(usuario);
    }
    @Override
    public void deleteUsuario(Long id_usuario){
        usuarioRepository.deleteById(id_usuario);
    }
    @Override
    public List<Usuario> findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public Usuario updateUsuario(Long username, Usuario usuarioDetails) {
        Usuario usuarioExistente = usuarioRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));


        usuarioExistente.setNombre(usuarioDetails.getNombre());
        usuarioExistente.setApellidos(usuarioDetails.getApellidos());


        if (!usuarioExistente.getEmail().equals(usuarioDetails.getEmail())) {
            if (usuarioRepository.existsByEmail(usuarioDetails.getEmail())) {
                throw new IllegalArgumentException("Email ya en uso");
            }
            usuarioExistente.setEmail(usuarioDetails.getEmail());
        }


        if (usuarioDetails.getPerfiles() != null && !usuarioDetails.getPerfiles().isEmpty()) {

            usuarioExistente.getPerfiles().clear();


            for (Perfil perfil : usuarioDetails.getPerfiles()) {
                Perfil perfilExistente = perfilService.findByNombre(perfil.getNombre());
                if (perfilExistente != null) {
                    usuarioExistente.getPerfiles().add(perfilExistente);
                }
            }
        }

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
