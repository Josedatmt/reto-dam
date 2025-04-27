package com.backend.controller;

import com.backend.model.Usuario;
import com.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        usuario.setFecha_registro(LocalDateTime.now());
        usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @DeleteMapping("{id_Usuario}")
    public ResponseEntity <?> deleteUsuario(@PathVariable Long id_Usuario){
        usuarioService.deleteUsuario(id_Usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsuario(){
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }



    @GetMapping("/{username}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long username) {
        Usuario usuario = usuarioService.findById(username);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Long username,
            @RequestBody Usuario usuarioDetails) {

        Usuario updatedUsuario = usuarioService.updateUsuario(username, usuarioDetails);
        return ResponseEntity.ok(updatedUsuario);
    }

}
