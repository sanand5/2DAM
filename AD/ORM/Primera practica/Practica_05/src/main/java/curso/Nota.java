package curso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOT_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "NOT_MOD_ID")
    private Modulo modulo;

    @ManyToOne
    @JoinColumn(name = "NOT_ALM_ID")
    private Alumno alumno;

    @Column(name = "NOT_NUM")
    private double numero;

    public Nota() {
    }

    public Nota(Modulo modulo, Alumno alumno, double numero) {
        this.modulo = modulo;
        this.alumno = alumno;
        this.numero = numero;
    }
}
