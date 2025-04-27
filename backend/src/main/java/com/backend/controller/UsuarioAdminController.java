package com.backend.controller;

import com.backend.model.Perfil;
import com.backend.model.Usuario;
import com.backend.service.PerfilService;
import com.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/usuarios")
public class UsuarioAdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;


    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        usuario.setEnabled(0);
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/activar")
    public ResponseEntity<Void> activarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        usuario.setEnabled(1);
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/hacer-admin")
    public ResponseEntity<Void> hacerAdministrador(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        Perfil perfilAdmin = perfilService.findByNombre("ADMIN");

        if (perfilAdmin != null) {
            if (usuario.getPerfiles() == null) {
                usuario.setPerfiles(new java.util.ArrayList<>()); // Se asegura que no sea null
            }

            if (usuario.getPerfiles().stream().noneMatch(p -> p.getNombre().equals("ADMIN"))) {
                usuario.getPerfiles().add(perfilAdmin);
                usuarioService.saveUsuario(usuario);
            }
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }
}
