package com.existdb.modelos;

public class Alumno { 
    private String nombre;
    private String apellidos;
    private String fecha;
    private String nia;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Alumno(String nombre, String apellidos, String fecha, String nia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
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
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNia() {
        return nia;
    }
    public void setNia(String nia) {
        this.nia = nia;
    }


    

    public String toXml() {
        return "<alumno id = \"" + this.getId() + "\">" +
                "<nombre>" + this.getNombre() + "</nombre>" +
                "<apellido>" + this.getApellidos() + "</apellido>" +
                "<fecha>" + this.getFecha() + "</fecha>" +
                "<nia>" + this.getNia() + "</nia>" +
                "</alumno>";
    }

    @Override
    public String toString() {
        return String.format("%s :: %s %s, %s ", nia, nombre, apellidos, fecha);
    }
}
