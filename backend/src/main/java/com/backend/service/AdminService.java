package com.backend.service;



import com.backend.model.Categoria;
import com.backend.model.Empresa;
import com.backend.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    // Métodos para Empresas
    public List<Empresa> getAllEmpresas() {
        return empresaService.findAll();
    }

    public Empresa createEmpresa(Empresa empresa) {
        return empresaService.save(empresa);
    }

    public ResponseEntity<Empresa> updateEmpresa(Integer id, Empresa empresa) {
        Optional<Empresa> empresaOptional = empresaService.findById(id);
        if (empresaOptional.isPresent()) {
            return ResponseEntity.ok(empresaService.save(empresa));
        }
        return ResponseEntity.notFound().build();
    }

    public void deleteEmpresa(Integer id) {
        empresaService.deleteById(id);
    }

    // Métodos para Categorías
    public List<Categoria> getAllCategorias() {
        return categoriaService.findAll();
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaService.save(categoria);
    }

    public ResponseEntity<Categoria> updateCategoria(Integer id, Categoria categoria) {
        Optional<Categoria> categoriaOptional = categoriaService.findById(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaService.save(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    public void deleteCategoria(Integer id) {
        categoriaService.deleteById(id);
    }

    // Métodos para Usuarios
    public ResponseEntity<Usuario> disableUsuario(String username) {
        Optional<Usuario> usuarioOptional = usuarioService.findByUsername(username);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setEnabled(false);
            return ResponseEntity.ok(usuarioService.save(usuario));
        }
        return ResponseEntity.notFound().build();
    }
}

