/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import practica_06.utilidades.Colors;
import practica_06.utilidades.ReadClient;

/**
 *
 * @author andre
 */
public class gsMatriculas extends gestor {

    ReadClient rc = new ReadClient();
    gsAlumnos gsa = new gsAlumnos();
    gsModulo gsm = new gsModulo();
    private final String MATRICULASPATH = "./res/matriculas.txt";

    public void crearMatricula() {
        int alumno = gsa.encontrarID(gsa.pedirNia(true));
        int modulo = gsm.pedirIDconNombre();
        if (encontrarIDconIDs(alumno, modulo) == -1) {
            insertMatricula(alumno, modulo, null);
        } else {
            Colors.errMsg("La matricula ya existe");
        }

    }

    public void eliminarMatricula() {
        int id = pedirID();
        if (id != -1) {
            dropMatricula(id);
            Colors.okMsg("Matricula eliminada correctamente");
        }
    }

    public void modificarNotas() {
        int matrID = pedirID();
        if (matrID != -1) {
            int modID = select("mat_mod_id", "matriculas", "mat_id = ?", Integer.class, matrID);
            String modName = select("mod_name", "modulos", "mod_id = ?", String.class, modID);
            mostrarNotas(matrID, modName);
            int notasLenth = getNotas(matrID).length;
            if (!(notasLenth <= 0)) {
                int iterator = rc.pedirIntRango("Dime el numero de notas que quieres modificar: ", 1, notasLenth);
                HashMap<Integer, Double> notasUpdate = new HashMap<>();
                for (int i = 0; i < iterator; i++) {
                    int pos = rc.pedirIntRango("Posicion de la nota ha modificar: ", 1, notasLenth);
                    double nota = rc.pedirDoubleRango("Nota ha modificar: ", 0, 10);
                    notasUpdate.put(pos - 1, nota);
                }
                modNotas(matrID, notasUpdate);
                Colors.okMsg("Notas actualizadas correctamente");
            }
        }
    }

    public void mostrarModuloAlumno() {
        int idAlm = gsa.getIDConNIA();
        if (idAlm != 0) {
            int idMod = gsm.pedirIDconNombre();
            if (idMod != -1) {
                String modname = select("mod_name", "modulos", "mod_id = ?", String.class, idMod);
                mostrarNotas(idMod, modname);
            }
        } else {
            Colors.warMsg("Operación cancelada por el usuario.");
        }
    }

    public void mostrarModulosAlumno(int almID) {
        ResultSet rs = executeSelect("SELECT mat_id, mat_alm_id, mat_mod_id FROM matriculas WHERE mat_alm_id = ?", almID);
        try {
            while (rs.next()) {
                int idAlmuno = rs.getInt("mat_alm_id");
                int idModulo = rs.getInt("mat_mod_id");
                String nameModulo = select("mod_name", "modulos", "mod_id = ?", String.class, idModulo);
                mostrarNotas(rs.getInt("mat_id"), nameModulo);

            }
        } catch (SQLException e) {
            Colors.errMsg("Imposible mostrar las notas.");
        }
    }

    public void mostrarCentro() {
        ResultSet rs = executeSelect("SELECT mat_alm_id FROM matriculas");
        HashSet<Integer> matAlmIdProcesados = new HashSet<>();
        String almName = "", almSurnames = "";
        int almNia = 0;
        try {
            while (rs.next()) {
                matAlmIdProcesados.add(rs.getInt("mat_alm_id"));
            }
            System.out.println(super.getTitulo("matriculas"));
            for (Integer matAlmIdProcesado : matAlmIdProcesados) {
                ResultSet rsa = executeSelect("SELECT alm_name, alm_surnames, alm_nia FROM alumnos WHERE alm_id = ?", matAlmIdProcesado);
                while (rsa.next()) {
                    almName = rsa.getString("alm_name");
                    almSurnames = rsa.getString("alm_surnames");
                    almNia = rsa.getInt("alm_nia");
                }
                System.out.println("");
                System.out.println(almName.toUpperCase() + " " + almSurnames.toUpperCase() + " : " + almNia);
                mostrarModulosAlumno(matAlmIdProcesado);
            }
        } catch (SQLException e) {
            Colors.errMsg("Imposible mostrar las notas.");
        }
    }

    public void createTable() {
        String tableName = "matriculas";
        String queryMySQL = """
                CREATE TABLE matriculas (
                  mat_id int NOT NULL AUTO_INCREMENT,
                  mat_alm_id int NOT NULL,
                  mat_mod_id int NOT NULL,
                  mat_notas text COLLATE utf8mb4_general_ci,
                  PRIMARY KEY (mat_id),
                  KEY mat_alm_id (mat_alm_id),
                  KEY mat_mod_id (mat_mod_id)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
                """;
        String queryPostgresSQL = """
                CREATE TABLE matriculas (
                  mat_id serial PRIMARY KEY,
                  mat_alm_id int NOT NULL,
                  mat_mod_id int NOT NULL,
                  mat_notas text
                );
                """;
        super.createTable(tableName, queryMySQL, queryPostgresSQL);
    }

    public void exportTable() {
        String query = "SELECT mat_id, mat_alm_id, mat_mod_id, mat_notas FROM matriculas;";
        ResultSet rs = super.executeSelect(query);
        String datos = "";
        try {
            while (rs.next()) {
                int id, idAlumno, idModulo;
                String notas;
                id = rs.getInt(1);
                idAlumno = rs.getInt(2);
                idModulo = rs.getInt(3);
                notas = rs.getString(4);
                datos += String.format("%d;%d;%d;%s%n", id, idAlumno, idModulo, notas);
            }
            super.write(MATRICULASPATH, datos);
        } catch (SQLException e) {
            Colors.errMsg("No se ha podido exportar la tabla de matriculas correctamente");
        }
    }

    public void importTable() {
        ArrayList<String> filas = super.read(MATRICULASPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarIDconIDs(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
            if (id != -1) {
                query = String.format("UPDATE matriculas SET mat_alm_id= %d,mat_mod_id= %d,mat_notas= '%s' WHERE mat_id = " + id, Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), datos[3]);
            } else {
                query = String.format("INSERT INTO matriculas(mat_id, mat_alm_id, mat_mod_id, mat_notas) VALUES (%d,%d,%d,'%s')", Integer.valueOf(datos[0]), Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), datos[3]);
            }
            super.executeUpdate(query);
        }
        Colors.okMsg("Los datos de %s se han importado correctamente" + MATRICULASPATH);
    }

    public void qualificar() {
        int idAlm = gsa.getIDConNIA();
        int idMod = gsm.pedirIDconNombre();
        int id = encontrarIDconIDs(idAlm, idMod);
        if (id == -1) {
            Colors.warMsg("El alumno no esta matriculado en ese modulo, desea matricular-lo directamente (y/n)");
            String opcion = rc.pedirOpcion("El alumno no esta matriculado en ese modulo, desea matricular-lo directamente", "y", "n");
            if (opcion.equals("n")) {
                System.out.println("No se ha podido qualificar al alumno");
                return;
            }
            insertMatricula(idAlm, idMod, "");
            id = encontrarIDconIDs(idAlm, idMod);

        }
        int cantNotas = rc.pedirIntPositivo("Dime la cantidad de notas ha añadir: ");
        double notas[] = new double[cantNotas];
        for (int i = 0; i < cantNotas; i++) {
            notas[i] = rc.pedirDoubleRango("Nota ha añadir: ", 0, 10);
        }
        addNotas(id, notas);
        Colors.okMsg("Notas actualizadas correctamente");
    }

    private void mostrarNotas(int matrID, String nombreModulo) {
        double[] notas = getNotas(matrID);
        System.out.println(nombreModulo + ": ");
        if (notas.length == 0) {
            Colors.warMsg("\tEl alumno aun no tiene notas en este modulo.");
        }
        for (int i = 0; i < notas.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + notas[i]);
        }
    }

    private int pedirID() {
        int nia = 0, modulo, id = -1;
        boolean repit = true;
        while (repit) {
            if (nia == 0) {
                nia = gsa.getIDConNIA();
            }
            if (nia == 0) {
                repit = false;
            } else {
                modulo = gsm.pedirIDconNombre();
                if (modulo == -1) {
                    repit = false;
                } else {
                    id = encontrarIDconIDs(nia, modulo);
                    if (id == -1) {
                        Colors.errMsg("El alumno no está matriculado en ese módulo");
                    } else {
                        repit = false;
                    }
                }
            }
        }
        return id;
    }

    private void modNotas(int matrID, HashMap<Integer, Double> notasUpdate) {
        String notasActuales[] = getNotasString(matrID).split("#");
        for (Map.Entry<Integer, Double> entry : notasUpdate.entrySet()) {
            int posicion = entry.getKey();
            double nuevaNota = entry.getValue();
            if (posicion >= 0 && posicion < notasActuales.length) {
                notasActuales[posicion] = String.valueOf(nuevaNota);
            } else {
                Colors.debMsg("Error: Posición fuera de rango - " + posicion);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String nota : notasActuales) {
            result.append(nota).append("#");
        }
        result.deleteCharAt(result.length() - 1);
        String query = String.format("UPDATE matriculas SET mat_notas = '%s' WHERE mat_id = %d", result, matrID);
        super.executeUpdate(query);

    }

    private double[] getNotas(int matrID) {
        String notasString = getNotasString(matrID);
        if (notasString != null && !"null".equals(notasString) && !"".equals(notasString)) {
            String notasActuales[] = notasString.split("#");
            double[] notas = new double[notasActuales.length];

            for (int i = 0; i < notasActuales.length; i++) {
                try {
                    notas[i] = Double.parseDouble(notasActuales[i]);
                } catch (NumberFormatException e) {
                    Colors.errMsg("Error de formato en : " + notasActuales[i]);
                }

            }
            return notas;
        } else {
            return new double[0];
        }

    }

    private String getNotasString(int matrID) {
        String notas = super.select("mat_notas", "matriculas", "mat_id = ?", String.class, matrID);
        if("null".equals(notas) || notas == null){
            notas = "";
        }
        return notas;
    }

    private void addNotas(int matrID, double... nota) {
        String notasActuales = getNotasString(matrID);
        String notas = createNotasString(nota);
        if (notasActuales != null) {
            if (!"".equals(notasActuales.trim())) {
                notas = notasActuales + "#" + notas;
            }
        }
        String query = String.format("UPDATE matriculas SET mat_notas = '%s' WHERE mat_id = %d", notas, matrID);
        super.executeUpdate(query);
    }

    private String createNotasString(double... notas) {
        if (notas.length == 0) {
            Colors.warMsg("No se proporcionaron notas para agregar.");
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (double nota : notas) {
            result.append(nota).append("#");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private int encontrarIDconIDs(int alumnoID, int moduloID) {
        Integer sel = super.select("mat_id", "matriculas", "mat_mod_id = ? AND mat_alm_id = ?", Integer.class, moduloID, alumnoID);
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        
        return res;
    }

    private void insertMatricula(int alumnoID, int moduloID, String notas) {
        String query = String.format("INSERT INTO matriculas(mat_alm_id, mat_mod_id, mat_notas) VALUES (%d,%d,'%s')",
                alumnoID,
                moduloID,
                notas);
        super.executeUpdate(query);
        Colors.okMsg("Matricula creada correctamente");
    }

    private void dropMatricula(int matrID) {
        String query = String.format("DELETE FROM matriculas WHERE mat_id = %d", matrID);
        super.executeUpdate(query);
    }
}
