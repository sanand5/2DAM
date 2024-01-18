import com.mongodb.client.MongoClient;
import gestor.Gestor;
import gestor.alumnos;

public class App {

    public static void main(String[] args) {
        Gestor gs = new Gestor();
        alumnos al = new alumnos();
        al.baja();
        //gs.fun();
    }
}
