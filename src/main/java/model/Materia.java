package model;

import java.util.Objects;

public abstract class Materia {
    private String codigo;
    private String nombre;
    private byte numeroHoras;
    private byte cantidadCreditos;
    private String semestre;
    private String profesor;
    private Carrera ownedByCarrera;

    public Materia(String codigo, String nombre, byte numeroHoras, byte cantidadCreditos, String semestre, String profesor, Carrera ownedByCarrera) {
        this.codigo = Objects.requireNonNull(codigo, "El código es obligatorio" );
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.numeroHoras = Objects.requireNonNull(numeroHoras, "El numero de horas es obligatorio");
        this.cantidadCreditos = cantidadCreditos;
        this.semestre = semestre;
        this.profesor = profesor;
        this.ownedByCarrera = ownedByCarrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(byte numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public byte getCantidadCreditos() {
        return cantidadCreditos;
    }

    public void setCantidadCreditos(byte cantidadCreditos) {
        this.cantidadCreditos = cantidadCreditos;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Carrera getOwnedByCarrera() {
        return ownedByCarrera;
    }

    public void setOwnedByCarrera(Carrera ownedByCarrera) {
        this.ownedByCarrera = ownedByCarrera;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", numeroHoras=" + numeroHoras +
                ", cantidadCreditos=" + cantidadCreditos +
                ", semestre='" + semestre + '\'' +
                ", ownedByCarrera=" + ownedByCarrera +
                '}';
    }
}
