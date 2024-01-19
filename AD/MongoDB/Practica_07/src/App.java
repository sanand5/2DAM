import com.mongodb.client.MongoDatabase;

import gestor.Conexion;
import gestor.Gestor;
import utilidades.Colors;

public class App {

    public static void main(String[] args) {
        if (Conexion.testConexion()) {
            Gestor.crearTablas();
            menus menus = new menus();
            menus.mainMenu();
        } else {
            System.out.println(Colors.RED_ANSI + "No se ha podido iniciar la conexion, revisa la conexión." + Colors.RESET_ANSI);
        }
    }
}
