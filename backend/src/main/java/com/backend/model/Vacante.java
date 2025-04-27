package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Table(name="vacantes")
@Entity
@Data

public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vacante;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 500, nullable = false)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "estatus", nullable = false)
    private EstatusVacante estatus;

    @Column(name = "destacado", nullable = false)
    private boolean destacado;

    @Column(name = "imagen" , length = 1200, nullable =true)
    private String imagen;

    @Column(name = "detalles", length = 150, nullable = true)
    private String detalles;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;


}
