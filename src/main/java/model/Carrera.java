package model;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


import java.util.ArrayList;


public record Carrera (String nombre, ArrayList<Estudiante>estudiantes,ArrayList<Materia>materias,ArrayList<Profesor>profesores) {

// CRUD´s
    // CRUD SUBJECT

    // Create Subject
    public void crearMateria(Materia m) {
        Objects.requireNonNull(m, "Materia requerida");
        boolean dup = materias.stream().anyMatch(x -> x.getCodigo().equals(m.getCodigo()));
        if (dup) throw new IllegalStateException("Ya existe materia con código " + m.getCodigo());
        materias.add(m);
    }

    // Update subject
    public Materia actualizarMateria(String codigo, UnaryOperator<Materia> actualizador) {
        Materia m = obtenerMateria(codigo)
                .orElseThrow(() -> new NoSuchElementException("No existe materia " + codigo));
        actualizador.apply(m);
        return m;
    }


    //CRUD TEACHER

    // Create Teacher
    public void crearProfesor(Profesor p) {
        Objects.requireNonNull(p, "Profesor requerido");
        boolean dup = profesores.stream().anyMatch(x -> x.getId().equals(p.getId()));
        if (dup) throw new IllegalStateException("Ya existe profesor con id " + p.getId());
        profesores.add(p);
    }

    // Read Teacher

    public Optional<Profesor> obtenerProfesor(String id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Update Teacher

    public Profesor actualizarProfesor(String id, UnaryOperator<Profesor> actualizador) {
        Profesor p = obtenerProfesor(id)
                .orElseThrow(() -> new NoSuchElementException("No existe profesor " + id));
        actualizador.apply(p);
        return p;
    }


    // Delete Teacher

    public void eliminarProfesor(String id) {
        boolean removed = profesores.removeIf(p -> p.getId().equals(id));
        if (!removed) throw new NoSuchElementException("No existe profesor " + id);
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

