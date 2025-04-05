package com.backend.repository;


import com.backend.model.Empresa;
import com.backend.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
    List<Vacante> findByEmpresa(Empresa empresa);
}


