package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="empresas")
@Entity
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_empresa;

    private Long id;
    @Column(name="razon_social", length= 500, nullable = false)
    private String razon_social;

    @Column(name="direccion_fiscal", length= 500, nullable = false)
    private String direccion_fiscal;

    @Column(name="pais", length= 150, nullable = false)
    private String pais;
}
