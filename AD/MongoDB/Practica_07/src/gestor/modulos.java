package gestor;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;

import utilidades.Colors;
import utilidades.ReadClient;


public class modulos extends Gestor {
    public final static String collection = "modulos";
    public final static String path = "res/modulos.json";
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
        try{
            String nombre = pedirNombre(false);
            String opcion = rc.pedirOpcion("Quieres dar de alta al modulo", "s", "n");
            if (opcion.equals("s")) {
                insertModulo(nombre);
                Colors.okMsg("Se a dado de alta el modulo");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }
    
    public void baja() {
        try {
            matriculas matr = new matriculas();
            String nombre = pedirNombre(true);
            if (!nombre.equals("0")) {
                String opcion = rc.pedirOpcion("Seguro que quieres elilminar el modulo", "s", "n");
                if (opcion.equals("s")) {
                    ObjectId id = getID(nombre);
                    FindIterable<Document> fr = super.realizarConsultaMongoDB(matriculas.collection, new Document("idmodulo", id));
                    for (Document doc : fr) {
                        ObjectId matrId = doc.getObjectId("_id");
                        matr.deleteMatricula(matrId);
                    }
                    Colors.okMsg("Se han eliminado todas las matriculas con ese modulo");
                    deleteModulo(nombre);
                    Colors.okMsg("Se a dado de baja el modulo");
                } else {
                    Colors.warMsg("Se ha cancelado la operación");
                }
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public ObjectId getID(String nombre) {
        return super.getID("nombre", nombre, collection);
    }

    public boolean comprobarModulo(String nombre) {
        return getID(nombre) != null;
    }

    public String pedirNombre(boolean exist) {
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
        try {
            FindIterable<Document> fr = realizarConsultaMongoDB(collection, new Document());
            int cont = 0;
            for (Document doc : fr) {
                cont++;
                String nombre = doc.getString("nombre");
                System.out.printf("%d.- %s%n", cont, nombre);
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public void modificar() {
        try {
            System.out.println("Intrduce primero el nombre antiguo");
            String nombre = pedirNombre(true);
            System.out.println("Intrduce el nuevo nombre");
            String nuevo = pedirNombre(false);
            if (nombre != null && nuevo != null) {
                super.updateDocumento(modulos.collection, new Document("nombre", nombre),
                        new Document("nombre", nuevo));
                Colors.okMsg("Se a modificado nombre del modulo");
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }
    
}
