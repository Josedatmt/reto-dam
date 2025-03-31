package com.backend.service;

import com.backend.dto.VacanteDTO;
import com.backend.model.Empresa;
import com.backend.model.EstadoVacante;
import com.backend.model.Vacante;
import com.backend.repository.VacanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacanteService {
    @Autowired
    private VacanteRepository vacanteRepository;

    public List<Vacante> findAll() {
        return vacanteRepository.findAll();
    }

    public Optional<Vacante> findById(Integer id) {
        return vacanteRepository.findById(id);
    }

    public List<Vacante> findByEmpresa(Empresa empresa) {
        return vacanteRepository.findByEmpresa(empresa);
    }

    public Vacante save(Vacante vacante) {
        return vacanteRepository.save(vacante);
    }

    public void deleteById(Integer id) {
        vacanteRepository.deleteById(id);
    }


}