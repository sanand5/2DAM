package curso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import utilidades.Colors;

import java.util.Arrays;

@Entity
@Table(name = "matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAT_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "MAT_ALM_ID")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "MAT_MOD_ID")
    private Modulo modulo;

    @Column(name = "MAT_NOTAS")
    private String notas;

    public Matricula() {
    }

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

    public double[] getNotasList() {
        double[] notasList = null;
        if (notas != null) {
            String[] partes = notas.split("#");
            notasList = new double[partes.length];
            for (int i = 0; i < partes.length; i++) {
                notasList[i] = Double.parseDouble(partes[i]);
            }
        }
        return notasList;
    }

    public void setNotasList(double[] notasList) {
        String cadena = String.join("#", Arrays.stream(notasList)
                .mapToObj(String::valueOf)
                .toArray(String[]::new));
        this.notas = cadena;
    }

    public void addNota(double... notas) {
        double[] lastNotas = getNotasList();
        double[] newNotas;
        int lastNotasSize = 0;
        if (lastNotas != null) {
            lastNotasSize = lastNotas.length;
            newNotas = new double[lastNotas.length + notas.length];
            for (int i = 0; i < lastNotas.length; i++) {
                newNotas[i] = lastNotas[i];
            }
        } else {
            newNotas = new double[notas.length];
        }
        for (int i = lastNotasSize; i < newNotas.length; i++) {
            newNotas[i] = notas[i - lastNotasSize];
        }
        setNotasList(newNotas);
    }

    public void mostrarNotas() {
        double[] notasList = getNotasList();
        if (notasList == null) {
            Colors.warMsg(this.alumno.getNombre() + " no tiene notas en " + this.modulo.getNombre());
        } else {

            System.out.println("Notas de " + this.alumno.getNombre() + " en " + this.modulo.getNombre() + ": ");
            for (int i = 0; i < notasList.length; i++) {
                System.out.println(i + 1 + ".- " + notasList[i]);
            }
        }
    }

}
