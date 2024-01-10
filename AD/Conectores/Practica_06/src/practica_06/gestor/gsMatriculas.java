/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

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

    public String getNotasString(int matrID) {
        String notas = super.select("MAT_NOTAS", "matriculas", "MAT_ID = ?", String.class, matrID);
        Colors.debMsg(notas);
        return notas;
    }

    public void addNotas(int matrID, double... nota) {
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
