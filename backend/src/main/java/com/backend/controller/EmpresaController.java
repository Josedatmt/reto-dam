package com.backend.controller;


import com.backend.model.Empresa;

import com.backend.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Integer id) {
        return empresaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa) {
        return empresaService.save(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Integer id, @RequestBody Empresa empresaDetails) {
        return empresaService.findById(id).map(empresa -> {
            empresa.setRazonSocial(empresaDetails.getRazonSocial());
            empresa.setDireccionFiscal(empresaDetails.getDireccionFiscal());
            empresa.setPais(empresaDetails.getPais());
            return ResponseEntity.ok(empresaService.save(empresa));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Integer id) {
        if (empresaService.findById(id).isPresent()) {
            empresaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}