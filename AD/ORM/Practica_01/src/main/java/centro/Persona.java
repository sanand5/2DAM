package centro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Persona")
public class Persona {
    
    // PER_ID PER_NAME1 PER_NAME2 PER_SURNAME1 PER_SURNAME2 PER_FECHA

    @Column(name = "PER_ID")
    private int id;
    @Column(name = "PER_NAME")
    private String name;
    @Column(name = "PER_SURNAME1")
    private String surname1;
    @Column(name = "PER_SURNAME2")
    private String surname2;
    @Column(name = "PER_FECHA")
    private String date;

    public Persona(int id, String name, String surname1, String surname2, String date) {
        this.id = id;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.date = date;
    }
}
