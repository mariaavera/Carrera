package model;

import java.util.ArrayList;

public class EstudiantePosgrado extends Estudiante {
    private String temaInvestigacion;
    private CURSO curso;

    public EstudiantePosgrado(String id, String nombre, String doc, String porgramaAca, String semestre, ArrayList<Materia> listaMaterias, Carrera ownedByCarrera, String temaInvestigacion, CURSO curso) {
        super(id, nombre, doc, porgramaAca, semestre, listaMaterias, ownedByCarrera);
        this.temaInvestigacion = temaInvestigacion;
        this.curso = curso;
    }

    public String getTemaInvestigacion() {
        return temaInvestigacion;
    }

    public void setTemaInvestigacion(String temaInvestigacion) {
        this.temaInvestigacion = temaInvestigacion;
    }

    public CURSO getCurso() {
        return curso;
    }

    public void setCurso(CURSO curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "EstudiantePosgrado{" +
                "temaInvestigacion='" + temaInvestigacion + '\'' +
                ", curso=" + curso +
                '}';
    }
}
