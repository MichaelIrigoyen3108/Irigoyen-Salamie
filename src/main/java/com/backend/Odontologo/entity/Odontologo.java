package com.backend.Odontologo.entity;

public class Odontologo {
    private int id;
    private Integer matricula;
    private String nombre;
    private String apellido;

    public Odontologo(Integer matricula, String nombre, String apellido, int id) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
    }

    public Odontologo(String nombre, String apellido, Integer matricula) {
    }

    public static void add(Odontologo odontologos) {
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
