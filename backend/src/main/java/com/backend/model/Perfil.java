package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Table(name="perfiles")
@Entity
@Data
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_perfil;

    @Column(name = "nombre",length =100, nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "perfiles")
    private List<Usuario> usuarios;

}
