package model;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


import java.util.ArrayList;


public record Carrera (String nombre, ArrayList<String>estudiantes,ArrayList<String>materias,ArrayList<String>profesores) {

// CRUD´s
    // CRUD SUBJECT

    // Create Subject
    public void crearMateria(String materia) {
        Objects.requireNonNull(materia, "Materia requerida");
        if (materias.contains(materia)) {
            throw new IllegalStateException("Ya existe materia " + materia);
        }
        materias.add(materia);
    }

    // Update subject
    public void actualizarMateria(String materia, String nuevaMateria) {
        int idx = materias.indexOf(materia);
        if (idx == -1) throw new NoSuchElementException("No existe materia " + materia);
        materias.set(idx, nuevaMateria);
    }


    //CRUD TEACHER

    // Create Teacher
    public void crearProfesor(String profesor) {
        Objects.requireNonNull(profesor, "Profesor requerido");
        if (profesores.contains(profesor)) {
            throw new IllegalStateException("Ya existe profesor " + profesor);
        }
        profesores.add(profesor);
    }

    // Read Teacher

    public String obtenerProfesor(String profesor) {
        return profesores.stream()
                .filter(p -> p.equals(profesor))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No existe profesor " + profesor));
    }

    // Update Teacher

    public void actualizarProfesor(String viejo, String nuevo) {
        int idx = profesores.indexOf(viejo);
        if (idx == -1) throw new NoSuchElementException("No existe profesor " + viejo);
        profesores.set(idx, nuevo);
    }

    // Delete Teacher

    public void eliminarProfesor(String profesor) {
        boolean removed = profesores.remove(profesor);
        if (!removed) throw new NoSuchElementException("No existe profesor " + profesor);
    }

    public ArrayList<Materia> buscarMateriaSemestre(String semestre){
        ArrayList<Materia> materiasSemestre = new ArrayList<>();
        for(Materia m : materias){
            if(m.getSemestre().equals(semestre)){
                materiasSemestre.add(m);
            }
        }
        return materiasSemestre;
    }
}

