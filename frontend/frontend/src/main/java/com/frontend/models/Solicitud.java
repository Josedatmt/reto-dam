package com.frontend.models;

import java.util.Date;

public class Solicitud {
    private Long id_solicitud;
    private Date fecha;
    private String archivo;
    private String comentarios;
    private String estado;
    private Long usuarioId;
    private Long vacanteId;


    public Long getId_solicitud() { return id_solicitud; }
    public void setId_solicitud(Long id) { this.id_solicitud = id; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long id) { this.usuarioId = id; }
    public Long getVacanteId() { return vacanteId; }
    public void setVacanteId(Long id) { this.vacanteId = id; }
}