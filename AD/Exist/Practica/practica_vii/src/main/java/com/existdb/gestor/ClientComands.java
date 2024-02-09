package com.existdb.gestor;

import com.existdb.modelos.*;
import com.existdb.utilidades.*;

import java.util.List;

public class ClientComands {

    ReadClient rc = new ReadClient();
    AlumnosDB alumnosdb = new AlumnosDB();
    ModulosDB modulosdb = new ModulosDB();
    MatriculasDB matriculasDB = new MatriculasDB();



    public void altaAlumno() {
        try {
            String nombre = rc.pedirString("Nombre de el alumno: ", false);
            String apellidos = rc.pedirString("Apellidos de el alumno: ", false);
            int dia = rc.pedirIntRango("Dia nacimiento de el alumno: ", 1, 31);
            int mes = rc.pedirIntRango("Mes nacimiento de el alumno: ", 1, 12);
            int ano = rc.pedirIntRango("Año nacimiento de el alumno: ", 1900, 2024);
            int nia = rc.pedirIntPositivo("NIA del alumno: ");// pedirNIA(false);
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
            e.printStackTrace();
        }
    }

    public void bajaAlumno() {
        try {
            int nia = rc.pedirIntPositivo("NIA del alumno: ");// pedirNIA(true);
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

    }

    public void altaModulo() {
        try {
            String nombre = rc.pedirString("Nombre modulo: ", false);
            String opcion = rc.pedirOpcion("Quieres dar de alta el modulo "+nombre, "s", "n");
            if (opcion.equals("s")) {
                modulosdb.anadirModulo(new Modulo(nombre));
                Colors.okMsg("Se a dado de alta el modulo :)");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("No se ha podido crear el modulo");
            e.printStackTrace();
        }
    }
    
    public void bajaModulo() {
        try {
            modulosdb.mostrarModulos();
            List<Modulo> modulosList = modulosdb.getAllModulos();
            int pos = rc.pedirIntRango("Modulo a eliminar: ", 1, modulosList.size())-1;
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
            e.printStackTrace();
        }
    }
    
    public void altaMatricula() {
        int nia = rc.pedirIntPositivo("NIA del alumno: ");
        Alumno a = alumnosdb.getAlumnoByNia(nia);
        if (a != null) {
            modulosdb.mostrarModulos();
            List<Modulo> modulosList = modulosdb.getAllModulos();
            int pos = rc.pedirIntRango("Modulo a matricular: ", 1, modulosList.size()) - 1;
            matriculasDB.anadirMatricula(new Matricula(Integer.valueOf(a.getId()), Integer.valueOf(modulosList.get(pos).getId())));
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
                    Modulo modulo = modulosdb.getModuloById(matriculas.get(i).getIdModulo()+"");
                    System.out.println(i +".- " + modulo.getNombre());
                }
                int pos = rc.pedirIntRango("Seleccione la matrícula a dar de baja: ", 1, matriculas.size()) - 1;
                Matricula matriculaSeleccionada = matriculas.get(pos);
                matriculasDB.eliminarMatricula(Integer.valueOf(matriculaSeleccionada.getId()));;
                Colors.okMsg("Matrícula eliminada con éxito.");
            } else {
                Colors.warMsg("El alumno no tiene matrículas.");
            }
        } else {
            Colors.warMsg("El nia no existe");
        }
    }


}
