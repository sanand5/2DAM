package gestor;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import curso.Alumno;

public class gsAlumnos {
    
    public void crearAlumno() {
        new Alumno(0);
    }

    private void addDB(Alumno alumno) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Alumno.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(alumno);
        session.getTransaction().commit();
        session.close();
    }
}
