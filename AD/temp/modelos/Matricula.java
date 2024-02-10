package com.existdb.modelos;

import java.util.ArrayList;
import java.util.List;

import com.existdb.utilidades.*;

public class Matricula {
    private String id;
    private int idAlumno;
    private int idModulo;
    private String notas;

    public Matricula(int idAlumno, int idModulo, String notas) {
        this.idAlumno = idAlumno;
        this.idModulo = idModulo;
        this.notas = notas;
    }

    public Matricula(int idAlumno, int idModulo) {
        this.idAlumno = idAlumno;
        this.idModulo = idModulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNotas() {
        if (notas == null) {
            notas = " ";
        }
        return notas;
    }

    public List<Double> getNotasArray() {
        String notasString = getNotas();
        List<Double> notasList = new ArrayList<>();
        if (notasString != null && !"null".equals(notasString) && !"".equals(notasString)) {
            String[] notasActuales = notasString.split("#");
            for (String notaActual : notasActuales) {
                try {
                    if (!notaActual.trim().equals("")) {
                        Double nota = Double.parseDouble(notaActual);
                        notasList.add(nota);
                    }
                } catch (NumberFormatException e) {
                    Colors.errMsg("Error de formato en: " + notaActual);
                }
            }
        }
        return notasList;
    }

    public void setNotasArray(List<Double> notas) {
        if (notas != null && !notas.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for (Double nota : notas) {
                sb.append(nota).append("#");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            this.notas = sb.toString();
        }
    }

    public void mostrarNotas() {
        List<Double> notas = getNotasArray();
        for (int i = 0; i < notas.size(); i++) {
            System.out.println(i+1+".- " + notas.get(i));
        }
    }

    public String toXml() {
        return "<matricula id = \"" + this.getId() + "\">" +
                "<idAlumno>" + this.getIdAlumno() + "</idAlumno>" +
                "<idModulo>" + this.getIdModulo() + "</idModulo>" +
                "<notas>" + this.getNotas() + "</notas>" +
                "</matricula>";
    }

}
