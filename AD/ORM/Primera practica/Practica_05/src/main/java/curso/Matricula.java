package curso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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

}
