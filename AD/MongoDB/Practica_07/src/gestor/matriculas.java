package gestor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;

import utilidades.Colors;
import utilidades.ReadClient;

public class matriculas extends Gestor {
    final static String collection = "matriculas";
    ReadClient rc = new ReadClient();
    alumnos al = new alumnos();
    modulos mod = new modulos();

    public void insertMatricula(ObjectId idAlumno, ObjectId idmodulo, String notas) {
        Document matricula = new Document()
                .append("_id", new ObjectId())
                .append("idAlumno", idAlumno)
                .append("idmodulo", idmodulo)
                .append("notas", notas);
        super.insertarDocumento(collection, matricula);
    }

    public void deleteMatricula(ObjectId idMatricula) {
        super.eliminarDocumento(collection, new Document("_id", idMatricula));
    }

    public ObjectId encontrarIDconIDs(ObjectId alumnoID, ObjectId moduloID) {
        try {
            Document filtro = new Document("idAlumno", alumnoID).append("idmodulo", moduloID);
            ObjectId id = super.getAtribute(collection, filtro, collection, ObjectId.class)
            return id;
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
            return null;
        }
    }

    public ObjectId[] obtenerIds(boolean exist) {
        do {
            ObjectId alumno = al.getID(al.pedirNIA(exist));

            if (alumno != null) {
                ObjectId modulo = mod.getID(mod.pedirNombre(exist));

                if (modulo != null) {
                    ObjectId id = encontrarIDconIDs(alumno, modulo);

                    if ((exist && id == null) || (!exist && id != null)) {
                        return new ObjectId[] { id, alumno, modulo };
                    } else {
                        Colors.errMsg(exist ? "La matrícula ya existe. Introduce otros datos."
                                : "La matrícula no existe. Introduce otros datos.");
                    }
                } else {
                    Colors.warMsg("Se ha cancelado la operación de módulo");
                    return new ObjectId[] { null, null, null };
                }
            } else {
                Colors.warMsg("Se ha cancelado la operación de matrícula");
                return new ObjectId[] { null, null, null };
            }
        } while (true);
    }

    public void crearMatricula() {
        ObjectId[] ids = obtenerIds(true);
        if (ids[1] != null && ids[2] != null) {
            insertMatricula(ids[1], ids[2], null);
        }
    }

    public void eliminarMatricula() {
        ObjectId id = obtenerIds(true)[0];
        if (id != null) {
            deleteMatricula(id);
        }
    }

    private Double[] getNotas(ObjectId matrID) {
        String notasString = getAtribute(collection, new Document("_id", matrID), "notas", String.class);
        if (notasString != null && !"null".equals(notasString) && !"".equals(notasString)) {
            String notasActuales[] = notasString.split("#");
            Double[] notas = new Double[notasActuales.length];

            for (int i = 0; i < notasActuales.length; i++) {
                try {
                    notas[i] = Double.parseDouble(notasActuales[i]);
                } catch (NumberFormatException e) {
                    Colors.errMsg("Error de formato en : " + notasActuales[i]);
                }

            }
            return notas;
        } else {
            return new Double[0];
        }
    }

    public void modificarNotas() {
        ObjectId[] ids = obtenerIds(true);
        ObjectId matrID = ids[0];
        String notas = modNotas(matrID);
        Document filtro = new Document("_id", matrID);
        Document update = new Document("$set", new Document("notas", notas));
        super.updateDocumento(collection, filtro, null);
        
    }

    public String notasToString(List<Double> notasArrayList) {
        StringBuilder result = new StringBuilder();
        for (Double nota : notasArrayList) {
            result.append(nota).append("#");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String modNotas(ObjectId matrID) {
        Double[] notas = getNotas(matrID);
        List<Double> notasArrayList = Arrays.asList(notas);

        while (true) {
            System.out.println("\t0.- Salir");
            mostrarNotas(notas);
            int posAñadir = notas.length + 1;
            System.out.println("\t" + posAñadir + ".- Para añadir");
            int pos = rc.pedirIntRango("Que nota quieres modificar: ", 0, notas.length);
            double nota = rc.pedirDoublePositivo("Nota: ");
            if (pos == 0) {
                break;
            }
            if (pos == posAñadir) {
                notasArrayList.add(nota);
            } else {
                notasArrayList.set(pos, nota);
            }
        }
        return notasToString(notasArrayList);
    }

    public void mostrarNotas(Double[] notas) {
        for (int i = 0; i < notas.length; i++) {
            System.out.printf("\t%d.- %d%n", i + 1, notas[i]);
        }
    }

}
