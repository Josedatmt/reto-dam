package com.frontend.models;


public class Perfil {
    private Long id_perfil;
    private String nombre;
    public Long getId_perfil() { return id_perfil; }
    public void setId_perfil(Long id) { this.id_perfil = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre;
    }
}