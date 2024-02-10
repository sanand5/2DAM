package com.existdb.gestor;

import com.existdb.modelos.*;
import com.existdb.utilidades.*;

import java.util.List;

public class ClientComands {

    ReadClient rc = new ReadClient();
    AlumnosDB alumnosdb = new AlumnosDB();
    ModulosDB modulosdb = new ModulosDB();
    MatriculasDB matriculasDB = new MatriculasDB();
    private static final String filePathAlumnos = "./res/alumnos.xml";
    private static final String filePathModulos = "./res/modulos.xml";
    private static final String filePathMatriculas = "./res/matriculas.xml";

    public void altaAlumno() {
        try {
            String nombre = rc.pedirString("Nombre de el alumno: ", false);
            String apellidos = rc.pedirString("Apellidos de el alumno: ", false);
            int dia = rc.pedirIntRango("Dia nacimiento de el alumno: ", 1, 31);
            int mes = rc.pedirIntRango("Mes nacimiento de el alumno: ", 1, 12);
            int ano = rc.pedirIntRango("Año nacimiento de el alumno: ", 1900, 2024);
            int nia = rc.pedirIntPositivo("NIA del alumno: ");
            String fecha = String.format("%02d/%02d/%d", dia, mes, ano);
            System.out.println("Resumen de los datos:\n");
            System.out.printf("Nombre completo: %s %s %nFecha de nacimiento: %s%nNia: %s%n%n", nombre, apellidos, fecha,
                    nia + "");
            String opcion = rc.pedirOpcion("Quieres dar de alta al alumno", "s", "n");
            if (opcion.equals("s")) {
                alumnosdb.anadirAlumno(new Alumno(nombre, apellidos, fecha, nia + ""));
                Colors.okMsg("Se a dado de alta el alumno :)");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("No se ha podido crear al alumno");
        }
    }

    public void bajaAlumno() {
        try {
            int nia = rc.pedirIntPositivo("NIA del alumno: ");
            String opcion = rc.pedirOpcion("Quieres dar de baja al alumno con nia " + nia + ":", "s", "n");
            if (opcion.equals("s")) {
                alumnosdb.eliminarAlumno(nia);
                Colors.okMsg("Se a dado de baja el alumno :(");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");

        }
    }

    public void listarAlumnos() {
        for (Alumno alumno : alumnosdb.getAllAlumnos()) {
            System.out.println(alumno.toString());
        }
    }

    public void altaModulo() {
        try {
            String nombre = rc.pedirString("Nombre modulo: ", false);
            String opcion = rc.pedirOpcion("Quieres dar de alta el modulo " + nombre, "s", "n");
            if (opcion.equals("s")) {
                modulosdb.anadirModulo(new Modulo(nombre));
                Colors.okMsg("Se a dado de alta el modulo :)");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("No se ha podido crear el modulo");
        }
    }

    public void bajaModulo() {
        try {
            modulosdb.mostrarModulos();
            List<Modulo> modulosList = modulosdb.getAllModulos();
            int pos = rc.pedirIntRango("Modulo a eliminar: ", 1, modulosList.size()) - 1;
            String opcion = rc.pedirOpcion("Quieres dar de baja el modulo " + modulosList.get(pos).getNombre(), "s",
                    "n");
            if (opcion.equals("s")) {
                modulosdb.eliminarModulo(Integer.valueOf(modulosList.get(pos).getId()));
                Colors.okMsg("Se a dado de baja el modulo :)");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("No se ha podido eliminar el modulo");
        }
    }

    public void listarModulos() {
        modulosdb.mostrarModulos();
    }

    public void altaMatricula() {
        int nia = rc.pedirIntPositivo("NIA del alumno: ");
        Alumno a = alumnosdb.getAlumnoByNia(nia);
        if (a != null) {
            modulosdb.mostrarModulos();
            List<Modulo> modulosList = modulosdb.getAllModulos();
            int pos = rc.pedirIntRango("Modulo a matricular: ", 1, modulosList.size()) - 1;
            matriculasDB.anadirMatricula(
                    new Matricula(Integer.valueOf(a.getId()), Integer.valueOf(modulosList.get(pos).getId())));
        } else {
            Colors.warMsg("El nia no existe");
        }
    }

    public void bajaMatricula() {
        int nia = rc.pedirIntPositivo("NIA del alumno: ");
        Alumno a = alumnosdb.getAlumnoByNia(nia);
        if (a != null) {
            List<Matricula> matriculas = matriculasDB.getMatriculasByExternalId(
                    Integer.valueOf(a.getId()),
                    MatriculasDB.CAMPO_ALUMNO);

            if (!matriculas.isEmpty()) {
                System.out.println("Matrículas del alumno:");
                for (int i = 1; i <= matriculas.size(); i++) {
                    Modulo modulo = modulosdb.getModuloById(matriculas.get(i).getIdModulo() + "");
                    System.out.println(i + ".- " + modulo.getNombre());
                }
                int pos = rc.pedirIntRango("Seleccione la matrícula a dar de baja: ", 1, matriculas.size()) - 1;
                Matricula matriculaSeleccionada = matriculas.get(pos);
                matriculasDB.eliminarMatricula(Integer.valueOf(matriculaSeleccionada.getId()));
                ;
                Colors.okMsg("Matrícula eliminada con éxito.");
            } else {
                Colors.warMsg("El alumno no tiene matrículas.");
            }
        } else {
            Colors.warMsg("El nia no existe");
        }
    }

    public void anadirNota() {
        int nia = rc.pedirIntPositivo("NIA del alumno: ");
        Alumno a = alumnosdb.getAlumnoByNia(nia);
        if (a != null) {
            List<Matricula> matriculas = matriculasDB.getMatriculasByExternalId(
                    Integer.valueOf(a.getId()),
                    MatriculasDB.CAMPO_ALUMNO);

            if (!matriculas.isEmpty()) {
                System.out.println("Matrículas del alumno:");
                for (int i = 0; i < matriculas.size(); i++) {
                    Modulo modulo = modulosdb.getModuloById(matriculas.get(i).getIdModulo() + "");
                    System.out.println(i + 1 + ".- " + modulo.getNombre());
                }
                int pos = rc.pedirIntRango("Seleccione la matrícula a actualizar: ", 1, matriculas.size()) - 1;
                Matricula matriculaSeleccionada = matriculas.get(pos);
                Double nota = rc.pedirDoubleRango("Nota a añadir: ", 0.0, 10.0);

                List<Double> notas = matriculaSeleccionada.getNotasArray();
                notas.add(nota);
                matriculaSeleccionada.setNotasArray(notas);
                matriculasDB.actualizarMatricula(Integer.valueOf(matriculaSeleccionada.getId()),
                        matriculaSeleccionada.getNotas());

                Colors.okMsg("Matrícula actualizada con éxito.");
            } else {
                Colors.warMsg("El alumno no tiene matrículas.");
            }
        } else {
            Colors.warMsg("El nia no existe");
        }
    }

    public void modificarNota() {
        int nia = rc.pedirIntPositivo("NIA del alumno: ");
        Alumno a = alumnosdb.getAlumnoByNia(nia);
        if (a != null) {
            List<Matricula> matriculas = matriculasDB.getMatriculasByExternalId(
                    Integer.valueOf(a.getId()),
                    MatriculasDB.CAMPO_ALUMNO);

            if (!matriculas.isEmpty()) {
                System.out.println("Matrículas del alumno:");
                for (int i = 0; i < matriculas.size(); i++) {
                    Modulo modulo = modulosdb.getModuloById(matriculas.get(i).getIdModulo() + "");
                    System.out.println(i + 1 + ".- " + modulo.getNombre());
                }
                int pos = rc.pedirIntRango("Seleccione la matrícula a actualizar: ", 1, matriculas.size()) - 1;
                Matricula matriculaSeleccionada = matriculas.get(pos);
                matriculaSeleccionada.mostrarNotas();
                int notaPos = rc.pedirIntRango("Nota a modificar: ", 0, matriculaSeleccionada.getNotasArray().size());
                Double newNota = rc.pedirDoubleRango("Nueva nota: ", 0.0, 10.0);

                List<Double> notas = matriculaSeleccionada.getNotasArray();
                notas.set(notaPos - 1, newNota);
                matriculaSeleccionada.setNotasArray(notas);
                matriculasDB.actualizarMatricula(Integer.valueOf(matriculaSeleccionada.getId()),
                        matriculaSeleccionada.getNotas());

                Colors.okMsg("Matrícula actualizada con éxito.");
            } else {
                Colors.warMsg("El alumno no tiene matrículas.");
            }
        } else {
            Colors.warMsg("El nia no existe");
        }
    }

    public void eliminarNota() {
        int nia = rc.pedirIntPositivo("NIA del alumno: ");
        Alumno a = alumnosdb.getAlumnoByNia(nia);
        if (a != null) {
            List<Matricula> matriculas = matriculasDB.getMatriculasByExternalId(
                    Integer.valueOf(a.getId()),
                    MatriculasDB.CAMPO_ALUMNO);

            if (!matriculas.isEmpty()) {
                System.out.println("Matrículas del alumno:");
                for (int i = 0; i < matriculas.size(); i++) {
                    Modulo modulo = modulosdb.getModuloById(matriculas.get(i).getIdModulo() + "");
                    System.out.println(i + 1 + ".- " + modulo.getNombre());
                }
                int pos = rc.pedirIntRango("Seleccione la matrícula a actualizar: ", 1, matriculas.size()) - 1;
                Matricula matriculaSeleccionada = matriculas.get(pos);
                matriculaSeleccionada.mostrarNotas();
                int notaPos = rc.pedirIntRango("Nota a eliminar: ", 0, matriculaSeleccionada.getNotasArray().size());

                List<Double> notas = matriculaSeleccionada.getNotasArray();
                notas.remove(notaPos - 1);
                matriculaSeleccionada.setNotasArray(notas);
                matriculasDB.actualizarMatricula(Integer.valueOf(matriculaSeleccionada.getId()),
                        matriculaSeleccionada.getNotas());

                Colors.okMsg("Matrícula actualizada con éxito.");
            } else {
                Colors.warMsg("El alumno no tiene matrículas.");
            }
        } else {
            Colors.warMsg("El nia no existe");
        }
    }

    public void listarCentor() {
        List<Alumno> alumnos = alumnosdb.getAllAlumnos();
        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);
            System.out.println(alumno.toString());
            List<Matricula> matriculas = matriculasDB.getMatriculasByExternalId(Integer.valueOf(alumno.getId()),
                    MatriculasDB.CAMPO_ALUMNO);
            for (Matricula matricula : matriculas) {
                Modulo modulo = modulosdb.getModuloById(matricula.getIdModulo() + "");
                System.out.println("\tMatrícula en " + modulo.getNombre());
                List<Double> notas = matricula.getNotasArray();
                if (!notas.isEmpty()) {
                    System.out.println("\t\tNotas:");
                    for (int j = 0; j < notas.size(); j++) {
                        System.out.println("\t\t\t" + (j + 1) + ".- " + notas.get(j));
                    }
                } else {
                    System.out.println("\t\tNo hay notas registradas.");
                }
            }
        }
    }

    public void exportarAll() {
        alumnosdb.exportar(filePathAlumnos);
        modulosdb.exportar(filePathModulos);
        matriculasDB.exportar(filePathMatriculas);
    }

    public void importarAll() {
        alumnosdb.importar(filePathAlumnos);
        modulosdb.importar(filePathModulos);
        matriculasDB.importar(filePathMatriculas);
    }

}
