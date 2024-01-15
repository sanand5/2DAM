/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import practica_06.gestor.Conexion.DatabaseType;
import practica_06.utilidades.*;

/**
 *
 * @author andre
 */
public class gsAlumnos extends gestor {

    ReadClient rc = new ReadClient();
    private final String ALUMNOSPATH = "./res/alumnos.txt";

    private void insertAlumno(String name, String surname, String fecha, int nia) {
        String query = String.format("INSERT INTO alumnos(alm_name, alm_surnames, alm_fecha, alm_nia) VALUES ('%s', '%s', '%s', %d)",
                name, surname, fecha, nia);
        super.executeUpdate(query);
    }

    private void dropAlumno(int id) {
        String query = String.format("DELETE FROM matriculas WHERE mat_alm_id = %d", id);
        super.executeUpdate(query);
        query = String.format("DELETE FROM alumnos WHERE alm_id = %d", id);
        super.executeUpdate(query);
    }

    public void alta() {
        String nombre = rc.pedirString("Dime el nombre de el alumno: ", false);
        String apellidos = rc.pedirString("Dime los apellidos de el Alumno: ", false);
        int nia = pedirNia(false);
        int dia = rc.pedirIntRango("Dime el dia que nació: ", 1, 31);
        int mes = rc.pedirIntRango("Dime el indice del mes que nació: ", 1, 12);
        int anio = rc.pedirIntRango("Dime el año que nació: ", 1900, 2024);
        insertAlumno(nombre, apellidos, dia + "/" + mes + "/" + anio, nia);
        //TODO gestionar fecha
        Colors.okMsg("El alumno se ha registrado correctamente.");
    }

    public void baja() {
        int id = getIDConNIA();
        if (id == 0) {System.out.println("Se ha cancelado la eliminación.");
        }else{dropAlumno(id);}
    }

    public void mostrarAlumnos() {
        // TODO: cambiar a nombre de las tablas los *
        String selectQueryAlumnos = "SELECT * FROM alumnos";

        try (ResultSet resultSetAlumnos = super.executeSelect(selectQueryAlumnos)) {
            // Procesar el ResultSet
            while (resultSetAlumnos.next()) {
                String name = resultSetAlumnos.getString("alm_name");
                String surnames = resultSetAlumnos.getString("alm_surnames");
                String fecha = resultSetAlumnos.getString("alm_fecha");
                int nia = resultSetAlumnos.getInt("alm_nia");

                //TODO gestionar sout
                System.out.println(String.format("%d : %s %s : %s", nia, name, surnames, fecha));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getIDConNIA() {
        int nia = pedirNia(true);
        if (nia == 0){
            return nia;
        }
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
        Integer sel = super.select("alm_id", "alumnos", "alm_nia = ?", Integer.class, nia);
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }

    public void createTable() {
        DatabaseType databaseType = Conexion.getDataBaseType();
        String tableName = "alumnos";
        if (!super.tableExists(tableName)) {
            // Primera instrucción: CREATE TABLE
            String createTableQuery = "";

            if (databaseType == DatabaseType.MYSQL) {
                createTableQuery = """
                CREATE TABLE alumnos (
                    alm_id int NOT NULL AUTO_INCREMENT,
                    alm_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                    alm_surnames varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                    alm_fecha varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                    alm_nia int NOT NULL,
                    PRIMARY KEY (alm_id)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
                """.formatted(tableName);
            } else if (databaseType == DatabaseType.POSTGRESQL) {
                createTableQuery
                        = """
                CREATE TABLE alumnos (
                    alm_id serial PRIMARY KEY,
                    alm_name varchar(255) NOT NULL,
                    alm_surnames varchar(255) NOT NULL,
                    alm_fecha varchar(255) NOT NULL,
                    alm_nia int NOT NULL
                );
                """;
            }
            super.executeUpdate(createTableQuery);
        } else {
            Colors.debMsg("La tabla '" + tableName + "' ya existe.");
        }
    }

    public void exportTable() {
        String query = "SELECT alm_id, alm_name, alm_surnames, alm_fecha, alm_nia FROM alumnos;";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void importTable() {
        ArrayList<String> filas = super.read(ALUMNOSPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarID(Integer.parseInt(datos[0]));
            System.out.println(id);
            if (id != -1) {
                query = String.format("UPDATE alumnos SET alm_name= '%s, alm_surnames= '%s',alm_fecha= '%s', alm_nia= %d WHERE alm_id = " + id, datos[1], datos[2], datos[3], datos[4]);
            } else {
                //query = String.format("INSERT INTO alumnos(alm_id, alm_name, alm_surnames, alm_fecha, alm_nia) VALUES (%d,'%s','%s','%s',%d)", Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], Integer.parseInt(datos[4]));
                System.out.println("epasadoporaqui");
                query = String.format("INSERT INTO alumnos(alm_id, alm_name, alm_surnames, alm_fecha, alm_nia) VALUES (%d,'%s','%s','%s',%d)", Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], Integer.parseInt(datos[4]));

            }
            super.executeUpdate(query);

        }
    }

}
