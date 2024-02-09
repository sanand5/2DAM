/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;

/**
 *
 * @author RASPUTIN
 */
public class Villanos implements Serializable{
    

    private int Id_SuperHeroe ;
    private int Id_Villano;
    private String NombreVillano;
    private String Habilidad;
    private boolean mascara;
    private char genero;
    private String NombreSuperHeroe;

    public Villanos() {
    }

    
    public Villanos(String NombreVillano, String Habilidad, boolean mascara, char genero, String NombreSuperHeroe) {
        
        this.NombreVillano = NombreVillano;
        this.Habilidad = Habilidad;
        this.mascara = mascara;
        this.genero = genero;
        this.NombreSuperHeroe = NombreSuperHeroe;
    }

    public int getId_SuperHeroe() {
        return Id_SuperHeroe;
    }

    public void setId_SuperHeroe(int Id_SuperHeroe) {
        this.Id_SuperHeroe = Id_SuperHeroe;
    }

    public int getId_Villano() {
        return Id_Villano;
    }

    public void setId_Villano(int Id_Villano) {
        this.Id_Villano = Id_Villano;
    }

    public String getNombreVillano() {
        return NombreVillano;
    }

    public void setNombreVillano(String NombreVillano) {
        this.NombreVillano = NombreVillano;
    }

    public String getHabilidad() {
        return Habilidad;
    }

    public void setHabilidad(String Habilidad) {
        this.Habilidad = Habilidad;
    }

    public boolean isMascara() {
        return mascara;
    }

    public void setMascara(boolean mascara) {
        this.mascara = mascara;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
    
    public String getNombreSuperHeroe() {
        return NombreSuperHeroe;
    }

    public void setNombreSuperHeroe(String NombreSuperHeroe) {
        this.NombreSuperHeroe = NombreSuperHeroe;
    }

    
}
