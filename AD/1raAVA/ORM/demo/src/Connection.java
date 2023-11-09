import org.hibernate.Configuration;
import org.hibernate.SessionFactory;
public class Connection {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configurations().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Error al crear sessioNFactoory " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
