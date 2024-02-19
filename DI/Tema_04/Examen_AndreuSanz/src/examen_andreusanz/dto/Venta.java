/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_andreusanz.dto;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Venta {
    private String ubicacion;
    private int habitaciones;
    private int banos;
    private double Precio;

    public Venta(String ubicacion, int habitaciones, int banos, double Precio) {
        this.ubicacion = ubicacion;
        this.habitaciones = habitaciones;
        this.banos = banos;
        this.Precio = Precio;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getBanos() {
        return banos;
    }

    public void setBanos(int banos) {
        this.banos = banos;
    }
    
    
}
