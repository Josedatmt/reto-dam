package com.backend.controller;


import com.backend.model.Categoria;
import com.backend.model.Empresa;
import com.backend.model.Usuario;
import com.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // Gestión de Empresas
    @GetMapping("/empresas")
    public List<Empresa> getAllEmpresas() {
        return adminService.getAllEmpresas();
    }

    @PostMapping("/empresas")
    public Empresa createEmpresa(@RequestBody Empresa empresa) {
        return adminService.createEmpresa(empresa);
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Integer id, @RequestBody Empresa empresa) {
        return adminService.updateEmpresa(id, empresa);
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Integer id) {
        adminService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    // Gestión de Categorías
    @GetMapping("/categorias")
    public List<Categoria> getAllCategorias() {
        return adminService.getAllCategorias();
    }

    @PostMapping("/categorias")
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return adminService.createCategoria(categoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        return adminService.updateCategoria(id, categoria);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        adminService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // Gestión de Usuarios
    @PutMapping("/usuarios/{username}/deshabilitar")
    public ResponseEntity<Usuario> disableUsuario(@PathVariable String username) {
        return adminService.disableUsuario(username);
    }
}
