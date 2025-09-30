package model;

import java.util.ArrayList;

public class ProfesorCatedra extends Profesor {
    private  int numHoras;
    private String empresaExterna;

    public ProfesorCatedra(String id, String nombre, String titulo, byte aniosExp, ArrayList<Materia> listaMaterias, Carrera ownedByCarrera, int numHoras, String empresaExterna) {
        super(id, nombre, titulo, aniosExp, listaMaterias, ownedByCarrera);
        this.numHoras = numHoras;
        this.empresaExterna = empresaExterna;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public String getEmpresaExterna() {
        return empresaExterna;
    }

    public void setEmpresaExterna(String empresaExterna) {
        this.empresaExterna = empresaExterna;
    }

    @Override
    public String toString() {
        return "ProfesorCatedra{" +
                "numHoras=" + numHoras +
                ", empresaExterna='" + empresaExterna + '\'' +
                '}';
    }
}
