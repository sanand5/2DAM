/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    //test
    private void dropMatricula(int matrID) {
        String query = String.format("DELETE FROM matriculas WHERE MAT_ID = %d", matrID);
        super.executeUpdate(query);
    }
    
    //Test
    public void modificarNotas() {
        int matrID = pedirID();
        double[] notas = getNotas(matrID);
        System.out.println("Estas son las notas de el alumno: ");
        for (int i = 0; i < notas.length; i++) {
            System.out.println(i + 1+". " + notas[i]);
        }
        int iterator = rc.pedirIntRango("Dime el numero de notas que quieres modificar: ", 1, notas.length);
        HashMap<Integer, Double> notasUpdate = new HashMap<>();
        for (int i = 0; i < iterator; i++) {
            System.out.println("Posicion de la nota ha modificar");
            System.out.print(">");
            int pos = rc.pedirIntRango(">", 1, notas.length);
            System.out.println("Nota ha modificar");
            double nota = rc.pedirDoubleRango(">", 0, 10);
            notasUpdate.put(pos-1, nota);
        }
        
        modNotas(matrID, notasUpdate);
    }
    
    //Test
    private int pedirID() {
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
        String query = String.format("UPDATE matriculas SET MAT_NOTAS = \"%s\" WHERE MAT_ID = %d", result, matrID);
        super.executeUpdate(query);
        
    }
    
    private double[] getNotas(int matrID) {
        String notasActuales[] = getNotasString(matrID).split("#");
        double[] notas = new double[notasActuales.length];
        for (int i = 0; i < notasActuales.length; i++) {
            notas[i] = Double.parseDouble(notasActuales[i]);
        }
        return notas;
    }
    
    private String getNotasString(int matrID) {
        String notas = super.select("MAT_NOTAS", "matriculas", "MAT_ID = ?", String.class, matrID);
        Colors.debMsg(notas);
        return notas;
    }
    
    private void addNotas(int matrID, double... nota) {
        String notasActuales = getNotasString(matrID);
        String notas = createNotas(nota);
        if (notasActuales != null) {
            if (!"".equals(notasActuales.trim())) {
                notas = notasActuales + "#" + notas;
            }
        }
        String query = String.format("UPDATE matriculas SET MAT_NOTAS = \"%s\" WHERE MAT_ID = %d", notas, matrID);
        super.executeUpdate(query);
    }

    private String createNotas(double... notas) {
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
        Integer sel = super.select("MAT_ID", "matriculas", "MAT_MOD_ID = ? AND MAT_ALM_ID = ?", Integer.class, moduloID, alumnoID);
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }

    private void insertMatricula(int alumnoID, int moduloID, String notas) {
        String query = String.format("INSERT INTO matriculas(MAT_ALM_ID, MAT_MOD_ID, MAT_NOTAS) VALUES (%d,%d,\"%s\")",
                alumnoID,
                moduloID,
                notas);
        super.executeUpdate(query);
    }
    
    public void createTable() {
        // Comprobar si la tabla ya existe antes de intentar crearla
        if (!tableExists("matriculas")) {
            // Primera instrucción: CREATE TABLE
            String createTableQuery = """
            CREATE TABLE `matriculas` (
              `MAT_ID` int NOT NULL,
              `MAT_ALM_ID` int NOT NULL,
              `MAT_MOD_ID` int NOT NULL,
              `MAT_NOTAS` text COLLATE utf8mb4_general_ci
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
            """;

            // Segunda instrucción: ALTER TABLE
            String alterTableQuery = """
            ALTER TABLE `matriculas`
                  ADD PRIMARY KEY (`MAT_ID`),
                  ADD KEY `MAT_ALM_ID` (`MAT_ALM_ID`),
                  ADD KEY `MAT_MOD_ID` (`MAT_MOD_ID`);
            """;

            // Ejecutar las instrucciones por separado
            super.executeUpdate(createTableQuery);
            super.executeUpdate(alterTableQuery);
        } else {
            System.out.println("La tabla 'modulos' ya existe.");
        }
    }

// Método para verificar si una tabla existe en la base de datos
    private boolean tableExists(String tableName) {
        String query = String.format("SHOW TABLES LIKE '%s'", tableName);
        try (ResultSet resultSet = super.executeSelect(query)) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void exportTable() {
        String query = "SELECT `MAT_ID`, `MAT_ALM_ID`, `MAT_MOD_ID`, `MAT_NOTAS` FROM `matriculas`;";
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
            if ( id != -1){
                //"UPDATE `matriculas` SET `MAT_ALM_ID`= %d,`MAT_MOD_ID`= %d,`MAT_NOTAS`= '%s' WHERE `MAT_ID` = " + id
                query = String.format("UPDATE `matriculas` SET `MAT_ALM_ID`= %d,`MAT_MOD_ID`= %d,`MAT_NOTAS`= '%s' WHERE `MAT_ID` = " + id, Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), datos[3]);
            }else{
                // INSERT INTO `matriculas`(`MAT_ID`, `MAT_ALM_ID`, `MAT_MOD_ID`, `MAT_NOTAS`) VALUES (%d,%d,%d,'%s')
                query = String.format("INSERT INTO `matriculas`(`MAT_ID`, `MAT_ALM_ID`, `MAT_MOD_ID`, `MAT_NOTAS`) VALUES (%d,%d,%d,'%s')",Integer.valueOf(datos[0]), Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), datos[3]);
            }
            super.executeUpdate(query);
                
        }
    }
}
