package com.backend.controller;

import com.backend.model.Empresa;
import com.backend.model.Vacante;
import com.backend.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacantes")
public class VacanteController {
    @Autowired
    private VacanteService vacanteService;

    @GetMapping
    public List<Vacante> getAllVacantes() {
        return vacanteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacante> getVacanteById(@PathVariable Integer id) {
        return vacanteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{idEmpresa}")
    public List<Vacante> getVacantesByEmpresa(@PathVariable Empresa empresa) {
        return vacanteService.findByEmpresa(empresa);
    }

    @PostMapping
    public Vacante createVacante(@RequestBody Vacante vacante) {
        return vacanteService.save(vacante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacante(@PathVariable Integer id) {
        if (vacanteService.findById(id).isPresent()) {
            vacanteService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

