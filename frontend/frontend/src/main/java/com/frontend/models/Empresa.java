package com.frontend.models;

public class Empresa {
    private Long id_empresa;
    private String razon_social;
    private String direccion_fiscal;
    private String pais;


    public Long getId_empresa() { return id_empresa; }
    public void setId_empresa(Long id) { this.id_empresa = id; }
    public String getRazon_social() { return razon_social; }
    public void setRazon_social(String razon) { this.razon_social = razon; }
    public String getDireccion_fiscal() { return direccion_fiscal; }
    public void setDireccion_fiscal(String dir) { this.direccion_fiscal = dir; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}