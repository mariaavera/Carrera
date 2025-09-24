package model;

import java.util.ArrayList;

public class ProfesorPlanta extends Profesor {


    public ProfesorPlanta(String id, String nombre, String titulo, byte aniosExp, ArrayList<Materia> listaMaterias, Carrera ownedByCarrera) {
        super(id, nombre, titulo, aniosExp, listaMaterias, ownedByCarrera);
    }
}
