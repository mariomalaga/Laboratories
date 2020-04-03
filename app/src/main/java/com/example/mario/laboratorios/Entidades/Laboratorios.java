/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios.Entidades;

public class Laboratorios {
    private int id;
    private String laboratorio;
    private int cerrado;
    public Laboratorios(int id, String laboratorio, int cerrado){
        this.id = id;
        this.laboratorio = laboratorio;
        this.cerrado = cerrado;
    }
    public Laboratorios(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int isCerrado() {
        return cerrado;
    }

    public void setCerrado(int cerrado) {
        this.cerrado = cerrado;
    }
}
