package com.backend.controller;

import com.backend.model.Solicitud;
import com.backend.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;


    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(
            @RequestPart("solicitud") Solicitud solicitud,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo) {

        if (archivo != null && !archivo.isEmpty()) {
            String nombreArchivo = almacenarArchivo(archivo);
            solicitud.setArchivo(nombreArchivo);
        }

        Solicitud nuevaSolicitud = solicitudService.saveSolicitud(solicitud);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @DeleteMapping("{id_Solicitud}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Long id_Solicitud) {
        solicitudService.deleteSolicitud(id_Solicitud);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllSolicitud() {
        return ResponseEntity.ok(solicitudService.findAllSolicitud());
    }

    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<Solicitud>> getSolicitudesByUsuario(
            @PathVariable Long username) {

        List<Solicitud> solicitudes = solicitudService.findByUsuario(username);
        return ResponseEntity.ok(solicitudes);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarSolicitud(
            @PathVariable Long id,
            @RequestParam Long username) {

        solicitudService.cancelarSolicitud(id, username);
        return ResponseEntity.ok().build();
    }

    private String almacenarArchivo(MultipartFile archivo) {
        // Implementación real aquí: guardar en disco, base de datos, nube, etc.
        return "ruta/del/archivo/" + archivo.getOriginalFilename();
    }
}
