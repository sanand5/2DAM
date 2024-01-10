/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_06;

import practica_06.gestor.gsAlumnos;
import practica_06.gestor.gsMatriculas;
import practica_06.gestor.gsModulo;


/**
 *
 * @author andre
 */
public class Practica_06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test();

    }
    
    public static void test() {
        // TODO code application logic here
        gsAlumnos gsa = new gsAlumnos();
        gsModulo gsm = new gsModulo();
        gsMatriculas gsM = new gsMatriculas();
        //System.out.println(gsM.encontrarID(12, 15));
//        gsm.pedirId();
        //gsM.crearMatricula();
        //gsM.getNotas(33);
        double V[] = {3.3, 4.6, 7.8 };
        gsM.addNotas(33, V);
        
//        Alumno al = new Alumno("LAura", "Llorens", "02/12/2004", 24515);
//        System.out.println(gs.comprobarNia(1258, true));
//        System.out.println(gs.comprobarNia(108258, false));
//        gs.mostrarAlumnos();
    }

}
