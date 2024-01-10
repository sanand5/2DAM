package practica_06.curso;


public class Matricula {
    
    private int id;
    private Alumno alumno;
    private Modulo modulo;
    private String notas;

    public Matricula(Alumno alumno, Modulo modulo, String notas) {
        this.alumno = alumno;
        this.modulo = modulo;
        this.notas = notas;
    }

    public int getId() {
        return id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getNotas() {
        return notas;
    }

    

}
