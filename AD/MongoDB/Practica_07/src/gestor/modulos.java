package gestor;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;

import utilidades.Colors;
import utilidades.ReadClient;


public class modulos extends Gestor {
    final static String collection = "modulos";
    ReadClient rc = new ReadClient();

    public void insertModulo(String nombre) {
        Document modulo = new Document()
                .append("_id", new ObjectId())
                .append("nombre", nombre);
        super.insertarDocumento(collection, modulo);
    }

    public void deleteModulo(String nombre) {
        super.eliminarDocumento(collection, new Document("nombre", nombre));
    }

    public void alta() {
        String nombre = pedirNombre(false);
        String opcion = rc.pedirOpcion("Quieres dar de alta al modulo", "s", "n");
        if (opcion.equals("s")) {
            insertModulo(nombre);
        } else {
            Colors.warMsg("Se ha cancelado la operación");
        }

    }
    
    public void baja() {
        String nombre = pedirNombre(true);
        if (!nombre.equals("0")) {
            String opcion = rc.pedirOpcion("Seguro que quieres elilminar el modulo", "s", "n");
            if (opcion.equals("s")) {
                deleteModulo(nombre);
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        }
    }

    public ObjectId getID(String nombre) {
        return super.getID("nombre", nombre, collection);
    }

    public boolean comprobarModulo(String nombre) {
        return getID(nombre) != null;
    }

    protected String pedirNombre(boolean exist) {
        String nombre;
        String cancel = exist ? ", 0 para salir" : "";
        String errMsg = exist ? "El modulo no existe." : "El modulo ya existe.";

        do {
            nombre = rc.pedirString("Nombre modulo" + cancel + ": ", false);
            if (nombre.equals("0") && exist == true) {
                break;
            }
            if ((exist && !comprobarModulo(nombre)) || (!exist && comprobarModulo(nombre))) {
                Colors.errMsg(errMsg);
            }

        } while ((exist && !comprobarModulo(nombre)) || (!exist && comprobarModulo(nombre)));
        return nombre;
    }

    public void mostrarModulos() {
        FindIterable<Document> fr = realizarConsultaMongoDB(collection, new Document());
        int cont = 0;
        for (Document doc : fr) {
            cont++;
            String nombre = doc.getString("nombre");
            System.out.printf("%d.- %s%n", cont, nombre);
        }
    }
    
    
}
