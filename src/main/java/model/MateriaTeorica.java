package model;

public class MateriaTeorica extends Materia {

    public MateriaTeorica(String codigo, String nombre, byte numeroHoras, byte cantidadCreditos, String semestre, String profesor, Carrera ownedByCarrera) {
        super(codigo, nombre, numeroHoras, cantidadCreditos, semestre, profesor, ownedByCarrera);
    }
}
