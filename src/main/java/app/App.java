package app;

import model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class App {

    // Instancia única de Carrera para todos los menús
    private static final Carrera miCarrera =
            new Carrera("Ingenieria Sistemas", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public static void main(String[] args) {
        int opcionPrincipal = -1;

        while (opcionPrincipal != 0) {
            String menu = """
                    ===== MENÚ PRINCIPAL =====
                    1. Gestionar Materias
                    2. Gestionar Estudiantes
                    3. Gestionar Profesores
                    0. Salir
                    """;

            opcionPrincipal = Integer.parseInt(
                    JOptionPane.showInputDialog(menu)
            );

            switch (opcionPrincipal) {
                case 1 -> menuMaterias();
                case 2 -> menuEstudiantes();
                case 3 -> menuProfesores();
                case 0 -> JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    public static void menuMaterias() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    --- CRUD MATERIAS ---
                    1. Buscar materia
                    2. Registrar materia
                    3. Actualizar materia
                    4. Eliminar materia
                    0. Volver
                    """;

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(menu)
            );

            switch (opcion) {
                case 1 -> {
                    String codigo = JOptionPane.showInputDialog("Código de la materia:");
                    Optional<Materia> om = miCarrera.obtenerMateria(codigo);
                    JOptionPane.showMessageDialog(null,
                            om.map(Object::toString).orElse("No existe materia " + codigo));
                }
                case 2 -> {
                    String tipo = JOptionPane.showInputDialog("Tipo (T=Teórica / P=Práctica):").trim().toUpperCase();
                    String codigo = JOptionPane.showInputDialog("Código:");
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    byte horasTeoria = Byte.parseByte(JOptionPane.showInputDialog("Horas teoría/semana (byte):"));
                    byte creditos = Byte.parseByte(JOptionPane.showInputDialog("Créditos (byte):"));
                    String semestre = JOptionPane.showInputDialog("Semestre (texto):");
                    String profesorId = JOptionPane.showInputDialog("ID profesor (vacío para ninguno):");
                    if (profesorId.isBlank()) profesorId = null;

                    if (tipo.equals("P")) {
                        byte horasPractica = Byte.parseByte(JOptionPane.showInputDialog("Horas práctica/semana (byte):"));
                        byte numeroLabs = Byte.parseByte(JOptionPane.showInputDialog("Número de laboratorios (byte):"));
                        Materia m = new MateriaPractica(codigo, nombre, horasTeoria, creditos, semestre, profesorId, miCarrera,
                                horasPractica, numeroLabs);
                        miCarrera.crearMateria(m);
                    } else {
                        Materia m = new MateriaTeorica(codigo, nombre, horasTeoria, creditos, semestre, profesorId, miCarrera);
                        miCarrera.crearMateria(m);
                    }
                    JOptionPane.showMessageDialog(null, "Materia registrada.");
                }
                case 3 -> {
                    String codigo = JOptionPane.showInputDialog("Código a actualizar:");
                    Optional<Materia> om = miCarrera.obtenerMateria(codigo);
                    if (om.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No existe materia " + codigo);
                        break;
                    }
                    int op = Integer.parseInt(JOptionPane.showInputDialog("""
                            ¿Qué desea actualizar?
                            1. Nombre
                            2. Horas teoría
                            3. Créditos
                            4. Semestre
                            5. Profesor (ID) - vacío para quitar
                            6. (Si es práctica) Horas práctica
                            7. (Si es práctica) Número de labs
                            """));
                    switch (op) {
                        case 1 -> {
                            String v = JOptionPane.showInputDialog("Nuevo nombre:");
                            miCarrera.actualizarMateria(codigo, m -> { m.setNombre(v); return m; });
                        }
                        case 2 -> {
                            byte v = Byte.parseByte(JOptionPane.showInputDialog("Nuevas horas teoría (byte):"));
                            miCarrera.actualizarMateria(codigo, m -> { m.setNumeroHoras(v); return m; });
                        }
                        case 3 -> {
                            byte v = Byte.parseByte(JOptionPane.showInputDialog("Nuevos créditos (byte):"));
                            miCarrera.actualizarMateria(codigo, m -> { m.setCantidadCreditos(v); return m; });
                        }
                        case 4 -> {
                            String v = JOptionPane.showInputDialog("Nuevo semestre:");
                            miCarrera.actualizarMateria(codigo, m -> { m.setSemestre(v); return m; });
                        }
                        case 5 -> {
                            String v = JOptionPane.showInputDialog("Nuevo ID profesor (vacío para quitar):");
                            if (v.isBlank()) v = null;
                            String finalV = v;
                            miCarrera.actualizarMateria(codigo, m -> { m.setProfesor(finalV); return m; });
                        }
                        case 6 -> {
                            Materia m = miCarrera.obtenerMateria(codigo).get();
                            if (m instanceof MateriaPractica mp) {
                                byte v = Byte.parseByte(JOptionPane.showInputDialog("Nuevas horas práctica (byte):"));
                                mp.setHorasPractica(v);
                            } else {
                                JOptionPane.showMessageDialog(null, "La materia no es práctica.");
                            }
                        }
                        case 7 -> {
                            Materia m = miCarrera.obtenerMateria(codigo).get();
                            if (m instanceof MateriaPractica mp) {
                                byte v = Byte.parseByte(JOptionPane.showInputDialog("Nuevo número de labs (byte):"));
                                mp.setNumeroLabs(v);
                            } else {
                                JOptionPane.showMessageDialog(null, "La materia no es práctica.");
                            }
                        }
                        default -> { }
                    }
                    JOptionPane.showMessageDialog(null, "Actualización completada.");
                }
                case 4 -> {
                    String codigo = JOptionPane.showInputDialog("Código a eliminar:");
                    miCarrera.eliminarMateria(codigo);
                    JOptionPane.showMessageDialog(null, "Materia eliminada.");
                }
                case 0 -> JOptionPane.showMessageDialog(null, "Regresando al menú principal...");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    public static void menuEstudiantes() {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    --- CRUD ESTUDIANTES ---
                    1. Buscar estudiante
                    2. Registrar estudiantes
                    3. Actualizar estudiante
                    4. Eliminar estudiante
                    5. Buscar materias de un semestre en especifico
                    6. Estudiantes que estan en un materia en especifico
                    7. Inscribir estudiantes en varias materias
                    8. Obtener el total de los creditos de un estudiante
                    0. Volver
                    """;

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(menu)
            );

            switch (opcion) {
                case 1 -> {
                    String id = JOptionPane.showInputDialog("ID del estudiante:");
                    Optional<Estudiante> oe = miCarrera.obtenerEstudiante(id);
                    JOptionPane.showMessageDialog(null,
                            oe.map(Object::toString).orElse("No existe estudiante " + id));
                }
                case 2 -> {
                    String id = JOptionPane.showInputDialog("ID:");
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String doc = JOptionPane.showInputDialog("Documento:");
                    String prog = JOptionPane.showInputDialog("Programa académico:");
                    String semestre = JOptionPane.showInputDialog("Semestre (texto):");
                    Estudiante e = new Estudiante(id, nombre, doc, prog, semestre, new ArrayList<>(), miCarrera);
                    miCarrera.crearEstudiante(e);
                    JOptionPane.showMessageDialog(null, "Estudiante registrado.");
                }
                case 3 -> {
                    String id = JOptionPane.showInputDialog("ID a actualizar:");
                    Optional<Estudiante> oe = miCarrera.obtenerEstudiante(id);
                    if (oe.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No existe estudiante " + id);
                        break;
                    }
                    int op = Integer.parseInt(JOptionPane.showInputDialog("""
                            ¿Qué desea actualizar?
                            1. Nombre
                            2. Documento
                            3. Programa
                            4. Semestre
                            """));
                    switch (op) {
                        case 1 -> {
                            String v = JOptionPane.showInputDialog("Nuevo nombre:");
                            miCarrera.actualizarEstudiante(id, s -> { s.setNombre(v); return s; });
                        }
                        case 2 -> {
                            String v = JOptionPane.showInputDialog("Nuevo documento:");
                            miCarrera.actualizarEstudiante(id, s -> { s.setDoc(v); return s; });
                        }
                        case 3 -> {
                            String v = JOptionPane.showInputDialog("Nuevo programa:");
                            miCarrera.actualizarEstudiante(id, s -> { s.setPorgramaAca(v); return s; });
                        }
                        case 4 -> {
                            String v = JOptionPane.showInputDialog("Nuevo semestre:");
                            miCarrera.actualizarEstudiante(id, s -> { s.setSemestre(v); return s; });
                        }
                        default -> { }
                    }
                    JOptionPane.showMessageDialog(null, "Actualización completada.");
                }
                case 4 -> {
                    String id = JOptionPane.showInputDialog("ID a eliminar:");
                    miCarrera.eliminarEstudiante(id);
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado.");
                }
                case 5 -> {
                    System.out.print("Ingrese el semestre que desea consultar (texto): ");
                    String semestreBuscado = teclado.nextLine();
                    ArrayList<Materia> mats = miCarrera.buscarMateriaSemestre(semestreBuscado);
                    System.out.println("Materias del semestre " + semestreBuscado + ":");
                    for (Materia m : mats) {
                        System.out.println(m);
                    }
                }
                case 6 -> {
                    System.out.print("Ingrese el CÓDIGO de la materia: ");
                    String codigoMateria = teclado.nextLine();
                    ArrayList<Estudiante> ests = miCarrera.listarEstudiantesDeMateria(codigoMateria);
                    for (Estudiante e : ests) {
                        System.out.println(e.getNombre() + " (ID: " + e.getId() + ")");
                    }
                }
                case 7 -> {
                    System.out.print("Ingrese el ID del estudiante: ");
                    String id = teclado.nextLine();
                    System.out.print("¿Cuántas materias (por CÓDIGO) quiere inscribir? ");
                    int cantidad = Integer.parseInt(teclado.nextLine());
                    ArrayList<String> codigosMaterias = new ArrayList<>();
                    for (int i = 0; i < cantidad; i++) {
                        System.out.print("Código de la materia " + (i + 1) + ": ");
                        codigosMaterias.add(teclado.nextLine());
                    }
                    miCarrera.inscribirEstudianteEnMaterias(id, codigosMaterias);
                }
                case 8 -> {
                    System.out.print("¿Cuál es el ID del estudiante? ");
                    String id2 = teclado.nextLine();
                    int total = miCarrera.obtenerTotalCreditosEstudiante(id2);
                    System.out.println("Total de créditos del estudiante " + id2 + ": " + total);
                }
                case 0 -> JOptionPane.showMessageDialog(null, "Regresando al menú principal...");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    public static void menuProfesores() {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    --- CRUD PROFESORES ---
                    1. Buscar profesor
                    2. Registrar profesores
                    3. Actualizar profesor
                    4. Eliminar profesor
                    5. Asociar profesor a materia
                    0. Volver
                    """;

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(menu)
            );

            switch (opcion) {
                case 1 -> {
                    String id = JOptionPane.showInputDialog("ID del profesor:");
                    Optional<Profesor> op = miCarrera.obtenerProfesor(id);
                    JOptionPane.showMessageDialog(null,
                            op.map(Object::toString).orElse("No existe profesor " + id));
                }
                case 2 -> {
                    String tipo = JOptionPane.showInputDialog("Tipo (PLANTA= p/ CATEDRA= c):").trim().toUpperCase();
                    String id = JOptionPane.showInputDialog("ID:");
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String titulo = JOptionPane.showInputDialog("Título:");
                    byte anios = Byte.parseByte(JOptionPane.showInputDialog("Años de experiencia (byte):"));

                    if (tipo.equalsIgnoreCase("p")) {
                        String proyecto = JOptionPane.showInputDialog("Proyecto:");
                        String tiempoStr = JOptionPane.showInputDialog("Dedicación (TIEMPO_COMPLETO= T/ MEDIO_TIEMPO= MT):")
                                .trim().toUpperCase();
                        TIEMPO tiempo = TIEMPO.valueOf(tiempoStr.toUpperCase());
                        Profesor p = new ProfesorPlanta(id, nombre, titulo, anios, new ArrayList<>(), miCarrera, proyecto, tiempo);
                        miCarrera.crearProfesor(p);
                    } else {
                        int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas contratadas:"));
                        String empresa = JOptionPane.showInputDialog("Empresa externa (puede ser vacío):");
                        Profesor p = new ProfesorCatedra(id, nombre, titulo, anios, new ArrayList<>(), miCarrera, horas, empresa);
                        miCarrera.crearProfesor(p);
                    }
                    JOptionPane.showMessageDialog(null, "Profesor registrado.");
                }
                case 3 -> {
                    String id = JOptionPane.showInputDialog("ID a actualizar:");
                    Optional<Profesor> op = miCarrera.obtenerProfesor(id);
                    if (op.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No existe profesor " + id);
                        break;
                    }
                    int v = Integer.parseInt(JOptionPane.showInputDialog("""
                            ¿Qué desea actualizar?
                            1. Nombre
                            2. Título
                            3. Años de experiencia (byte)
                            """));
                    switch (v) {
                        case 1 -> {
                            String nv = JOptionPane.showInputDialog("Nuevo nombre:");
                            miCarrera.actualizarProfesor(id, p -> { p.setNombre(nv); return p; });
                        }
                        case 2 -> {
                            String nv = JOptionPane.showInputDialog("Nuevo título:");
                            miCarrera.actualizarProfesor(id, p -> { p.setTitulo(nv); return p; });
                        }
                        case 3 -> {
                            byte nv = Byte.parseByte(JOptionPane.showInputDialog("Nuevo años de experiencia (byte):"));
                            miCarrera.actualizarProfesor(id, p -> { p.setAniosExp(nv); return p; });
                        }
                        default -> { }
                    }
                    JOptionPane.showMessageDialog(null, "Actualización completada.");
                }
                case 4 -> {
                    String id = JOptionPane.showInputDialog("ID a eliminar:");
                    miCarrera.eliminarProfesor(id);
                    JOptionPane.showMessageDialog(null, "Profesor eliminado.");
                }
                case 5 -> {
                    System.out.print("Ingrese el CÓDIGO de la materia: ");
                    String codigoMateria = teclado.nextLine();
                    System.out.print("Ingrese el ID del profesor: ");
                    String idProfesor = teclado.nextLine();
                    boolean asociacion = miCarrera.asociarProfesorAMateria(codigoMateria, idProfesor);
                    System.out.println(asociacion ? "Asociación exitosa" : "No se pudo asociar");
                }
                case 0 -> JOptionPane.showMessageDialog(null, "Regresando al menú principal...");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }
}
