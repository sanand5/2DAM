package gestor;

import org.bson.Document;
import org.bson.types.ObjectId;

public class modulos extends Gestor {
    final String collection = "modulos";

    public void insertModulo(String nombre) {
        Document modulo = new Document()
                .append("_id", new ObjectId())
                .append("nombre", nombre);
        super.insertarDocumento(collection, modulo);
    }

    public void crearModulo() {
        String nombre = rc.pedirString("Nombre de el modulo: ", false);
        insertModulo(nombre);
    }
}
