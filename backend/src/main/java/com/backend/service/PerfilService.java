package com.backend.service;

import com.backend.model.Perfil;

import java.util.List;

public interface PerfilService {
    Perfil savePerfil(Perfil perfil);

    void deletePerfil(Long id_perfil);

    List<Perfil> findAllPerfil();

    Perfil findByNombre(String admin);
}
