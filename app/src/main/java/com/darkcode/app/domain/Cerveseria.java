package com.darkcode.app.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cerveserias")

public class Cerveseria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "departamento")
    private String departamento;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "nomenclatura")
    private String nomencaltura;
    @Column(name = "marca_cerveza")
    private String marca_cerveza;
    @Column(name = "cantidad_en_stock")
    private Long cantidad_en_stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNomencaltura() {
        return nomencaltura;
    }

    public void setNomencaltura(String nomencaltura) {
        this.nomencaltura = nomencaltura;
    }

    public String getMarca_cerveza() {
        return marca_cerveza;
    }

    public void setMarca_cerveza(String marca_cerveza) {
        this.marca_cerveza = marca_cerveza;
    }

    public Long getCantidad_en_stock() {
        return cantidad_en_stock;
    }

    public void setCantidad_en_stock(Long cantidad_en_stock) {
        this.cantidad_en_stock = cantidad_en_stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

    @Override
    public String toString() {
        return "Cerveseria [id=" + id + ", departamento=" + departamento + ", municipio=" + municipio
                + ", nomencaltura=" + nomencaltura + ", marca_cerveza=" + marca_cerveza + ", tipo_cerveza="
                + cantidad_en_stock + "]";

    }

}
