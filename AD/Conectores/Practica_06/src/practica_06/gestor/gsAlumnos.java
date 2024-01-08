/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import practica_06.curso.Alumno;
import practica_06.utilidades.*;

/**
 *
 * @author andre
 */
public class gsAlumnos extends gestor {

    ReadClient rc = new ReadClient();

    private void insertAlumno(Alumno alumno) {
        String query = String.format("INSERT INTO `alumnos`(`ALM_NAME`, `ALM_SURNAMES`, `ALM_FECHA`, `ALM_NIA`) VALUES ('%s', '%s', '%s', %d)",
                 alumno.getNombre(), alumno.getApellidos(), alumno.getFechaNacimiento(), alumno.getNia());
        super.executeUpdate(query);
    }

    public void dropAlumno(int id) {

    }

    public void alta() {
        String nombre = rc.pedirString("Dime el nombre de el alumno: ", false);
        String apellidos = rc.pedirString("Dime los apellidos de el Alumno: ", false);
        int nia = pedirNia(false);
        int dia = rc.pedirIntRango("Dime el dia que nació: ", 1, 31);
        int mes = rc.pedirIntRango("Dime el indice del mes que nació: ", 1, 12);
        int anio = rc.pedirIntRango("Dime el año que nació: ", 1960, 2023);
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellidos(apellidos);
        alumno.setFechaNacimiento(dia, mes, anio);
        alumno.setNia(nia);
        insertAlumno(alumno);
        Colors.okMsg("El alumno se ha registrado correctamente.");
    }

    public void baja() {
//        int nia = pedirNia(true);
//        if (nia != 0) {
//            Session session = Conexion.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//
//            try {
//                Alumno alumno = buscarAlmNia(nia);
//
//                if (alumno != null) {
//                    gsMatriculas gsMatr = new gsMatriculas();
//                    gsMatr.delMatriculaPorNia(nia);
//                    session.delete(alumno);
//                    transaction.commit();
//                    Colors.okMsg("Alumno eliminado exitosamente.");
//                } else {
//                    Colors.errMsg("No se encontró un alumno con el NIA proporcionado.");
//                }
//            } catch (Exception e) {
//                transaction.rollback();
//                Colors.errMsg("Error al eliminar el alumno: " + e.getMessage());
//            } finally {
//                session.close();
//            }
//        }
    }

    public void mostrarAlumnos() {
//        Session session = Conexion.getSessionFactory().openSession();
//        Query<Alumno> query = session.createQuery("FROM Alumno", Alumno.class);
//        List<Alumno> alumnosList = query.getResultList();
//        alumnosList.forEach(it -> {
//            System.out.println(it.toString());
//        });
//        session.close();
        String selectQueryAlumnos = "SELECT * FROM `alumnos`";

        try (ResultSet resultSetAlumnos = super.executeSelect(selectQueryAlumnos)) {
            // Procesar el ResultSet
            while (resultSetAlumnos.next()) {
                String name = resultSetAlumnos.getString("ALM_NAME");
                String surnames = resultSetAlumnos.getString("ALM_SURNAMES");
                String fecha = resultSetAlumnos.getString("ALM_FECHA");
                int nia = resultSetAlumnos.getInt("ALM_NIA");

                System.out.println(new Alumno(name, surnames, fecha, nia).toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected int pedirNia(boolean exist) {
        String cancel = "";
        String errMsg = "El NIA ya existe.";
        if (exist) {
            cancel = ", 0 para cancelar";
            errMsg = "El NIA no existe.";
        }
        int nia;
        do {
            nia = rc.pedirIntPositivo("Dime el NIA del alumno" + cancel + " : ");
            if (exist && nia == 0) {
                break;
            }
            if (!comprobarNia(nia, exist)) {
                Colors.errMsg(errMsg);
            }
        } while (!comprobarNia(nia, exist));
        return nia;
    }

//    protected Alumno buscarAlmNia(int nia) {
//        Session session = Conexion.getSessionFactory().openSession();
//        Alumno alumno = null;
//        try {
//            alumno = session.createQuery("FROM Alumno WHERE nia = :nia", Alumno.class)
//                    .setParameter("nia", nia)
//                    .uniqueResult();
//        } catch (Exception e) {
//            Colors.errMsg("No se ha podido buscar al alumno");
//        } finally {
//            session.close();
//        }
//        return alumno;
//    }
    
    public Boolean comprobarNia(int nia, boolean exist) {
        String query = "SELECT `ALM_ID`, `ALM_NAME`, `ALM_SURNAMES`, `ALM_FECHA`, `ALM_NIA` FROM `alumnos` WHERE ALM_NIA = ";
        boolean existe;
        try {
            ResultSet rs = super.executeSelect(query + nia);
            existe = rs.next();
            if (exist) {
            // Devuelve true si el NIA existe
            return existe;
        } else {
            // Devuelve true si el NIA NO existe
            return !existe;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
