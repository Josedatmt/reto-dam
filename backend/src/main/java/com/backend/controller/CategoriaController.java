package com.backend.controller;


import com.backend.model.Categoria;
import com.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> saveCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(
                categoriaService.saveCategoria(categoria),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("{id_categoria}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id_categoria) {
        categoriaService.deleteCategoria(id_categoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAllCategorias());
    }

}
