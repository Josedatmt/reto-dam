package com.frontend.model;

public class Empresa {
    private int id_empresa;
    private String nombre_social;
    private String direccion_fiscal;
    private String pais;

    public Empresa() {}

    public Empresa(int id_empresa, String nombre_social, String direccion_fiscal, String pais) {
        this.id_empresa = id_empresa;
        this.nombre_social = nombre_social;
        this.direccion_fiscal = direccion_fiscal;
        this.pais = pais;
    }

    // Getters y setters
    public int getId_empresa() { return id_empresa; }
    public void setId_empresa(int id_empresa) { this.id_empresa = id_empresa; }
    public String getNombre_social() { return nombre_social; }
    public void setNombre_social(String nombre_social) { this.nombre_social = nombre_social; }
    public String getDireccion_fiscal() { return direccion_fiscal; }
    public void setDireccion_fiscal(String direccion_fiscal) { this.direccion_fiscal = direccion_fiscal; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    @Override
    public String toString() {
        return nombre_social;
    }
}