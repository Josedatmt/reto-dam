package com.frontend.models;

import java.util.Date;

public class Vacante {
    private Long id_vacante;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private String estatus;
    private boolean destacado;
    private String imagen;
    private String detalles;
    private Long categoriaId;
    private Long empresaId;


    public Long getId_vacante() { return id_vacante; }
    public void setId_vacante(Long id) { this.id_vacante = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String desc) { this.descripcion = desc; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }
    public boolean isDestacado() { return destacado; }
    public void setDestacado(boolean destacado) { this.destacado = destacado; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long id) { this.categoriaId = id; }
    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long id) { this.empresaId = id; }
}