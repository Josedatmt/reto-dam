package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Table(name="solicitudes")
@Entity
@Data
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "archivo", length = 500, nullable = false)
    private String archivo;

    @Column(name = "comentarios", length = 2000,nullable = true)
    private Date comentarios;

    @Column(name = "estado", nullable = false)
    private EstadoSolicitud estado;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacante;


}
