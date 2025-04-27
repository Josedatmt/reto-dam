package com.backend.service;

import com.backend.model.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria saveCategoria(Categoria categoria);

    void deleteCategoria(Long id_categoria);

    List<Categoria> findAllCategorias();
}
