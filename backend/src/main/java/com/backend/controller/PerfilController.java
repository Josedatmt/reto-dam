package com.backend.controller;

import com.backend.model.Perfil;
import com.backend.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity<?> savePerfil(@RequestBody Perfil perfil){
        return new ResponseEntity<>(
                perfilService.savePerfil(perfil),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id_perfil}")

    public ResponseEntity <?> deletePerfil(@PathVariable Long id_perfil){
        perfilService.deletePerfil(id_perfil);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping

    public ResponseEntity<?> findAllPerfil(){
        return ResponseEntity.ok(perfilService.findAllPerfil());
    }





}
