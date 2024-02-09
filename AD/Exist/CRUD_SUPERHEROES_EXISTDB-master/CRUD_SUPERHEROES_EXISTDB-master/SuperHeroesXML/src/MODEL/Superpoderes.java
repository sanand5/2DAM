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
public class Superpoderes implements Serializable{
    
    int id_SuperHeroe;
    int id_SuperPoder;
    String NombreSuperHeroe;
    String NombrePoder;
    int Daño;
    int Potencia;

    public Superpoderes() {
        
    }

    public Superpoderes(String NombrePoder, int Daño, int Potencia, String NombreSuperHeroe) {
      
        this.NombrePoder = NombrePoder;
        this.Daño = Daño;
        this.Potencia = Potencia;
        this.NombreSuperHeroe = NombreSuperHeroe;
    }

     public String getNombreSuperHeroe() {
        return NombreSuperHeroe;
    }

    public void setNombreSuperHeroe(String NombreSuperHeroe) {
        this.NombreSuperHeroe = NombreSuperHeroe;
    }

    
    public int getId_SuperHeroe() {
        return id_SuperHeroe;
    }

    public void setId_SuperHeroe(int id_SuperHeroe) {
        this.id_SuperHeroe = id_SuperHeroe;
    }

    public int getId_SuperPoder() {
        return id_SuperPoder;
    }

    public void setId_SuperPoder(int id_SuperPoder) {
        this.id_SuperPoder = id_SuperPoder;
    }

    public String getNombrePoder() {
        return NombrePoder;
    }

    public void setNombrePoder(String NombrePoder) {
        this.NombrePoder = NombrePoder;
    }

    public int getDaño() {
        return Daño;
    }

    public void setDaño(int Daño) {
        this.Daño = Daño;
    }

    public int getPotencia() {
        return Potencia;
    }

    public void setPotencia(int Potencia) {
        this.Potencia = Potencia;
    }
    
    
}