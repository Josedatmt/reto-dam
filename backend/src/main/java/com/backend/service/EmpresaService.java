package com.backend.service;

import com.backend.model.Empresa;

import java.util.List;

public interface EmpresaService {
    Empresa saveEmpresa(Empresa empresa);

    void deleteEmpresa(Long id_empresa);

    List<Empresa> findAllEmpresas();

    Empresa findById(Long id);
}
