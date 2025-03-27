package com.frontend.model;

import java.util.Date;

public class Usuario {
    private String username;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private int enabled;
    private Date fecha_registro;
    private Perfil perfil;



    public boolean isEnabled() {
        return enabled == 1;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled ? 1 : 0;
    }


    public int getEnabledValue() {
        return enabled;
    }
    // Getters y setters
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
    public Date getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(Date fecha_registro) { this.fecha_registro = fecha_registro; }
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }


    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public String getPerfilNombre() {
        return perfil != null ? perfil.getNombre() : "";
    }

}