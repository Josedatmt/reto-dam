package com.backend.controller;

import com.backend.model.EstatusVacante;
import com.backend.model.Vacante;
import com.backend.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vacante")
public class VacanteController {
    @Autowired
    private VacanteService vacanteService;


    @PostMapping
    public ResponseEntity<?> saveVacante(@RequestBody Vacante vacante) {
        vacante.setEstatus(EstatusVacante.ABIERTA);
        vacante.setFecha(new Date());
        return new ResponseEntity<>(vacanteService.saveVacante(vacante), HttpStatus.CREATED);
    }


    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarVacante(@PathVariable("id") Long idVacante) {
        Vacante vacante = vacanteService.findById(idVacante);
        vacante.setEstatus(EstatusVacante.CANCELADA);
        vacanteService.saveVacante(vacante);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> findAllVacantes() {
        return ResponseEntity.ok(vacanteService.findAllVacantes());
    }


    @GetMapping("/activas")
    public ResponseEntity<?> findVacantesActivas() {
        return ResponseEntity.ok(vacanteService.findByEstatus(EstatusVacante.ABIERTA));
    }


    @DeleteMapping("{id_vacante}")
    public ResponseEntity<?> deleteVacante(@PathVariable Long id_vacante) {
        vacanteService.deleteVacante(id_vacante);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Vacante>> buscarVacantes(
            @RequestParam(required = false) Long idCategoria,
            @RequestParam(required = false) Long idEmpresa,
            @RequestParam(required = false) String contrato) {

        List<Vacante> vacantes = vacanteService.buscarConFiltros(
                EstatusVacante.ABIERTA,
                idCategoria,
                idEmpresa,
                contrato
        );
        return ResponseEntity.ok(vacantes);
    }
}