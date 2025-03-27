package com.frontend.model;

import java.util.Date;

public class Vacante {
    private int id_vacante;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private double salario;
    private String estatus;
    private int destacado;
    private String imagen;
    private String detalles;
    private int id_categoria;
    private int id_empresa;
    private String estado;

    public Vacante() {}


    public int getId_vacante() { return id_vacante; }
    public void setId_vacante(int id_vacante) { this.id_vacante = id_vacante; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public String getCesbias() { return estatus; }
    public void setCesbias(String cesbias) { this.estatus = cesbias; }
    public int getDestacado() { return destacado; }
    public void setDestacado(int destacado) { this.destacado = destacado; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }
    public int getId_categoria() { return id_categoria; }
    public void setId_categoria(int id_categoria) { this.id_categoria = id_categoria; }
    public int getId_empresa() { return id_empresa; }
    public void setId_empresa(int id_empresa) { this.id_empresa = id_empresa; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return nombre;
    }
}