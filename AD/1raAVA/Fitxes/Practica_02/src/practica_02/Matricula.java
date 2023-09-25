/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Matricula {
    ArrayList<Modul> moduls = new ArrayList();
    Alumne alumne;
    Boolean alta = false;

    public Matricula(Alumne alumne) {
        this.alumne = alumne;
    }

    public void donarAlta() {
        alta = true;
    }
    
    public void donarBaiza() {
        alta = false;
    }
    
    public void insertarModul(Modul m) {
        moduls.add(m);
    }

    @Override
    public String toString() {
        return "Matricula{" + "moduls=" + moduls + ", alumne=" + alumne + ", alta=" + alta + '}';
    }
    
    
    
}
