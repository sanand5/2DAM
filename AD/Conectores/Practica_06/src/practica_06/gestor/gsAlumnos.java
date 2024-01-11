/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import practica_06.utilidades.*;

/**
 *
 * @author andre
 */
public class gsAlumnos extends gestor {

    ReadClient rc = new ReadClient();
    private final String ALUMNOSPATH = "./res/alumnos.txt";

    private void insertAlumno(String name, String surname, String fecha, int nia) {
        String query = String.format("INSERT INTO `alumnos`(`ALM_NAME`, `ALM_SURNAMES`, `ALM_FECHA`, `ALM_NIA`) VALUES ('%s', '%s', '%s', %d)",
                name, surname, fecha, nia);
        super.executeUpdate(query);
    }

    private void dropAlumno(int id) {
        String query = String.format("DELETE FROM matriculas WHERE MAT_ALM_ID = %d", id);
        super.executeUpdate(query);
        query = String.format("DELETE FROM alumnos WHERE ALM_ID = %d", id);
        super.executeUpdate(query);
    }

    public void alta() {
        String nombre = rc.pedirString("Dime el nombre de el alumno: ", false);
        String apellidos = rc.pedirString("Dime los apellidos de el Alumno: ", false);
        int nia = pedirNia(false);
        int dia = rc.pedirIntRango("Dime el dia que nació: ", 1, 31);
        int mes = rc.pedirIntRango("Dime el indice del mes que nació: ", 1, 12);
        int anio = rc.pedirIntRango("Dime el año que nació: ", 1900, 2024);
        insertAlumno(nombre,apellidos,dia +"/"+ mes +"/"+ anio,nia);
        //TODO gestionar fecha
        Colors.okMsg("El alumno se ha registrado correctamente.");
    }

    public void baja() {
        int id = getIDConNIA();
        dropAlumno(id);
    }

    public void mostrarAlumnos() {
        // TODO: cambiar a nombre de las tablas los *
        String selectQueryAlumnos = "SELECT * FROM `alumnos`";

        try (ResultSet resultSetAlumnos = super.executeSelect(selectQueryAlumnos)) {
            // Procesar el ResultSet
            while (resultSetAlumnos.next()) {
                String name = resultSetAlumnos.getString("ALM_NAME");
                String surnames = resultSetAlumnos.getString("ALM_SURNAMES");
                String fecha = resultSetAlumnos.getString("ALM_FECHA");
                int nia = resultSetAlumnos.getInt("ALM_NIA");

                //TODO gestionar sout
                System.out.println(String.format("%d : %s %s : %s", nia, name, surnames, fecha));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getIDConNIA() {
        int nia = pedirNia(true);
        return encontrarID(nia);
    }

    protected int pedirNia(boolean exist) {
        String cancel = "";
        String errMsg = "El NIA ya existe.";
        if (exist) {
            cancel = ", 0 para cancelar";
            errMsg = "El NIA no existe.";
        }
        int nia;
        do {
            nia = rc.pedirIntPositivo("Dime el NIA del alumno" + cancel + " : ");
            if (exist && nia == 0) {
                break;
            }
            if (!comprobarNia(nia, exist)) {
                Colors.errMsg(errMsg);
            }
        } while (!comprobarNia(nia, exist));
        return nia;
    }

    /**
     *
     * @param nia
     * @param exist True si buscas que el nia exista, false si buscas que el nia
     * no exista
     * @return
     */
    public Boolean comprobarNia(int nia, boolean exist) {
        boolean existe = false;
        int id = encontrarID(nia);
        existe = (id != -1);
        if (exist) {
            // Devuelve true si el NIA existe
            return existe;
        } else {
            // Devuelve true si el NIA NO existe
            return !existe;
        }
    }

    public int encontrarID(int nia) {
        Integer sel = super.select("ALM_ID", "alumnos", "ALM_NIA = ?", Integer.class, nia);
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }

    public void createTable() {
        if (!tableExists("alumnos")) {
            // Primera instrucción: CREATE TABLE
            String createTableQuery = """
            CREATE TABLE `alumnos` (
                `ALM_ID` int NOT NULL,
                `ALM_NAME` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                `ALM_SURNAMES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                `ALM_FECHA` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                `ALM_NIA` int NOT NULL
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
            """;

            // Segunda instrucción: ALTER TABLE
            String alterTableQuery = """
            ALTER TABLE `alumnos`
            ADD PRIMARY KEY (`ALM_ID`);
            """;

            // Ejecutar las instrucciones por separado
            super.executeUpdate(createTableQuery);
            super.executeUpdate(alterTableQuery);
        } else {
            System.out.println("La tabla 'alumnos' ya existe.");
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
        String query = "SELECT `ALM_ID`, `ALM_NAME`, `ALM_SURNAMES`, `ALM_FECHA`, `ALM_NIA` FROM `alumnos`;";
        ResultSet rs = super.executeSelect(query);
        String datos = "";
        try {
            while (rs.next()) {
                int id, nia;
                String name, surname, date;
                id = rs.getInt(1);
                name = rs.getString(2);
                surname = rs.getString(3);
                date = rs.getString(4);
                nia = rs.getInt(5);
                datos += String.format("%d;%s;%s;%s;%d%n", id, name, surname, date, nia);
            }
            super.write(ALUMNOSPATH, datos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void importTable() {
        ArrayList<String> filas = super.read(ALUMNOSPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarID(Integer.parseInt(datos[0]));
            if ( id != -1){                
                query = String.format("UPDATE `alumnos` SET `ALM_NAME`= '%s',`ALM_SURNAMES`= '%s',`ALM_FECHA`= '%s',`ALM_NIA`= %d WHERE ALM_ID = " + id, datos[1], datos[2], datos[3], datos[4]);
            }else{
                query = String.format("INSERT INTO `alumnos`(`ALM_ID`, `ALM_NAME`, `ALM_SURNAMES`, `ALM_FECHA`, `ALM_NIA`) VALUES (%d,'%s','%s','%s',%d)",Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], Integer.parseInt(datos[4]));
            }
            super.executeUpdate(query);
                
        }
    }

}
