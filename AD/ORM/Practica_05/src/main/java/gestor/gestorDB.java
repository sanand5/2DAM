package gestor;

import org.hibernate.*;

public class gestorDB {
    protected void addDB(Object obj) {
        Session session = Conexion.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
    }
}
