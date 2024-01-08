package practica_06.curso;

public class Modulo {
    
    private int id;
    private String nombre;

    public Modulo(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nombre: " + this.getNombre();
    }
}
