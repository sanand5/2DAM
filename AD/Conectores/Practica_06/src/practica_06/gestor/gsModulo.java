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
public class gsModulo extends gestor {

    ReadClient rc = new ReadClient();
    private final String MODULOSPATH = "./res/modulos.txt";

    public void baja() {
        int id = pedirIDconNombre();
        dropModulo(id);
    }

    private void dropModulo(int id) {
        String query = String.format("DELETE FROM matriculas WHERE MAT_MOD_ID = %d", id);
        super.executeUpdate(query);
        query = String.format("DELETE FROM modulos WHERE MOD_ID = %d", id);
        super.executeUpdate(query);
    }

    private void insertModulo(String modulo) {
        String query = String.format("INSERT INTO `modulos`(`MOD_NAME`) VALUES ('%s')",
                modulo);
        super.executeUpdate(query);
    }

    public void alta() {
        boolean nombreValido;
        String nombre;
        do {
            nombre = rc.pedirString("Dime el nombre del nuevo modulo (o escribe '/c' para cancelar): ", false);
            if (nombre.equalsIgnoreCase("/c")) {
                Colors.warMsg("Registro cancelado por el usuario.");
                return;
            }
            int id = encontrarIDconNombre(nombre);
            if (id == -1) {
                nombreValido = true;
                insertModulo(nombre);
                Colors.okMsg("Módulo registrado correctamente.");
            } else {
                nombreValido = false;
                Colors.errMsg("No pueden haber varios módulos con el mismo nombre.");
            }
        } while (!nombreValido);
    }

    public int pedirIDconNombre() {
        String name;
        int rs;
        do {
            name = rc.pedirString("Dime el nombre del módulo (o escribe '/c' para cancelar): ", false);
            if (name.equalsIgnoreCase("/c")) {
                Colors.warMsg("Operación cancelada por el usuario.");
                return -1;
            }
            rs = encontrarIDconNombre(name);
            if (rs == -1) {
                Colors.errMsg("No existe ningún módulo con ese nombre.");
            }
        } while (rs == -1);
        return rs;
    }

    public int encontrarIDconNombre(String name) {
        Integer sel = super.select("MOD_ID", "modulos", "MOD_NAME = ?", Integer.class, name);;
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }

    public void createTable() {
        // Comprobar si la tabla ya existe antes de intentar crearla
        if (!tableExists("modulos")) {
            // Primera instrucción: CREATE TABLE
            String createTableQuery = """
            CREATE TABLE `modulos` (
              `MOD_ID` int NOT NULL,
              `MOD_NAME` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
            """;

            // Segunda instrucción: ALTER TABLE
            String alterTableQuery = """
            ALTER TABLE `modulos`
                ADD PRIMARY KEY (`MOD_ID`);
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
        String query = "SELECT `MOD_ID`, `MOD_NAME` FROM `modulos`;";
        ResultSet rs = super.executeSelect(query);
        String datos = "";
        try {
            while (rs.next()) {
                int id;
                String name;
                id = rs.getInt(1);
                name = rs.getString(2);
                datos += String.format("%d;%s%n", id, name);
            }
            super.write(MODULOSPATH, datos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importTable() {
        ArrayList<String> filas = super.read(MODULOSPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarIDconNombre(datos[1]);
            if (id != -1) {
                query = String.format("UPDATE `modulos` SET `MOD_ID`='[value-1]',`MOD_NAME`='[value-2]' WHERE `MOD_ID`= " + id, datos[1], datos[2], datos[3], datos[4]);
            } else {
                query = String.format("INSERT INTO `modulos`(`MOD_ID`, `MOD_NAME`) VALUES (%d,'%s')", Integer.valueOf(datos[0]), datos[1]);
            }
            super.executeUpdate(query);

        }
    }
}
