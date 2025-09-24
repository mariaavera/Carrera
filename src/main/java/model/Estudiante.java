package model;

import java.util.ArrayList;

public class Estudiante {
    private String id;
    private String nombre;
    private String doc;
    private String porgramaAca;
    private String semestre;
    private ArrayList<Materia>listaMaterias;
    private Carrera ownedByCarrera;

    public Estudiante(String id, String nombre, String doc, String porgramaAca, String semestre, ArrayList<Materia> listaMaterias, Carrera ownedByCarrera) {
        this.id = id;
        this.nombre = nombre;
        this.doc = doc;
        this.porgramaAca = porgramaAca;
        this.semestre = semestre;
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

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getPorgramaAca() {
        return porgramaAca;
    }

    public void setPorgramaAca(String porgramaAca) {
        this.porgramaAca = porgramaAca;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
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
        return "Estudiante{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", doc='" + doc + '\'' +
                ", porgramaAca='" + porgramaAca + '\'' +
                ", semestre='" + semestre + '\'' +
                ", listaMaterias=" + listaMaterias +
                ", ownedByCarrera=" + ownedByCarrera +
                '}';
    }
}
