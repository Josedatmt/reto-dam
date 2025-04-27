package com.backend.service;

import com.backend.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria saveCategoria(Categoria categoria){
        categoria.setNombre(categoria.getNombre().toUpperCase());
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(Long id_categoria){
        categoriaRepository.deleteById(id_categoria);
    }


    @Override
    public List<Categoria> findAllCategorias(){
        return categoriaRepository.findAll();
    }


}
