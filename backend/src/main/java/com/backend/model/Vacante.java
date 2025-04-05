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
@Table(name = "vacantes")
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVacante;
    private String nombre;
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Double salario;
    private Boolean destacado;
    private String imagen;
    private String detalles;

    @ManyToOne
    @JoinColumn(name = "id_Categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "vacante")
    private List<Solicitud> solicitudes;
}
