package curso;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOD_ID")
    private int id;

    @Column(name = "MOD_NAME")
    private String nombre;

    public Modulo() {
    }

    public Modulo(String nombre) {
        this.nombre = nombre;
    }
}
