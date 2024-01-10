/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.util.HashMap;
import java.util.Map;
import practica_06.utilidades.Colors;
import practica_06.utilidades.ReadClient;

/**
 *
 * @author andre
 */
public class gsMatriculas extends gestor {

    ReadClient rc = new ReadClient();
    gsAlumnos gsa = new gsAlumnos();
    gsModulo gsm = new gsModulo();

    public void crearMatricula() {
        int alumno = gsa.encontrarID(gsa.pedirNia(true));
        int modulo = gsm.pedirIDconNombre();

        if (encontrarID(alumno, modulo) == -1) {
            insertMatricula(alumno, modulo, "");
        } else {
            Colors.errMsg("La matricula ya existe");
        }

    }
    
    private void dropMatricula() {
        
    }
    
    
    //Test
    public void modificarNotas() {
        int matrID = pedirID();
        double[] notas = getNotas(matrID);
        System.out.println("Estas son las notas de el alumno: ");
        for (int i = 0; i < notas.length; i++) {
            System.out.println(i + 1+". " + notas[i]);
        }
        int iterator = rc.pedirIntRango("Dime el numero de notas que quieres modificar: ", 1, notas.length);
        HashMap<Integer, Double> notasUpdate = new HashMap<>();
        for (int i = 0; i < iterator; i++) {
            System.out.println("Posicion de la nota ha modificar");
            System.out.print(">");
            int pos = rc.pedirIntRango(">", 1, notas.length);
            System.out.println("Nota ha modificar");
            double nota = rc.pedirDoubleRango(">", 0, 10);
            notasUpdate.put(pos-1, nota);
        }
        
        modNotas(matrID, notasUpdate);
    }
    
    //Test
    private int pedirID() {
        int nia = gsa.getIDConNIA();
        int modulo = gsm.pedirIDconNombre();
        return encontrarID(nia, modulo);
    }
    

    private void modNotas(int matrID, HashMap<Integer, Double> notasUpdate) {
        String notasActuales[] = getNotasString(matrID).split("#");
        for (Map.Entry<Integer, Double> entry : notasUpdate.entrySet()) {
            int posicion = entry.getKey();
            double nuevaNota = entry.getValue();
            if (posicion >= 0 && posicion < notasActuales.length) {
                notasActuales[posicion] = String.valueOf(nuevaNota);
            } else {
                Colors.debMsg("Error: Posición fuera de rango - " + posicion);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (String nota : notasActuales) {
            result.append(nota).append("#");
        }
        result.deleteCharAt(result.length() - 1);
        String query = String.format("UPDATE matriculas SET MAT_NOTAS = \"%s\" WHERE MAT_ID = %d", result, matrID);
        super.executeUpdate(query);
        
    }
    
    private double[] getNotas(int matrID) {
        String notasActuales[] = getNotasString(matrID).split("#");
        double[] notas = new double[notasActuales.length];
        for (int i = 0; i < notasActuales.length; i++) {
            notas[i] = Double.parseDouble(notasActuales[i]);
        }
        return notas;
    }
    
    private String getNotasString(int matrID) {
        String notas = super.select("MAT_NOTAS", "matriculas", "MAT_ID = ?", String.class, matrID);
        Colors.debMsg(notas);
        return notas;
    }
    
    private void addNotas(int matrID, double... nota) {
        String notasActuales = getNotasString(matrID);
        String notas = createNotas(nota);
        if (notasActuales != null) {
            if (!"".equals(notasActuales.trim())) {
                notas = notasActuales + "#" + notas;
            }
        }
        String query = String.format("UPDATE matriculas SET MAT_NOTAS = \"%s\" WHERE MAT_ID = %d", notas, matrID);
        super.executeUpdate(query);
    }

    private String createNotas(double... notas) {
        if (notas.length == 0) {
            Colors.warMsg("No se proporcionaron notas para agregar.");
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (double nota : notas) {
            result.append(nota).append("#");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private int encontrarID(int alumnoID, int moduloID) {
        Integer sel = super.select("MAT_ID", "matriculas", "MAT_MOD_ID = ? AND MAT_ALM_ID = ?", Integer.class, moduloID, alumnoID);
        int res = -1;
        if (sel != null) {
            res = sel;
        }
        return res;
    }

    private void insertMatricula(int alumnoID, int moduloID, String notas) {
        String query = String.format("INSERT INTO matriculas(MAT_ALM_ID, MAT_MOD_ID, MAT_NOTAS) VALUES (%d,%d,\"%s\")",
                alumnoID,
                moduloID,
                notas);
        super.executeUpdate(query);
    }
}
