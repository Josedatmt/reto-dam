package com.frontend.model;

import java.util.Date;

public class Solicitud {
    private int id_solicitud;
    private Date fecha;
    private String archivo;
    private String comentarios;
    private int estado;
    private int id_vacante;
    private String username;

    // Getters y setters
    public int getId_solicitud() { return id_solicitud; }
    public void setId_solicitud(int id_solicitud) { this.id_solicitud = id_solicitud; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public int getId_vacante() { return id_vacante; }
    public void setId_vacante(int id_vacante) { this.id_vacante = id_vacante; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}