package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Profesor {
    private String id;
    private String nombre;
    private String titulo;
    private byte aniosExp;
    private ArrayList<Materia> listaMaterias;
    private Carrera ownedByCarrera;

    public Profesor(String id, String nombre, String titulo, byte aniosExp,ArrayList<Materia>listaMaterias, Carrera ownedByCarrera){
        this.id = id;
        this.nombre = nombre;
        this.titulo = titulo;
        this.aniosExp = aniosExp;
        this.listaMaterias = listaMaterias;
        this.ownedByCarrera = ownedByCarrera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte getAniosExp() {
        return aniosExp;
    }

    public void setAniosExp(byte aniosExp) {
        this.aniosExp = aniosExp;
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public Carrera getOwnedByCarrera() {
        return ownedByCarrera;
    }

    public void setOwnedByCarrera(Carrera ownedByCarrera) {
        this.ownedByCarrera = ownedByCarrera;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", titulo='" + titulo + '\'' +
                ", aniosExp=" + aniosExp +
                ", listaMaterias=" + listaMaterias +
                ", ownedByCarrera=" + ownedByCarrera +
                '}';
    }
}

