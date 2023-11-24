package gestor;

import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;

import curso.Alumno;
import utilidades.Colors;
import utilidades.ReadClient;

public class gsAlumnos extends gestorDB {

    ReadClient rc = new ReadClient();

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
        super.addDB(alumno);
        Colors.okMsg("El alumno se ha registrado correctamente.");
    }

    public void baja() {
        int nia = pedirNia(true);
        if (nia != 0) {
            Session session = Conexion.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            try {
                Alumno alumno = buscarAlmNia(nia);

                if (alumno != null) {
                    gsMatriculas gsMatr = new gsMatriculas();
                    gsMatr.delMatriculaPorNia(nia);
                    session.delete(alumno);
                    transaction.commit();
                    Colors.okMsg("Alumno eliminado exitosamente.");
                } else {
                    Colors.errMsg("No se encontró un alumno con el NIA proporcionado.");
                }
            } catch (Exception e) {
                transaction.rollback();
                Colors.errMsg("Error al eliminar el alumno: " + e.getMessage());
            } finally {
                session.close();
            }
        }
    }

    public void mostrarAlumnos() {
        Session session = Conexion.getSessionFactory().openSession();
        Query<Alumno> query = session.createQuery("FROM Alumno", Alumno.class);
        List<Alumno> alumnosList = query.getResultList();
        alumnosList.forEach(it -> {
            System.out.println(it.toString());
        });
        session.close();
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
            if (exist && nia == 0) {break;}
            if (!comprobarNia(nia, exist)) {Colors.errMsg(errMsg);}
        } while (!comprobarNia(nia, exist));
        return nia;
    }

    protected Alumno buscarAlmNia(int nia) {
        Session session = Conexion.getSessionFactory().openSession();
        Alumno alumno = null;
        try {
            alumno = session.createQuery("FROM Alumno WHERE nia = :nia", Alumno.class)
                    .setParameter("nia", nia)
                    .uniqueResult();
        } catch (Exception e) {
            Colors.errMsg("No se ha podido buscar al alumno");
        } finally {
            session.close();
        }
        return alumno;
    }

    private Boolean comprobarNia(int nia, boolean exist) {
        Session session = Conexion.getSessionFactory().openSession();
        Query<Integer> query = session.createQuery("SELECT a.nia FROM Alumno a", Integer.class);
        List<Integer> listaNia = query.getResultList();
        session.close();

        if (exist) {
            // Devuelve true si el NIA existe en la lista
            return listaNia.contains(nia);
        } else {
            // Devuelve true si el NIA NO existe en la lista
            return !listaNia.contains(nia);
        }
    }

}
