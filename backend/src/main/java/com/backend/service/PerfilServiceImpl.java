package com.backend.service;

import com.backend.model.Perfil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.repository.PerfilRepository;
import java.util.List;
import java.util.Optional;


@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private final PerfilRepository perfilrepository;

    public PerfilServiceImpl(PerfilRepository perfilRepository, PerfilRepository perfilrespository) {
        this.perfilrepository = perfilrespository;

    }

    @Override
    public Perfil savePerfil(Perfil perfil) {

        perfil.setId_perfil(perfil.getId_perfil());
        return perfilrepository.save(perfil);
    }

    @Override
    public void deletePerfil(Long id_perfil) {
        perfilrepository.deleteById(id_perfil);
    }

    @Override
    public List<Perfil> findAllPerfil() {
        return perfilrepository.findAll();
    }

    @Override
    public Perfil findByNombre(String nombre) {
        Optional<Perfil> perfilOpt = perfilrepository.findByNombre(nombre);
        if (perfilOpt.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Perfil con nombre '%s' no encontrado", nombre)
            );
        }
        return perfilOpt.get();
    }

}
