package com.backend.controller;

import com.backend.model.Empresa;
import com.backend.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;


    @PostMapping
    public ResponseEntity<?> saveEmpresa(@RequestBody Empresa empresa) {
        return new ResponseEntity<>(
                empresaService.saveEmpresa(empresa),
                HttpStatus.CREATED);
    }


    @DeleteMapping("/{id_empresa}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable Long id_empresa) {
        empresaService.deleteEmpresa(id_empresa);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllEmpresas() {
        return ResponseEntity.ok(empresaService.findAllEmpresas());
    }
}