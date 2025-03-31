package com.backend.controller;


import com.backend.model.Solicitud;
import com.backend.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public List<Solicitud> getAllSolicitudes() {
        return solicitudService.findAll();
    }

    @PostMapping
    public Solicitud createSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.save(solicitud);
    }

    @PutMapping("/{id}/adjudicar")
    public ResponseEntity<Solicitud> adjudicarSolicitud(@PathVariable Integer id) {
        return solicitudService.findById(id).map(solicitud -> {
            solicitud.setEstado(true);
            return ResponseEntity.ok(solicitudService.save(solicitud));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}