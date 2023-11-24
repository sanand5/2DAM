package gestor;

import org.hibernate.*;
import curso.*;
import utilidades.Colors;
import utilidades.ReadClient;

import java.util.List;
import org.hibernate.query.Query;

public class gsMatriculas extends gestorDB {
    ReadClient rc = new ReadClient();
    gsAlumnos gsAl = new gsAlumnos();
    gsModulos gsModulos = new gsModulos();

    public void qualificar() {
        Matricula matr = obtenerMatriculaPorInput();
        if (matr != null) {
            double[] notas = new double[rc.pedirIntPositivo("Numero de notas a añadir: ")];

            for (int i = 0; i < notas.length; i++) {
                notas[i] = rc.pedirDoubleRango("Dime la nota " + (i + 1) + " a añadir: ", 0, 10);
            }
            matr.addNota(notas);
            updateNotas(matr);
            Colors.okMsg("Se han añadido las notas.");
        } else {
            Colors.errMsg("El alumno no esta registrado en ese módulo");
        }
    }

    public void modificar() {
        Matricula matr = obtenerMatriculaPorInput();

        if (matr != null) {
            double[] notas = matr.getNotasList();
            int num = rc.pedirIntRango("Dime el identificador de la nota, 0 para mostrar notas: ", 0, notas.length);

            while (num == 0) {
                matr.mostrarNotas();
                num = rc.pedirIntRango("Dime el identificador de la nota, 0 para mostrar notas: ", 0, notas.length);
            }

            num--;
            notas[num] = rc.pedirDoubleRango("Dime la nota que quieres cambiar: ", 0, 10);
            
            matr.setNotasList(notas);
            updateNotas(matr);
            Colors.okMsg("Se han modificado las notas.");
        } else {
            Colors.errMsg("El alumno no esta registrado en ese módulo");
        }
    }

    public void mostrarNotas() {
        Matricula matr = obtenerMatriculaPorInput();

        if (matr != null) {
            matr.mostrarNotas();
        } else {
            Colors.errMsg("El alumno no esta registrado en ese módulo");
        }
    }

    public void mostrarAlumno() {
        int nia = gsAl.pedirNia(true);
        if (nia != 0) {
            Alumno alm = buscarAlumno(nia).stream().findFirst().orElse(null);

            if (alm != null) {
                List<Matricula> matrList = buscar(Matricula.class, "alumno.nia", nia);
                matrList.forEach(it -> {
                    it.mostrarNotas();
                });
            }
        }
    }

    public void mostrarAll() {
        Session session = Conexion.getSessionFactory().openSession();
        Query<Matricula> query = session.createQuery("FROM Matricula", Matricula.class);
        List<Matricula> alumnosList = query.getResultList();
        alumnosList.forEach(it -> {
            it.mostrarNotas();
        });
        session.close();
    }




    private Matricula obtenerMatriculaPorInput() {
        int nia = gsAl.pedirNia(true);
        int id = gsModulos.pedirId();

        if (nia != 0 && id != 0) {
            
            Alumno alm = buscarAlumno(nia).stream().findFirst().orElse(null);
            Modulo mod = buscarModulo(id).stream().findFirst().orElse(null);

            if (alm != null && mod != null) {
                return buscarMatricula(alm, mod);
            }
        }

        return null;
    }

    private void updateNotas(Matricula matr) {
        try (Session session = Conexion.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();            
            session.update(matr);
            transaction.commit();
        } catch (HibernateException e) {
            Colors.errMsg("No se han podido actualizar las notas");
        }
    }

    protected void delMatriculaPorNia(int nia) {
        delMatricula("alumno.nia", nia, "NIA");
    }

    protected void delMatriculaIdModulo(int id) {
        delMatricula("modulo.id", id, "ID de Módulo");
    }

    private List<Modulo> buscarModulo(int id) {
        return buscar(Modulo.class, "id", id);
    }

    private List<Alumno> buscarAlumno(int nia) {
        return buscar(Alumno.class, "nia", nia);
    }

    protected Matricula buscarMatricula(Alumno alm, Modulo mod) {
        Session session = Conexion.getSessionFactory().openSession();

        Query<Matricula> query = session.createQuery("FROM Matricula WHERE alumno.nia = :nia AND modulo.id = :id",
                Matricula.class);
        query.setParameter("nia", alm.getNia());
        query.setParameter("id", mod.getId());

        Matricula matr = query.uniqueResult();
        session.close();
        return matr;
    }

    private <T> List<T> buscar(Class<T> entidad, String campo, Object valor) {
        Session session = Conexion.getSessionFactory().openSession();
        String queryString = String.format("FROM %s WHERE %s = :valor", entidad.getSimpleName(), campo);

        Query<T> query = session.createQuery(queryString, entidad);
        query.setParameter("valor", valor);

        List<T> listaEntidades = query.getResultList();
        session.close();
        return listaEntidades;
    }

    private void delMatricula(String campo, Object valor, String descripcion) {
        Session session = Conexion.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Matricula> matriculas = buscar(Matricula.class, campo, valor);

            if (matriculas.isEmpty()) {
                Colors.warMsg("No hay matriculas ese campo.");
            } else {
                for (Matricula matricula : matriculas) {
                    session.delete(matricula);
                }

                Colors.okMsg("Todas las matrículas con " + descripcion + " " + valor + " han sido eliminadas.");
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            Colors.errMsg("Error al eliminar las matrículas.");
        } finally {
            session.close();
        }
    }
}
