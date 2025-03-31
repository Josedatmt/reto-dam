package com.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;
    private String razonSocial;

    @Column(name = "direccion_fiscal")
    private String direccionFiscal;
    private String pais;

    @OneToMany(mappedBy = "empresa")
    private List<Vacante> vacantes;
}
