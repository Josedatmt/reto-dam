package com.backend.controller;





import com.backend.model.Usuario;
import com.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{username}/deshabilitar")
    public ResponseEntity<Usuario> disableUsuario(@PathVariable String username) {
        return usuarioService.findByUsername(username).map(usuario -> {
            usuario.setEnabled(false);
            return ResponseEntity.ok(usuarioService.save(usuario));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}