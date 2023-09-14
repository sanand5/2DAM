/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_1;

/**
 *
 * @author andre
 */
public class Alumne implements Comparable<Alumne> {

    int percentatge, moduls;
    String name;

    public Alumne(String name, int percentatge, int moduls) {
        this.percentatge = percentatge;
        this.moduls = moduls;
        this.name = name;
    }

    /**
     * Sirve para comparar dos objetos
     * @param o objeto a comparar
     * @return resultado : 0 si es menor y uno si es mayor o igual
     */
    public int compareTo(Alumne o) {

        int resultado = 0;
        if (this.percentatge > o.percentatge) {
            resultado = 1 ;
        }else if(this.percentatge == o.percentatge){
            if (this.moduls > o.moduls || this.moduls == o.moduls) {
                resultado = 1;
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return this.name + " - Percentatge: " + this.percentatge + " - Moduls: " + this.moduls;
    }

}

        /*
        if (this.percentatge < o.percentatge) { //menor
            resultado = -4;
        } else if (this.percentatge > o.percentatge) { //mayor
            resultado = 4;
        } else {
            if (this.moduls < o.moduls) { //menor
                resultado *= 2;
            } else if (this.moduls > o.moduls) { //mayor
                resultado /= 2;
            } else {
                resultado += 1;
            }
        }
*/