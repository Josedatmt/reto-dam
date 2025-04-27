package com.backend.controller;

import com.backend.model.*;
import com.backend.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/empresas")
public class EmpresaAdminController {

    @Autowired
    private EmpresaService empresaService;


    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) {
        return new ResponseEntity<>(empresaService.saveEmpresa(empresa), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(
            @PathVariable Long id,
            @RequestBody Empresa empresa) {
        empresa.setId_empresa(id);
        return ResponseEntity.ok(empresaService.saveEmpresa(empresa));
    }


    @DeleteMapping("/{id_empresa}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id_empresa) {
        empresaService.deleteEmpresa(id_empresa);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.findAllEmpresas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Empresa> verDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.findById(id));
    }
}