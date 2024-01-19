import com.mongodb.client.MongoClient;
import gestor.Gestor;
import gestor.alumnos;
import gestor.matriculas;
import gestor.modulos;

public class App {

    public static void main(String[] args) {
        Gestor gs = new Gestor();
        alumnos al = new alumnos();
        matriculas mat = new matriculas();
        modulos mod = new modulos();
        //mod.alta();
        mat.crearMatricula();
        //al.alta();
        //al.mostrarAlumnos();
        //gs.fun();
    }
}
