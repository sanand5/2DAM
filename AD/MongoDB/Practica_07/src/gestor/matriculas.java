package gestor;

import org.bson.Document;
import org.bson.types.ObjectId;

import utilidades.Colors;

public class matriculas extends Gestor {
    final String collection = "matriculas";

    public void insertMatricula(String idAlumno, String idmodulo, String notas) {
        Document matricula = new Document()
                .append("_id", new ObjectId())
                .append("idAlumno", idAlumno)
                .append("idmodulo", idmodulo)
                .append("notas", notas);
        super.insertarDocumento(collection, matricula);
    }

    private int encontrarIDconIDs(int alumnoID, int moduloID) {
        Integer sel = super.select("mat_id", "matriculas", "mat_mod_id = ? AND mat_alm_id = ?", Integer.class, moduloID, alumnoID);
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }

    public void crearMatricula() {
        String alumno = gsa.encontrarID(gsa.pedirNia(true));
        String modulo = gsm.pedirIDconNombre();
        if (encontrarIDconIDs(alumno, modulo) == -1) {
            insertMatricula(alumno, modulo, null);
        } else {
            Colors.errMsg("La matricula ya existe");
        }
    }
}
