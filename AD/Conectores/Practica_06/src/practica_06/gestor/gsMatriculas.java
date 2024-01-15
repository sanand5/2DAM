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
import practica_06.gestor.Conexion.DatabaseType;
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
            insertMatricula(alumno, modulo, "");
        } else {
            Colors.errMsg("La matricula ya existe");
        }

    }

    //test
    public void eliminarMatricula() {
        int id = pedirID();
        dropMatricula(id);
    }

    //Test
    public void modificarNotas() {
        int matrID = pedirID();
        int modID = select("mat_mod_id", "matriculas", "mat_id = ?", Integer.class, matrID);
        String modName = select("mod_name", "modulos", "mod_id = ?", String.class, modID);
        mostrarNotas(matrID, modName);
        int notasLenth = getNotas(matrID).length;
        int iterator = rc.pedirIntRango("Dime el numero de notas que quieres modificar: ", 1, notasLenth);
        HashMap<Integer, Double> notasUpdate = new HashMap<>();
        for (int i = 0; i < iterator; i++) {
            int pos = rc.pedirIntRango("Posicion de la nota ha modificar: ", 1, notasLenth);
            double nota = rc.pedirDoubleRango("Nota ha modificar: ", 0, 10);
            notasUpdate.put(pos - 1, nota);
        }

        modNotas(matrID, notasUpdate);
    }

    public void mostrarNotas(int matrID, String nombreModulo) {
        double[] notas = getNotas(matrID);
        System.out.println(nombreModulo + ": ");
        if (notas.length == 0) {
            Colors.warMsg("\tEl alumno aun no tiene notas en este modulo.");
        }
        for (int i = 0; i < notas.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + notas[i]);
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
        }else {
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
            Colors.errMsg(e.getMessage());
        }
    }

    public void mostrarCentro() {
        ResultSet rs = executeSelect("SELECT mat_alm_id FROM matriculas");
        HashSet<Integer> matAlmIdProcesados = new HashSet<>();
        String almName = "", almSurnames = "";
        try {
            while (rs.next()) {
                matAlmIdProcesados.add(rs.getInt("mat_alm_id"));
            }
            for (Integer matAlmIdProcesado : matAlmIdProcesados) {
                ResultSet rsa = executeSelect("SELECT alm_name, alm_surnames FROM alumnos WHERE alm_id = ?", matAlmIdProcesado);
                while (rsa.next()) {
                    almName = rsa.getString("alm_name");
                    almSurnames = rsa.getString("alm_surnames");
                }
                System.out.println(almName.toUpperCase() + " " + almSurnames.toUpperCase() + " - " + matAlmIdProcesado);
                mostrarModulosAlumno(matAlmIdProcesado);
            }
        } catch (SQLException e) {
        }
    }

    public void createTable() {
        DatabaseType databaseType = Conexion.getDataBaseType();
        String tableName = "matriculas";
        if (!super.tableExists(tableName)) {
            // Primera instrucción: CREATE TABLE
            String createTableQuery = "";

            if (databaseType == DatabaseType.MYSQL) {
                createTableQuery = """
                CREATE TABLE matriculas (
                  mat_id int NOT NULL AUTO_INCREMENT,
                  mat_alm_id int NOT NULL,
                  mat_mod_id int NOT NULL,
                  mat_notas text COLLATE utf8mb4_general_ci,
                  PRIMARY KEY (mat_id),
                  KEY mat_alm_id (mat_alm_id),
                  KEY mat_mod_id (mat_mod_id)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
                """.formatted(tableName);
            } else if (databaseType == DatabaseType.POSTGRESQL) {
                createTableQuery = """
                CREATE TABLE %s (
                  mat_id serial PRIMARY KEY,
                  mat_alm_id int NOT NULL,
                  mat_mod_id int NOT NULL,
                  mat_notas text
                );
                """.formatted(tableName);
            }
            super.executeUpdate(createTableQuery);
        } else {
            System.out.println("La tabla '" + tableName + "' ya existe.");
        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importTable() {
        ArrayList<String> filas = super.read(MATRICULASPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarIDconIDs(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
            if (id != -1) {
                //"UPDATE matriculas SET mat_alm_id= %d,mat_mod_id= %d,mat_notas= '%s' WHERE mat_id = " + id
                query = String.format("UPDATE matriculas SET mat_alm_id= %d,mat_mod_id= %d,mat_notas= '%s' WHERE mat_id = " + id, Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), datos[3]);
            } else {
                // INSERT INTO matriculas(mat_id, mat_alm_id, mat_mod_id, mat_notas) VALUES (%d,%d,%d,'%s')
                query = String.format("INSERT INTO matriculas(mat_id, mat_alm_id, mat_mod_id, mat_notas) VALUES (%d,%d,%d,'%s')", Integer.valueOf(datos[0]), Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), datos[3]);
            }
            super.executeUpdate(query);

        }
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
    }

    protected boolean tableExists(String tableName) {
        String query = String.format("SHOW TABLES LIKE '%s'", tableName);
        try (ResultSet resultSet = super.executeSelect(query)) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Test
    public int pedirID() {
        int nia = gsa.getIDConNIA();
        int modulo = gsm.pedirIDconNombre();
        return encontrarIDconIDs(nia, modulo);
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
        if (notasString != null && !"null".equals(notasString)) {
            String notasActuales[] = notasString.split("#");
            double[] notas = new double[notasActuales.length];
            for (int i = 0; i < notasActuales.length; i++) {
                notas[i] = Double.parseDouble(notasActuales[i]);
            }
            return notas;
        } else {
            return new double[0];
        }

    }

    private String getNotasString(int matrID) {
        String notas = super.select("mat_notas", "matriculas", "mat_id = ?", String.class, matrID);
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
    }

    //test
    private void dropMatricula(int matrID) {
        String query = String.format("DELETE FROM matriculas WHERE mat_id = %d", matrID);
        super.executeUpdate(query);
    }
}
