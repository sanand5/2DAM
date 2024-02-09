package com.existdb.modelos;
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

    public void addNota() {

    }
    
    public Double[] getNotasArray() {
        String notasString = getNotas();
        if (notasString != null && !"null".equals(notasString) && !"".equals(notasString)) {
            String notasActuales[] = notasString.split("#");
            Double[] notas = new Double[notasActuales.length];

            for (int i = 0; i < notasActuales.length; i++) {
                try {
                    notas[i] = Double.parseDouble(notasActuales[i]);
                } catch (NumberFormatException e) {
                    Colors.errMsg("Error de formato en : " + notasActuales[i]);
                }

            }
            return notas;
        } else {
            return new Double[0];
        }
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String toXml() {
        return "<matricula id = \"" + this.getId() + "\">" +
                "<idAlumno>" + this.getIdAlumno() + "</idAlumno>" +
                "<idModulo>" + this.getIdModulo() + "</idModulo>" +
                "<notas>" + this.getNotas() + "</notas>" +
                "</matricula>";
    }

}
