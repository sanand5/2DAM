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
        if (id != -1) {
            dropModulo(id);
        }
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

    public void createTable() {
        String tableName = "modulos";
        String queryMySQL = """
                CREATE TABLE modulos (
                  mod_id int NOT NULL AUTO_INCREMENT,
                  mod_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                  PRIMARY KEY (mod_id)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
                """;
        String queryPostgresSQL = """
                CREATE TABLE modulos (
                  mod_id serial PRIMARY KEY,
                  mod_name varchar(255) NOT NULL
                );
                """;
        super.createTable(tableName, queryMySQL, queryPostgresSQL);
    }

    public void exportTable() {
        String query = "SELECT mod_id, mod_name FROM modulos;";
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
        } catch (SQLException e) {
            Colors.errMsg("Imposible exportar los modulos");
        }
    }

    public void importTable() {
        ArrayList<String> filas = super.read(MODULOSPATH);
        String query = "";
        for (String fila : filas) {
            String[] datos = fila.split(";");
            int id = encontrarIDconNombre(datos[1]);
            if (id != -1) {
                query = String.format("UPDATE modulos SET mod_name= '%s' WHERE mod_id= " + id, datos[1]);
            } else {
                query = String.format("INSERT INTO modulos(mod_id, mod_name) VALUES (%d,'%s')", Integer.valueOf(datos[0]), datos[1]);
            }
            super.executeUpdate(query);
        }
        Colors.okMsg("Los datos de %s se han importado correctamente" + MODULOSPATH);
    }

    public void mostrarModulos() {
        String selectQueryAlumnos = "SELECT mod_name FROM modulos";
        System.out.println(super.getTitulo("modulos"));
        try (ResultSet resultSetAlumnos = super.executeSelect(selectQueryAlumnos)) {
            // Procesar el ResultSet
            int cont = 1;
            while (resultSetAlumnos.next()) {
                String name = resultSetAlumnos.getString("mod_name");
                System.out.println(String.format("%d.- %s", cont, name));
                cont++;

            }
        } catch (SQLException e) {
            Colors.errMsg("Imposible mostrar los modulos");
        }
    }
    
    protected int pedirIDconNombre() {
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

    protected int encontrarIDconNombre(String name) {
        Integer sel = super.select("mod_id", "modulos", "mod_name = ?", Integer.class, name);;
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }
    
    private void dropModulo(int id) {
        String query = String.format("DELETE FROM matriculas WHERE mat_mod_id = %d", id);
        super.executeUpdate(query);
        query = String.format("DELETE FROM modulos WHERE mod_id = %d", id);
        super.executeUpdate(query);
        Colors.okMsg("Modulo eliminado exitosamente");
    }

    private void insertModulo(String modulo) {
        String query = String.format("INSERT INTO modulos(mod_name) VALUES ('%s')",
                modulo);
        super.executeUpdate(query);
    }
}
