package com.frontend.models;

public class Categoria {
    private Long id_categoria;
    private String nombre;
    private String descripcion;


    public Categoria() {}
    public Categoria(Long id, String nombre, String desc) {
        this.id_categoria = id;
        this.nombre = nombre;
        this.descripcion = desc;
    }


    public Long getId_categoria() { return id_categoria; }
    public void setId_categoria(Long id) { this.id_categoria = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String desc) { this.descripcion = desc; }

    @Override
    public String toString() {
        return nombre;
    }
}