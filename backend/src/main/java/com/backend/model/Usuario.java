package com.backend.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name="usuarios")
@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long username;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "apellidos", length = 150, nullable = false)
    private String apellidos;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "password", length = 150, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private int enabled;

    @Column(name = "fecha_registro", length = 1200, nullable = true)
    private LocalDateTime fecha_registro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarioperfil",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private List<Perfil> perfiles = new ArrayList<>();


    public List<Perfil> getPerfiles() {
        if (this.perfiles == null) {
            this.perfiles = new ArrayList<>();
        }
        return this.perfiles;
    }}