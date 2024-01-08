package practica_06.curso;

import java.util.Calendar;
import practica_06.utilidades.Colors;

public class Alumno {

    private int id;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
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
