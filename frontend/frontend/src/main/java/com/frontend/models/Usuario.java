package com.frontend.models;

import java.time.LocalDateTime;
import java.util.List;


public class Usuario {
    private String username;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private int enabled;
    String token;
    private LocalDateTime fecha_registro;
    private List<Perfil> perfiles;


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getEnabled() { return enabled; }
    public void setEnabled(int enabled) { this.enabled = enabled; }
    public LocalDateTime getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(LocalDateTime fecha) { this.fecha_registro = fecha; }
    public List<Perfil> getPerfiles() { return perfiles; }
    public void setPerfiles(List<Perfil> perfiles) { this.perfiles = perfiles; }
    public String getToken() {return token;}
    public void setToken(String token) {
    }
}