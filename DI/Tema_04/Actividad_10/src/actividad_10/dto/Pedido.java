/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividad_10.dto;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Pedido {
    private String nombre;
    private int catidad;

    public Pedido(String nombre, int catidad) {
        this.nombre = nombre;
        this.catidad = catidad;
    }

    public int getCatidad() {
        return catidad;
    }

    public void setCatidad(int catidad) {
        this.catidad = catidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
