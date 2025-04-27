package com.backend.controller;

import com.backend.dto.LoginDTO;
import com.backend.model.Perfil;
import com.backend.model.Usuario;
import com.backend.repository.PerfilRepository;
import com.backend.repository.UsuarioRepository;
import com.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {

            if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
                return ResponseEntity.badRequest().body("Email y contraseña son requeridos");
            }


            Usuario user = usuarioRepository.findByEmailWithPerfiles(loginDTO.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


            if (user.getPerfiles() == null) {
                user = usuarioRepository.findById(user.getUsername())
                        .orElseThrow(() -> new RuntimeException("Error al recargar usuario"));
                user.setPerfiles(new ArrayList<>());
            }


            if (!new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }


            if (user.getPerfiles().isEmpty()) {
                asignarPerfilDefault(user);
            }


            return ResponseEntity.ok(crearResponseLogin(user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.existsByEmail(usuario.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El email ya está registrado");
            }


            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
            usuario.setEnabled(1);
            usuario.setPerfiles(new ArrayList<>());

            Usuario nuevoUsuario = usuarioService.saveUsuario(usuario);
            asignarPerfilDefault(nuevoUsuario);

            return ResponseEntity.ok(nuevoUsuario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }


    private void asignarPerfilDefault(Usuario usuario) {
        try {
            Perfil perfil = perfilRepository.findByNombre("USUARIO")
                    .orElseGet(() -> {
                        Perfil p = new Perfil();
                        p.setNombre("USUARIO");
                        return perfilRepository.save(p);
                    });


            usuario.getPerfiles().add(perfil);
            usuarioRepository.saveAndFlush(usuario);

        } catch (Exception e) {
            throw new RuntimeException("Error al asignar perfil: " + e.getMessage());
        }
    }

    private Map<String, Object> crearResponseLogin(Usuario user) {
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("rol", user.getPerfiles().get(0).getNombre());
        return response;
    }
}