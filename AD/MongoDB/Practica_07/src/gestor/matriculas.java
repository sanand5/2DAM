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
    public final static String collection = "matriculas";
    public final static String path = "res/matriculas.json";
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
            ObjectId id = super.getAtribute(collection, filtro, "_id", ObjectId.class);
            return id;
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
            return null;
        }
    }

    public ObjectId[] obtenerIds(boolean exist) {
        do {
            ObjectId alumno = al.getID(al.pedirNIA(true));
            if (alumno != null) {
                ObjectId modulo = mod.getID(mod.pedirNombre(true));
                if (modulo != null) {
                    ObjectId id = encontrarIDconIDs(alumno, modulo);
                    if ((exist && id != null) || (!exist && id == null)) {
                        return new ObjectId[] { id, alumno, modulo };
                    } else {
                        Colors.errMsg(exist ? "La matrícula no existe. Introduce otros datos."
                                : "La matrícula ya existe. Introduce otros datos.");
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
        try {
            while (true) {
                ObjectId[] ids = obtenerIds(true);
                ObjectId matrID = ids[0];
                if (ids[1] != null && ids[2] != null) {
                    if (matrID == null) {
                        insertMatricula(ids[1], ids[2], null);
                        Colors.okMsg("Se ha matriculado al alumno");
                        break;
                    } else {
                        Colors.errMsg("El alumno no esta matriculado en este modulo.");
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }

    }

    public void eliminarMatricula() {
        try {
            while (true) {
                ObjectId[] ids = obtenerIds(true);
                ObjectId matrID = ids[0];
                if (ids[1] != null && ids[2] != null) {
                    if (matrID != null) {
                        deleteMatricula(matrID);
                        break;
                    } else {
                        Colors.errMsg("El alumno no esta matriculado en este modulo.");
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public Double[] notasDouble(String notasString) {
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

    private Double[] getNotas(ObjectId matrID) {
        String notasString = getAtribute(collection, new Document("_id", matrID), "notas", String.class);
        return notasDouble(notasString);
    }

    public void modificarNotas() {
        try {
            while (true) {

                ObjectId[] ids = obtenerIds(true);
                ObjectId matrID = ids[0];
                if (ids[1] != null && ids[2] != null) {
                    if (matrID != null) {
                        String notas = modNotas(matrID);
                        Document filtro = new Document("_id", matrID);
                        Document update = new Document("notas", notas);
                        super.updateDocumento(collection, filtro, update);
                        Colors.okMsg("Se han modificado las notas del alumno");
                        break;
                    } else {
                        Colors.errMsg("El alumno no esta matriculado en este modulo.");
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public String notasToString(List<Double> notasArrayList) {
        StringBuilder result = new StringBuilder();
        for (Double nota : notasArrayList) {
            result.append(nota).append("#");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public String modNotas(ObjectId matrID) {
        Double[] notas = getNotas(matrID);
        ArrayList<Double> notasArrayList = new ArrayList<>(Arrays.asList(notas));

        while (true) {
            System.out.println("\t0.- Salir");
            mostrarNotas(notasArrayList.toArray(Double[]::new));
            int posAñadir = notas.length + 1;
            System.out.println("\t" + posAñadir + ".- Para añadir");
            int pos = rc.pedirIntRango("Que nota quieres modificar: ", 0, posAñadir);
            if (pos == 0) {
                break;
            }
            double nota = rc.pedirDoubleRango("Nota: ", 0, 10);
            if (pos == posAñadir) {
                notasArrayList.add(nota);
            } else {
                notasArrayList.set(pos-1, nota);
            }
        }
        return notasToString(notasArrayList);
    }

    public void mostrarFormato(ObjectId alumnoid, ObjectId moduloid, Double[] notas) {
        String alumno = super.getAtribute(alumnos.collection, new Document("_id", alumnoid), "nia", String.class)
                + " : ";
        alumno += super.getAtribute(alumnos.collection, new Document("_id", alumnoid), "nombre", String.class) + " ";
        alumno += super.getAtribute(alumnos.collection, new Document("_id", alumnoid), "apellidos", String.class);
        String modulo = super.getAtribute(modulos.collection, new Document("_id", moduloid), "nombre", String.class);
        System.out.println(alumno + " - " + modulo);
        mostrarNotas(notas);
    }

    public void mostrarNotasModulo() {
        try {
            ObjectId[] ids = obtenerIds(true);
            Double[] notas = getNotas(ids[0]);
            mostrarFormato(ids[1], ids[2], notas);
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public void mostrarAlumno() {
        try {
            String nia = al.pedirNIA(true);
            if (nia != null) {
                ObjectId idAlumno = al.getID(nia);
                FindIterable<Document> fr = super.realizarConsultaMongoDB(collection,
                        new Document("idAlumno", idAlumno));
                if (fr != null) {
                    for (Document doc : fr) {
                        ObjectId alumnoid = doc.getObjectId("idAlumno");
                        ObjectId moduloid = doc.getObjectId("idmodulo");
                        String notasString = doc.getString("notas");
                        Double[] notas = notasDouble(notasString);
                        mostrarFormato(alumnoid, moduloid, notas);
                    }
                }
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public void mostrarCentro() {
        try {
            FindIterable<Document> fr = super.realizarConsultaMongoDB(collection, new Document());
            for (Document doc : fr) {
                ObjectId alumnoid = doc.getObjectId("idAlumno");
                ObjectId moduloid = doc.getObjectId("idmodulo");
                String notasString = doc.getString("notas");
                Double[] notas = notasDouble(notasString);
                mostrarFormato(alumnoid, moduloid, notas);
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public void mostrarNotas(Double[] notas) {
        for (int i = 0; i < notas.length; i++) {
            System.out.printf("\t%d.- %.2f%n", i + 1, notas[i]);
        }
    }

}
