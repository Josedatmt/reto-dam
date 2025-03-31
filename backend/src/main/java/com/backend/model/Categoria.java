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
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Vacante> vacantes;
}
