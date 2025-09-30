package model;

import java.util.ArrayList;

public class ProfesorPlanta extends Profesor {
    private String proyecto;
    private TIEMPO tiempo;



    public ProfesorPlanta(String id, String nombre, String titulo, byte aniosExp, ArrayList<Materia> listaMaterias, Carrera ownedByCarrera, String proyecto, TIEMPO tiempo) {
        super(id, nombre, titulo, aniosExp, listaMaterias, ownedByCarrera);
        this.proyecto = proyecto;
        this.tiempo = tiempo;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public TIEMPO getTiempo() {
        return tiempo;
    }

    public void setTiempo(TIEMPO tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "ProfesorPlanta{" +
                "proyecto='" + proyecto + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }
}
