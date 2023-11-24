package gestor;

import java.util.List;

import curso.Alumno;
import curso.Matricula;
import curso.Modulo;
import utilidades.Colors;
import utilidades.ReadClient;

import org.hibernate.query.Query;
import org.hibernate.*;

public class gsModulos extends gestorDB {
    ReadClient rc = new ReadClient();
    

    public void alta() {
        String nombre = rc.pedirString("Dime el nombre de el nuevo modulo: ", false);
        Modulo m = new Modulo(nombre);
        super.addDB(m);
        Colors.okMsg("Módulo registrado correctamente.");
    }

    public void baja() {
        int id = pedirId();
        if (id != 0) {
            eliminar(id);
            Colors.okMsg("Módulo eliminado correctamente");
        } else {
            Colors.warMsg("Se ha cancelado la opción");
        }
    }

    public void matricular() {
        gsAlumnos gsAl = new gsAlumnos();
        gsMatriculas gsMatr = new gsMatriculas();
        int nia = gsAl.pedirNia(true);
        if (nia != 0) {
            int id = pedirId();
            Alumno alumno = gsAl.buscarAlmNia(nia);
            List<Modulo> modulos = buscar("id", id);
            if (alumno != null && !modulos.isEmpty()) {
                Matricula matr = gsMatr.buscarMatricula(alumno, modulos.get(0));
                if (matr == null) {
                    matr = new Matricula(alumno, modulos.get(0), null);
                    super.addDB(matr);
                    Colors.okMsg("Alumno matriculado exitosamente.");

                } else {
                     Colors.errMsg("El alumno ya esta registrado");
                }
            }
        }

    }

    public void mostrarModulos() {
        Session session = Conexion.getSessionFactory().openSession();
        Query<Modulo> query = session.createQuery("FROM Modulo", Modulo.class);
        List<Modulo> alumnosList = query.getResultList();
        alumnosList.forEach(it -> {
            System.out.println(it.toString());
        });
        session.close();
    }

    protected int pedirId() {
        int id = rc.pedirIntPositivo("Dime el id de el modulo, 0 para cancelar: ");
        List<Modulo> list = buscar("id", id);
        while (list.isEmpty() && id != 0) {
            Colors.errMsg("No existe ningun modulo con ese ID");
            id = rc.pedirIntPositivo("Dime el id de el modulo, 0 para cancelar: ");
            list = buscar("id", id);
        }
        return id;
    }

    private void eliminar(int id) {

        try {
            List<Modulo> modulos = buscar("id", id);
            if (modulos.isEmpty()) {
                Colors.warMsg("No hay modulos con ese ID.");
            } else {
                Session session = Conexion.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                for (Modulo modulo : modulos) {
                    gsMatriculas gsMatr = new gsMatriculas();
                    gsMatr.delMatriculaIdModulo(id);
                    session.delete(modulo);
                }

                Colors.okMsg("Todas los modulos con ID de Módulo " + id + " han sido eliminadas.");
                transaction.commit();
                session.close();
            }
        } catch (Exception e) {
            Colors.errMsg("Error al eliminar los modulos.");
        }
    }

    private List<Modulo> buscar(String campo, int valor) {
        Session session = Conexion.getSessionFactory().openSession();
        String queryString = String.format("FROM Modulo WHERE %s = :valor", campo);
        Query<Modulo> query = session.createQuery(queryString, Modulo.class);
        query.setParameter("valor", valor);

        List<Modulo> listaEntidades = query.getResultList();
        session.close();
        return listaEntidades;
    }
}
