package prueba;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Controller {
   // private static final Conexion conexion = new Conexion();
    public void fun() {
        
        Usuario user = new Usuario("Andreu");
        //Session session = conexion.getSessionFactory().openSession();
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        session.close();
    }
}
