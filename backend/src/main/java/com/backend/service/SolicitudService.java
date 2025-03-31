package com.backend.service;

import com.backend.model.Solicitud;
import com.backend.repository.SolicitudRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    public Solicitud save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public Optional<Solicitud> findById(Integer id) {
        return solicitudRepository.findById(id);
    }
}