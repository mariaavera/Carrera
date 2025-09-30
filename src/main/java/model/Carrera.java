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

    // Read Subject

    public Optional<Materia> obtenerMateria(String codigo) {
        return materias.stream().filter(m -> m.getCodigo().equals(codigo)).findFirst();
    }

    // Update Subject

    public Materia actualizarMateria(String codigo, UnaryOperator<Materia> actualizador) {
        Materia m = obtenerMateria(codigo)
                .orElseThrow(() -> new NoSuchElementException("No existe materia " + codigo));
        actualizador.apply(m);
        return m;
    }

    // Delete Subject

    public void eliminarMateria(String codigo) {
        boolean removed = materias.removeIf(m -> m.getCodigo().equals(codigo));
        if (!removed) throw new NoSuchElementException("No existe materia " + codigo);
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

    // CRUD STUDENT

    // Create Student

    public void crearEstudiante(Estudiante e) {
        Objects.requireNonNull(e, "Estudiante requerido");
        boolean dup = estudiantes.stream().anyMatch(x -> x.getId().equals(e.getId()));
        if (dup) throw new IllegalStateException("Ya existe estudiante con id " + e.getId());
        estudiantes.add(e);
    }

    // Read Student

    public Optional<Estudiante> obtenerEstudiante(String id) {
        return estudiantes.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    // Update Student

    public Estudiante actualizarEstudiante(String id, UnaryOperator<Estudiante> actualizador) {
        Estudiante e = obtenerEstudiante(id)
                .orElseThrow(() -> new NoSuchElementException("No existe estudiante " + id));
        actualizador.apply(e);
        return e;
    }

    // Delete Student

    public void eliminarEstudiante(String id) {
        boolean removed = estudiantes.removeIf(s -> s.getId().equals(id));
        if (!removed) throw new NoSuchElementException("No existe estudiante " + id);
    }

    // Methods
    //1
    public ArrayList<Materia> buscarMateriaSemestre(String semestre){
        ArrayList<Materia> materiasSemestre = new ArrayList<>();
        for(Materia m : materias){
            if(m.getSemestre().equals(semestre)){
                materiasSemestre.add(m);
            }
        }
        return materiasSemestre;
    }

    //2

    public boolean asociarProfesorAMateria(String codigoMateria, String idProfesor) {
        Optional<Materia> om = obtenerMateria(codigoMateria);
        if (om.isEmpty()) {
            System.out.println("La materia no existe.");
            return false;
        }
        if (obtenerProfesor(idProfesor).isEmpty()) {
            System.out.println("El profesor no existe.");
            return false;
        }
        Materia m = om.get();
        if (m.getProfesor() != null) {
            System.out.println("La materia ya tiene un profesor asignado.");
            return false;
        }
        m.setProfesor(idProfesor);
        return true;
    }

    //3

    public ArrayList<Estudiante> listarEstudiantesDeMateria(String nombreMateria) {
        Optional<Materia> materiaOpt = obtenerMateria(nombreMateria);

        if (materiaOpt.isEmpty()) {
            System.out.println("La materia " + nombreMateria + " no existe.");
            return new ArrayList<>();
        }

        Materia materia = materiaOpt.get();
        ArrayList<Estudiante> estudiantes = materia.getOwnedByCarrera().estudiantes();

        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes inscritos en " + nombreMateria);
        }

        return estudiantes;
    }

    //4

    public void inscribirEstudianteEnMaterias(String idEstudiante, ArrayList<String> codigosMaterias) {
        Optional<Estudiante> estudianteOpt = obtenerEstudiante(idEstudiante);

        if (estudianteOpt.isEmpty()) {
            System.out.println("El estudiante con ID " + idEstudiante + " no está registrado.");
            return;
        }

        Estudiante estudiante = estudianteOpt.get();

        for (String codigoMateria : codigosMaterias) {
            Optional<Materia> materiaOpt = obtenerMateria(codigoMateria);

            if (materiaOpt.isEmpty()) {
                System.out.println("La materia " + codigoMateria + " no existe.");
                continue;
            }

            Materia materia = materiaOpt.get();
            estudiante.getListaMaterias().add(materia);
            System.out.println("Estudiante inscrito en " + codigoMateria);
        }
    }

    //5

    public int obtenerTotalCreditosEstudiante(String idEstudiante) {
        Optional<Estudiante> estudianteOpt = obtenerEstudiante(idEstudiante);

        if (estudianteOpt.isEmpty()) {
            System.out.println("El estudiante con ID " + idEstudiante + " no está registrado.");
            return 0;
        }

        Estudiante estudiante = estudianteOpt.get();

        int totalCreditos = 0;

        for (Materia materia : estudiante.getListaMaterias()) {

            totalCreditos += Byte.toUnsignedInt(materia.getCantidadCreditos());
        }

        return totalCreditos;
    }


    //6

    public byte cantidadDeHorasSemanales(String nombreMateria) {
        byte horasSemanales = 0;
        for(Materia m : materias){
            if(m.getNombre().equalsIgnoreCase(nombreMateria)){
                if(m instanceof MateriaPractica mp){
                    horasSemanales+=(mp.getHorasPractica()+mp.getNumeroHoras());
                } else{
                    horasSemanales+=(m.getNumeroHoras());
                }
            }
            return horasSemanales;
        }
        System.out.println("La materia " + nombreMateria + " no está registrada.");
        return 0;
    }


}

