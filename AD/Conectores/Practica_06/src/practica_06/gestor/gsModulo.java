/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.*;
import practica_06.curso.Modulo;
import practica_06.utilidades.*;

/**
 *
 * @author andre
 */
public class gsModulo extends gestor {

    ReadClient rc = new ReadClient();

    private void insertModulo(Modulo modulo) {
        String query = String.format("INSERT INTO `modulos`(`MOD_NAME`) VALUES ('%s')",
                 modulo.getNombre());
        super.executeUpdate(query);
    }

    public void alta() {
        String nombre = rc.pedirString("Dime el nombre de el nuevo modulo: ", false);
        Modulo m = new Modulo(nombre);
        insertModulo(m);
        Colors.okMsg("Módulo registrado correctamente.");
    }

    public int pedirId() {
        int id = rc.pedirIntPositivo("Dime el id de el modulo, 0 para cancelar: ");
        String query = "SELECT `MOD_ID`, `MOD_NAME` FROM `modulos` WHERE MOD_ID = ";
        try {
            ResultSet rs = super.executeSelect(query + id);
            while (!rs.next() && id != 0) {
                Colors.errMsg("No existe ningun modulo con ese ID");
                id = rc.pedirIntPositivo("Dime el id de el modulo, 0 para cancelar: ");
                rs = super.executeSelect(query + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public void matricular() {
        
    }
}
