package com.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String archivo;
    private String comentarios;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_Vacante")
    private Vacante vacante;

    @ManyToOne
    @JoinColumn(name = "username")
    private Usuario usuario;
}

