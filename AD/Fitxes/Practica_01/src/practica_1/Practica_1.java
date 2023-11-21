/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_1;

/**
 *
 * @author andre
 */
public class Practica_1 {

    public static void main(String[] args) {
        // TODO code application logic here
        Alumne alumnes[] = {
            new Alumne("Mario", 45, 22),
            new Alumne("Pepe", 80, 52),
            new Alumne("Manuel", 63, 10),
            new Alumne("David", 75, 25),
            new Alumne("Alberto", 63, 10)};
        imprimirLista(alumnes);
        comparar(alumnes);
        imprimirLista(alumnes);
    }

    /**
     * Imprime una lista por pantalla
     * @param list : Hace referencia a la lista que se quiere imprimir
     */
    public static void imprimirLista(Alumne[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + 1 + ". " + list[i]);
        }
        System.out.println();

    }

    /**
     * Ordena la lista siguiendo el criterio de ordenanza de la clase
     * @param list Hace referencia a la lista que se quiere imprimir
     */
    public static void comparar(Alumne[] list) {
        boolean intercambio;
        do {
            intercambio = false;
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i].compareTo(list[i + 1]) == -1) {
                    Alumne temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    intercambio = true;
                }
            }
        } while (intercambio);
    }

}
