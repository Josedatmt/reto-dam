package com.backend.repository;

import com.backend.model.EstatusVacante;
import com.backend.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VacanteRepository extends JpaRepository<Vacante, Long>, JpaSpecificationExecutor<Vacante> {

    List<Vacante> findByEstatus(EstatusVacante estatus);

    List<Vacante> findByEmpresaId(Long id);
}
