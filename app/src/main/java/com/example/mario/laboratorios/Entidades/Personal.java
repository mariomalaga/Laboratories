/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios.Entidades;

public class Personal {
    private int id;
    private String nombre;
    private String Apellido;
    private int horaEntrada;
    private int horaSalida;
    private int id_laboratorio;

    public Personal(int id, String nombre, String apellido, int horaEntrada, int horaSalida, int id_laboratorio) {
        this.id = id;
        this.nombre = nombre;
        Apellido = apellido;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.id_laboratorio = id_laboratorio;
    }
    public Personal(){

    }
    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(int id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
    }
}
