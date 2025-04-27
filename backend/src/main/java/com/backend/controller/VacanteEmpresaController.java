package com.backend.controller;

import com.backend.model.EstatusVacante;
import com.backend.model.Vacante;
import com.backend.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa/vacantes")
public class VacanteEmpresaController {

    @Autowired
    private VacanteService vacanteService;

    @PostMapping
    public ResponseEntity<?> crearVacante(@RequestBody Vacante vacante) {
        vacante.setEstatus(EstatusVacante.ABIERTA);
        return new ResponseEntity<>(vacanteService.saveVacante(vacante), HttpStatus.CREATED);
    }

    @PutMapping("/{id_vacante}/cancelar")
    public ResponseEntity<?> cancelarVacante(@PathVariable Long id_vacante) {
        vacanteService.cancelarVacante(id_vacante);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id_vacante}/asignar/{id_solicitud}")
    public ResponseEntity<?> asignarVacante(
            @PathVariable Long id_vacante,
            @PathVariable Long id_solicitud) {
        vacanteService.asignarVacante(id_vacante, id_solicitud);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/empresa/{id_empresa}")
    public ResponseEntity<?> listarVacantesEmpresa(@PathVariable Long id_empresa) {
        return ResponseEntity.ok(vacanteService.findByEmpresaId(id_empresa));
    }
}