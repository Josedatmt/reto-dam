package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;


@Table(name="categorias")
@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column(name="nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 2000, nullable = true)
    private String descripcion;

}
