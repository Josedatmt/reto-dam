package com.backend.service;

import com.backend.model.EstatusVacante;
import com.backend.model.Vacante;
import jakarta.transaction.Transactional;

import java.util.List;

public interface VacanteService {

    Vacante saveVacante(Vacante vacante);

    void deleteVacante(Long id_vacante);

    List<Vacante> findAllVacantes();

    Vacante findById(Long id);

    List<Vacante> findByEstatus(EstatusVacante estatus);

    List<Vacante> findByEmpresaId(Long idEmpresa);

    @Transactional
    void cancelarVacante(Long idVacante);

    @Transactional
    void asignarVacante(Long idVacante, Long idSolicitud);

    List<Vacante> buscarConFiltros(EstatusVacante estatusVacante, Long idCategoria, Long idEmpresa, String contrato);
}
