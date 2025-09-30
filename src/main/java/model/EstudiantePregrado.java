package model;

import java.util.ArrayList;

public class EstudiantePregrado extends Estudiante {
    private String beca;
    private double promedio;

    public EstudiantePregrado(String id, String nombre, String doc, String porgramaAca, String semestre, ArrayList<Materia> listaMaterias, Carrera ownedByCarrera, String beca, double promedio) {
        super(id, nombre, doc, porgramaAca, semestre, listaMaterias, ownedByCarrera);
        this.beca = beca;
        this.promedio = promedio;
    }

    public String getBeca() {
        return beca;
    }

    public void setBeca(String beca) {
        this.beca = beca;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "EstudiantePregrado{" +
                "beca='" + beca + '\'' +
                ", promedio=" + promedio +
                '}';
    }
}
