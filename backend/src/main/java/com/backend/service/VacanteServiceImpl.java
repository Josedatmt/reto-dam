package com.backend.service;

import com.backend.model.EstatusVacante;
import com.backend.model.Vacante;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.backend.repository.VacanteRepository;

import java.util.List;

@Service
public class VacanteServiceImpl implements VacanteService {

    @Autowired
    private VacanteRepository vacanteRepository;
    @Autowired
    private SolicitudService solicitudService;

    @Override
    public Vacante saveVacante(Vacante vacante) {
        return vacanteRepository.save(vacante);
    }

    @Override
    public void deleteVacante(Long id_vacante) {
        vacanteRepository.deleteById(id_vacante);
    }

    @Override
    public List<Vacante> findAllVacantes() {
        return vacanteRepository.findAll();
    }

    @Override
    public Vacante findById(Long id) {
        return vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada"));
    }

    @Override
    public List<Vacante> findByEstatus(EstatusVacante estatus) {
        return vacanteRepository.findByEstatus(estatus);
    }



    @Override
    public List<Vacante> findByEmpresaId(Long idEmpresa) {
        return vacanteRepository.findByEmpresaId(idEmpresa);
    }

    @Transactional
    @Override
    public void cancelarVacante(Long idVacante) {
        Vacante vacante = this.findById(idVacante);
        vacante.setEstatus(EstatusVacante.CANCELADA);
        vacanteRepository.save(vacante);
    }

    @Transactional
    @Override
    public void asignarVacante(Long idVacante, Long idSolicitud) {

        Vacante vacante = this.findById(idVacante);
        vacante.setEstatus(EstatusVacante.ABIERTA);
        vacanteRepository.save(vacante);


        solicitudService.actualizarEstadoSolicitud(idSolicitud, 1);
    }

    @Override
    public List<Vacante> buscarConFiltros(EstatusVacante estatus, Long idCategoria, Long idEmpresa, String contrato) {

        Specification<Vacante> spec = (root, query, cb) ->
                cb.equal(root.get("estatus"), estatus);


        if (idCategoria != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("categoria").get("id"), idCategoria));
        }

        if (idEmpresa != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("empresa").get("id"), idEmpresa));
        }

        if (contrato != null && !contrato.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("detalles")), "%" + contrato.toLowerCase() + "%"));
        }


        return vacanteRepository.findAll((Sort) spec);
    }
}



