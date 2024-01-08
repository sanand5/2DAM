package curso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Table;

import utilidades.Colors;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALM_ID")
    private int id;
    @Column(name = "ALM_NAME")
    private String nombre;

    @Column(name = "ALM_SURNAMES")
    private String apellidos;

    @Column(name = "ALM_FECHA")
    private String fechaNacimiento;

    @Column(name = "ALM_NIA")
    private int nia;

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos, String fechaNacimiento, int nia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.nia = nia;
    }

    public int getId() {
        return id;
    }

    public int getNia() {
        return nia;
    }

    public void setNia(int nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int dia, int mes, int ano) {
        String fecha = null;
        if (dia < 32 && mes < 13 && ano > 0 && ano <= Calendar.getInstance().get(Calendar.YEAR)) {
            fecha = dia + "/" + mes + "/" + ano;
            this.fechaNacimiento = fecha;
        } else {
            Colors.errMsg("Err: La fecha no se ha podido modificar");
        }

    }

    @Override
    public String toString() {
        return nia + " : " + nombre + " " + apellidos;
    }
}
