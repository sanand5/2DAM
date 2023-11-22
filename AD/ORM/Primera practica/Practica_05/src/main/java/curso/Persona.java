package curso;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID")
    private int id;

    @Column(name = "PER_NAME1")
    private String nombre;

    @Column(name = "PER_NAME2")
    private String nombre2;

    @Column(name = "PER_SURNAME1")
    private String apellido;

    @Column(name = "PER_SURNAME2")
    private String apellido2;

    @Column(name = "PER_FECHA")
    private String fechaNacimiento;

    public Persona() {}
    
    public Persona(String nombre, String nombre2, String apellido, String apellido2, String fechaNacimiento) {
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        
    }

    public String crearFecha(int dia, int mes, int ano) {
        String fecha = null;
        if (dia < 32 && mes < 13 && ano > 0 && ano <= Calendar.getInstance().get(Calendar.YEAR)) {
            fecha = dia + "/" + mes + "/" + ano;
        }
        return fecha;
    }
}
