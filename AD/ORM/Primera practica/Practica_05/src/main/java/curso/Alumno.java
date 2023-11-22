package curso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALM_PER_ID")
    private int id;

    @Column(name = "ALM_NIA")
    private int nia;

    @OneToOne
    @JoinColumn(name = "ALM_PER_ID")
    private Persona persona;

    public Alumno() {
    }

    public Alumno(int nia) {
        this.nia = nia;
    }
}
