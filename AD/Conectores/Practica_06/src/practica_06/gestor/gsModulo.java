/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import practica_06.utilidades.*;

/**
 *
 * @author andre
 */
public class gsModulo extends gestor {

    ReadClient rc = new ReadClient();

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
        int id = encontrarID(nombre);
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
        rs = encontrarID(name);
        if (rs == -1) {
            Colors.errMsg("No existe ningún módulo con ese nombre.");
        }
    } while (rs == -1);
    return rs;
}


    public int encontrarID(String name) {
        Integer sel = super.select("MOD_ID", "modulos", "MOD_NAME = ?", Integer.class, name);;
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }
}
