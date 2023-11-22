package curso;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "Alumne")
public class Alumne extends Persona {
    @Column(name = "ALM_PER_ID")
    private int id;
    @Column(name = "ALM_NIA")
    private int nia;
    public Alumne(){}

    public Alumne(String nombre, String nombre2, String apellido, String apellido2, String fechaNacimiento, int nia) {
        super(nombre, nombre2, apellido, apellido2, fechaNacimiento);
        this.nia = nia;
    }
}
