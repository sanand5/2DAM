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

    public void alta() {
        String nombre = rc.pedirString("Dime el nombre de el alumno: ", false);
        String apellidos = rc.pedirString("Dime los apellidos de el Alumno: ", false);
        int nia = pedirNia(false);
        int dia = rc.pedirIntRango("Dime el dia que nació: ", 1, 31);
        int mes = rc.pedirIntRango("Dime el indice del mes que nació: ", 1, 12);
        int anio = rc.pedirIntRango("Dime el año que nació: ", 1900, 2024);
        String fecha = String.format("%02d/%02d/%d", dia,mes,anio);
        insertAlumno(nombre, apellidos, fecha, nia);
        Colors.okMsg("El alumno se ha registrado correctamente.");
    }

    public void baja() {
        int id = getIDConNIA();
        if (id == 0) {Colors.warMsg("Se ha cancelado la eliminación.");
        }else{
            dropAlumno(id);
            Colors.okMsg("El alumno se ha eliminado correctamente");
        }
    }

    public void mostrarAlumnos() {
        String selectQueryAlumnos = "SELECT alm_name, alm_surnames, alm_fecha, alm_nia FROM alumnos";
        System.out.println(super.getTitulo("alumnos"));
        try (ResultSet resultSetAlumnos = super.executeSelect(selectQueryAlumnos)) {
            while (resultSetAlumnos.next()) {
                String name = resultSetAlumnos.getString("alm_name");
                String surnames = resultSetAlumnos.getString("alm_surnames");
                String fecha = resultSetAlumnos.getString("alm_fecha");
                int nia = resultSetAlumnos.getInt("alm_nia");
                System.out.println(String.format("%d : %s %s : %s", nia, name, surnames, fecha));
            }
            System.out.println("");
        } catch (SQLException e) {
            Colors.errMsg("No se han podido mostrar alumnos.");
        }

    }

    public int getIDConNIA() {
        int nia = pedirNia(true);
        if (nia == 0){
            Colors.warMsg("Operación cancelada por el usuario.");
            return nia;
        }
        return encontrarID(nia);
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
        String tableName = "alumnos";
        String queryMySQL = """
                CREATE TABLE alumnos (
                    alm_id int NOT NULL AUTO_INCREMENT,
                    alm_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                    alm_surnames varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                    alm_fecha varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                    alm_nia int NOT NULL,
                    PRIMARY KEY (alm_id)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
                """;
        String queryPostgresSQL = """
                CREATE TABLE alumnos (
                    alm_id serial PRIMARY KEY,
                    alm_name varchar(255) NOT NULL,
                    alm_surnames varchar(255) NOT NULL,
                    alm_fecha varchar(255) NOT NULL,
                    alm_nia int NOT NULL
                );
                """;
        super.createTable(tableName, queryMySQL, queryPostgresSQL);
        
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
            Colors.errMsg("No se ha podido exportar la tabla de alumnos correctamente");
        }
    }

    public void importTable() {
        ArrayList<String> filas = super.read(ALUMNOSPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarID(Integer.parseInt(datos[4]));
            if (id != -1) {
                query = String.format("UPDATE alumnos SET alm_name= '%s', alm_surnames= '%s',alm_fecha= '%s', alm_nia= %d WHERE alm_id = " + id, datos[1], datos[2], datos[3], Integer.parseInt(datos[4]));
            } else {               
                query = String.format("INSERT INTO alumnos(alm_id, alm_name, alm_surnames, alm_fecha, alm_nia) VALUES (%d,'%s','%s','%s',%d)", Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], Integer.parseInt(datos[4]));

            }
            super.executeUpdate(query);
        }
        Colors.okMsg("Los datos de %s se han importado correctamente" + ALUMNOSPATH);
    }
    
    protected Boolean comprobarNia(int nia, boolean exist) {
        boolean existe = false;
        int id = encontrarID(nia);
        existe = (id != -1);
        if (exist) {
            return existe;
        } else {
            return !existe;
        }
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

}
