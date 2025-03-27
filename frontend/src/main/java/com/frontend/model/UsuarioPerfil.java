package com.frontend.model;

public class UsuarioPerfil {
    private String username;
    private int id_perfil;

    public UsuarioPerfil() {}

    public UsuarioPerfil(String username, int id_perfil) {
        this.username = username;
        this.id_perfil = id_perfil;
    }


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getId_perfil() { return id_perfil; }
    public void setId_perfil(int id_perfil) { this.id_perfil = id_perfil; }
}