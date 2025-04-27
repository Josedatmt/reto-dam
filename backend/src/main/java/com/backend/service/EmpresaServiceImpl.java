package com.backend.service;

import com.backend.model.Empresa;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.repository.EmpresaRepository;
import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }



    @Override
    public Empresa saveEmpresa(Empresa empresa){
        empresa.setId_empresa(empresa.getId_empresa());
        return empresaRepository.save(empresa);
    }


    @Override
    public void deleteEmpresa(Long id_empresa){
        empresaRepository.deleteById(id_empresa);

    }

    @Override
    public List<Empresa> findAllEmpresas(){
       return empresaRepository.findAll();
    }

    @Override
    public Empresa findById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));
    }
}
