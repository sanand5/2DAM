package practica_06.curso;

public class Modulo {
    
    private String nombre;

    public Modulo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getNombre();
    }
}
