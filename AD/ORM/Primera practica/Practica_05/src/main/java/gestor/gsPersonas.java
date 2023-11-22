package gestor;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import curso.Persona;

public class gsPersonas {
    private void addDB(Persona persona) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Persona.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(persona);
        session.getTransaction().commit();
        session.close();
    }
}
