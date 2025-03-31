package com.backend.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

import java.util.Date;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    private String username;

    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private boolean enabled;
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> solicitudes;

    @ManyToMany
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "id_Perfil")
    )
    private List<Perfil> perfiles;


}
