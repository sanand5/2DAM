/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import iohelpers.Colors;
import iohelpers.ReadClient;

/**
 *
 * @author andre
 */
public class Modul {

    String nom;
    String id;
  
    public Modul(String nom) {
        this.nom = nom;
        this.id = generarId();
    }
    
    public Modul(String nom, String id) {
        this.nom = nom;
        this.id = id;
    }
    
    public static String pedirId() {
        ReadClient rc = new ReadClient();
        String id;
        String matches = "\\d{8}";
        boolean ok = false;
        do {
            id = rc.pedirString("Id del mòdul: ");
            if (id.matches(matches)) {
                ok = true;
            }else{
                Colors.errMsg("Format del id incorrecte");
            }
            
        } while (!ok);
        return id;
    }
    
    private String generarId() {
        int min = 10000000, max = 99999999;
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random+"";
    }

    public String fromString() {
        String obj = nom+","+id;
        return obj;
    }

}
