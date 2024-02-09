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
public class Marcas implements Serializable{
    
    private int Id_Villano;
    private int Id_Marca;
    private String NombreMarca;
    private int AñoCreacion;
    private boolean Pelicula;
    private String NombreVillano;

    public Marcas() {
    }

    public Marcas(String NombreMarca, int AñoCreacion, boolean Pelicula, String NombreVillano) {
      
        this.NombreMarca = NombreMarca;
        this.AñoCreacion = AñoCreacion;
        this.Pelicula = Pelicula;
        this.NombreVillano = NombreVillano;
    }
    
     public int getAñoCreacion() {
        return AñoCreacion;
    }

    public void setAñoCreacion(int AñoCreacion) {
        this.AñoCreacion = AñoCreacion;
    }

    public String getNombreVillano() {
        return NombreVillano;
    }

    public void setNombreVillano(String NombreVillano) {
        this.NombreVillano = NombreVillano;
    }

    public int getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(int Id_Marca) {
        this.Id_Marca = Id_Marca;
    }
    
    public int getId_Villano() {
        return Id_Villano;
    }

    public void setId_Villano(int Id_Villano) {
        this.Id_Villano = Id_Villano;
    }

   
    public String getNombreMarca() {
        return NombreMarca;
    }

    public void setNombreMarca(String NombreMarca) {
        this.NombreMarca = NombreMarca;
    }

    public int getAño() {
        return AñoCreacion;
    }

    public void setAño(int AñoCreacion) {
        this.AñoCreacion = AñoCreacion;
    }

    public boolean isPelicula() {
        return Pelicula;
    }

    public void setPelicula(boolean Pelicula) {
        this.Pelicula = Pelicula;
    }
    
    
    
}
